package com.isep.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.services.review.SortByUpVotes;

public class SortByUpVotesTest {
   
    @Test
    public void testSortReviewsByUpVotes() {
    // Criação de avaliações para teste
        List<Review> reviews = new ArrayList<>();
        Review review1 = createReviewWithUpVotes(5, 2); // 5 upVotes, 2 downVotes
        Review review2 = createReviewWithUpVotes(3, 1); // 3 upVotes, 1 downVote
        Review review3 = createReviewWithUpVotes(10, 5); // 10 upVotes, 5 downVotes
        Review review4 = createReviewWithUpVotes(1, 1); // 1 upVote, 1 downVote (menos de 4 votos totais)

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);

        SortByUpVotes sortByUpVotes = new SortByUpVotes();

        // Chama o método de classificação
        List<Review> sortedReviews = sortByUpVotes.sortReviews(reviews, 123L); // Supondo um ID de usuário arbitrário

        // Verifica se as avaliações estão ordenadas corretamente e se as avaliações com menos de 4 votos foram filtradas
        assertEquals(review3, sortedReviews.get(0)); // review3 com mais upVotes deve vir primeiro
        assertEquals(review1, sortedReviews.get(1)); // review1 em segundo
        assertNotEquals(review2, sortedReviews); // Verifica se a review2 com menos de 4 votos não está presente na lista ordenada
        assertNotEquals(review4, sortedReviews); // Verifica se a outra review4 com menos de 4 votos não está presente na lista ordenada
    }

    // Método de apoio para criar uma Review com um número específico de upVotes e downVotes
    private Review createReviewWithUpVotes(int upVotes, int downVotes) {
        Review review = Mockito.mock(Review.class);
        List<Vote> upVoteList = new ArrayList<>();
        List<Vote> downVoteList = new ArrayList<>();

        // Criar upVotes
        for (int i = 0; i < upVotes; i++) {
            Vote upVote = new Vote("up", 1L); // ID de usuário arbitrário
            upVoteList.add(upVote);
        }

        // Criar downVotes
        for (int i = 0; i < downVotes; i++) {
            Vote downVote = new Vote("down", 2L); // ID de usuário arbitrário
            downVoteList.add(downVote);
        }

        Mockito.when(review.getUpVote()).thenReturn(upVoteList);
        Mockito.when(review.getDownVote()).thenReturn(downVoteList);
        return review;
    }

}
