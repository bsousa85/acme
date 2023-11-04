package com.isep.acme.repositories.product;

import com.isep.acme.model.BaseProduct;
import com.isep.acme.model.ProductNeo4J;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("neo4j")
public class IProductRepositoryNeo4JImpl implements IProductRepository {

    @Autowired
    private INeo4JDriver neo4JDriver;

    @Override
    public List<BaseProduct> findByDesignation(String designation) {
        return neo4JDriver.findByDesignation(designation).stream().map(ProductNeo4J::toBaseProduct).collect(Collectors.toList());
    }

    @Override
    public Optional<BaseProduct> findBySku(String sku) {
        return neo4JDriver.findBySku(sku).map(ProductNeo4J::toBaseProduct);
    }

    @Override
    public Optional<BaseProduct> getCatalog() {
        return neo4JDriver.getCatalog().map(ProductNeo4J::toBaseProduct);
    }

    @Override
    public void deleteBySku(String sku) {
        neo4JDriver.deleteBySku(sku);
    }

    @Override
    public BaseProduct updateBySku(String sku) {
        return neo4JDriver.updateBySku(sku).toBaseProduct();
    }

    @Override
    public Optional<BaseProduct> findById(Long productID) {
        return neo4JDriver.findById(productID).map(ProductNeo4J::toBaseProduct);
    }

    @Override
    public BaseProduct save(BaseProduct product) {
        final var neo4jProduct = new ProductNeo4J(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return neo4JDriver.save(neo4jProduct).toBaseProduct();
    }

    @Override
    public List<BaseProduct> findAll() {
        System.out.println("FINDING ALL IN NEO4J !");
        return neo4JDriver.findAll().stream().map(ProductNeo4J::toBaseProduct).collect(Collectors.toList());
    }
}
