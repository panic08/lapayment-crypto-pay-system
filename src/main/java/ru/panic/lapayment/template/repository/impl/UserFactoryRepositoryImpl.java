package ru.panic.lapayment.template.repository.impl;

import com.example.jooq.model.tables.Userfactory;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import ru.panic.lapayment.template.entity.UserFactory;
import ru.panic.lapayment.template.repository.UserFactoryRepository;

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
}
