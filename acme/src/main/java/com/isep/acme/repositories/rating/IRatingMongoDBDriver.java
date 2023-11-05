package com.isep.acme.repositories.rating;

import com.isep.acme.model.rating.RatingMongo;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("mongo")
public interface IRatingMongoDBDriver extends MongoRepository<RatingMongo, Long> {

    @Query("{rate: ?0}")
    Optional<RatingMongo> findByRate(Double rate);

}
