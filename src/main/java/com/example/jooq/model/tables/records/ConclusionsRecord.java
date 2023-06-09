/*
 * This file is generated by jOOQ.
 */
package com.example.jooq.model.tables.records;


import com.example.jooq.model.tables.Conclusions;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ConclusionsRecord extends UpdatableRecordImpl<ConclusionsRecord> implements Record7<Integer, String, Double, String, String, String, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.conclusions.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.conclusions.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.conclusions.principal</code>.
     */
    public void setPrincipal(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.conclusions.principal</code>.
     */
    public String getPrincipal() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.conclusions.amount</code>.
     */
    public void setAmount(Double value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.conclusions.amount</code>.
     */
    public Double getAmount() {
        return (Double) get(2);
    }

    /**
     * Setter for <code>public.conclusions.wallet</code>.
     */
    public void setWallet(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.conclusions.wallet</code>.
     */
    public String getWallet() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.conclusions.cryptocurrency</code>.
     */
    public void setCryptocurrency(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.conclusions.cryptocurrency</code>.
     */
    public String getCryptocurrency() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.conclusions.status</code>.
     */
    public void setStatus(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.conclusions.status</code>.
     */
    public String getStatus() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.conclusions.timestamp</code>.
     */
    public void setTimestamp(Long value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.conclusions.timestamp</code>.
     */
    public Long getTimestamp() {
        return (Long) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, String, Double, String, String, String, Long> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Integer, String, Double, String, String, String, Long> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Conclusions.CONCLUSIONS.ID;
    }

    @Override
    public Field<String> field2() {
        return Conclusions.CONCLUSIONS.PRINCIPAL;
    }

    @Override
    public Field<Double> field3() {
        return Conclusions.CONCLUSIONS.AMOUNT;
    }

    @Override
    public Field<String> field4() {
        return Conclusions.CONCLUSIONS.WALLET;
    }

    @Override
    public Field<String> field5() {
        return Conclusions.CONCLUSIONS.CRYPTOCURRENCY;
    }

    @Override
    public Field<String> field6() {
        return Conclusions.CONCLUSIONS.STATUS;
    }

    @Override
    public Field<Long> field7() {
        return Conclusions.CONCLUSIONS.TIMESTAMP;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getPrincipal();
    }

    @Override
    public Double component3() {
        return getAmount();
    }

    @Override
    public String component4() {
        return getWallet();
    }

    @Override
    public String component5() {
        return getCryptocurrency();
    }

    @Override
    public String component6() {
        return getStatus();
    }

    @Override
    public Long component7() {
        return getTimestamp();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getPrincipal();
    }

    @Override
    public Double value3() {
        return getAmount();
    }

    @Override
    public String value4() {
        return getWallet();
    }

    @Override
    public String value5() {
        return getCryptocurrency();
    }

    @Override
    public String value6() {
        return getStatus();
    }

    @Override
    public Long value7() {
        return getTimestamp();
    }

    @Override
    public ConclusionsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ConclusionsRecord value2(String value) {
        setPrincipal(value);
        return this;
    }

    @Override
    public ConclusionsRecord value3(Double value) {
        setAmount(value);
        return this;
    }

    @Override
    public ConclusionsRecord value4(String value) {
        setWallet(value);
        return this;
    }

    @Override
    public ConclusionsRecord value5(String value) {
        setCryptocurrency(value);
        return this;
    }

    @Override
    public ConclusionsRecord value6(String value) {
        setStatus(value);
        return this;
    }

    @Override
    public ConclusionsRecord value7(Long value) {
        setTimestamp(value);
        return this;
    }

    @Override
    public ConclusionsRecord values(Integer value1, String value2, Double value3, String value4, String value5, String value6, Long value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ConclusionsRecord
     */
    public ConclusionsRecord() {
        super(Conclusions.CONCLUSIONS);
    }

    /**
     * Create a detached, initialised ConclusionsRecord
     */
    public ConclusionsRecord(Integer id, String principal, Double amount, String wallet, String cryptocurrency, String status, Long timestamp) {
        super(Conclusions.CONCLUSIONS);

        setId(id);
        setPrincipal(principal);
        setAmount(amount);
        setWallet(wallet);
        setCryptocurrency(cryptocurrency);
        setStatus(status);
        setTimestamp(timestamp);
        resetChangedOnNotNull();
    }
}
