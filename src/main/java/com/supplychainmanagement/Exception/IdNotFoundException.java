package com.supplychainmanagement.Exception;

public class IdNotFoundException extends RuntimeException {
	
	@Override
	public String getMessage() {
		
		return "Id is not found";
	}

}
