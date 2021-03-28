package com.shop.Service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shop.DAO.MemberDAO;
import com.shop.Model.MemberBean;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private HttpSession session;

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
		
		if(memberDAO.findByAccount(account)!=null) {
		memberDAO.addAccount(account, password, name, addr, tel, email);
		}
	}
	public void update(String account, 
			 		   String password,
			 		   String name,
			 		   String addr,
			 		   String tel,
			 		   String email) {
		
		memberDAO.update(password, name, addr, tel, email, account);
	}

	public MemberBean findByAccountAndPassword(String account, String Passwrod) {
		return memberDAO.findByAccountAndPassword(account, Passwrod);
	}
	public MemberBean findByAccount(String account) {
		return memberDAO.findByAccount(account);
	}
}
