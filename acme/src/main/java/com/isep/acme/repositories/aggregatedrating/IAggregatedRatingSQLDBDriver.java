package com.isep.acme.repositories.aggregatedrating;

import com.isep.acme.model.aggregatedrating.AggregatedRatingSQL;
import com.isep.acme.model.product.ProductSQL;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("sql")
public interface IAggregatedRatingSQLDBDriver extends CrudRepository<AggregatedRatingSQL, Long> {

    @Query("SELECT a FROM AggregatedRating a WHERE a.product=:product")
    Optional<AggregatedRatingSQL> findByProductId(ProductSQL product);
}
