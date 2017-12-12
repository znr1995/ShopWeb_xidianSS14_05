package com.project_management.shoppingweb.exception;

public class ServiceException extends RuntimeException{
	public ServiceException() {
		
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, Throwable cause) {
		
	}
}
