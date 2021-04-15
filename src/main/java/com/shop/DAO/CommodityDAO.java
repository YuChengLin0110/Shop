package com.shop.DAO;

import com.shop.Model.CommodityBean;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommodityDAO extends JpaRepository<CommodityBean,Long> {

    @Query(value = "SELECT * FROM commodity WHERE id=?",nativeQuery = true)
    public CommodityBean findCommodityById(Long id);

    @Query(value = "SELECT * FROM commodity",nativeQuery = true)
    public List<CommodityBean> findAllCommodity();

    @Modifying
    @Transactional
    @Query(value = "UPDATE commodity SET name=?, category=?, price=?, quantity=?, detail=?, spec=?, image=? WHERE id=?",nativeQuery = true)
    void update(String name,String category,int price, int quantity,String detail,String spec,String image,Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO commodity(name,category,price,quantity,detail,spec,image) VALUES(?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    void add(String name,String category,int price, int quantity,String detail,String spec,String image);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM commodity WHERE id=?)",nativeQuery = true)
    void delete(int id);





}
