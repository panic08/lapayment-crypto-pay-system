package ru.panic.lapayment.template.service.impl;

import org.springframework.stereotype.Service;
import ru.panic.lapayment.template.dto.factory.ConclusionRequestDto;
import ru.panic.lapayment.template.entity.Conclusion;
import ru.panic.lapayment.template.entity.User;
import ru.panic.lapayment.template.entity.enums.Status;
import ru.panic.lapayment.template.exception.InsufficientFundsException;
import ru.panic.lapayment.template.repository.impl.ConclusionRepositoryImpl;
import ru.panic.lapayment.template.repository.impl.UserRepositoryImpl;
import ru.panic.lapayment.template.service.ConclusionService;

@Service
public class ConclusionServiceImpl implements ConclusionService {
    public ConclusionServiceImpl(ConclusionRepositoryImpl conclusionRepository, UserRepositoryImpl userRepository) {
        this.conclusionRepository = conclusionRepository;
        this.userRepository = userRepository;
    }

    ConclusionRepositoryImpl conclusionRepository;
    UserRepositoryImpl userRepository;
    @Override
    public Conclusion createConclusion(ConclusionRequestDto request) {
        User user = userRepository.findByUsername(request.getPrincipal());
        switch (request.getCurrency()){
            case BTC -> {
                if (user.getBitcoin_balance() >= request.getAmount()){
                    user.setBitcoin_balance(user.getBitcoin_balance() - request.getAmount());
                }else{
                    throw new InsufficientFundsException("Недостаточно средств для вывода");
                }
            }
            case TRX -> {
                if (user.getTron_balance() >= request.getAmount()){
                    user.setTron_balance(user.getTron_balance() - request.getAmount());
                }else{
                    throw new InsufficientFundsException("Недостаточно средств для вывода");
                }
            }
            case ETH -> {
                if (user.getEthereum_balance() >= request.getAmount()){
                    user.setEthereum_balance(user.getEthereum_balance() - request.getAmount());
                }else{
                    throw new InsufficientFundsException("Недостаточно средств для вывода");
                }
            }
            case MATIC -> {
                if (user.getMatic_balance() >= request.getAmount()){
                    user.setMatic_balance(user.getMatic_balance() - request.getAmount());
                }else{
                    throw new InsufficientFundsException("Недостаточно средств для вывода");
                }
            }
        }
        userRepository.update(user);
        Conclusion conclusion = new Conclusion();
        conclusion.setPrincipal(request.getPrincipal());
        conclusion.setCurrency(request.getCurrency());
        conclusion.setWallet(request.getWallet());
        conclusion.setAmount(request.getAmount());
        conclusion.setStatus(Status.PROCEED);
        conclusionRepository.save(conclusion);
        return conclusion;
    }

    @Override
    public Conclusion deleteConclusion(Conclusion request) {
        User user = userRepository.findByUsername(request.getPrincipal());
        switch (request.getCurrency()){
            case BTC -> {
               user.setBitcoin_balance(user.getBitcoin_balance() + request.getAmount());
            }
            case TRX -> {
                user.setTron_balance(user.getTron_balance() + request.getAmount());
            }
            case ETH -> {
                user.setEthereum_balance(user.getEthereum_balance() + request.getAmount());
            }
            case MATIC -> {
                user.setMatic_balance(user.getMatic_balance() + request.getAmount());
            }
        }
        conclusionRepository.delete(request);
        userRepository.update(user);
        return request;
    }

}
