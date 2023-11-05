package com.isep.acme.repositories.review;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.User;
import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.product.ProductNeo4J;
import com.isep.acme.model.rating.RatingNeo4J;
import com.isep.acme.model.review.BaseReview;
import com.isep.acme.model.review.ReviewNeo4J;
import com.isep.acme.model.user.UserNeo4J;

@Component
@Profile("neo4j")
public class ReviewRepositoryNeo4JImpl implements IReviewRepository {

    @Autowired
    private IReviewNeo4JDBDriver neo4JDBDriver;

    @Override
    public Optional<BaseReview> findById(Long reviewID) {
        return neo4JDBDriver.findById(reviewID).map(ReviewNeo4J::toBaseReview);
    }

    @Override
    public List<BaseReview> findByProductId(BaseProduct product) {
        final var neo4JProduct = new ProductNeo4J(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return neo4JDBDriver.findByProductId(neo4JProduct).stream().map(ReviewNeo4J::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findPendingReviews(String approvalStatus) {
        return neo4JDBDriver.findPendingReviews(approvalStatus).stream().map(ReviewNeo4J::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findActiveReviews(String approvalStatus) {
        return neo4JDBDriver.findActiveReviews(approvalStatus).stream().map(ReviewNeo4J::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findByProductIdStatus(BaseProduct product, String status) {
        final var neo4JProduct = new ProductNeo4J(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return neo4JDBDriver.findByProductIdStatus(neo4JProduct, status).stream().map(ReviewNeo4J::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findByUserId(User user) {
        return neo4JDBDriver.findByUserId(user).stream().map(ReviewNeo4J::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public BaseReview save(BaseReview review) {
        final var product = review.getProduct();
        final var rating = review.getRating();
        final var user = review.getUser();
        final var neo4JProduct = new ProductNeo4J(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        final var neo4JRating = new RatingNeo4J(rating.getIdRating(), rating.getVersion(), rating.getRate());
        final var neo4JUser = new UserNeo4J(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        final var neo4JReview = new ReviewNeo4J(review.getIdReview(), review.getVersion(), review.getApprovalStatus(), review.getReviewText(), review.getUpVote(), review.getDownVote(), review.getReport(), review.getPublishingDate(), review.getFunFact(), neo4JProduct, neo4JRating, neo4JUser);
        return neo4JDBDriver.save(neo4JReview).toBaseReview();
    }

    @Override
    public List<BaseReview> findAll() {
       return neo4JDBDriver.findAll().stream().map(ReviewNeo4J::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public void delete(BaseReview review) {
        final var product = review.getProduct();
        final var rating = review.getRating();
        final var user = review.getUser();
        final var neo4JProduct = new ProductNeo4J(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        final var neo4JRating = new RatingNeo4J(rating.getIdRating(), rating.getVersion(), rating.getRate());
        final var neo4JUser = new UserNeo4J(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        final var neo4JReview = new ReviewNeo4J(review.getIdReview(), review.getVersion(), review.getApprovalStatus(), review.getReviewText(), review.getUpVote(), review.getDownVote(), review.getReport(), review.getPublishingDate(), review.getFunFact(), neo4JProduct, neo4JRating, neo4JUser);
        neo4JDBDriver.delete(neo4JReview);
    }
}

