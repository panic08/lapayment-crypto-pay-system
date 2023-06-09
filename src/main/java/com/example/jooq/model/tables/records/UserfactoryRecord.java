/*
 * This file is generated by jOOQ.
 */
package com.example.jooq.model.tables.records;


import com.example.jooq.model.tables.Userfactory;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserfactoryRecord extends UpdatableRecordImpl<UserfactoryRecord> implements Record6<Integer, String, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.userfactory.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.userfactory.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.userfactory.merchantid</code>.
     */
    public void setMerchantid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.userfactory.merchantid</code>.
     */
    public String getMerchantid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.userfactory.principal</code>.
     */
    public void setPrincipal(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.userfactory.principal</code>.
     */
    public String getPrincipal() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.userfactory.apikey</code>.
     */
    public void setApikey(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.userfactory.apikey</code>.
     */
    public String getApikey() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.userfactory.urlback</code>.
     */
    public void setUrlback(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.userfactory.urlback</code>.
     */
    public String getUrlback() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.userfactory.requestmethod</code>.
     */
    public void setRequestmethod(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.userfactory.requestmethod</code>.
     */
    public String getRequestmethod() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, String, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, String, String, String, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Userfactory.USERFACTORY.ID;
    }

    @Override
    public Field<String> field2() {
        return Userfactory.USERFACTORY.MERCHANTID;
    }

    @Override
    public Field<String> field3() {
        return Userfactory.USERFACTORY.PRINCIPAL;
    }

    @Override
    public Field<String> field4() {
        return Userfactory.USERFACTORY.APIKEY;
    }

    @Override
    public Field<String> field5() {
        return Userfactory.USERFACTORY.URLBACK;
    }

    @Override
    public Field<String> field6() {
        return Userfactory.USERFACTORY.REQUESTMETHOD;
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
        return getPrincipal();
    }

    @Override
    public String component4() {
        return getApikey();
    }

    @Override
    public String component5() {
        return getUrlback();
    }

    @Override
    public String component6() {
        return getRequestmethod();
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
        return getPrincipal();
    }

    @Override
    public String value4() {
        return getApikey();
    }

    @Override
    public String value5() {
        return getUrlback();
    }

    @Override
    public String value6() {
        return getRequestmethod();
    }

    @Override
    public UserfactoryRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public UserfactoryRecord value2(String value) {
        setMerchantid(value);
        return this;
    }

    @Override
    public UserfactoryRecord value3(String value) {
        setPrincipal(value);
        return this;
    }

    @Override
    public UserfactoryRecord value4(String value) {
        setApikey(value);
        return this;
    }

    @Override
    public UserfactoryRecord value5(String value) {
        setUrlback(value);
        return this;
    }

    @Override
    public UserfactoryRecord value6(String value) {
        setRequestmethod(value);
        return this;
    }

    @Override
    public UserfactoryRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserfactoryRecord
     */
    public UserfactoryRecord() {
        super(Userfactory.USERFACTORY);
    }

    /**
     * Create a detached, initialised UserfactoryRecord
     */
    public UserfactoryRecord(Integer id, String merchantid, String principal, String apikey, String urlback, String requestmethod) {
        super(Userfactory.USERFACTORY);

        setId(id);
        setMerchantid(merchantid);
        setPrincipal(principal);
        setApikey(apikey);
        setUrlback(urlback);
        setRequestmethod(requestmethod);
        resetChangedOnNotNull();
    }
}
