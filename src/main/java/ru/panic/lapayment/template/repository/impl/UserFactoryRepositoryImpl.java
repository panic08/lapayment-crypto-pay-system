package ru.panic.lapayment.template.repository.impl;

import com.example.jooq.model.tables.Userfactory;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import ru.panic.lapayment.template.entity.UserFactory;
import ru.panic.lapayment.template.repository.UserFactoryRepository;

import java.util.List;

@Service
public class UserFactoryRepositoryImpl implements UserFactoryRepository {
    public UserFactoryRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    DSLContext dslContext;
    @Override
    public UserFactory findByMerchantId(String merchantId) {
        UserFactory userFactory = dslContext.selectFrom(Userfactory.USERFACTORY)
                .where(Userfactory.USERFACTORY.MERCHANTID.eq(merchantId))
                .fetchOneInto(UserFactory.class);
        return userFactory != null ? userFactory : null;
    }

    @Override
    public List<UserFactory> findByPrincipal(String principal) {
        List<UserFactory> userFactory = dslContext.selectFrom(Userfactory.USERFACTORY)
                .where(Userfactory.USERFACTORY.PRINCIPAL.eq(principal))
                .fetchInto(UserFactory.class);

        return userFactory != null ? userFactory : null;
    }

    @Override
    public void save(UserFactory userFactory) {
        dslContext.insertInto(Userfactory.USERFACTORY)
                .set(Userfactory.USERFACTORY.MERCHANTID, userFactory.getMerchantId())
                .set(Userfactory.USERFACTORY.APIKEY, userFactory.getApikey())
                .set(Userfactory.USERFACTORY.PRINCIPAL, userFactory.getPrincipal())
                .set(Userfactory.USERFACTORY.REQUESTMETHOD, userFactory.getRequestMethod())
                .set(Userfactory.USERFACTORY.URLBACK, userFactory.getUrlBack())
                .execute();
    }

    @Override
    public void delete(UserFactory userFactory) {
        dslContext.deleteFrom(Userfactory.USERFACTORY)
                .where(Userfactory.USERFACTORY.MERCHANTID.eq(userFactory.getMerchantId()))
                .and(Userfactory.USERFACTORY.ID.eq(userFactory.getId().intValue()))
                .and(Userfactory.USERFACTORY.APIKEY.eq(userFactory.getApikey()))
                .and(Userfactory.USERFACTORY.URLBACK.eq(userFactory.getUrlBack()))
                .and(Userfactory.USERFACTORY.REQUESTMETHOD.eq(userFactory.getRequestMethod()))
                .execute();
    }
}
