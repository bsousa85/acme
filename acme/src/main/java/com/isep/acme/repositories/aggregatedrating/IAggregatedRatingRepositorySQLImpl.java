package com.isep.acme.repositories.aggregatedrating;

import com.isep.acme.model.aggregatedrating.AggregatedRatingSQL;
import com.isep.acme.model.aggregatedrating.BaseAggregatedRating;
import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.product.ProductSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("sql")
public class IAggregatedRatingRepositorySQLImpl implements IAggregatedRatingRepository {

    @Autowired
    private IAggregatedRatingSQLDBDriver sqlDriver;

    @Override
    public Optional<BaseAggregatedRating> findByProductId(BaseProduct product) {
        final var sqlProduct = new ProductSQL(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return sqlDriver.findByProductId(sqlProduct).map(AggregatedRatingSQL::toBaseAggregatedRating);
    }

    @Override
    public BaseAggregatedRating save(BaseAggregatedRating aggregatedRating) {
        final var product = aggregatedRating.getProduct();
        final var sqlProduct = new ProductSQL(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        final var sqlAggregatedRating = new AggregatedRatingSQL(aggregatedRating.getAverage(), sqlProduct);
        return sqlDriver.save(sqlAggregatedRating).toBaseAggregatedRating();
    }
}
