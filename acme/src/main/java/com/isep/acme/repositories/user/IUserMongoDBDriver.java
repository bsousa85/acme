package com.isep.acme.repositories.user;

import com.isep.acme.model.user.UserMongo;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("mongo")
public interface IUserMongoDBDriver extends MongoRepository<UserMongo, Long> {

    @Query("{username: ?0}")
    Optional<UserMongo> findByUsername(String username);
}
