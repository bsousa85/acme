package com.isep.acme.repositories.rating;

import com.isep.acme.model.rating.BaseRating;

import java.util.Optional;

public interface IRatingRepository {

    Optional<BaseRating> findByRate(Double rate);

    BaseRating save(BaseRating rating);

}
