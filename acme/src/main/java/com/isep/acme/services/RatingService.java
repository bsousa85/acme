package com.isep.acme.services;

import java.util.Optional;

import com.isep.acme.model.rating.BaseRating;

public interface RatingService {

    Optional<BaseRating> findByRate(Double rate);
}
