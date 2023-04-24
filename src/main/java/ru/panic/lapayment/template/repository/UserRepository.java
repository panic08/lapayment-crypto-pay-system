package ru.panic.lapayment.template.repository;

import org.springframework.stereotype.Repository;
import ru.panic.lapayment.template.entity.User;

@Repository
public interface UserRepository {
     User findByUsername(String username);
     void save(User user);
}
