package com.shop.Service;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.DAO.MemberDAO;
import com.shop.Model.MemberBean;


@Service
public class MemberService implements UserDetailsService {

	@Autowired
	MemberDAO memberDAO;
	@Autowired
	HttpSession session;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	// Spring Security用UserDetailsService
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		MemberBean memberBean = memberDAO.findByAccount(userName);
		if(memberBean==null) {
			throw new UsernameNotFoundException("未找到使用者：" + userName);
		}else {
			logger.info(memberBean.getAccount()+" "+memberBean.getPassword());
			return new User(memberBean.getAccount(),memberBean.getPassword(),Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		}
		
	}
//	已改用Spring Security
//	public void login(MemberBean memberBean) {
//		session.setAttribute("user", memberBean);
//	}

//	已改用Spring Security
//	public void logout(HttpSession session) {
//		if (session.getAttribute("user") != null) {
//			session.removeAttribute("user");
//		}
//	}

	public boolean isLogin() {
		boolean isLogin = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info(auth.toString());
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			isLogin = true;
		} else {
			isLogin = false;
		}
		return isLogin;
	}

	public void register(String account, String password, String name, String addr, String tel, String email) {

		if (memberDAO.findByAccount(account) == null) {
			//String md5Pwd = md5Util.md5Password(account, password);
			String bcryptPwd = passwordEncoder.encode(password);

			memberDAO.addAccount(account, bcryptPwd, name, addr, tel, email);
		}
	}

	public void update(String name, String addr, String tel, String email, String account) {

		memberDAO.update(name, addr, tel, email, account);
	}

	public MemberBean findByAccountAndPassword(String account, String password) {
		
		MemberBean memberBean = this.findByAccount(account);
		String bcryptPwd = passwordEncoder.encode(password);
		String encodedPassword = memberBean.getPassword();
		
		if(passwordEncoder.matches(bcryptPwd, encodedPassword)) {
			return memberBean;
		}else
		return null;
	}

	public MemberBean findByAccount(String account) {
		return memberDAO.findByAccount(account);
	}
	
	//Spring Serurity將登入後的使用者資訊存在Authentication 和 session
	public Authentication getUserAuth() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return auth;
	}
	
	public String getUserName() {
		String userName = this.getUserAuth().getName();
		
		return userName;
	}

}
