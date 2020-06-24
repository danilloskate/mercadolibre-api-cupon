package com.mercadolibre.cupon.items;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class Items implements IItems {

	//URI de la api de items
	private static final String URI_ITEMS = "https://api.mercadolibre.com/items/";
	//LOGGER de la clase
	private static final Logger LOG = LoggerFactory.getLogger(Items.class);
	
	/**
	 * Método que obtiene el precio de un producto según su itemID correspondiente
	 */
	@Override
	public Float getItemPrice(String itemID) {
		//Le concateneo a la uri el item que quiero obtener el precio
		String url = URI_ITEMS + itemID;
	
		ResponseEntity<String> response  = null;
		Integer priceInt = null;
		
		//Realizo la API y obtengo de ese objeto el tag price
		try {
			response = new RestTemplate().getForEntity(url, String.class);
			JSONObject itemData = new JSONObject(response.getBody());
			priceInt = (Integer) itemData.get("price");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	    
		//Si pudo obtener el precio del producto devuelvo su valor en formato float, sino devuelvo nulo
		return (priceInt != null)? priceInt.floatValue():null;
	}
}
