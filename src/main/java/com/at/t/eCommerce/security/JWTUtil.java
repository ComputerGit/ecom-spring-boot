////Retrieves the RSA public key from the KeyVaultService.
////Constructs the RSAPublicKey from modulus and exponent extracted from the JsonWebKey.
////Validates and parses the JWT token using the RSAPublicKey
//package com.at.t.eCommerce.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import java.security.interfaces.RSAPublicKey;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JWTUtil {
//
//    private final KmsService kmsService;
//
//    public JWTUtil(KmsService kmsService) {
//        this.kmsService = kmsService;
//    }
//
//    private RSAPublicKey getPublicKey() {
//        try {
//            return kmsService.getPublicKey(); // No arguments needed
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to retrieve RSA public key from KMS", e);
//        }
//    }
//
//    public Claims parseToken(String token) {
//        RSAPublicKey publicKey = getPublicKey();
//        try {
//            return Jwts.parserBuilder()
//                .setSigningKey(publicKey)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        } catch (Exception e) {
//            throw new RuntimeException("Error parsing JWT token", e);
//        }
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            parseToken(token);
//            return true;
//        } catch (Exception e) {
//            // Optionally log the exception
//            return false;
//        }
//    }
//}
