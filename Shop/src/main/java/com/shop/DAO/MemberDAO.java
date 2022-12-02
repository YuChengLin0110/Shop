package com.shop.DAO;

import com.shop.Model.MemberBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Query(value = "UPDATE member SET password=?1, name=?2, addr=?3, tel=?4, email=?5 WHERE account=?6",nativeQuery = true)
    void update(String password, String name,String addr,String tel,String email,String account);

}
