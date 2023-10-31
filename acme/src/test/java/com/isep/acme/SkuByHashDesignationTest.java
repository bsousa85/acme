package com.isep.acme;

import org.junit.jupiter.api.Test;

import com.isep.acme.services.sku.SkuByHashDesignation;

import static org.junit.jupiter.api.Assertions.*;

public class SkuByHashDesignationTest {

    @Test
    public void testCreateSku() {
        SkuByHashDesignation skuGenerator = new SkuByHashDesignation();
        String productInformation = "Example Product Information";

        String sku = skuGenerator.createSku(productInformation);

        // Test if the generated SKU has a length of 10 characters
        assertEquals(10, sku.length());

        // Test if the generated SKU contains only hexadecimal characters
        assertTrue(sku.matches("[0-9a-fA-F]+"));
    }
}
