package com.mercadolibre.cupon;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mercadolibre.cupon.controlador.CuponRestController;
import com.mercadolibre.cupon.cupon.Cupon;


@SpringBootTest
public class CuponRestControllerTest {
	
	@Autowired
	private CuponRestController cuponRestController;
	
	/**
	 * Se testea que funcione correctamente la llamada a la API de CouponRestController
	 */
	@Test
	public void itemsValidForCouponOK() {
		Cupon couponRequest = new Cupon();
		couponRequest.setAmount((float) 15000);
		List<String> item_ids = new ArrayList<String>();
		item_ids.add("MLA604171651");
		item_ids.add("MLA755156853");
		couponRequest.setItem_ids(item_ids);
		
		ResponseEntity<?> couponResponse = cuponRestController.itemsValidForCoupon(couponRequest);
		
		Assert.assertEquals(HttpStatus.OK, couponResponse.getStatusCode());
	}
	
	/**
	 * Se testea que si al usuario no le alcanza el monto para comprar alguno de los items
	 * del request que la API Coupon devuelva un 404-NOT FOUND
	 */
	@Test
	public void itemsValidForCouponNotFound() {
		Cupon cuponRequest = new Cupon();
		cuponRequest.setAmount((float) 1000);
		List<String> item_ids = new ArrayList<String>();
		item_ids.add("MLA764012028");
		item_ids.add("MLA755156853");
		cuponRequest.setItem_ids(item_ids);
		
		ResponseEntity<?> couponResponse = cuponRestController.itemsValidForCoupon(cuponRequest);
		
		Assert.assertEquals(HttpStatus.NOT_FOUND, couponResponse.getStatusCode());
	}
	
	/**
	 * Se testea que si falla la API Rest devuelva un 500-InternalServerError, caso de que no se
	 * le setee la lista de items del request
	 */
	@Test
	public void itemsValidForCouponInternalServerError() {
		Cupon cuponRequest = new Cupon();
		
		ResponseEntity<?> cuponResponse = cuponRestController.itemsValidForCoupon(cuponRequest);
		
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, cuponResponse.getStatusCode());
	}

}
