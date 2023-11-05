package com.isep.acme.model.image;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.isep.acme.model.ImageDTO;
import com.isep.acme.model.product.ProductNeo4J;

import javax.annotation.Resource;

@Node("image")
public class ImageNeo4J {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "HAS_PRODUCT")
    private ProductNeo4J product;

    private Resource image;

    public ImageNeo4J(ProductNeo4J product, Resource image){
        setProduct(product);
        setImage(image);
    }

    public ImageNeo4J() {

    }

    private void setProduct(ProductNeo4J product) {
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

    public ProductNeo4J getProduct() {
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