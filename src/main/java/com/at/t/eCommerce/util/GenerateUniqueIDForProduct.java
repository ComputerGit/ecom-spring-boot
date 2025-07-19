package com.at.t.eCommerce.util;

import java.security.SecureRandom;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.at.t.eCommerce.repo.ProductModelRepo;

@Component
public class GenerateUniqueIDForProduct {
	
	@Autowired
	private ProductModelRepo productRepo;

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();
	private static final int RANDOM_LENGTH = 5;

	public synchronized String generateUniqueProductId(String categoryPrefix) {
		String uniqueId;
		do {
			uniqueId = categoryPrefix + encodeCurrentTimeStamp() + getRandomAlphaNumeric();
		} while (productRepo.existsByUniqueID(uniqueId)); // Ensure the ID is unique in the database
		return uniqueId;
	}

	private String encodeCurrentTimeStamp() {

		Long now = Instant.now().toEpochMilli();

		return Long.toString(now, 32).toUpperCase();
	}

	private String getRandomAlphaNumeric() {

		StringBuilder randomComponent = new StringBuilder(RANDOM_LENGTH);

		for (int i = 0; i < RANDOM_LENGTH; i++) {

			int index = RANDOM.nextInt(CHARACTERS.length());
			randomComponent.append(CHARACTERS.charAt(index));
		}

		return randomComponent.toString();

	}


}
