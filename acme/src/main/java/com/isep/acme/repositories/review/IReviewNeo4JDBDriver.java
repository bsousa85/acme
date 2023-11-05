package com.isep.acme.repositories.review;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Component;

import com.isep.acme.model.User;
import com.isep.acme.model.product.ProductNeo4J;
import com.isep.acme.model.review.ReviewNeo4J;

@Component
@Profile("neo4j")
public interface IReviewNeo4JDBDriver extends Neo4jRepository<ReviewNeo4J, Long> {

    @Query("MATCH (p:ProductNeo4J {id: $productId})<-[:REVIEWS_PRODUCT]-(r:ReviewNeo4J) RETURN r ORDER BY r.publishingDate DESC")
    List<ReviewNeo4J> findByProductId(ProductNeo4J product);

    @Query("MATCH (r:ReviewNeo4J {approvalStatus: 'pending'}) RETURN r")
    List<ReviewNeo4J> findPendingReviews(String approvalStatus);

    @Query("MATCH (r:ReviewNeo4J {approvalStatus: 'active'}) RETURN r")
    List<ReviewNeo4J> findActiveReviews(String approvalStatus);

    @Query("MATCH (p:ProductNeo4J {id: $productId})<-[:REVIEWS_PRODUCT]-(r:ReviewNeo4J {approvalStatus: $status}) RETURN r ORDER BY r.publishingDate DESC")
    List<ReviewNeo4J> findByProductIdStatus(ProductNeo4J product, String status);

    @Query("MATCH (u:UserNeo4J {id: $userId})-[:WRITTEN_BY]->(r:ReviewNeo4J) RETURN r ORDER BY r.publishingDate DESC")
    List<ReviewNeo4J> findByUserId(User user);
}
