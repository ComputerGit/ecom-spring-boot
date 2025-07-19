package com.at.t.eCommerce.service.JUNIT_TEST;

public class ProductExceptSelf {

	public static int[] productExceptSelf(int[] ar) {

		int n = ar.length;
		int left[] = new int[n];
		int right[] = new int[n];
		int result[] = new int[n];

		left[0] = 1;
		for (int i = 1; i < n; i++) {

			left[i] = ar[i - 1] * left[i - 1];
		}

		right[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {

			right[i] = ar[i + 1] * right[i + 1];

		}

		for (int i = 0; i < n; i++) {

			result[i] = left[i] * right[i];
		}

		return result;

	}

	public static void main(String[] args) {

		int[] nums = { 1, 2, 3, 4 };

		int[] res = productExceptSelf(nums);

		for (int r : res) {
			System.out.print(r);
		}

	}

}
