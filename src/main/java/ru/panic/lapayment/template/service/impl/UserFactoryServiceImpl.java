package ru.panic.lapayment.template.service.impl;

import org.springframework.stereotype.Service;
import ru.panic.lapayment.template.entity.User;
import ru.panic.lapayment.template.entity.UserFactory;
import ru.panic.lapayment.template.entity.enums.CryptoCurrency;
import ru.panic.lapayment.template.exception.UserFactoryFoundedException;
import ru.panic.lapayment.template.repository.impl.UserFactoryRepositoryImpl;
import ru.panic.lapayment.template.repository.impl.UserRepositoryImpl;
import ru.panic.lapayment.template.service.UserFactoryService;
import ru.panic.lapayment.util.ApikeyGenerator;

@Service
public class UserFactoryServiceImpl implements UserFactoryService {
    public UserFactoryServiceImpl(UserFactoryRepositoryImpl userFactoryRepository, UserRepositoryImpl userRepository) {
        this.userFactoryRepository = userFactoryRepository;
        this.userRepository = userRepository;
    }

    UserFactoryRepositoryImpl userFactoryRepository;
    UserRepositoryImpl userRepository;
    @Override
    public void assignCryptoToUser(CryptoCurrency currency, String merchantId, Double amount) {
        User user = userRepository.findByUsername(userFactoryRepository.findByMerchantId(merchantId).getPrincipal());
        switch (currency){
            case TRX -> {
                user.setTron_balance(user.getTron_balance() + amount);
                userRepository.save(user);
            }
            case BTC -> {
                user.setBitcoin_balance(user.getBitcoin_balance() + amount);
                userRepository.save(user);
            }
            case ETH -> {
                user.setEthereum_balance(user.getEthereum_balance() + amount);
                userRepository.save(user);
            }
            case MATIC -> {
                user.setMatic_balance(user.getMatic_balance() + amount);
                userRepository.save(user);
            }
        }

    }

    @Override
    public UserFactory createApikey(UserFactory userFactory) {
        UserFactory userFactory1 = userFactoryRepository.findByMerchantId(userFactory.getMerchantId());
        if (userFactory1 != null) {
            throw new UserFactoryFoundedException("Такое название приложения уже существует, придумайте другое");
        }
        userFactory.setApikey(ApikeyGenerator.generate());
        userFactoryRepository.save(userFactory);
        return userFactory;
    }


}
