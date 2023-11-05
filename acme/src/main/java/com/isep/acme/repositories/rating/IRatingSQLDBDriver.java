package com.isep.acme.repositories.rating;

import com.isep.acme.model.rating.RatingSQL;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("sql")
public interface IRatingSQLDBDriver extends CrudRepository<RatingSQL, Long> {

    @Query("SELECT r FROM Rating r WHERE r.rate=:rate")
    Optional<RatingSQL> findByRate(Double rate);
}
