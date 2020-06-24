package com.mercadolibre.cupon.service;

import org.springframework.http.ResponseEntity;

import com.mercadolibre.cupon.cupon.Cupon;

public interface ICuponService {

	ResponseEntity<?> itemsValidForCoupon(Cupon request);

}
