package ru.panic.lapayment.template.repository;

import org.springframework.stereotype.Repository;
import ru.panic.lapayment.template.entity.Conclusion;
import ru.panic.lapayment.template.entity.enums.Status;

import java.util.List;

@Repository
public interface ConclusionRepository {
    void save(Conclusion conclusion);
    void delete(Conclusion conclusion);
    void updateStatus(Conclusion conclusion, Status status);
    List<Conclusion> findAll();
    List<Conclusion> findAllByPrincipal(String principal);
}
