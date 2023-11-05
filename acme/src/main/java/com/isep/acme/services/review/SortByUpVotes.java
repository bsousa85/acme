package com.isep.acme.services.review;

import com.isep.acme.model.review.BaseReview;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "review.sort.type", havingValue = "byUpvotes")
public class SortByUpVotes implements IReviewSorting {

    @Value("${review.sort.minimum_munber.votes}")
    private int minVotes;

    @Value("${review.sort.minimum_percentage.upVotes}")
    private double percentagem; 

    @Override
    public List<BaseReview> sortReviews(List<BaseReview> reviews, Long userID) {

        //Filtra as reviews com pelo menos 4 votos (upVotes + downVotes) e mais de 60% de UpVotes
        List<BaseReview> filteredReviews = reviews.stream()
        .filter(review -> (review.getUpVote().size() + review.getDownVote().size()) > minVotes)
        .filter(review -> (double) review.getUpVote().size() / (review.getUpVote().size() + review.getDownVote().size()) > (percentagem / 100))
        .collect(Collectors.toList());

        //Ordena as reviews pelo número de upVotes em ordem decrescente
        filteredReviews.sort(Comparator.comparingInt(review -> review.getUpVote().size()));
        Collections.reverse(filteredReviews);

        return filteredReviews;
    }
}
