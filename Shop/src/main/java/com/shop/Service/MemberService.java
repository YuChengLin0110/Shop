package com.shop.Service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.DAO.MemberDAO;
import com.shop.MD5.MD5Util;
import com.shop.Model.MemberBean;

@Service
public class MemberService {

	@Autowired
	MemberDAO memberDAO;
	@Autowired
	HttpSession session;
	@Autowired
	MD5Util md5Util;

	public void login(MemberBean memberBean) {
		session.setAttribute("user", memberBean);
	}

	public void logout(HttpSession session) {
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
	}

	public boolean isLogin(HttpSession session) {
		boolean isLogin;
		if (session.getAttribute("user") != null) {
			isLogin = true;
		} else {
			isLogin = false;
		}
		return isLogin;
	}

	public void register(String account, 
						 String password,
						 String name,
						 String addr,
						 String tel,
						 String email) {
		
		if(memberDAO.findByAccount(account)==null) {
			String md5Pwd = md5Util.md5Password(account, password);
			
		memberDAO.addAccount(account, md5Pwd, name, addr, tel, email);
		}
	}
	public void update(String password,
			 		   String name,
			 		   String addr,
			 		   String tel,
			 		   String email,
			 		   String account) {

		memberDAO.update(password, name, addr, tel, email, account);
	}

	public MemberBean findByAccountAndPassword(String account, String password) {
		
		 String md5Pwd = md5Util.md5Password(account, password);
		
		return memberDAO.findByAccountAndPassword(account, md5Pwd);
	}
	public MemberBean findByAccount(String account) {
		return memberDAO.findByAccount(account);
	}
}
