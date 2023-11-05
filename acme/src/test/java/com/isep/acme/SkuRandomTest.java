package com.isep.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Array;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.isep.acme.services.sku.SkuRandom;

public class SkuRandomTest {

    final String specialChars = "$&@?<>~!%#";
    
    @Test
    public void testCreateSkuRandom(){
        SkuRandom skuRandom = new SkuRandom();
        // O Atributo é necessário apenas por uma exigencia da interface de SkuGenerator
        // Porém estes teste não irá utilizar esta propriedade
        String sku = skuRandom.createSku("sampleProductInfo");

        char[] arraySku = sku.toCharArray();

        assertEquals(11, arraySku.length);
        assertTrue(isNumber(arraySku[0]));
        assertTrue(isLetter(arraySku[1]));
        assertTrue(isNumber(arraySku[2]));
        assertTrue(isLetter(arraySku[3]));
        assertTrue(isNumber(arraySku[4]));
        assertTrue(isLetter(arraySku[5]));
        assertTrue(isLetter(arraySku[6]));
        assertTrue(isNumber(arraySku[7]));
        assertTrue(isLetter(arraySku[8]));
        assertTrue(isNumber(arraySku[9]));
        assertTrue(isSpecialCharacter(arraySku[10]));
    }

    private boolean isLetter(char letter){
        return Character.isLetter(letter);
    }
   
    private boolean isNumber(char number){
        return Character.isDigit(number);
    }

    private boolean isSpecialCharacter(char character){
        return specialChars.contains(String.valueOf(character));
    }



    
}
