package ru.panic.lapayment.template.repository;

import org.springframework.stereotype.Repository;
import ru.panic.lapayment.template.entity.UserFactory;

@Repository
public interface UserFactoryRepository {
    public UserFactory findByMerchantId(String merchantId);
}
