package com.mercadolibre.cupon.error;

public enum CuponErrorEnum {
	

    NOT_ENOUGH_QUANTITY(404,"The amount is not enough to buy an item for the requested list"),
	INTERNAL_SERVER_ERROR(500, "An error ocurred while processing response");
	
	private int errorCode;
	private String message;
	
	private CuponErrorEnum(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
	public String getMessage() {
		return message;
	}

}
