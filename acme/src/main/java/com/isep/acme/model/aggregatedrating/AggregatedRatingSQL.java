package com.isep.acme.model.aggregatedrating;


import com.isep.acme.model.product.ProductSQL;

import javax.persistence.*;

@Entity
public class AggregatedRatingSQL {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aggregatedId;

    @Column()
    private double average;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private ProductSQL product;

    protected AggregatedRatingSQL() {}

    public AggregatedRatingSQL(double average, ProductSQL product) {
        this.average = average;
        this.product = product;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public ProductSQL getProduct() {
        return product;
    }

    public void setProduct(ProductSQL product) {
        this.product = product;
    }

    public Long getAggregatedId() {
        return aggregatedId;
    }

    public BaseAggregatedRating toBaseAggregatedRating() {
        return new BaseAggregatedRating(this.average, this.getProduct().toBaseProduct());
    }
}
