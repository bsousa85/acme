package com.isep.acme.repositories.review;

import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.review.BaseReview;
import com.isep.acme.model.User;

import java.util.List;
import java.util.Optional;

public interface IReviewRepository {

    Optional<BaseReview> findById(Long reviewID);

    List<BaseReview> findByProductId(BaseProduct product);

    List<BaseReview> findPendingReviews(String approvalStatus);

    List<BaseReview> findActiveReviews(String approvalStatus);

    List<BaseReview> findByProductIdStatus(BaseProduct product, String status);

    List<BaseReview> findByUserId(User user);

    BaseReview save(BaseReview review);

    List<BaseReview> findAll();

    void delete(BaseReview review);
}
