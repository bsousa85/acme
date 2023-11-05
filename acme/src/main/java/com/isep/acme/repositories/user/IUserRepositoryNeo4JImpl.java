package com.isep.acme.repositories.user;

import com.isep.acme.model.user.BaseUser;
import com.isep.acme.model.user.UserNeo4J;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("neo4j")
public class IUserRepositoryNeo4JImpl implements IUserRepository {

    @Autowired
    private IUserNeo4JDBDriver neo4jdbDriver;

    @Override
    public BaseUser save(BaseUser user) {
        final var username = neo4jdbDriver.findByUsername(user.getUsername());
        final var nif = neo4jdbDriver.findByNif(user.getNif());
        
        if(username != null){
            throw new ExistingUsernameException("Username already exists!");
        }

        if(nif != null){
            throw new ExistingNifException("NIF already exists!");
        }

        final var neo4jUser = new UserNeo4J(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        return neo4jdbDriver.save(neo4jUser).toBaseUser();
    }

    @Override
    public Optional<BaseUser> findById(Long userID) {
        return neo4jdbDriver.findById(userID).map(UserNeo4J::toBaseUser);
    }

    @Override
    public Optional<BaseUser> findByUsername(String username) {
        return neo4jdbDriver.findByUsername(username).map(UserNeo4J::toBaseUser);
    }
}
