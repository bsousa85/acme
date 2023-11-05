package com.isep.acme.model.rating;

import java.util.Objects;

import javax.annotation.Generated;
import javax.persistence.Version;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("rating")
public class RatingNeo4J {

    @Id
    @GeneratedValue
    private Long idRating;

    @Version
    private Long version;

    private Double rate;

    protected RatingNeo4J(){}

    public RatingNeo4J(Long version, Double rate) {
        this.version = Objects.requireNonNull(version);
        setRate(rate);
    }

    public RatingNeo4J(Double rate) {
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
