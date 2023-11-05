package com.isep.acme.repositories.rating;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.rating.BaseRating;
import com.isep.acme.model.rating.RatingMongo;
import com.isep.acme.model.rating.RatingNeo4J;
import com.isep.acme.services.SequenceGeneratorService;

@Component
@Profile("neo4j")
public class IRatingRepositoryNeo4JImpl implements IRatingRepository {

    @Autowired
    private IRatingNeo4JDBDriver neo4jdbDriver;

    @Override
    public Optional<BaseRating> findByRate(Double rate) {
        return neo4jdbDriver.findByRate(rate).map(RatingNeo4J::toBaseRating);
    }

    @Override
    public BaseRating save(BaseRating rating) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
