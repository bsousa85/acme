package com.isep.acme.model;

import com.isep.acme.model.review.BaseReview;

import java.util.ArrayList;
import java.util.List;

public class ReviewMapper {

    public static ReviewDTO toDto(BaseReview review){
        return new ReviewDTO(review.getIdReview(), review.getReviewText(), review.getPublishingDate(), review.getApprovalStatus(), review.getFunFact(), review.getRating().getRate(), review.getUpVote().size());
    }

    public static List<ReviewDTO> toDtoList(List<BaseReview> review) {
        List<ReviewDTO> dtoList = new ArrayList<>();

        for (BaseReview rev: review) {
            dtoList.add(toDto(rev));
        }
        return dtoList;
    }
}
