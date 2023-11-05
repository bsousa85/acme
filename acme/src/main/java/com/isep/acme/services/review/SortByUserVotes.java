package com.isep.acme.services.review;

import com.isep.acme.model.Vote;
import com.isep.acme.model.review.BaseReview;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "review.sort.type", havingValue = "byUserVotes")
public class SortByUserVotes implements IReviewSorting {
    @Override
    public List<BaseReview> sortReviews(List<BaseReview> reviews, Long userID) {

        if (reviews.isEmpty()) {
            return List.of();
        }

        final var userVotes = new HashMap<Long, Integer>();

        var myVotes = 0;

        /// checks which users vote same as I did
        for (BaseReview review : reviews) {

            final var userUpVoted = review.getUpVote().stream().anyMatch(c -> c.getUserID().equals(userID));

            List<Long> users = new ArrayList<>();

            // Check where I voted
            if (userUpVoted) {
                myVotes++;
                users = review.getUpVote().stream().map(Vote::getUserID).filter(userId -> !userId.equals(userID)).collect(Collectors.toList());
            } else {
                final var userDownVoted = review.getDownVote().stream().anyMatch(c -> c.getUserID().equals(userID));

                if (userDownVoted) {
                    myVotes++;
                    users = review.getDownVote().stream().map(Vote::getUserID).filter(userId -> !userId.equals(userID)).collect(Collectors.toList());
                }
            }

            if (users.isEmpty()) {
                continue;
            }

            // fill map with users that voted the same as I did
            fillUsersMap(users, userVotes);
        }

        // get users that voted the same as I did 50% of the times
        final var userList = fillUserList(myVotes, userVotes);

        return fillAndSortReviews(reviews, userList);
    }

    private List<Long> fillUserList(Integer myVotes, Map<Long, Integer> userVotes) {
        final var userList = new ArrayList<Long>();

        final var targetNumber = (myVotes * 50) / 100;

        for (var entry : userVotes.entrySet()) {
            if (entry.getValue() >= targetNumber) {
                userList.add(entry.getKey());
            }
        }

        return userList;
    }

    private List<BaseReview> fillAndSortReviews(List<BaseReview> reviews, List<Long> userList) {
        final var filteredReviews = new ArrayList<BaseReview>();

        for (BaseReview review : reviews) {
            if (review.getUpVote().stream().anyMatch(vote -> userList.contains(vote.getUserID())) || review.getDownVote().stream().anyMatch(vote -> userList.contains(vote.getUserID()))) {
                filteredReviews.add(review);
            }
        }

        // sort reviews by upVotes
        filteredReviews.sort(Comparator.comparingInt(review -> review.getUpVote().size()));
        Collections.reverse(filteredReviews);

        return filteredReviews;
    }

    private void fillUsersMap(List<Long> users, Map<Long, Integer> userVotes) {
        for (Long user : users) {
            if (!userVotes.containsKey(user)) {
                userVotes.put(user, 1);
            } else {
                userVotes.put(user, userVotes.get(user) + 1);
            }
        }
    }
}
