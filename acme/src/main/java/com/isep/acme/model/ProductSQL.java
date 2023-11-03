package com.isep.acme.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductSQL {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productID;

    @Column(nullable = false, unique = true)
    public String sku;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private String description;
    /*
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    protected ProductSQL(){}

    public ProductSQL(final Long productID, final String sku) {
        this.productID = Objects.requireNonNull(productID);
        setSku(sku);
    }

    public ProductSQL(final Long productID, final String sku, final String designation, final String description) {
        this(productID, sku);
        setDescription(description);
        setDesignation(designation);
    }

    public ProductSQL(final String sku) {
        setSku(sku);
    }

    public ProductSQL(final String sku, final String designation, final String description) {
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


    public void updateProduct(ProductSQL p) {
        setDesignation(p.designation);
        setDescription(p.description);
    }

    public Long getProductID() {
        return productID;
    }

    public ProductDTO toDto() {
        return new ProductDTO(this.sku, this.designation);
    }

    public BaseProduct toBaseProduct() {
        return new BaseProduct(this.productID, this.sku, this.designation, this.description);
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