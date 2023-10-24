package com.isep.acme.services.review;

import com.isep.acme.model.Review;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "review.sort.type", havingValue = "byUpvotes")
public class SortByUpVotes implements IReviewSorting {

    private int minVotes = 4;
    private double percentagem = 60; 

    @Override
    public List<Review> sortReviews(Optional<List<Review>> reviews) {
        
        List<Review> reviewList = reviews.get();
        
        //Filtra as reviews com mais de 4 votos (upVotes + downVotes) e mais de 60% de UpVotes
        List<Review> filteredReviews = reviewList.stream()
        .filter(review -> (review.getUpVote().size() + review.getDownVote().size()) > minVotes)
        .filter(review -> (double) review.getUpVote().size() / (review.getUpVote().size() + review.getDownVote().size()) > (percentagem / 100))
        .collect(Collectors.toList());

        //Ordena as reviews pelo número de upVotes em ordem decrescente
        filteredReviews.sort(Comparator.comparingInt(review -> review.getUpVote().size()));
        Collections.reverse(filteredReviews);

        return filteredReviews;
    }
}
