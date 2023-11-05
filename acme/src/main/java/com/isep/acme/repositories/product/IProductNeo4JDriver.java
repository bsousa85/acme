package com.isep.acme.repositories.product;

import com.isep.acme.model.product.ProductNeo4J;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Profile("neo4j")
public interface IProductNeo4JDriver extends Neo4jRepository<ProductNeo4J,Long> {

    @Query("MATCH(p:ProductNeo4J) WHERE p.designation =~ $designation RETURN p")
    List<ProductNeo4J> findByDesignation(String designation);

    @Query("MATCH(p:ProductNeo4J) WHERE p.sku =~ $sku RETURN p")
    Optional<ProductNeo4J> findBySku(@Param("sku") String sku); 

    //Obtain the catalog of products -> Catalog: show sku and designation of all products
//    @Query("SELECT p FROM Product p WHERE p.sku=:sku AND p.description=:description")
    @Query("MATCH(p:ProductNeo4J) WHERE p.sku =~ $sku AND p.description =~ $description RETURN p")
    Optional<ProductNeo4J> getCatalog();

    //Obtain the details of products -> Destails: show sku, designation and description of all products

    //Delete the product when given the SKU
    @Transactional
//    @Query("DELETE FROM Product p WHERE p.sku=:sku")
    @Query("MATCH (p:ProductNeo4J) WHERE p.sku = $sku DETACH DELETE p")
    void deleteBySku(@Param("sku") String sku);

    //Update the product when given the SKU
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.sku = :sku WHERE p.sku=:sku")
    ProductNeo4J updateBySku(@Param("sku") String sku);

//    @Query("SELECT p FROM Product p WHERE p.productID=:productID")
    @Query("MATCH(p:ProductNeo4J) WHERE p.productID =~ productID RETURN p")
    Optional<ProductNeo4J> findById(Long productID);

  /*  @Query("SELECT p FROM ProdImage p WHERE p.id=:id")
    Optional<Product> findById(Long  id); */

}
