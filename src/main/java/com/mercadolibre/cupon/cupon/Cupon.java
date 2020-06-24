package com.mercadolibre.cupon.cupon;

import java.util.List;

public class Cupon{
	
	private List<String> item_ids;
	private Float price;
	
	public Cupon() {
	}
	
	public Cupon(List<String> item_ids, Float amount) {
		this.item_ids = item_ids;
		this.price = amount;
	}
	
	public List<String> getItem_ids() {
		return item_ids;
	}
	public void setItem_ids(List<String> item_ids) {
		this.item_ids = item_ids;
	}
	
	public Float getAmount() {
		return price;
	}
	public void setAmount(Float amount) {
		this.price = amount;
	}
	

}
