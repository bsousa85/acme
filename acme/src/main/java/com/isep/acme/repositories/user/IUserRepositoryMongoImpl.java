package com.isep.acme.repositories.user;

import com.isep.acme.model.user.BaseUser;
import com.isep.acme.model.user.UserMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("mongo")
public class IUserRepositoryMongoImpl implements IUserRepository {

    @Autowired
    private IUserMongoDBDriver mongoDBDriver;

    @Override
    public BaseUser save(BaseUser user) {
        final var mongoUser = new UserMongo(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        return mongoDBDriver.save(mongoUser).toBaseUser();
    }

    @Override
    public Optional<BaseUser> findById(Long userId) {
        return mongoDBDriver.findById(userId).map(UserMongo::toBaseUser);
    }

    @Override
    public BaseUser findByUsername(String username) {
        return mongoDBDriver.findByUsername(username).toBaseUser();
    }
}
