package com.isep.acme.services.review;

import com.isep.acme.model.Review;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ConditionalOnProperty(name = "review.sort.type", havingValue = "byUserVotes")
public class SortByUserVotes implements IReviewSorting {
    @Override
    public List<Review> sortReviews(Optional<List<Review>> reviews) {
        System.out.println("SORTING REVIEWS BY USER VOTES!");
        return null;
    }
}
