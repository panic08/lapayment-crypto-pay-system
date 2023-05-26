package ru.panic.lapayment.template.repository;

import org.springframework.stereotype.Repository;
import ru.panic.lapayment.template.entity.UserFactory;

import java.util.List;


public interface UserFactoryRepository {
     UserFactory findByMerchantId(String merchantId);
     UserFactory findByApikey(String apikey);
     List<UserFactory> findAllByPrincipal(String principal);
     void save(UserFactory userFactory);
     void delete(UserFactory userFactory);
}
