package com.isep.acme.model.image;

import com.isep.acme.model.ImageDTO;
import com.isep.acme.model.product.BaseProduct;

import javax.annotation.Resource;

public class BaseImage {

    private Long id;

    private BaseProduct product;

    private Resource image;

    public BaseImage(BaseProduct product, Resource image){
        setProduct(product);
        setImage(image);
    }

    public BaseImage() {

    }

    private void setProduct(BaseProduct product) {
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

    public BaseProduct getProduct() {
        return product;
    }

    public Resource getImage() {
        return image;
    }

    public void setImage(Resource image) {
        this.image = image;
    }
}
