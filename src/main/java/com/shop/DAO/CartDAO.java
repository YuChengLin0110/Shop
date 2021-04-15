package com.shop.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.shop.Model.CartBean;

public interface CartDAO extends JpaRepository<CartBean, Long>{
	
    @Query(value = "SELECT * FROM Cart WHERE id=?",nativeQuery = true)
    public CartBean findCartById(int id);

    @Query(value = "SELECT * FROM Cart WHERE member_Account=?1",nativeQuery = true)
    public CartBean findCartByAccount(String account);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Cart SET member_Account=? commodity_id=? quantity=?",nativeQuery = true)
    void update(String account,Long commodity_id , int quantity);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Cart(member_Account,commodity_id,quantity) VALUES(?1,?2,?3)",nativeQuery = true)
    void add(String account,Long commodity_id , int quantity);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Cart WHERE id=?)",nativeQuery = true)
    void delete(int id);

}
