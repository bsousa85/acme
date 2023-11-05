package com.isep.acme.repositories.rating;

import com.isep.acme.model.rating.BaseRating;
import com.isep.acme.model.rating.RatingMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("mongo")
public class IRatingRepositoryMongoImpl implements IRatingRepository {

    @Autowired
    private IRatingMongoDBDriver mongoDBDriver;

    @Override
    public Optional<BaseRating> findByRate(Double rate) {
        return mongoDBDriver.findByRate(rate).map(RatingMongo::toBaseRating);
    }


}
