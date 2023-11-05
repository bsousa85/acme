package com.isep.acme.model.review;

import org.springframework.data.neo4j.core.schema.*;

import com.isep.acme.model.Vote;

import com.isep.acme.model.product.ProductNeo4J;
import com.isep.acme.model.rating.RatingNeo4J;
import com.isep.acme.model.user.UserNeo4J;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Version;

@Node
public class ReviewNeo4J {
    
    @Id @GeneratedValue
    private Long idReview;

    @Version
    private long version;
    
    private String approvalStatus;
    private String reviewText;
    
    @Relationship(type = "UP_VOTE", direction = Relationship.Direction.OUTGOING)
    private List<Vote> upVote;
    
    @Relationship(type = "DOWN_VOTE", direction = Relationship.Direction.OUTGOING)
    private List<Vote> downVote;
    
    private String report;
    private LocalDate publishingDate;
    private String funFact;

    @Relationship(type = "REVIEWS_PRODUCT", direction = Relationship.Direction.OUTGOING)
    private ProductNeo4J product;

    @Relationship(type = "WRITTEN_BY", direction = Relationship.Direction.OUTGOING)
    private UserNeo4J user;

    @Relationship(type = "HAS_RATING", direction = Relationship.Direction.OUTGOING)
    private RatingNeo4J rating;

    // Constructors, Getters, and Setters
    protected ReviewNeo4J(){}

    public ReviewNeo4J(final Long idReview, final long version, final String approvalStatus, final String reviewText, final LocalDate publishingDate, final String funFact) {
        this.idReview = Objects.requireNonNull(idReview);
        this.version = Objects.requireNonNull(version);
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public ReviewNeo4J(final Long idReview, final long version, final String approvalStatus, final  String reviewText, final List<Vote> upVote, final List<Vote> downVote, final String report, final LocalDate publishingDate, final String funFact, ProductNeo4J product, RatingNeo4J rating, UserNeo4J user) {
        this(idReview, version, approvalStatus, reviewText, publishingDate, funFact);

        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setProduct(product);
        setRating(rating);
        setUser(user);

    }

    public ReviewNeo4J(final String reviewText, LocalDate publishingDate, ProductNeo4J product, String funFact, RatingNeo4J rating, UserNeo4J user) {
        setReviewText(reviewText);
        setProduct(product);
        setPublishingDate(publishingDate);
        setApprovalStatus("pending");
        setFunFact(funFact);
        setRating(rating);
        setUser(user);
        this.upVote = new ArrayList<>();
        this.downVote = new ArrayList<>();
    }

    public Long getIdReview() {
        return idReview;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public Boolean setApprovalStatus(String approvalStatus) {

        if( approvalStatus.equalsIgnoreCase("pending") ||
                approvalStatus.equalsIgnoreCase("approved") ||
                approvalStatus.equalsIgnoreCase("rejected")) {

            this.approvalStatus = approvalStatus;
            return true;
        }
        return false;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        if (reviewText == null || reviewText.isBlank()) {
            throw new IllegalArgumentException("Review Text is a mandatory attribute of Review.");
        }
        if (reviewText.length() > 2048) {
            throw new IllegalArgumentException("Review Text must not be greater than 2048 characters.");
        }

        this.reviewText = reviewText;
    }

    public void setReport(String report) {
        if (report.length() > 2048) {
            throw new IllegalArgumentException("Report must not be greater than 2048 characters.");
        }
        this.report = report;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public long getVersion() {
        return version;
    }

    public String getFunFact() {
        return funFact;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }

    public void setProduct(ProductNeo4J product) {
        this.product = product;
    }

    public ProductNeo4J getProduct() {
        return product;
    }

    public UserNeo4J getUser() {
        return user;
    }

    public void setUser(UserNeo4J user) {
        this.user = user;
    }

    public RatingNeo4J getRating() {
        if(rating == null) {
            return new RatingNeo4J(0.0);
        }
        return rating;
    }

    public void setRating(RatingNeo4J rating) {
        this.rating = rating;
    }

    public List<Vote> getUpVote() {
        return upVote;
    }

    public void setUpVote(List<Vote> upVote) {
        this.upVote = upVote;
    }

    public List<Vote> getDownVote() {
        return downVote;
    }

    public void setDownVote(List<Vote> downVote) {
        this.downVote = downVote;
    }

    public boolean addUpVote(Vote upVote) {

        if( !this.approvalStatus.equals("approved"))
            return false;

        if(!this.upVote.contains(upVote)){
            this.upVote.add(upVote);
            return true;
        }
        return false;
    }

    public boolean addDownVote(Vote downVote) {

        if( !this.approvalStatus.equals( "approved") )
            return false;

        if(!this.downVote.contains(downVote)){
            this.downVote.add(downVote);
            return true;
        }
        return false;
    }

    public BaseReview toBaseReview() {
        return new BaseReview(this.idReview, this.version, this.approvalStatus, this.reviewText, this.upVote, this.downVote, this.report, this.publishingDate, this.funFact, this.product.toBaseProduct(), this.rating.toBaseRating(), this.user.toBaseUser());
    }
}

