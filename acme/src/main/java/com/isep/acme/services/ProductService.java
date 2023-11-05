package com.isep.acme.services;

import java.util.Optional;

import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.ProductDTO;
import com.isep.acme.model.ProductDetailDTO;

public interface ProductService {

    Optional<ProductDTO> findBySku(final String sku);

    Optional<BaseProduct> getProductBySku(final String sku );

    Iterable<ProductDTO> findByDesignation(final String designation);

    Iterable<ProductDTO> getCatalog();

    ProductDetailDTO getDetails(final String sku);

    ProductDTO create(final BaseProduct manager);

    ProductDTO updateBySku(final String sku, final BaseProduct product);

    void deleteBySku(final String sku);
}
