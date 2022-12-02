package com.shop.Model;

public class OrderAddDataVO {
	
	private Long cart_id;
	private int price;
	
	public OrderAddDataVO(Long cart_id, int price) {
		this.cart_id = cart_id;
		this.price = price;
	}
	
	public Long getCart_id() {
		return cart_id;
	}
	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
}
