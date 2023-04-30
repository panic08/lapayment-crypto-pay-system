package ru.panic.lapayment.template.repository;

import org.springframework.stereotype.Repository;
import ru.panic.lapayment.template.entity.Conclusion;

import java.util.List;

@Repository
public interface ConclusionRepository {
    void save(Conclusion conclusion);
    void delete(Conclusion conclusion);
    List<Conclusion> findAll();
    List<Conclusion> findAllByPrincipal(String principal);
}
