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
	
	
	public final static String TRANS_USER_ID_SHOULD_NOT_NULL="USER NOT FOUND";
	public final static String TRANS_DEBT_ID_SHOULD_NOT_NULL="DEBT NOT FOUND";
	public final static String TRANS_PREDICT_NOT_CORRECT="PLEASE BET WHICH SIDE YOU THINK WILL WIN";
	public final static String TRANS_AMOUNT_NOT_CORRECT="THIS AMOUNT MAYBE USED OR NOT CORRECT";
}
