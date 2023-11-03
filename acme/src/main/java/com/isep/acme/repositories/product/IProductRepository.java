package com.isep.acme.repositories.product;

import com.isep.acme.model.BaseProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IProductRepository {

    List<BaseProduct> findByDesignation(String designation);

    Optional<BaseProduct> findBySku(String sku);

    //Obtain the catalog of products -> Catalog: show sku and designation of all products
    Optional<BaseProduct> getCatalog();

    //Delete the product when given the SKU

    void deleteBySku(String sku);

    //Update the product when given the SKU
    BaseProduct updateBySku(String sku);

    Optional<BaseProduct> findById(Long productID);

  /*  @Query("SELECT p FROM ProdImage p WHERE p.id=:id")
    Optional<Product> findById(Long  id); */

    BaseProduct save(BaseProduct entity);

    List<BaseProduct> findAll();
}
