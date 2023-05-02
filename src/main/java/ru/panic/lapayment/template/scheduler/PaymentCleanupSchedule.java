package ru.panic.lapayment.template.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.panic.lapayment.template.entity.Payment;
import ru.panic.lapayment.template.entity.enums.Status;
import ru.panic.lapayment.template.repository.impl.PaymentRepositoryImpl;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
@Slf4j
public class PaymentCleanupSchedule {
    public PaymentCleanupSchedule(PaymentRepositoryImpl paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    PaymentRepositoryImpl paymentRepository;

    @Scheduled(fixedRate = 1800000) // 30 минут = 30 * 60 * 1000 миллисекунд
    public void cleanupNotCompletedPayments() {
        log.info("Starting payments-cleanuping");
        List<Payment> payments = paymentRepository.findAllByStatus(Status.NOT_COMPLETED);
        Instant currentTime = Instant.now();
        for (Payment payment : payments) {
            Instant paymentTime = Instant.ofEpochMilli(payment.getTimestamp());
            Duration duration = Duration.between(currentTime, paymentTime);
            if (duration.toMinutes() >= 20) {
                log.info("Deleting addled payment with paymentId: {}", payment.getPaymentId());
                paymentRepository.delete(payment);
            }
        }
        log.info("Close scheduler");
    }
}
