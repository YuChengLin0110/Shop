package com.shop.Model;

import javax.persistence.*;

@Entity
@Table(name = "Cart")
public class CartBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	private String member_Account;
	
	private Long commodity_id;
	
	private int quantity;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getMember_Account() {
		return member_Account;
	}

	public void setMember_Account(String member_Account) {
		this.member_Account = member_Account;
	}

	public Long getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(Long commodity_id) {
		this.commodity_id = commodity_id;
	}


}
