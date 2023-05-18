package com.shop.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.Model.MemberBean;

@Repository
public interface MemberDAO extends JpaRepository<MemberBean,Long> {

    @Query(value = "SELECT * FROM member WHERE account=? and password=?" ,nativeQuery = true)
    public MemberBean findByAccountAndPassword(String account, String password) ;

    @Query(value = "SELECT * FROM member WHERE account=?1" ,nativeQuery = true)
    public MemberBean findByAccount(String account);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO member (account,password,name,addr,tel,email,role) VALUES(?1,?2,?3,?4,?5,?6,'user')",nativeQuery = true)
    void addAccount(String account,String password, String name,String addr,String tel,String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE member SET name=?1, addr=?2, tel=?3, email=?4 WHERE account=?5",nativeQuery = true)
    void update(String name,String addr,String tel,String email,String account);

}
