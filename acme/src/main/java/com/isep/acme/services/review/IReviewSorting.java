package com.isep.acme.services.review;

import com.isep.acme.model.Review;

import java.util.List;
import java.util.Optional;

public interface IReviewSorting {

    List<Review> sortReviews(Optional<List<Review>> reviews);
}
