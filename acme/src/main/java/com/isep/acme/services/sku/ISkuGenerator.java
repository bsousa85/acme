package com.isep.acme.services.sku;

import com.isep.acme.model.Product;

public interface ISkuGenerator {
    String createSku(Product product);
}
