package com.isep.acme.model;

import javax.persistence.Column;
import javax.persistence.GenerationType;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import java.util.Objects;

@Node
public class ProductNeo4J {

    @Id
    @GeneratedValue
    private Long productID;

    public String sku;
    private String designation;
    private String description;
  
    
    protected ProductNeo4J(){}

    public ProductNeo4J(final Long productID, final String sku) {
        this.productID = Objects.requireNonNull(productID);
        setSku(sku);
    }

    public ProductNeo4J(final Long productID, final String sku, final String designation, final String description) { 
        setProductID(productID);
        setSku(sku);
        setDescription(description);
        setDesignation(designation);
    }

    public ProductNeo4J(final String sku) {
        setSku(sku);
    }

    public ProductNeo4J(final String sku, final String designation, final String description) {
        this(sku);
        setDescription(description);
        setDesignation(designation);
    }

    public void setSku(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is a mandatory attribute of Product.");
        }

        this.sku = sku;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.isBlank()) {
            throw new IllegalArgumentException("Designation is a mandatory attribute of Product.");
        }
        if (designation.length() > 50) {
            throw new IllegalArgumentException("Designation must not be greater than 50 characters.");
        }
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is a mandatory attribute of Product.");
        }

        if (description.length() > 1200) {
            throw new IllegalArgumentException("Description must not be greater than 1200 characters.");
        }

        this.description = description;
    }

    public String getSku() {
        return sku;
    }


    public void updateProduct(ProductNeo4J p) {
        setDesignation(p.designation);
        setDescription(p.description);
    }

    public void setProductID(Long id){
        this.productID = id;
    }

    public Long getProductID() {
        return productID;
    }

    public ProductDTO toDto() {
        return new ProductDTO(this.sku, this.designation);
    }

    public BaseProduct toBaseProduct() {
        return new BaseProduct(this.sku, this.designation, this.description);
    }
/*
    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }
*/
}
