package com.isep.acme.repositories.user;

import com.isep.acme.model.user.BaseUser;
import com.isep.acme.model.user.UserSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("sql")
public class IUserRepositorySQLImpl implements IUserRepository {

    @Autowired
    private IUserSQLDBDriver sqlDriver;

    @Override
    public BaseUser save(BaseUser user) {
        final var sqlUser = new UserSQL(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        return sqlDriver.save(sqlUser).toBaseUser();
    }

    @Override
    public Optional<BaseUser> findById(Long userId) {
        return sqlDriver.findById(userId).map(UserSQL::toBaseUser);
    }

    @Override
    public Optional<BaseUser> findByUsername(String username) {
        return sqlDriver.findByUsername(username).map(UserSQL::toBaseUser);
    }
}
