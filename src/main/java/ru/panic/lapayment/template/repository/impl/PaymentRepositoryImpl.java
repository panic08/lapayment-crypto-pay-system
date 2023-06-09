package ru.panic.lapayment.template.repository.impl;

import com.example.jooq.model.tables.Payments;
import com.example.jooq.model.tables.records.PaymentsRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import ru.panic.lapayment.template.entity.Payment;
import ru.panic.lapayment.template.entity.enums.Status;
import ru.panic.lapayment.template.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentRepositoryImpl implements PaymentRepository {
    public PaymentRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    DSLContext dslContext;
    @Override
    public void save(Payment payment) {
        dslContext.insertInto(Payments.PAYMENTS)
                .set(Payments.PAYMENTS.MERCHANTID, payment.getMerchantId())
                .set(Payments.PAYMENTS.OAUTH, payment.getOauth())
                .set(Payments.PAYMENTS.AMOUNT, (Double) payment.getAmount())
                .set(Payments.PAYMENTS.TRON_AMOUNT, payment.getTron_amount())
                .set(Payments.PAYMENTS.BITCOIN_AMOUNT, payment.getBitcoin_amount())
                .set(Payments.PAYMENTS.ETHEREUM_AMOUNT, payment.getEthereum_amount())
                .set(Payments.PAYMENTS.MATIC_AMOUNT, payment.getMatic_amount())
                .set(Payments.PAYMENTS.CURRENCY, String.valueOf(payment.getCurrency()))
                .set(Payments.PAYMENTS.STATUS, String.valueOf(payment.getStatus()))
                .set(Payments.PAYMENTS.TIMESTAMP, payment.getTimestamp())
                .execute();

    }

    @Override
    public void delete(Payment payment) {
        dslContext.deleteFrom(Payments.PAYMENTS)
                .where(Payments.PAYMENTS.ID.eq(payment.getPaymentId().intValue()))
                .execute();
    }

    @Override
    public Payment findPaymentByPaymentId(Integer paymentId) {
        PaymentsRecord paymentsRecord = dslContext
                .selectFrom(Payments.PAYMENTS)
                .where(Payments.PAYMENTS.ID.eq(paymentId))
                .fetchOne();
        assert paymentsRecord != null;
        return paymentsRecord.into(Payment.class);
    }

    @Override
    public List<Payment> findAllByStatus(Status status) {
        return dslContext.selectFrom(Payments.PAYMENTS)
                .where(Payments.PAYMENTS.STATUS.eq(status.toString()))
                .fetchInto(Payment.class);
    }

    @Override
    public Long findLastId() {
                Long request = dslContext.select(DSL.max(Payments.PAYMENTS.ID))
                .from(Payments.PAYMENTS)
                .fetchOneInto(Long.class);
                return request != null ? request : 0;
    }

    @Override
    public void updateStatusByPaymentId(Integer paymentId, Status status) {
        dslContext
                .update(Payments.PAYMENTS)
                .set(Payments.PAYMENTS.STATUS, String.valueOf(status))
                .where(Payments.PAYMENTS.ID.eq(paymentId))
                .execute();
    }
}
