package com.isep.acme.model.aggregatedrating;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.isep.acme.model.product.ProductNeo4J;

@Node
public class AggregatedRatingNeo4J {
    
    @Id
    @GeneratedValue
    private Long aggregatedId;

    private double average;

    @Relationship(type = "HAS_PRODUCT")
    private ProductNeo4J product;

    protected AggregatedRatingNeo4J() {}

    public AggregatedRatingNeo4J(double average, ProductNeo4J product) {
        this.average = average;
        this.product = product;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public ProductNeo4J getProduct() {
        return product;
    }

    public void setProduct(ProductNeo4J product) {
        this.product = product;
    }

    public Long getAggregatedId() {
        return aggregatedId;
    }

    public BaseAggregatedRating toBaseAggregatedRating() {
        return new BaseAggregatedRating(this.average, this.getProduct().toBaseProduct());
    }
}
