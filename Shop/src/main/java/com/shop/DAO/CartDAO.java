package com.shop.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.shop.Model.CartBean;
import com.shop.Model.CartVO;

public interface CartDAO extends JpaRepository<CartBean, Long>{
	
    @Query(value = "SELECT * FROM cart WHERE id=? AND bought = 0",nativeQuery = true)
    public CartBean findCartById(Long id);
    
    @Query(value = "SELECT * FROM cart WHERE id IN:id AND bought = 0",nativeQuery = true)
    public List<CartBean> findCartById(@Param("id") List<Long> id);
    
    @Query(value = "SELECT * FROM cart WHERE id=? AND bought = 1",nativeQuery = true)
    public CartBean findBoughtCartById(Long id);
    
    @Query(value = "SELECT * FROM cart WHERE id IN:id AND bought = 1",nativeQuery = true)
    public List<CartBean> findBoughtCartById(@Param("id") List<Long> id);
    

    @Query(value = "SELECT * FROM cart WHERE account=? AND bought = 0",nativeQuery = true)
    public List<CartBean> findCartByAccount(String account);
    
    @Query(value = " SELECT new com.shop.Model.CartVO(c.id AS cart_id, p.image AS image, p.name AS name, p.spec AS spec, p.price AS price, c.quantity AS quantity )"
    			 + " FROM product AS p JOIN cart AS c ON p.id = c.product_id "
    			 + " WHERE account=?1 AND bought = 0 ",nativeQuery = false)
    public List<CartVO> findCartVOByAccount(String account);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cart SET quantity=? WHERE product_id=? AND bought = 0",nativeQuery = true)
    void update(int quantity , Long product_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cart(account,product_id,quantity,bought) VALUES(?1,?2,?3,0)",nativeQuery = true)
    void add(String account,Long product_id , int quantity);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart WHERE id IN :id",nativeQuery = true)
    void delete(@Param("id") List<Long> id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE cart SET bought =1 WHERE id IN :id" ,nativeQuery = true)
    void buy(@Param("id") List<Long> id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE cart SET bought =1 WHERE id=?" ,nativeQuery = true)
    void buy(@Param("id") Long id);
    
    @Query(value = "SELECT * FROM cart WHERE product_id=? AND bought=0",nativeQuery = true)
    public CartBean findCartByProductId(Long product_id);
    
}

