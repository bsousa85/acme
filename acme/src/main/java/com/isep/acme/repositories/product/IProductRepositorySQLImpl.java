package com.isep.acme.repositories.product;

import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.product.ProductSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("sql")
public class IProductRepositorySQLImpl implements IProductRepository {

    @Autowired
    private IProductSQLDriver sqlDriver;

    @Override
    public List<BaseProduct> findByDesignation(String designation) {
        return sqlDriver.findByDesignation(designation).stream().map(ProductSQL::toBaseProduct).collect(Collectors.toList());
    }

    @Override
    public Optional<BaseProduct> findBySku(String sku) {
        return sqlDriver.findBySku(sku).map(ProductSQL::toBaseProduct);
    }

    @Override
    public Optional<BaseProduct> getCatalog() {
        return sqlDriver.getCatalog().map(ProductSQL::toBaseProduct);
    }

    @Override
    public void deleteBySku(String sku) {
        sqlDriver.deleteBySku(sku);
    }

    @Override
    public BaseProduct updateBySku(String sku) {
        return sqlDriver.updateBySku(sku).toBaseProduct();
    }

    @Override
    public Optional<BaseProduct> findById(Long productID) {
        return sqlDriver.findById(productID).map(ProductSQL::toBaseProduct);
    }

    @Override
    public BaseProduct save(BaseProduct product) {
        final var sqlProduct = new ProductSQL(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
        return sqlDriver.save(sqlProduct).toBaseProduct();
    }

    @Override
    public List<BaseProduct> findAll() {
        final var allProducts = sqlDriver.findAll();

        List<ProductSQL> productsList = new ArrayList<>();
        allProducts.forEach(productsList::add);

        return productsList.stream().map(ProductSQL::toBaseProduct).collect(Collectors.toList());
    }
}
