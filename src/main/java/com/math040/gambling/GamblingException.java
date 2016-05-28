package com.math040.gambling;

public class GamblingException extends Exception {

	 
	private static final long serialVersionUID = 1L;
 
	
	private String message;
	
	public GamblingException(){
		super();
	}
			
	public GamblingException(String message){
		super(message); 
		this.message = message; 
	}
 

	public String getMessage() {
		return message;
	} 
	
	
	 
	public final static String DEBT_USER_ID_SHOULD_NOT_NULL="USER NOT FOUND";
	public final static String DEBT_DEADLINE_SHOULD_NOT_NULL="DEADLINE SHOULD NOT BE NULL";
	public final static String DEBT_TITLE_SHOULD_NOT_NULL="TITLE IS MANDATORY FOR A DEBT";
	public final static String DEBT_CANNOT_CANCLE_A_CLOSED_DEBT="CANNOT CANCLE A CLOSED DEBT";
	public final static String DEBT_RESULT_SHOULD_NOT_NULL_WHEN_END_DEBT="PLEASE GIVE A RESULT WHEN ENDING A DEBT";
	public final static String DEBT_IS_CLOSED_OR_CANCELED="THIS DEBT HAS BEEN CLOSED OR CANCELED";
	
	
	public final static String TRANS_USER_ID_SHOULD_NOT_NULL="USER NOT FOUND";
	public final static String TRANS_DEBT_ID_SHOULD_NOT_NULL="DEBT NOT FOUND";
	public final static String TRANS_PREDICT_NOT_CORRECT="PLEASE BET WHICH SIDE YOU THINK WILL WIN";
	public final static String TRANS_AMOUNT_NOT_CORRECT="THIS AMOUNT MAYBE USED OR NOT CORRECT";
	public final static String TRANS_DEALER_SHOULD_NOT_GAMBLE="DEALER SHOULD NOT GAMBLE";
	public final static String TRANS_GAMBLER_SHOULD_GAMBLE_ONCE_IN_ONE_GAME="GAMBLER SHOULD GAMBLE ONLY ONCE IN ONE GAME";
	public final static String TRANS_SHOULD_NOT_GAMBLE_AFTER_DEADLINE="GAMBLER SHOULD NOT GAMBLE AFTER DEADLINE";
	public final static String TRANS_NOT_CORRECT_AMOUNT_SIDE_PARAM="not correct ammount and side param";
	
	public final static String USER_STATIS_CANNOT_GET_TOTAL_AMOUNTS="CANNOT GET TOTAL AMOUNTS"; 
	
	public final static String TITLE_TITLE_AND_DESC_SHOULD_NOT_BE_NULL="PLEASE INPUT A TITLE NAME AND ITS ";  
	public final static String TITLE_TITLE_CODE_SHOULD_NOT_BE_NULL="PLEASE INPUT A TITLE CODE "; 
	public final static String TITLE_TITLE_CODE_SHOULD_BE_UNIQUE="THIS TITLE CODE HAS BEEN ALREADY EXISTED.PLEASE INPUT A NEW ONE ";  
}
