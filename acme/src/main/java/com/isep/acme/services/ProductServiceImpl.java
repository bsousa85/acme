package com.isep.acme.services;

import com.isep.acme.model.Product;
import com.isep.acme.model.ProductDTO;
import com.isep.acme.model.ProductDetailDTO;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.services.sku.ISkuGenerator;
import com.isep.acme.services.sku.SkuByHashDesignation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

//    @Autowired
//    private IRepository repository;

    @Autowired
    private ISkuGenerator skuGenerator;

    @Override
    public Optional<Product> getProductBySku( final String sku ) {
        return repository.findBySku(sku);
    }

    @Override
    public Optional<ProductDTO> findBySku(String sku) {
        final Optional<Product> product = repository.findBySku(sku);

        if( product.isEmpty() )
            return Optional.empty();
        else
            return Optional.of( product.get().toDto() );
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(final String designation) {
        Iterable<Product> p = repository.findByDesignation(designation);
        List<ProductDTO> pDto = new ArrayList();
        for (Product pd:p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        Iterable<Product> p = repository.findAll();
        List<ProductDTO> pDto = new ArrayList();
        for (Product pd:p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    public ProductDetailDTO getDetails(String sku) {

        Optional<Product> p = repository.findBySku(sku);

        if (p.isEmpty())
            return null;
        else
            return new ProductDetailDTO(p.get().getSku(), p.get().getDesignation(), p.get().getDescription());
    }


    @Override
    public ProductDTO create(final Product product) {
        final Product p;
        String sku;
                
        //Cria o SKU do produto baseado na designação
        sku = skuGenerator.createSku(product.getDesignation());
        
        //Busca producto na Base de dados
        Optional<Product> productFound = this.getProductBySku(sku);

        //Se o SKU já existir, cria um novo código SKU
        if(!productFound.isEmpty()){
            //Recria o SKU
            sku = skuGenerator.createSku(product.getDesignation() + product.getDescription());
        }
        
        p = new Product(sku, product.getDesignation(), product.getDescription());            
        return repository.save(p).toDto();
        

    }

    @Override
    public ProductDTO updateBySku(String sku, Product product) {
        
        final Optional<Product> productToUpdate = repository.findBySku(sku);

        if( productToUpdate.isEmpty() ) return null;

        productToUpdate.get().updateProduct(product);

        Product productUpdated = repository.save(productToUpdate.get());
        
        return productUpdated.toDto();
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }
}
