package com.shop.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.shop.Model.OrderAddDataVO;
import com.shop.Model.OrderDetailVO;
import com.shop.Model.OrderVO;

//MyBatis
public interface OrderVoMapper {

	@Select("SELECT o.order_number AS order_number,SUM((o.product_price)*c.quantity) AS price, o.create_date AS create_date, o.status AS status "
			+ "FROM c_order AS o JOIN cart AS c ON o.cart_id = c.id JOIN product AS p ON c.product_id = p.id "
			+ "WHERE o.account=#{account} GROUP BY o.order_number")
	public List<OrderVO> findOrderVoByAccount(String account);
	
	@Select("SELECT o.order_number AS order_number,SUM((o.product_price)*c.quantity) AS price, o.create_date AS create_date, o.status AS status "
			+ "FROM c_order AS o JOIN cart AS c ON o.cart_id = c.id JOIN product AS p ON c.product_id = p.id "
			+ "WHERE o.order_number=#{orderNumber} GROUP BY o.order_number")
	public OrderVO findOrderVoByOrderNumber(String orderNumber);
	
	@Select("SELECT o.order_number AS order_number,p.name AS name, p.image AS image, o.product_price AS price, c.quantity AS quantity, o.create_date AS create_date, o.status AS status "
			+ "FROM c_order AS o JOIN cart AS c ON o.cart_id = c.id JOIN product AS p ON c.product_id = p.id "
			+ "WHERE o.account = #{account} AND o.order_number = #{orderNumber}")
	public List<OrderDetailVO> findOrderDetailVoByOrderNumber(String account, String orderNumber);
	
	@Select("SELECT c.id AS cart_id, p.price AS price "
			+ "FROM cart AS c JOIN product AS p ON c.product_id = p.id "
			+ "WHERE c.id IN #{cart_id} AND c.account = #{account} AND c.bought = 1")
	public List<OrderAddDataVO>prepareAddOrderData(@Param("cart_id")List<Long> cart_id, String account);
}
