package com.isep.acme.repositories.aggregatedrating;

import com.isep.acme.model.aggregatedrating.BaseAggregatedRating;
import com.isep.acme.model.product.BaseProduct;

import java.util.Optional;

public interface IAggregatedRatingRepository {

    Optional<BaseAggregatedRating> findByProductId(BaseProduct product);

    BaseAggregatedRating save(BaseAggregatedRating aggregatedRating);

}
