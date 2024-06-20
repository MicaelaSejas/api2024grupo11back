package com.uade.tpo.controller.config;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretKeyGenerator {
    
    private static final int KEY_LENGTH_BYTES = 32; // Longitud de la clave en bytes

    public static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[KEY_LENGTH_BYTES];
        secureRandom.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static void main(String[] args) {
        String secretKey = generateSecretKey();
        System.out.println("Generated JWT Secret Key: " + secretKey);
    }
}
