package com.mercadolibre.cupon.error;

import com.mercadolibre.cupon.cupon.Cupon;


public class CuponResponseError {
	
	private String message;
	private String error;
	private int status;
	private Cupon data;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Cupon getData() {
		return data;
	}
	
	public void setData(Cupon data) {
		this.data = data;
	}

}
