package com.isep.acme.services.sku;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.util.Random;

import com.isep.acme.model.Product;

@Service
@ConditionalOnProperty(name = "sku.generator.type", havingValue = "random")
public class SkuRandom implements ISkuGenerator{
    
    @Override
    public String createSku(Product product) {

        final var sku = new StringBuilder();
        final var random = new Random();
        final var specialChars = "$&@?<>~!%#";

        for (int i = 0; i < 3; i++) {
            sku.append(random.nextInt(10));
            sku.append((char)(random.nextInt(26) + 'a'));
        }

        for (int i=0; i < 2; i++) {
            sku.append((char)(random.nextInt(26) + 'a'));
            sku.append(random.nextInt(10));
        }

        sku.append(specialChars.charAt(random.nextInt(specialChars.length())));

        return sku.toString();
    }

}
