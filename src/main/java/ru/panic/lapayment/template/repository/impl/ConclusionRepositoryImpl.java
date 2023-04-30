package ru.panic.lapayment.template.repository.impl;

import com.example.jooq.model.tables.Conclusions;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import ru.panic.lapayment.template.entity.Conclusion;
import ru.panic.lapayment.template.repository.ConclusionRepository;

import java.util.List;

@Service
public class ConclusionRepositoryImpl implements ConclusionRepository {
    public ConclusionRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    DSLContext dslContext;

    @Override
    public void save(Conclusion conclusion) {
        dslContext.insertInto(Conclusions.CONCLUSIONS)
                .set(Conclusions.CONCLUSIONS.AMOUNT, conclusion.getAmount())
                .set(Conclusions.CONCLUSIONS.PRINCIPAL, conclusion.getPrincipal())
                .set(Conclusions.CONCLUSIONS.WALLET, conclusion.getWallet())
                .set(Conclusions.CONCLUSIONS.CRYPTOCURRENCY, conclusion.getCurrency().toString())
                .set(Conclusions.CONCLUSIONS.STATUS, conclusion.getStatus().toString())
                .execute();
    }

    @Override
    public void delete(Conclusion conclusion) {
        dslContext.deleteFrom(Conclusions.CONCLUSIONS)
                .where(Conclusions.CONCLUSIONS.ID.eq(conclusion.getId().intValue()))
                .execute();
    }

    @Override
    public List<Conclusion> findAll() {
        return dslContext.selectFrom(Conclusions.CONCLUSIONS).fetchInto(Conclusion.class);
    }

    @Override
    public List<Conclusion> findAllByPrincipal(String principal) {
        return dslContext.selectFrom(Conclusions.CONCLUSIONS)
                .where(Conclusions.CONCLUSIONS.PRINCIPAL.eq(principal))
                .fetchInto(Conclusion.class);
    }
}