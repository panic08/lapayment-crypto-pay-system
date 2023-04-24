package ru.panic.lapayment.template.repository;

import org.springframework.stereotype.Repository;
import ru.panic.lapayment.template.entity.Payment;

@Repository
public interface PaymentRepository {
    void save(Payment payment);
}
