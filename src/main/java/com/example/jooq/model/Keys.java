/*
 * This file is generated by jOOQ.
 */
package com.example.jooq.model;


import com.example.jooq.model.tables.Conclusions;
import com.example.jooq.model.tables.FlywaySchemaHistory;
import com.example.jooq.model.tables.Payments;
import com.example.jooq.model.tables.Userfactory;
import com.example.jooq.model.tables.Users;
import com.example.jooq.model.tables.records.ConclusionsRecord;
import com.example.jooq.model.tables.records.FlywaySchemaHistoryRecord;
import com.example.jooq.model.tables.records.PaymentsRecord;
import com.example.jooq.model.tables.records.UserfactoryRecord;
import com.example.jooq.model.tables.records.UsersRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ConclusionsRecord> CONCLUSIONS_PKEY = Internal.createUniqueKey(Conclusions.CONCLUSIONS, DSL.name("conclusions_pkey"), new TableField[] { Conclusions.CONCLUSIONS.ID }, true);
    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("flyway_schema_history_pk"), new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
    public static final UniqueKey<PaymentsRecord> PAYMENTS_PKEY = Internal.createUniqueKey(Payments.PAYMENTS, DSL.name("payments_pkey"), new TableField[] { Payments.PAYMENTS.ID }, true);
    public static final UniqueKey<UserfactoryRecord> USERFACTORY_PKEY = Internal.createUniqueKey(Userfactory.USERFACTORY, DSL.name("userfactory_pkey"), new TableField[] { Userfactory.USERFACTORY.ID }, true);
    public static final UniqueKey<UsersRecord> USERS_PKEY = Internal.createUniqueKey(Users.USERS, DSL.name("users_pkey"), new TableField[] { Users.USERS.ID }, true);
}
