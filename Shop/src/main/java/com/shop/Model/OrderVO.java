package com.shop.Model;


public class OrderVO {

	private String order_number;
	// sum要用Long去接收
	private Long price;
	private String create_date;
	
	public OrderVO(String order_number, Long price, String create_date) {
		this.order_number = order_number;
		this.price = price;
		this.create_date = create_date;
	}
	
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	
}
