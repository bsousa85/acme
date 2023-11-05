package com.isep.acme.services;

import com.isep.acme.model.aggregatedrating.BaseAggregatedRating;

public interface AggregatedRatingService {

    BaseAggregatedRating save(String sku);
}
