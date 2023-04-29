package ru.panic.lapayment.template.repository;

import org.springframework.stereotype.Repository;
import ru.panic.lapayment.template.entity.UserFactory;

import java.util.List;

@Repository
public interface UserFactoryRepository {
     UserFactory findByMerchantId(String merchantId);
     List<UserFactory> findByPrincipal(String principal);
     void save(UserFactory userFactory);
     void delete(UserFactory userFactory);
}
