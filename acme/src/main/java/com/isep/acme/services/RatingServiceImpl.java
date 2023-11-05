package com.isep.acme.services;

import com.isep.acme.model.rating.BaseRating;
import com.isep.acme.repositories.rating.IRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    IRatingRepository repository;

    public Optional<BaseRating> findByRate(Double rate){
        return repository.findByRate(rate);
    }

}
