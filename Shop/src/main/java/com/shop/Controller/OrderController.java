package com.shop.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.Model.MemberBean;
import com.shop.Service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/orderAdd")
	public String orderAdd(@RequestParam("valCartId") List<Long> cart_id, HttpSession session) {
		
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		String account = memberBean.getAccount();
		
		orderService.addOrder(account, cart_id);
		
		return "redirect:/order";
		
	}

}
