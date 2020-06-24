package com.mercadolibre.cupon;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mercadolibre.cupon.controlador.CuponRestController;
import com.mercadolibre.cupon.cupon.Cupon;
import com.mercadolibre.cupon.error.CuponErrorEnum;
import com.mercadolibre.cupon.error.CuponResponseError;



@SpringBootTest
public class CuponResponseErrorTest {
	
	@Autowired
	private CuponRestController cuponRestController;
	
	/**
	 * Se testea que se correspondan los msjs de error cuando el error es Internal_Server_Error
	 */
	@Test
	public void obtenerErrores() {
		Cupon cuponRequest = new Cupon();
		
		@SuppressWarnings("unchecked")
		ResponseEntity<CuponResponseError> couponResponse = 
				(ResponseEntity<CuponResponseError>) cuponRestController.itemsValidForCoupon(cuponRequest);
		
		CuponResponseError couponErrorResponse = couponResponse.getBody();
		
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), couponErrorResponse.getStatus());
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), couponErrorResponse.getError());
		Assert.assertEquals(CuponErrorEnum.INTERNAL_SERVER_ERROR.getMessage(), couponErrorResponse.getMessage());
		Assert.assertEquals(null, couponErrorResponse.getData());
	}

}
