package com.carselector.exception;

public class CarNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2343463452365647342L;
	
	public CarNotFoundException(String message)
	{
		super(message);
	}
	
}
