package com.isep.acme.repositories.aggregatedrating;

import com.isep.acme.model.aggregatedrating.AggregatedRatingNeo4J;
import com.isep.acme.model.product.ProductNeo4J;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("neo4j")
public interface IAggregatedRatingNeo4JDBDriver extends Neo4jRepository<AggregatedRatingNeo4J, Long> {

    @Query("MATCH (a:AggregatedRatingNeo4J)-[:HAS_PRODUCT]->(p:ProductNeo4J) WHERE p = $product RETURN a")
    Optional<AggregatedRatingNeo4J> findByProduct(@Param("product") ProductNeo4J product);
}
