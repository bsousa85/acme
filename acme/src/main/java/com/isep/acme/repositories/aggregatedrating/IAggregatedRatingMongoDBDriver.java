package com.isep.acme.repositories.aggregatedrating;

import com.isep.acme.model.aggregatedrating.AggregatedRatingMongo;
import com.isep.acme.model.product.ProductMongo;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("mongo")
public interface IAggregatedRatingMongoDBDriver extends MongoRepository<AggregatedRatingMongo, Long> {

    @Query("{product: ?0}")
    Optional<AggregatedRatingMongo> findByProductId(ProductMongo product);
}
