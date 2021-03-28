package com.shop.Controller;

import com.shop.Model.MemberBean;
import com.shop.Service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    private MemberBean memberBean = new MemberBean();


    @PostMapping("/login")
    public String login(@RequestParam("valUser") String account,
                        @RequestParam("valPwd") String password, HttpSession session) {
//        memberBean = memberDAO.findByAccountAndPassword(account, password);
//        if (memberBean != null) {
//            session.setAttribute("id", memberBean);
//            return "redirect:/";
//        } else {
//            return "redirect:/login";
//        }
        memberBean = memberService.findByAccountAndPassword(account, password);
        if(memberBean != null) {
        	//session.setAttribute("user", memberBean);
        	memberService.login(memberBean);
        	return "redirect:/";
        }else {
        	return "redirect:/login";
        }
    }

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
    @PostMapping("/checkAccount")
    @ResponseBody
    public boolean checkAccount(@RequestParam("data") String data){
    	boolean isExist = false;
    	if(memberService.findByAccount(data)!=null) {
    		isExist = true;
    	}
    	return isExist;
    }

}
