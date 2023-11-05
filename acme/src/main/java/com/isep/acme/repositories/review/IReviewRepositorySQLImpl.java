package com.isep.acme.repositories.review;

import com.isep.acme.model.User;
import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.product.ProductSQL;
import com.isep.acme.model.rating.RatingSQL;
import com.isep.acme.model.review.BaseReview;
import com.isep.acme.model.review.ReviewSQL;
import com.isep.acme.model.user.UserMongo;
import com.isep.acme.model.user.UserSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("sql")
public class IReviewRepositorySQLImpl implements IReviewRepository {

    @Autowired
    private IReviewSQLDBDriver sqlDriver;

    @Override
    public Optional<BaseReview> findById(Long reviewID) {
        return sqlDriver.findById(reviewID).map(ReviewSQL::toBaseReview);
    }

    @Override
    public List<BaseReview> findByProductId(BaseProduct product) {
        final var sqlProduct = new ProductSQL(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return sqlDriver.findByProductId(sqlProduct).stream().map(ReviewSQL::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findPendingReviews(String approvalStatus) {
        return sqlDriver.findPendingReviews(approvalStatus).stream().map(ReviewSQL::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findActiveReviews(String approvalStatus) {
        return sqlDriver.findActiveReviews(approvalStatus).stream().map(ReviewSQL::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findByProductIdStatus(BaseProduct product, String status) {
        final var sqlProduct = new ProductSQL(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return sqlDriver.findByProductIdStatus(sqlProduct, status).stream().map(ReviewSQL::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public List<BaseReview> findByUserId(User user) {
        return sqlDriver.findByUserId(user).stream().map(ReviewSQL::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public BaseReview save(BaseReview review) {
        final var product = review.getProduct();
        final var rating = review.getRating();
        final var user = review.getUser();
        final var sqlProduct = new ProductSQL(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        final var sqlRating = new RatingSQL(rating.getIdRating(), rating.getVersion(), rating.getRate());
        final var sqlUser = new UserSQL(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        final var sqlReview = new ReviewSQL(review.getIdReview(), review.getVersion(), review.getApprovalStatus(), review.getReviewText(), review.getUpVote(), review.getDownVote(), review.getReport(), review.getPublishingDate(), review.getFunFact(), sqlProduct, sqlRating, sqlUser);
        return sqlDriver.save(sqlReview).toBaseReview();
    }

    @Override
    public List<BaseReview> findAll() {
        final var allReviews = sqlDriver.findAll();

        List<ReviewSQL> reviewsList = new ArrayList<>();
        allReviews.forEach(reviewsList::add);

        return reviewsList.stream().map(ReviewSQL::toBaseReview).collect(Collectors.toList());
    }

    @Override
    public void delete(BaseReview review) {
        final var product = review.getProduct();
        final var rating = review.getRating();
        final var user = review.getUser();
        final var sqlProduct = new ProductSQL(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        final var sqlRating = new RatingSQL(rating.getIdRating(), rating.getVersion(), rating.getRate());
        final var sqlUser = new UserSQL(user.getUsername(), user.getPassword(), user.getFullName(), user.getNif(), user.getMorada());
        final var sqlReview = new ReviewSQL(review.getIdReview(), review.getVersion(), review.getApprovalStatus(), review.getReviewText(), review.getUpVote(), review.getDownVote(), review.getReport(), review.getPublishingDate(), review.getFunFact(), sqlProduct, sqlRating, sqlUser);
        sqlDriver.delete(sqlReview);
    }
}
