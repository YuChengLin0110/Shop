package com.shop.MD5;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
@Component
public class MD5Util {

	public String md5Password(String account,String userPwd) {
		String salt = account;
		String pwd = userPwd + salt;
		String md5Pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
		
		return md5Pwd;
	}
}
