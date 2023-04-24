package ru.panic.lapayment.template.repository.impl;

import com.example.jooq.model.tables.Payments;
import com.example.jooq.model.tables.records.PaymentsRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import ru.panic.lapayment.template.entity.Payment;
import ru.panic.lapayment.template.entity.enums.Status;
import ru.panic.lapayment.template.repository.PaymentRepository;

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
                .set(Payments.PAYMENTS.AMOUNT, (Double) payment.getAmount())
                .set(Payments.PAYMENTS.TRON_AMOUNT, payment.getTron_amount())
                .set(Payments.PAYMENTS.BITCOIN_AMOUNT, payment.getBitcoin_amount())
                .set(Payments.PAYMENTS.ETHEREUM_AMOUNT, payment.getEthereum_amount())
                .set(Payments.PAYMENTS.RIPPLE_AMOUNT, payment.getRipple_amount())
                .set(Payments.PAYMENTS.CURRENCY, String.valueOf(payment.getCurrency()))
                .set(Payments.PAYMENTS.STATUS, String.valueOf(payment.getStatus()))
                .set(Payments.PAYMENTS.BLOCKTIME, String.valueOf(payment.getBlockTime()))
                .execute();

    }

    @Override
    public Payment findPaymentByPaymentId(Integer paymentId) {
        PaymentsRecord paymentsRecord = dslContext
                .selectFrom(Payments.PAYMENTS)
                .where(Payments.PAYMENTS.ID.eq(paymentId))
                .fetchOne();
        return paymentsRecord.into(Payment.class);
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
