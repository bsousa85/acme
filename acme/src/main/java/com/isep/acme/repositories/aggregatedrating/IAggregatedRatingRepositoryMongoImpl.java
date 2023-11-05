package com.isep.acme.repositories.aggregatedrating;

import com.isep.acme.model.aggregatedrating.AggregatedRatingMongo;
import com.isep.acme.model.aggregatedrating.BaseAggregatedRating;
import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.product.ProductMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("mongo")
public class IAggregatedRatingRepositoryMongoImpl implements IAggregatedRatingRepository {

    @Autowired
    private IAggregatedRatingMongoDBDriver mongoDBDriver;

    @Override
    public Optional<BaseAggregatedRating> findByProductId(BaseProduct product) {
        final var mongoProduct = new ProductMongo(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return mongoDBDriver.findByProductId(mongoProduct).map(AggregatedRatingMongo::toBaseAggregatedRating);
    }

    @Override
    public BaseAggregatedRating save(BaseAggregatedRating aggregatedRating) {
        final var product = aggregatedRating.getProduct();
        final var mongoProduct = new ProductMongo(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        final var mongoAggregatedRating = new AggregatedRatingMongo(aggregatedRating.getAverage(), mongoProduct);
        return mongoDBDriver.save(mongoAggregatedRating).toBaseAggregatedRating();
    }
}
