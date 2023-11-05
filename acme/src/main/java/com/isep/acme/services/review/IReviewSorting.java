package com.isep.acme.services.review;

import com.isep.acme.model.review.BaseReview;

import java.util.List;

public interface IReviewSorting {

    List<BaseReview> sortReviews(List<BaseReview> reviews, Long userID);
}
