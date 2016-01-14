package io.restaurant.exception;

public class AppException extends Exception{

	private static final long serialVersionUID = 6374198390974852793L;


	public AppException(String msg){
		super(msg);
	}
	
	
	public AppException(String msg, Throwable cause){
		super(msg,cause);
	}
}
