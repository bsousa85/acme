package com.isep.acme.repositories.rating;

import com.isep.acme.model.rating.BaseRating;
import com.isep.acme.model.rating.RatingMongo;
import com.isep.acme.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("mongo")
public class IRatingRepositoryMongoImpl implements IRatingRepository {

    @Autowired
    private IRatingMongoDBDriver mongoDBDriver;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Optional<BaseRating> findByRate(Double rate) {
        return mongoDBDriver.findByRate(rate).map(RatingMongo::toBaseRating);
    }

    @Override
    public BaseRating save(BaseRating rating) {
        final var mongoRating = new RatingMongo(sequenceGeneratorService.generateSequence(RatingMongo.RATING_SEQUENCE), rating.getVersion(), rating.getRate());
        return mongoDBDriver.save(mongoRating).toBaseRating();
    }
}
