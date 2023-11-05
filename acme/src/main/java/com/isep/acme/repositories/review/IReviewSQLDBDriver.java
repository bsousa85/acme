package com.isep.acme.repositories.review;

import com.isep.acme.model.User;
import com.isep.acme.model.product.ProductSQL;
import com.isep.acme.model.review.ReviewSQL;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("sql")
public interface IReviewSQLDBDriver extends CrudRepository<ReviewSQL, Long> {

    @Query("SELECT r FROM Review r WHERE r.product=:product ORDER BY r.publishingDate DESC")
    List<ReviewSQL> findByProductId(ProductSQL product);

    @Query("SELECT r FROM Review r WHERE r.approvalStatus='pending'")
    List<ReviewSQL> findPendingReviews(String approvalStatus);

    @Query("SELECT r FROM Review r WHERE r.approvalStatus='active'")
    List<ReviewSQL> findActiveReviews(String approvalStatus);

    @Query("SELECT r FROM Review r WHERE r.product=:product AND r.approvalStatus=:status ORDER BY r.publishingDate DESC")
    List<ReviewSQL> findByProductIdStatus(ProductSQL product, String status);

    @Query("SELECT r FROM Review r WHERE r.user=:user ORDER BY r.publishingDate DESC")
    List<ReviewSQL> findByUserId(User user);
}
