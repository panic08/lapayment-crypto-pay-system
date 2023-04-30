package ru.panic.lapayment.template.repository.impl;

import com.example.jooq.model.tables.Users;
import com.example.jooq.model.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import ru.panic.lapayment.template.entity.User;
import ru.panic.lapayment.template.repository.UserRepository;

import static com.example.jooq.model.Tables.USERS;
@Service
public class UserRepositoryImpl implements UserRepository {
    public UserRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    DSLContext dslContext;
    @Override
    public User findByUsername(String username) {

        UsersRecord userRecord = dslContext.selectFrom(Users.USERS).where(Users.USERS.USERNAME.eq(username)).fetchOne();
        return userRecord != null ? userRecord.into(User.class) : null;
    }

    @Override
    public void save(User user) {
        dslContext.insertInto(USERS)
                .set(USERS.USERNAME, user.getUsername())
                .set(USERS.PASSWORD, user.getPassword())
                .set(USERS.BITCOIN_BALANCE, user.getBitcoin_balance())
                .set(USERS.ETHEREUM_BALANCE, user.getEthereum_balance())
                .set(USERS.TRON_BALANCE, user.getTron_balance())
                .set(USERS.MATIC_BALANCE, user.getMatic_balance())
                .set(USERS.REGISTEREDAT, String.valueOf(user.getRegisteredAt()))
                .execute();
    }

    @Override
    public void update(User user) {
        dslContext.update(USERS)
                .set(USERS.ID, user.getId().intValue())
                .set(USERS.USERNAME, user.getUsername())
                .set(USERS.PASSWORD, user.getPassword())
                .set(USERS.BITCOIN_BALANCE, user.getBitcoin_balance())
                .set(USERS.ETHEREUM_BALANCE, user.getEthereum_balance())
                .set(USERS.TRON_BALANCE, user.getTron_balance())
                .set(USERS.MATIC_BALANCE, user.getMatic_balance())
                .set(USERS.REGISTEREDAT, String.valueOf(user.getRegisteredAt()))
                .execute();
    }
}
