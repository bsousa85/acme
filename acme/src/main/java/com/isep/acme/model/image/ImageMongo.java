package com.isep.acme.model.image;

import com.isep.acme.model.ImageDTO;
import com.isep.acme.model.product.ProductMongo;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Resource;
import javax.persistence.Id;

@Document("image")
public class ImageMongo {

    @Transient
    public static final String IMAGE_SEQUENCE = "imageSequence";

    @Id
    private Long id;

    @DBRef
    private ProductMongo product;

    private Resource image;

    public ImageMongo(ProductMongo product, Resource image){
        setProduct(product);
        setImage(image);
    }

    public ImageMongo() {

    }

    private void setProduct(ProductMongo product) {
        this.product = product;
    }

    public ImageDTO toDto() {
        return new ImageDTO(id, product.getProductID());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductMongo getProduct() {
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
