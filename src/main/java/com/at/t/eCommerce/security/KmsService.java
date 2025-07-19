//package com.at.t.eCommerce.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.kms.KmsClient;
//import software.amazon.awssdk.services.kms.model.GetPublicKeyRequest;
//import software.amazon.awssdk.services.kms.model.GetPublicKeyResponse;
//
//import java.math.BigInteger;
//import java.security.KeyFactory;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.RSAPublicKeySpec;
//import java.util.Base64;
//
//@Service
//public class KmsService {
//
//    private final KmsClient kmsClient;
//
//    @Value("${aws.region}")
//    private String awsRegion;
//
//    @Value("${aws.kms.key-id}")
//    private String kmsKeyId;
//
//    public KmsService() {
//        this.kmsClient = KmsClient.builder()
//            .region(Region.of(awsRegion)) // Use the injected AWS region
//            .credentialsProvider(DefaultCredentialsProvider.create())
//            .build();
//    }
//
//    public RSAPublicKey getPublicKey() {
//        try {
//            GetPublicKeyRequest request = GetPublicKeyRequest.builder()
//                .keyId(kmsKeyId)  // Use the injected KMS Key ID
//                .build();
//
//            GetPublicKeyResponse response = kmsClient.getPublicKey(request);
//            byte[] pemEncodedKey = response.publicKey().asByteArray(); // Convert SdkBytes to byte array
//
//            // Decode the PEM-encoded public key
//            String pem = new String(pemEncodedKey);
//            byte[] derEncodedKey = Base64.getDecoder().decode(pem);
//
//            // Construct the RSA public key
//            BigInteger modulus = new BigInteger(1, derEncodedKey);
//            BigInteger exponent = BigInteger.valueOf(65537); // Common public exponent used in RSA
//
//            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
//            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to retrieve public key from AWS KMS", e);
//        }
//    }
//}
