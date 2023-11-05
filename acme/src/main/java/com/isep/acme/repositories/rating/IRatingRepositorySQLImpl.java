package com.isep.acme.repositories.rating;

import com.isep.acme.model.rating.BaseRating;
import com.isep.acme.model.rating.RatingSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("sql")
public class IRatingRepositorySQLImpl implements IRatingRepository {

    @Autowired
    private IRatingSQLDBDriver sqlDriver;

    @Override
    public Optional<BaseRating> findByRate(Double rate) {
        return sqlDriver.findByRate(rate).map(RatingSQL::toBaseRating);
    }
}
