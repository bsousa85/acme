package com.isep.acme.model.aggregatedrating;

import com.isep.acme.model.product.BaseProduct;

public class BaseAggregatedRating {

    private Long aggregatedId;

    private double average;

    private BaseProduct product;

    public BaseAggregatedRating() {}

    public BaseAggregatedRating(double average, BaseProduct product) {
        this.average = average;
        this.product = product;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public BaseProduct getProduct() {
        return product;
    }

    public void setProduct(BaseProduct product) {
        this.product = product;
    }

    public Long getAggregatedId() {
        return aggregatedId;
    }
}
