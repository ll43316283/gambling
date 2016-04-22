package com.math040.gambling.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class AmountTest extends TestCase{
	
	@Test
	public void testGetAvailableAmount_with_empty_amounts(){
		List<Integer> amounts = new ArrayList<>(); 
		List<Integer> avaliableAmount = Amount.getAvailableAmount(amounts);
		assertEquals(Amount.AVALIABLE_AMOUNTS.length, avaliableAmount.size());
	}
	
	@Test
	public void testGetAvailableAmount_with_29_23_amounts(){
		List<Integer> amounts = new ArrayList<>(); 
		amounts.add(29);
		amounts.add(23);
		List<Integer> avaliableAmount = Amount.getAvailableAmount(amounts);
		assertEquals(Amount.AVALIABLE_AMOUNTS.length-2, avaliableAmount.size());
		assertFalse(avaliableAmount.contains(29));
		assertFalse(avaliableAmount.contains(23));
		assertEquals(19,avaliableAmount.get(0).intValue());
	}
	
	@Test
	public void testGetAvailableAmount_with_full_amounts_should_return_1(){
		List<Integer> amounts = new ArrayList<>(Arrays.asList(Amount.AVALIABLE_AMOUNTS));  
		List<Integer> avaliableAmount = Amount.getAvailableAmount(amounts);
		assertEquals(1, avaliableAmount.size()); 
		assertEquals(1,avaliableAmount.get(0).intValue());
	}
	
	@Test
	public void testValidate_non_predicted_validate_29(){
		List<Integer> amounts = new ArrayList<>();  
		assertTrue( Amount.validate(amounts, 29)); 
	}
	
	@Test
	public void testValidate_non_predicted_validate_null(){
		List<Integer> amounts = new ArrayList<>();  
		assertFalse( Amount.validate(amounts, null)); 
	}
	
	@Test
	public void testValidate_29_23_predicted_validate_23(){
		List<Integer> amounts = new ArrayList<>(); 
		amounts.add(29);
		amounts.add(23);
		assertFalse( Amount.validate(amounts, 23));
		
	}
	
	@Test
	public void testValidate_29_23_predicted_validate_19(){
		List<Integer> amounts = new ArrayList<>(); 
		amounts.add(29);
		amounts.add(23);
		assertTrue( Amount.validate(amounts, 19));
		
	}
	
	@Test
	public void testValidate_29_1_predicted_validate_1(){
		List<Integer> amounts = new ArrayList<>(); 
		amounts.add(29);
		amounts.add(1);
		assertTrue( Amount.validate(amounts, 1));
		
	} 
	
	@Test
	public void testValidate_all_predicted_validate_1(){
		List<Integer> amounts = new ArrayList<>(Arrays.asList(Amount.AVALIABLE_AMOUNTS));  
		assertTrue( Amount.validate(amounts, 1));
		
	}
}
