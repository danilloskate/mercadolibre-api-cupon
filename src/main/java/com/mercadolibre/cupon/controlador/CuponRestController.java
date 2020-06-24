package com.mercadolibre.cupon.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.cupon.cupon.Cupon;
import com.mercadolibre.cupon.service.ICuponService;


/**
 * Clase CuponRestController que recibe la llamada del api y reparte las
 * funiconalidades, se crea un método POST con el path /cupon el cual su
 * respuesta sera en formato Json
 * 
 * @author dfzapata
 */
@RestController
public class CuponRestController {

	@Autowired
	private ICuponService couponService;

	@PostMapping(path = "/cupon", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> itemsValidForCoupon(@RequestBody Cupon request) {
		return couponService.itemsValidForCoupon(request);
	}
}
