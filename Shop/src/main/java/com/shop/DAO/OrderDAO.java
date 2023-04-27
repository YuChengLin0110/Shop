package com.shop.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.shop.Model.OrderAddDataVO;
import com.shop.Model.OrderBean;
import com.shop.Model.OrderDetailVO;
import com.shop.Model.OrderVO;

public interface OrderDAO extends JpaRepository<OrderBean, Long>{
	//order為MySQL關鍵字 需做更改
	
	@Query(value = "SELECT * FROM c_order WHERE account=?1", nativeQuery = true)
	public List<OrderBean> findOrderByAccount(String account);
	
	@Query(value = "SELECT * FROM c_order WHERE order_number = ?1", nativeQuery = true)
		public OrderBean findOrderByOrderNumber(String orderNumber);
	
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO c_order(order_Number,account,product_price,create_date,cart_id,status) VALUES(?1,?2,?3,?4,?5,0)", nativeQuery = true)	
	void add(String orderNumber,String account,int productPrice,String createDate,Long cart_id,int status);


//提出寫在OrderVoMapper 改為使用MyBatis
//	@Query(value = "SELECT NEW com.shop.Model.OrderVO(o.order_number AS order_number,SUM((o.product_price)*c.quantity) AS price, o.create_date AS create_date, o.status AS status) "
//				+ "FROM c_order AS o JOIN cart AS c ON o.cart_id = c.id JOIN product AS p ON c.product_id = p.id "
//				+ "WHERE o.account=?1 GROUP BY o.order_number", nativeQuery = false)
//	public List<OrderVO> findOrderVOByAccount(String account);
//	
//
//	@Query(value = "SELECT NEW com.shop.Model.OrderVO(o.order_number AS order_number,SUM((o.product_price)*c.quantity) AS price, o.create_date AS create_date, o.status AS status) "
//			+ "FROM c_order AS o JOIN cart AS c ON o.cart_id = c.id JOIN product AS p ON c.product_id = p.id "
//			+ "WHERE o.order_number=?1 GROUP BY o.order_number", nativeQuery = false)
//	public OrderVO findOrderVOByOrderNumber(String orderNumber);
//
//
//	@Query(value = "SELECT NEW com.shop.Model.OrderDetailVO(o.order_number AS order_number,p.name AS name, p.image AS image, o.product_price AS price, c.quantity AS quantity, o.create_date AS create_date, o.status AS status) "
//			+ "FROM c_order AS o JOIN cart AS c ON o.cart_id = c.id JOIN product AS p ON c.product_id = p.id "
//			+ "WHERE o.account = ?1 AND o.order_number = ?2",nativeQuery = false)
//	public List<OrderDetailVO> findOrderDetailVOByOrderNumber(String account, String order_number);
//
//
//	
//	@Query(value = "SELECT NEW com.shop.Model.OrderAddDataVO(c.id AS cart_id, p.price AS price) "
//			+ "FROM cart AS c JOIN product AS p ON c.product_id = p.id "
//			+ "WHERE c.id IN :cart_id AND c.account = :account AND c.bought = 1", nativeQuery = false)
//	public List<OrderAddDataVO>prepareAddOrderData(@Param("cart_id")List<Long> cart_id, String account);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE c_order SET status = ?1 WHERE order_number = ?2")
	void updateOrderStatus(int rtnCode, String orderNumber);
	
}
