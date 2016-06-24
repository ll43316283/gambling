package com.math040.gambling.dto;
  
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.math040.gambling.vo.Transaction;
 

public class Amount {
	public static final Integer[] AVALIABLE_AMOUNTS = {29,23,19,17,13,11,7,5,3,1};
	public static List<Integer> getAvailableAmount(List<Integer> amounts){
		ArrayList<Integer> result = new ArrayList<>(Arrays.asList(AVALIABLE_AMOUNTS));
		if(CollectionUtils.isEmpty(amounts)){
			return result;
		} 
		for(Integer amount : amounts){ 
				if(1==amount.intValue()){
					continue;
				}
				result.remove(amount);
		}
		return result;
	}
	
	public static List<Integer> getAvailableAmountByTransList(List<Transaction> transList){
		ArrayList<Integer> result = new ArrayList<>(Arrays.asList(AVALIABLE_AMOUNTS));
		if(CollectionUtils.isEmpty(transList)){
			return result;
		} 
		for(Transaction trans : transList){ 
			Integer amount = trans.getAmount();
				if(1==amount.intValue()){
					continue;
				}
				result.remove(amount);
		}
		return result;
	}
	
	public static boolean validate(List<Integer> predictedAmounts, Integer validateAmount){ 
		List<Integer> avaliableAmount = getAvailableAmount(predictedAmounts);
		return avaliableAmount.contains(validateAmount);
	}
}
