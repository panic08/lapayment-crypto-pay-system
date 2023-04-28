/*
 * This file is generated by jOOQ.
 */
package com.example.jooq.model.tables.records;


import com.example.jooq.model.tables.Payments;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PaymentsRecord extends UpdatableRecordImpl<PaymentsRecord> implements Record11<Integer, String, String, Double, Double, Double, Double, Double, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.payments.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.payments.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.payments.merchantid</code>.
     */
    public void setMerchantid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.payments.merchantid</code>.
     */
    public String getMerchantid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.payments.oauth</code>.
     */
    public void setOauth(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.payments.oauth</code>.
     */
    public String getOauth() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.payments.amount</code>.
     */
    public void setAmount(Double value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.payments.amount</code>.
     */
    public Double getAmount() {
        return (Double) get(3);
    }

    /**
     * Setter for <code>public.payments.tron_amount</code>.
     */
    public void setTronAmount(Double value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.payments.tron_amount</code>.
     */
    public Double getTronAmount() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>public.payments.bitcoin_amount</code>.
     */
    public void setBitcoinAmount(Double value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.payments.bitcoin_amount</code>.
     */
    public Double getBitcoinAmount() {
        return (Double) get(5);
    }

    /**
     * Setter for <code>public.payments.ethereum_amount</code>.
     */
    public void setEthereumAmount(Double value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.payments.ethereum_amount</code>.
     */
    public Double getEthereumAmount() {
        return (Double) get(6);
    }

    /**
     * Setter for <code>public.payments.matic_amount</code>.
     */
    public void setMaticAmount(Double value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.payments.matic_amount</code>.
     */
    public Double getMaticAmount() {
        return (Double) get(7);
    }

    /**
     * Setter for <code>public.payments.currency</code>.
     */
    public void setCurrency(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.payments.currency</code>.
     */
    public String getCurrency() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.payments.status</code>.
     */
    public void setStatus(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.payments.status</code>.
     */
    public String getStatus() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public.payments.time</code>.
     */
    public void setTime(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.payments.time</code>.
     */
    public String getTime() {
        return (String) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row11<Integer, String, String, Double, Double, Double, Double, Double, String, String, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<Integer, String, String, Double, Double, Double, Double, Double, String, String, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Payments.PAYMENTS.ID;
    }

    @Override
    public Field<String> field2() {
        return Payments.PAYMENTS.MERCHANTID;
    }

    @Override
    public Field<String> field3() {
        return Payments.PAYMENTS.OAUTH;
    }

    @Override
    public Field<Double> field4() {
        return Payments.PAYMENTS.AMOUNT;
    }

    @Override
    public Field<Double> field5() {
        return Payments.PAYMENTS.TRON_AMOUNT;
    }

    @Override
    public Field<Double> field6() {
        return Payments.PAYMENTS.BITCOIN_AMOUNT;
    }

    @Override
    public Field<Double> field7() {
        return Payments.PAYMENTS.ETHEREUM_AMOUNT;
    }

    @Override
    public Field<Double> field8() {
        return Payments.PAYMENTS.MATIC_AMOUNT;
    }

    @Override
    public Field<String> field9() {
        return Payments.PAYMENTS.CURRENCY;
    }

    @Override
    public Field<String> field10() {
        return Payments.PAYMENTS.STATUS;
    }

    @Override
    public Field<String> field11() {
        return Payments.PAYMENTS.TIME;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getMerchantid();
    }

    @Override
    public String component3() {
        return getOauth();
    }

    @Override
    public Double component4() {
        return getAmount();
    }

    @Override
    public Double component5() {
        return getTronAmount();
    }

    @Override
    public Double component6() {
        return getBitcoinAmount();
    }

    @Override
    public Double component7() {
        return getEthereumAmount();
    }

    @Override
    public Double component8() {
        return getMaticAmount();
    }

    @Override
    public String component9() {
        return getCurrency();
    }

    @Override
    public String component10() {
        return getStatus();
    }

    @Override
    public String component11() {
        return getTime();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getMerchantid();
    }

    @Override
    public String value3() {
        return getOauth();
    }

    @Override
    public Double value4() {
        return getAmount();
    }

    @Override
    public Double value5() {
        return getTronAmount();
    }

    @Override
    public Double value6() {
        return getBitcoinAmount();
    }

    @Override
    public Double value7() {
        return getEthereumAmount();
    }

    @Override
    public Double value8() {
        return getMaticAmount();
    }

    @Override
    public String value9() {
        return getCurrency();
    }

    @Override
    public String value10() {
        return getStatus();
    }

    @Override
    public String value11() {
        return getTime();
    }

    @Override
    public PaymentsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public PaymentsRecord value2(String value) {
        setMerchantid(value);
        return this;
    }

    @Override
    public PaymentsRecord value3(String value) {
        setOauth(value);
        return this;
    }

    @Override
    public PaymentsRecord value4(Double value) {
        setAmount(value);
        return this;
    }

    @Override
    public PaymentsRecord value5(Double value) {
        setTronAmount(value);
        return this;
    }

    @Override
    public PaymentsRecord value6(Double value) {
        setBitcoinAmount(value);
        return this;
    }

    @Override
    public PaymentsRecord value7(Double value) {
        setEthereumAmount(value);
        return this;
    }

    @Override
    public PaymentsRecord value8(Double value) {
        setMaticAmount(value);
        return this;
    }

    @Override
    public PaymentsRecord value9(String value) {
        setCurrency(value);
        return this;
    }

    @Override
    public PaymentsRecord value10(String value) {
        setStatus(value);
        return this;
    }

    @Override
    public PaymentsRecord value11(String value) {
        setTime(value);
        return this;
    }

    @Override
    public PaymentsRecord values(Integer value1, String value2, String value3, Double value4, Double value5, Double value6, Double value7, Double value8, String value9, String value10, String value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PaymentsRecord
     */
    public PaymentsRecord() {
        super(Payments.PAYMENTS);
    }

    /**
     * Create a detached, initialised PaymentsRecord
     */
    public PaymentsRecord(Integer id, String merchantid, String oauth, Double amount, Double tronAmount, Double bitcoinAmount, Double ethereumAmount, Double maticAmount, String currency, String status, String time) {
        super(Payments.PAYMENTS);

        setId(id);
        setMerchantid(merchantid);
        setOauth(oauth);
        setAmount(amount);
        setTronAmount(tronAmount);
        setBitcoinAmount(bitcoinAmount);
        setEthereumAmount(ethereumAmount);
        setMaticAmount(maticAmount);
        setCurrency(currency);
        setStatus(status);
        setTime(time);
        resetChangedOnNotNull();
    }
}
