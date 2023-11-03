package com.isep.acme.repositories.product;

import com.isep.acme.model.BaseProduct;
import com.isep.acme.model.ProductMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("mongo")
public class IProductRepositoryMongoImpl implements IProductRepository {

    @Autowired
    private IMongoDBDriver mongoDBDriver;

    @Override
    public List<BaseProduct> findByDesignation(String designation) {
        return mongoDBDriver.findAll().stream().map(ProductMongo::toBaseProduct).collect(Collectors.toList());
    }

    @Override
    public Optional<BaseProduct> findBySku(String sku) {
        return mongoDBDriver.findBySku(sku).map(ProductMongo::toBaseProduct);
    }

    @Override
    public Optional<BaseProduct> getCatalog() {
        return mongoDBDriver.getCatalog().map(ProductMongo::toBaseProduct);
    }

    @Override
    public void deleteBySku(String sku) {
        mongoDBDriver.deleteBySku(sku);
    }

    @Override
    public BaseProduct updateBySku(String sku) {
        return mongoDBDriver.updateBySku(sku).toBaseProduct();
    }

    @Override
    public Optional<BaseProduct> findById(Long productID) {
        return mongoDBDriver.findById(productID).map(ProductMongo::toBaseProduct);
    }

    @Override
    public BaseProduct save(BaseProduct product) {
        final var mongoProduct = new ProductMongo(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return mongoDBDriver.save(mongoProduct).toBaseProduct();
    }

    @Override
    public List<BaseProduct> findAll() {
        return mongoDBDriver.findAll().stream().map(ProductMongo::toBaseProduct).collect(Collectors.toList());
    }
}
