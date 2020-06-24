package com.mercadolibre.cupon.service;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mercadolibre.cupon.cupon.Cupon;
import com.mercadolibre.cupon.error.CuponErrorEnum;
import com.mercadolibre.cupon.error.CuponResponseError;
import com.mercadolibre.cupon.items.IItems;

@Service
public class CuponService implements ICuponService {

	private float totalExpending;

	@Autowired
	private IItems items;

	private static final Logger LOG = LoggerFactory.getLogger(CuponService.class);
	private static final Float FLOAT_ZERO = Float.parseFloat("0.00");

	@Override
	public ResponseEntity<?> itemsValidForCoupon(Cupon request) {
		try {

			List<String> itemsRequest = request.getItem_ids();
			Map<String, Float> itemsWithPrice = this.obtainItemsWithPrice(itemsRequest);
			List<String> itemsResponse = this.calculate(itemsWithPrice, request.getAmount());
			Cupon cuponResponse = new Cupon(itemsResponse, totalExpending);

			if (itemsResponse.isEmpty()) {
				return new ResponseEntity<CuponResponseError>(this.getCuponError(HttpStatus.NOT_FOUND, cuponResponse),
						HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Cupon>(cuponResponse, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			return new ResponseEntity<CuponResponseError>(this.getCuponError(HttpStatus.INTERNAL_SERVER_ERROR, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private List<String> calculate(Map<String, Float> itemsWithPrice, Float amount) {

		List<String> itemsResponse = new ArrayList<String>();
		Float totalExpendingLocal = FLOAT_ZERO;

		// Itero el Map y llamo a la api de items por cada producto
		for (Map.Entry<String, Float> item : itemsWithPrice.entrySet()) {
			Float priceItem = items.getItemPrice(item.getKey());
			// Si el precio del item es distinto de null y la suma entre lo gastado más el
			// precio del item
			if (priceItem != null && totalExpendingLocal + priceItem <= amount) {
				itemsResponse.add(item.getKey());
				totalExpendingLocal += priceItem;
			}
		}

		// Cuando termino de iterar seteo el total gastado
		totalExpending = totalExpendingLocal;
		return itemsResponse;
	}

	/**
	 * Se arma un custom response para devolver en la respuesta del servicio
	 * 
	 * @param status
	 * @param cuponResponse
	 * @return
	 */
	private CuponResponseError getCuponError(HttpStatus status, Cupon cuponResponse) {
		CuponResponseError error = new CuponResponseError();
		if (status.equals(HttpStatus.NOT_FOUND)) {
			error.setMessage(CuponErrorEnum.NOT_ENOUGH_QUANTITY.getMessage());
		} else if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
			error.setMessage(CuponErrorEnum.INTERNAL_SERVER_ERROR.getMessage());
		}

		error.setError(status.getReasonPhrase());
		error.setData(cuponResponse);
		error.setStatus(status.value());

		return error;
	}

	/**
	 * Recibe una lista de String y la convierte a un Map<String,Float> donde
	 * inicialmente se inicializa el monto del item en null Se utiliza stream de
	 * java 8 para poder cumplir con que solo una unidad por item Collectors.toMap
	 * recibe el keyMapper que se va a usar como key en el Map, 0 que es el precio
	 * que se le va a setear al item en este mapeo, y se pasa una función como
	 * ultimo parametro que logra mapear la lista que recibe en orden de llegada
	 * 
	 * @param itemsRequest
	 * @return Map<String,Float>
	 */
	private Map<String, Float> obtainItemsWithPrice(List<String> itemsRequest) {

		Map<String, Float> itemsWithPrice = itemsRequest.stream().distinct().collect(Collectors.toMap(
				Function.identity(), number -> FLOAT_ZERO, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		return itemsWithPrice;
	}

}
