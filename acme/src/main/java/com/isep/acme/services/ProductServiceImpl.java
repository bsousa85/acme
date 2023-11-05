package com.isep.acme.services;

import com.isep.acme.model.product.BaseProduct;
import com.isep.acme.model.ProductDTO;
import com.isep.acme.model.ProductDetailDTO;
import com.isep.acme.repositories.product.IProductRepository;
import com.isep.acme.services.sku.ISkuGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductRepository repository;

    @Autowired
    private ISkuGenerator skuGenerator;

    @Override
    public Optional<BaseProduct> getProductBySku( final String sku ) {
        return repository.findBySku(sku);
    }

    @Override
    public Optional<ProductDTO> findBySku(String sku) {
        final Optional<BaseProduct> product = repository.findBySku(sku);

        return product.map(BaseProduct::toDto);
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(final String designation) {
        Iterable<BaseProduct> p = repository.findByDesignation(designation);
        List<ProductDTO> pDto = new ArrayList<>();
        for (BaseProduct pd : p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        final var p = repository.findAll();
        List<ProductDTO> pDto = new ArrayList<>();
        for (BaseProduct pd : p) {
            pDto.add(pd.toDto());
        }
        return pDto;
    }

    public ProductDetailDTO getDetails(String sku) {

        Optional<BaseProduct> p = repository.findBySku(sku);

        if (p.isEmpty())
            return null;
        else
            return new ProductDetailDTO(p.get().getSku(), p.get().getDesignation(), p.get().getDescription());
    }


    @Override
    public ProductDTO create(final BaseProduct product) {
        final BaseProduct p;
    
        String sku = skuGenerator.createSku(product.getDesignation());
        
        p = new BaseProduct(sku, product.getDesignation(), product.getDescription());
        return repository.save(p).toDto();

    }

    @Override
    public ProductDTO updateBySku(String sku, BaseProduct product) {
        
        final Optional<BaseProduct> productToUpdate = repository.findBySku(sku);

        if( productToUpdate.isEmpty() ) return null;

        productToUpdate.get().updateProduct(product);

        BaseProduct productUpdated = repository.save(productToUpdate.get());
        
        return productUpdated.toDto();
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }
}
