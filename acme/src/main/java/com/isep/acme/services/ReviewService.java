package com.isep.acme.services;

import java.util.List;

import com.isep.acme.model.*;
import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.review.BaseReview;

public interface ReviewService {

    Iterable<BaseReview> getAll();

    List<ReviewDTO> getReviewsOfProduct(String sku, String status, Long userId);

    ReviewDTO create(CreateReviewDTO createReviewDTO, String sku);

    boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO);

    Double getWeightedAverage(BaseProduct product);

    Boolean DeleteReview(Long reviewId);

    List<ReviewDTO> findPendingReview();

    ReviewDTO moderateReview(Long reviewID, String approved);

    List<ReviewDTO> findReviewsByUser(Long userID);
}
