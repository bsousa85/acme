package com.isep.acme.repositories.rating;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.isep.acme.model.rating.RatingNeo4J;

@Component
@Profile("neo4j")
public interface IRatingNeo4JDBDRiver extends Neo4jRepository<RatingNeo4J, Long> {
    
    @Query("MATCH (r:RatingNeo4J) WHERE r.rate = $rate RETURN r")
    Optional<RatingNeo4J> findByRate(@Param("rate") Double rate);

}
