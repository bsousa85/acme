package com.isep.acme.repositories.review;

import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.product.ProductMongo;
import com.isep.acme.model.User;
import com.isep.acme.model.rating.RatingMongo;
import com.isep.acme.model.review.BaseReview;
import com.isep.acme.model.review.ReviewMongo;
import com.isep.acme.model.user.UserMongo;
import com.isep.acme.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("mongo")
public class IReviewRepositoryMongoImpl implements IReviewRepository {

    @Autowired
    private IReviewMongoDBDriver mongoDBDriver;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Optional<BaseReview> findById(Long reviewID) {
        return mongoDBDriver.findById(reviewID).map(ReviewMongo::toBaseReview);
    }

    @Override
    public List<BaseReview> findByProductId(BaseProduct product) {
        final var mongoProduct = new ProductMongo(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return mongoDBDriver.findByProductId(mongoProduct).stream().map(ReviewMongo::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findPendingReviews(String approvalStatus) {
        return mongoDBDriver.findPendingReviews(approvalStatus).stream().map(ReviewMongo::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findActiveReviews(String approvalStatus) {
        return mongoDBDriver.findActiveReviews(approvalStatus).stream().map(ReviewMongo::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findByProductIdStatus(BaseProduct product, String status) {
        final var mongoProduct = new ProductMongo(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return mongoDBDriver.findByProductIdStatus(mongoProduct, status).stream().map(ReviewMongo::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findByUserId(User user) {
        return mongoDBDriver.findByUserId(user).stream().map(ReviewMongo::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public BaseReview save(BaseReview review) {
        final var product = review.getProduct();
        final var rating = review.getRating();
        final var user = review.getUser();
        final var mongoProduct = new ProductMongo(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        final var mongoRating = new RatingMongo(rating.getIdRating(), rating.getVersion(), rating.getRate());
        final var mongoUser = new UserMongo(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        final var mongoReview = new ReviewMongo(sequenceGeneratorService.generateSequence(ReviewMongo.REVIEW_SEQUENCE), review.getVersion(), review.getApprovalStatus(), review.getReviewText(), review.getUpVote(), review.getDownVote(), review.getReport(), review.getPublishingDate(), review.getFunFact(), mongoProduct, mongoRating, mongoUser);
        return mongoDBDriver.save(mongoReview).toBaseReview();
    }

    @Override
    public List<BaseReview> findAll() {
        return mongoDBDriver.findAll().stream().map(ReviewMongo::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public void delete(BaseReview review) {
        final var product = review.getProduct();
        final var rating = review.getRating();
        final var user = review.getUser();
        final var mongoProduct = new ProductMongo(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        final var mongoRating = new RatingMongo(rating.getIdRating(), rating.getVersion(), rating.getRate());
        final var mongoUser = new UserMongo(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        final var mongoReview = new ReviewMongo(review.getIdReview(), review.getVersion(), review.getApprovalStatus(), review.getReviewText(), review.getUpVote(), review.getDownVote(), review.getReport(), review.getPublishingDate(), review.getFunFact(), mongoProduct, mongoRating, mongoUser);
        mongoDBDriver.delete(mongoReview);
    }
}
