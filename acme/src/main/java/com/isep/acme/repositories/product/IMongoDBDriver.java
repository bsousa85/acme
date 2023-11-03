package com.isep.acme.repositories.product;

import com.isep.acme.model.ProductMongo;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Profile("mongo")
public interface IMongoDBDriver extends MongoRepository<ProductMongo, Long> {

    List<ProductMongo> findByDesignation(String designation);

    Optional<ProductMongo> findBySku(String sku);

    //Obtain the catalog of products -> Catalog: show sku and designation of all products
    //    @Query("SELECT p FROM Product p WHERE p.sku=:sku AND p.description=:description")
    @Query("{sku: ?0, description: ?1}")
    Optional<ProductMongo> getCatalog();

    //Obtain the details of products -> Destails: show sku, designation and description of all products

    //Delete the product when given the SKU
    @Transactional
//    @Query("DELETE FROM Product p WHERE p.sku=:sku")
    @DeleteQuery(value="{'sku' : $0}")
    void deleteBySku(@Param("sku") String sku);

    //Update the product when given the SKU
    @Transactional
    @Query("UPDATE Product p SET p.sku = :sku WHERE p.sku=:sku")
    ProductMongo updateBySku(@Param("sku") String sku);

    @Query("SELECT p FROM Product p WHERE p.productID=:productID")
    Optional<ProductMongo> findById(Long productID);

  /*  @Query("SELECT p FROM ProdImage p WHERE p.id=:id")
    Optional<Product> findById(Long  id); */

}
