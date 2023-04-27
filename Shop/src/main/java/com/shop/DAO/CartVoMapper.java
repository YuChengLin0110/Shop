package com.shop.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shop.Model.CartVO;

//@MyBatis
public interface CartVoMapper {
	
	@Select("SELECT c.id AS cart_id, p.image AS image, p.name AS name, p.spec AS spec, p.price AS price, c.quantity AS quantity "
			+ "FROM product AS p JOIN cart AS c ON p.id = c.product_id "
			+ "WHERE account=#{account} AND bought = 0")
	public List<CartVO> findcartVoByAccount(String account);
}
