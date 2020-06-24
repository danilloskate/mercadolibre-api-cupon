package com.mercadolibre.cupon;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mercadolibre.cupon.items.IItems;


@SpringBootTest
public class ItemsTest {
	
	@Autowired
	private IItems items;

	/**
	 * Se testea que se obtenga bien el precio del item
	 */
	@Test
	public void getItemPriceOK() {
		String itemID = "MLA604171651";
		Float priceResult = items.getItemPrice(itemID);
		
		Assert.assertNotEquals(null, priceResult);
	}
	
	/**
	 * Se testea el flujo de que la API de items no encuentre el itemID o que falle la llamada
	 */
	@Test
	public void getItemPriceNotFound() {
		String itemID = "MLA1";
		Float priceResult = items.getItemPrice(itemID);
		
		Assert.assertEquals(null, priceResult);
	}
	
	/**
	 * Se testea el flujo de que la API de items reciba un itemID null
	 */
	@Test
	public void getItemPriceServerError() {
		String itemID = "";
		Float priceResult = items.getItemPrice(itemID);
		
		Assert.assertEquals(null, priceResult);
	}


}
