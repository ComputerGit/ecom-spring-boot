package com.at.t.eCommerce.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class HashUtil {

	public static String sha256B64(String value) {

		try {

			MessageDigest md = MessageDigest.getInstance("SHA-256");

			byte[] digest = md.digest(value.getBytes(StandardCharsets.UTF_8));

			return Base64.getEncoder().encodeToString(digest);

		} catch (Exception e) {

			throw new IllegalStateException(e);
		}

	}

}
