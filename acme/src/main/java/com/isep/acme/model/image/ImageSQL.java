package com.isep.acme.model.image;

import com.isep.acme.model.ImageDTO;
import com.isep.acme.model.product.ProductSQL;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class ImageSQL {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private ProductSQL product;

    @Lob
    private Resource image;

    public ImageSQL(ProductSQL product, Resource image){
        setProduct(product);
        setImage(image);
    }

    public ImageSQL() {

    }

    private void setProduct(ProductSQL product) {
        this.product = product;
    }

    public ImageDTO toDto() {
        return new ImageDTO(this.id, product.getProductID());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductSQL getProduct() {
        return product;
    }

    public Resource getImage() {
        return image;
    }

    public void setImage(Resource image) {
        this.image = image;
    }

    public BaseImage toBaseImage() {
        return new BaseImage(product.toBaseProduct(), image);
    }
}
