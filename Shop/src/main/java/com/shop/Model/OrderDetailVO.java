package com.shop.Model;

public class OrderDetailVO {
	
	private String order_number;
	private String name; 
	private String image;
	private int price; 
	private int quantity;
	private String create_date;
	private int status;
	
	public OrderDetailVO(String order_number,String name, String image, int price, int quantity, String create_date, int status) {
		this.order_number = order_number;
		this.name = name;
		this.image = image;
		this.price = price;
		this.quantity = quantity;
		this.create_date = create_date;
		this.status = status;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
