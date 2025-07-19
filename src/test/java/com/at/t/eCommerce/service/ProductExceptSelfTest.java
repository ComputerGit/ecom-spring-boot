package com.at.t.eCommerce.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import com.at.t.eCommerce.service.JUNIT_TEST.ProductExceptSelf;

@SpringBootTest
public class ProductExceptSelfTest {

	@Test
	public void testExample() {
	
		int[] input = {1,2,3,4};
		int[] expected = {24,12,8,6};
		
		assertArrayEquals(expected, ProductExceptSelf.productExceptSelf(input));
 		
	}
	 
//	@Disabled
	 @Test
	 @ParameterizedTest
	 @CsvSource
	 @CsvFileSource
	 @EnumSource
	 @ValueSource
//	 @ArgumentsSource and also what is builder in Model
	    public void testWithZeros() {
	        int[] input = {0, 2, 3, 4};
	        int[] expected = {24, 0, 0, 1};
	        assertArrayEquals(expected, ProductExceptSelf.productExceptSelf(input));
	    }
	 
	 
	
}
