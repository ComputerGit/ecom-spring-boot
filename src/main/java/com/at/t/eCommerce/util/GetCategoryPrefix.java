package com.at.t.eCommerce.util;

import org.springframework.stereotype.Component;

@Component
public class GetCategoryPrefix {

	private String getCategoryPrefix(String category) {

		switch (category.toLowerCase()) {

		case "electronics":
			return "EE";
		case "books":
			return "BO";
		case "clothing":
			return "C0";
		default:
			return "PR";
		}

	}

	public String fetchCategoryPrefix(String category) {

		return getCategoryPrefix(category);

	}
}
