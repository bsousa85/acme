package com.isep.acme.services;

import com.isep.acme.controllers.ResourceNotFoundException;
import java.lang.IllegalArgumentException;

import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.rating.BaseRating;
import com.isep.acme.model.review.BaseReview;
import com.isep.acme.repositories.product.IProductRepository;
import com.isep.acme.repositories.review.IReviewRepository;
import com.isep.acme.services.review.IReviewSorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.model.*;

import com.isep.acme.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    IReviewRepository repository;

    @Autowired
    IProductRepository pRepository;

    @Autowired
    UserRepository uRepository;

    @Autowired
    UserService userService;

    @Autowired
    RatingService ratingService;

    @Autowired
    RestService restService;

    @Autowired
    IReviewSorting reviewSorting;

    @Override
    public Iterable<BaseReview> getAll() {
        return repository.findAll();
    }

    @Override
    public ReviewDTO create(final CreateReviewDTO createReviewDTO, String sku) {

        final Optional<BaseProduct> product = pRepository.findBySku(sku);

        if(product.isEmpty()) return null;

        final var user = userService.getUserId(createReviewDTO.getUserID());

        if(user.isEmpty()) return null;

        BaseRating rating = null;
        Optional<BaseRating> r = ratingService.findByRate(createReviewDTO.getRating());
        if(r.isPresent()) {
            rating = r.get();
        }

        LocalDate date = LocalDate.now();

        String funfact = restService.getFunFact(date);

        if (funfact == null) return null;

        BaseReview review = new BaseReview(createReviewDTO.getReviewText(), date, product.get(), funfact, rating, user.get());

        review = repository.save(review);

        if (review == null) return null;

        return ReviewMapper.toDto(review);
    }

    @Override
    public List<ReviewDTO> getReviewsOfProduct(String sku, String status, Long userID) {

        Optional<BaseProduct> product = pRepository.findBySku(sku);
        if( product.isEmpty() ) return null;

        List<BaseReview> r = repository.findByProductIdStatus(product.get(), status);

        if (r.isEmpty()) return null;
        
        final var sortedReviews = reviewSorting.sortReviews(r, userID);

        return ReviewMapper.toDtoList(sortedReviews);
    }

    @Override
    public boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO) {

        Optional<BaseReview> review = this.repository.findById(reviewID);

        if (review.isEmpty()) return false;

        Vote vote = new Vote(voteReviewDTO.getVote(), voteReviewDTO.getUserID());
        if (voteReviewDTO.getVote().equalsIgnoreCase("upVote")) {
            boolean added = review.get().addUpVote(vote);
            if (added) {
                BaseReview reviewUpdated = this.repository.save(review.get());
                return reviewUpdated != null;
            }
        } else if (voteReviewDTO.getVote().equalsIgnoreCase("downVote")) {
            boolean added = review.get().addDownVote(vote);
            if (added) {
                BaseReview reviewUpdated = this.repository.save(review.get());
                return reviewUpdated != null;
            }
        }
        return false;
    }

    @Override
    public Double getWeightedAverage(BaseProduct product){

        List<BaseReview> r = repository.findByProductId(product);

        if (r.isEmpty()) return 0.0;

        double sum = 0;

        for (BaseReview rev: r) {
            BaseRating rate = rev.getRating();

            if (rate != null){
                sum += rate.getRate();
            }
        }

        return sum/r.size();
    }

    @Override
    public Boolean DeleteReview(Long reviewId)  {

        Optional<BaseReview> rev = repository.findById(reviewId);

        if (rev.isEmpty()){
            return null;
        }
        BaseReview r = rev.get();

        if (r.getUpVote().isEmpty() && r.getDownVote().isEmpty()) {
            repository.delete(r);
            return true;
        }
        return false;
    }

    @Override
    public List<ReviewDTO> findPendingReview(){

        String PENDING_STATUS = "pending";
        List<BaseReview> r = repository.findPendingReviews(PENDING_STATUS);

        if(r.isEmpty()){
            return null;
        }

        return ReviewMapper.toDtoList(r);
    }

    @Override
    public ReviewDTO moderateReview(Long reviewID, String approved) throws ResourceNotFoundException, IllegalArgumentException {

        Optional<BaseReview> r = repository.findById(reviewID);

        if(r.isEmpty()){
            throw new ResourceNotFoundException("Review not found");
        }

        Boolean ap = r.get().setApprovalStatus(approved);

        if(!ap) {
            throw new IllegalArgumentException("Invalid status value");
        }

        BaseReview review = repository.save(r.get());

        return ReviewMapper.toDto(review);
    }


    @Override
    public List<ReviewDTO> findReviewsByUser(Long userID) {

        final Optional<User> user = uRepository.findById(userID);

        if(user.isEmpty()) return null;

        List<BaseReview> r = repository.findByUserId(user.get());

        if (r.isEmpty()) return null;

        return ReviewMapper.toDtoList(r);
    }
}