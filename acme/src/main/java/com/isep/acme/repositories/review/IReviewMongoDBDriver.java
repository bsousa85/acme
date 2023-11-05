package com.isep.acme.repositories.review;

import com.isep.acme.model.product.ProductMongo;
import com.isep.acme.model.User;
import com.isep.acme.model.review.ReviewMongo;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("mongo")
public interface IReviewMongoDBDriver extends MongoRepository<ReviewMongo, Long> {

    @Query("{rate: ?0}")
    List<ReviewMongo> findByProductId(ProductMongo product);

    @Query("{approvalStatus: ?0}")
    List<ReviewMongo> findPendingReviews(String approvalStatus);

    @Query("{approvalStatus: ?0}")
    List<ReviewMongo> findActiveReviews(String approvalStatus);

    @Query("{product: ?0, status: ?1}")
    List<ReviewMongo> findByProductIdStatus(ProductMongo product, String status);

    @Query("{user: ?0}")
    List<ReviewMongo> findByUserId(User user);

    List<ReviewMongo> findAll();
}
