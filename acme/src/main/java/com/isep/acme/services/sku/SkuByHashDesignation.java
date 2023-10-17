package com.isep.acme.services.sku;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.isep.acme.model.Product;

@Service
@ConditionalOnProperty(name = "sku.generator.type", havingValue = "hexDesignation")
public class SkuByHashDesignation implements ISkuGenerator{

    @Override
    public String createSku(Product product) {
       try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(product.getDesignation().getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return extractMiddle10Characters(hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating SHA-256 hash", e);
        }
    }

    public static String extractMiddle10Characters(String input) {
        int length = input.length();
        int start = (length - 10) / 2;  // Starting index for the 10 characters
        int end = start + 10;  // Ending index for the 10 characters
        return input.substring(start, end);
    }
    
}
