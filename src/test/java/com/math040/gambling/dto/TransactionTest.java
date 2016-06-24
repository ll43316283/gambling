package com.math040.gambling.dto;

import org.junit.Assert;
import org.junit.Test;

import com.math040.gambling.vo.Transaction;

import junit.framework.TestCase;

public class TransactionTest  extends TestCase {
	@Test
	public void testValidateSideAmount(){ 
		Transaction trans = new Transaction();
		
		Assert.assertFalse(trans.validateSideAmount());
		
		trans.setSideAmmount("Y3");
		Assert.assertTrue(trans.validateSideAmount());
		trans.setSideAmmount("N3");
		Assert.assertTrue(trans.validateSideAmount());
		trans.setSideAmmount("N31");
		Assert.assertTrue(trans.validateSideAmount());
		trans.setSideAmmount("Z31");
		Assert.assertFalse(trans.validateSideAmount());
		trans.setSideAmmount("Y313");
		Assert.assertFalse(trans.validateSideAmount());
		trans.setSideAmmount("YN13");
		Assert.assertFalse(trans.validateSideAmount());
		trans.setSideAmmount("");
		Assert.assertFalse(trans.validateSideAmount());
	}
}
