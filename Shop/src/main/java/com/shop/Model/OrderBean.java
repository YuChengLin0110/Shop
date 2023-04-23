package com.shop.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "c_order")
@Table(name = "c_order") //order為MySQL關鍵字 需加``包起來或改名
public class OrderBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String order_number;
	
	private String account;
	
	private int product_price;

	private Long cart_id;
	
	private String create_date;
	
	private int status;



	public String getOrderNumber() {
		return order_number;
	}

	public void setOrderNumber(String orderNumber) {
		this.order_number = orderNumber;
	}

	public String getCreatDate() {
		return create_date;
	}

	public void setCreatDate(String create_date) {
		this.create_date = create_date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public Long getCart_id() {
		return cart_id;
	}

	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
