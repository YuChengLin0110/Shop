package com.shop.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.Model.ProductBean;

@Repository
public interface ProductDAO extends JpaRepository<ProductBean,Long> {

    @Query(value = "SELECT * FROM product WHERE id=? AND removed = 0",nativeQuery = true)
    public ProductBean findProductById(Long id);
    
    @Query(value = "SELECT * FROM product WHERE id IN:id AND removed = 0",nativeQuery = true)
    public List<ProductBean> findProductById(@Param("id") List<Long> id);

    @Query(value = "SELECT * FROM product WHERE removed = 0",nativeQuery = true)
    public List<ProductBean> findAllproduct();

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET name=?, category=?, price=?, quantity=?, detail=?, spec=?, image=? WHERE id=?",nativeQuery = true)
    void update(String name,String category,int price, int quantity,String detail,String spec,String image,Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO product(name,category,price,quantity,detail,spec,image,removed) VALUES(?1,?2,?3,?4,?5,?6,?7,0)",nativeQuery = true)
    void add(String name,String category,int price, int quantity,String detail,String spec,String image);

//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM product WHERE id=?",nativeQuery = true)
//    void delete(Long id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET removed=1 WHERE id=?",nativeQuery = true)
    void delete(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET quantity=? WHERE id=?",nativeQuery = true)
    void productBuy(int quantity, Long id);



}
