package com.isep.acme.bootstrapper;

import com.isep.acme.model.rating.BaseRating;
import com.isep.acme.repositories.rating.IRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Rating;
import com.isep.acme.repositories.RatingRepository;

@Component
public class RatingBootstrapper implements CommandLineRunner {

    @Autowired
    private IRatingRepository repository;

    @Override
    public void run(String... args) throws Exception {

        if(repository.findByRate(0.5).isEmpty()) {
            BaseRating rate05 = new BaseRating(0.5);
            repository.save(rate05);
        }

        if(repository.findByRate(1.0).isEmpty()) {
            BaseRating rate1 = new BaseRating(1.0);
            repository.save(rate1);
        }

        if(repository.findByRate(1.5).isEmpty()) {
            BaseRating rate15 = new BaseRating(1.5);
            repository.save(rate15);
        }

        if(repository.findByRate(2.0).isEmpty()) {
            BaseRating rate2 = new BaseRating(2.0);
            repository.save(rate2);
        }

        if(repository.findByRate(2.5).isEmpty()) {
            BaseRating rate25 = new BaseRating(2.5);
            repository.save(rate25);
        }

        if(repository.findByRate(3.0).isEmpty()) {
            BaseRating rate3 = new BaseRating(3.0);
            repository.save(rate3);
        }

        if(repository.findByRate(3.5).isEmpty()) {
            BaseRating rate35 = new BaseRating(3.5);
            repository.save(rate35);
        }

        if(repository.findByRate(4.0).isEmpty()) {
            BaseRating rate4 = new BaseRating(4.0);
            repository.save(rate4);
        }

        if(repository.findByRate(4.5).isEmpty()) {
            BaseRating rate45 = new BaseRating(4.5);
            repository.save(rate45);
        }

        if(repository.findByRate(5.0).isEmpty()) {
            BaseRating rate5 = new BaseRating(5.0);
            repository.save(rate5);
        }
    }
}
