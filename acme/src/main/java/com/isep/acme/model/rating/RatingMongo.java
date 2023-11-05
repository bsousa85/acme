package com.isep.acme.model.rating;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Objects;

@Document("rating")
public class RatingMongo {

    @Id
    private Long idRating;

    @Version
    private long version;

    private Double rate;

    protected RatingMongo(){}

    public RatingMongo(Long idRating, long version, Double rate) {
        this.idRating = Objects.requireNonNull(idRating);
        this.version = Objects.requireNonNull(version);
        setRate(rate);
    }

    public RatingMongo(Double rate) {
        setRate(rate);
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public BaseRating toBaseRating() {
        return new BaseRating(this.idRating, this.version, this.rate);
    }
}
