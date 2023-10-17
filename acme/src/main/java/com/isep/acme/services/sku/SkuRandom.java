package com.isep.acme.services.sku;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.isep.acme.model.Product;

@Service
@ConditionalOnProperty(name = "sku.generator.type", havingValue = "random")
public class SkuRandom implements ISkuGenerator{
    
    @Override
    public String createSku(Product product) {
        return "aaaaaa";
    }

}
