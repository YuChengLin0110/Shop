package com.shop.Model;

public class CartVO {

	private Long cart_id;

	private String image, name, spec;

	private int price, cart_Quantity;

	public CartVO(Long cart_id, String image, String name, String spec, int price, int quantity) {
		this.cart_id = cart_id;
		this.image = image;
		this.name = name;
		this.spec = spec;
		this.price = price;
		this.cart_Quantity = quantity;

	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCart_Quantity() {
		return cart_Quantity;
	}

	public void setCart_Quantity(int cart_Quantity) {
		this.cart_Quantity = cart_Quantity;
	}

	public Long getCart_id() {
		return cart_id;
	}

	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}

}