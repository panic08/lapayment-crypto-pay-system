/*
 * This file is generated by jOOQ.
 */
package com.example.jooq.model.tables;


import com.example.jooq.model.Keys;
import com.example.jooq.model.Public;
import com.example.jooq.model.tables.records.PaymentsRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function11;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row11;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Payments extends TableImpl<PaymentsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.payments</code>
     */
    public static final Payments PAYMENTS = new Payments();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PaymentsRecord> getRecordType() {
        return PaymentsRecord.class;
    }

    /**
     * The column <code>public.payments.id</code>.
     */
    public final TableField<PaymentsRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.payments.merchantid</code>.
     */
    public final TableField<PaymentsRecord, String> MERCHANTID = createField(DSL.name("merchantid"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.payments.oauth</code>.
     */
    public final TableField<PaymentsRecord, String> OAUTH = createField(DSL.name("oauth"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.payments.amount</code>.
     */
    public final TableField<PaymentsRecord, Double> AMOUNT = createField(DSL.name("amount"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>public.payments.tron_amount</code>.
     */
    public final TableField<PaymentsRecord, Double> TRON_AMOUNT = createField(DSL.name("tron_amount"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>public.payments.bitcoin_amount</code>.
     */
    public final TableField<PaymentsRecord, Double> BITCOIN_AMOUNT = createField(DSL.name("bitcoin_amount"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>public.payments.ethereum_amount</code>.
     */
    public final TableField<PaymentsRecord, Double> ETHEREUM_AMOUNT = createField(DSL.name("ethereum_amount"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>public.payments.matic_amount</code>.
     */
    public final TableField<PaymentsRecord, Double> MATIC_AMOUNT = createField(DSL.name("matic_amount"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>public.payments.currency</code>.
     */
    public final TableField<PaymentsRecord, String> CURRENCY = createField(DSL.name("currency"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.payments.status</code>.
     */
    public final TableField<PaymentsRecord, String> STATUS = createField(DSL.name("status"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.payments.timestamp</code>.
     */
    public final TableField<PaymentsRecord, Long> TIMESTAMP = createField(DSL.name("timestamp"), SQLDataType.BIGINT.nullable(false), this, "");

    private Payments(Name alias, Table<PaymentsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Payments(Name alias, Table<PaymentsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.payments</code> table reference
     */
    public Payments(String alias) {
        this(DSL.name(alias), PAYMENTS);
    }

    /**
     * Create an aliased <code>public.payments</code> table reference
     */
    public Payments(Name alias) {
        this(alias, PAYMENTS);
    }

    /**
     * Create a <code>public.payments</code> table reference
     */
    public Payments() {
        this(DSL.name("payments"), null);
    }

    public <O extends Record> Payments(Table<O> child, ForeignKey<O, PaymentsRecord> key) {
        super(child, key, PAYMENTS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<PaymentsRecord, Integer> getIdentity() {
        return (Identity<PaymentsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<PaymentsRecord> getPrimaryKey() {
        return Keys.PAYMENTS_PKEY;
    }

    @Override
    public Payments as(String alias) {
        return new Payments(DSL.name(alias), this);
    }

    @Override
    public Payments as(Name alias) {
        return new Payments(alias, this);
    }

    @Override
    public Payments as(Table<?> alias) {
        return new Payments(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Payments rename(String name) {
        return new Payments(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Payments rename(Name name) {
        return new Payments(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Payments rename(Table<?> name) {
        return new Payments(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row11 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row11<Integer, String, String, Double, Double, Double, Double, Double, String, String, Long> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function11<? super Integer, ? super String, ? super String, ? super Double, ? super Double, ? super Double, ? super Double, ? super Double, ? super String, ? super String, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function11<? super Integer, ? super String, ? super String, ? super Double, ? super Double, ? super Double, ? super Double, ? super Double, ? super String, ? super String, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
