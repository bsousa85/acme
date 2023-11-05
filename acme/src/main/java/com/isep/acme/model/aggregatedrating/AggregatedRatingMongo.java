package com.isep.acme.model.aggregatedrating;

import com.isep.acme.model.product.ProductMongo;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("aggregatedRating")
public class AggregatedRatingMongo {

    @Transient
    public static final String AGGREGATED_RATING_SEQUENCE = "aggregateRatingSequence";

    @Id
    private Long aggregatedId;

    private double average;

    @DBRef
    private ProductMongo product;

    protected AggregatedRatingMongo() {}

    public AggregatedRatingMongo(Long aggregatedId, double average, ProductMongo product) {
        this.aggregatedId = aggregatedId;
        this.average = average;
        this.product = product;
    }

    public AggregatedRatingMongo(double average, ProductMongo product) {
        this.average = average;
        this.product = product;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public ProductMongo getProduct() {
        return product;
    }

    public void setProduct(ProductMongo product) {
        this.product = product;
    }

    public Long getAggregatedId() {
        return aggregatedId;
    }

    public BaseAggregatedRating toBaseAggregatedRating() {
        return new BaseAggregatedRating(this.average, this.getProduct().toBaseProduct());
    }
}
