package com.shop.MD5Util;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

//已改為使用Spring Security管理，加密法改為BCrypt
@Component
public class MD5Util {

	public String md5Password(String account,String userPwd) {
		String salt = account;
		String pwd = userPwd + salt;
		String md5Pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
		
		return md5Pwd;
	}
}
