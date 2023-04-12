package com.shop.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.Model.MemberBean;
import com.shop.Service.MemberService;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    private MemberBean memberBean = new MemberBean();
    
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);


//	  已改用Spring Security
//    @PostMapping("/doLogin")
//    public String login(@RequestParam("valAcc") String account,
//                        @RequestParam("valPwd") String password, HttpSession session) {
//        memberBean = memberDAO.findByAccountAndPassword(account, password);
//        if (memberBean != null) {
//            session.setAttribute("id", memberBean);
//            return "redirect:/";
//        } else {
//            return "redirect:/login";
//        }
//        memberBean = memberService.findByAccountAndPassword(account, password);
//        if(memberBean != null) {
//        	//session.setAttribute("user", memberBean);
//        	memberService.login(memberBean);
//        	return "redirect:/";
//        }else {
//        	return "redirect:/login";
//        }
//    }

    @PostMapping("/register")
    public String register(@RequestParam("valUser") String account,
                           @RequestParam("valPwd") String password,
                           @RequestParam("valName") String name,
                           @RequestParam("valAddr") String addr,
                           @RequestParam("valTel") String tel,
                           @RequestParam("valEmail") String email){

        //memberDAO.addAccount(account, password, name, addr, tel, email);
    		memberService.register(account, password, name, addr, tel, email);
        return "redirect:/login";
    }

    @PostMapping("/memberUpdate")
    public String memberUpdate(@RequestParam("valUser") String account,
                               @RequestParam("valPwd") String password,
                               @RequestParam("valName") String name,
                               @RequestParam("valAddr") String addr,
                               @RequestParam("valTel") String tel,
                               @RequestParam("valEmail") String email,
                               HttpSession session){
        //memberDAO.update(password, name, addr, tel, email,account);

    	memberService.update(password, name, addr, tel, email , account);
    	
        //memberBean = memberDAO.findByAccount(account);
    	memberBean = memberService.findByAccount(account);
        session.setAttribute("user",memberBean);
        return "redirect:/member";
    }
    //Ajax用來檢查帳號是否重複
    @PostMapping("/checkAccount")
    @ResponseBody
    public boolean checkAccount(@RequestParam("account") String account){
    	boolean isExist = false;
    	//Ajax傳回來資料命名為data

    	if(memberService.findByAccount(account)!=null) {
    		isExist = true;
    	}
    	return isExist;
    }

}
