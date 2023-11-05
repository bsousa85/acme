package com.isep.acme.repositories.aggregatedrating;

import com.isep.acme.model.aggregatedrating.AggregatedRatingNeo4J;
import com.isep.acme.model.aggregatedrating.BaseAggregatedRating;
import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.product.ProductNeo4J;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("neo4j")
public class IAggregatedRatingRepositoryNeo4JImpl implements IAggregatedRatingRepository {

    @Autowired
    private IAggregatedRatingNeo4JDBDriver neo4JDriver;

    @Override
    public Optional<BaseAggregatedRating> findByProductId(BaseProduct product) {
        final var neo4JProduct = new ProductNeo4J(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return neo4JDriver.findByProduct(neo4JProduct).map(AggregatedRatingNeo4J::toBaseAggregatedRating);
    }

    @Override
    public BaseAggregatedRating save(BaseAggregatedRating aggregatedRating) {
        final var product = aggregatedRating.getProduct();
        final var neo4JProduct = new ProductNeo4J(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        final var neo4JAggregatedRating = new AggregatedRatingNeo4J(aggregatedRating.getAverage(), neo4JProduct);
        return neo4JDriver.save(neo4JAggregatedRating).toBaseAggregatedRating();
    }
}
