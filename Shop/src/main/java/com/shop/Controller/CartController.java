package com.shop.Controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.Service.CartService;
import com.shop.Service.MemberService;
import com.shop.Service.ProductService;

@Controller
public class CartController {

	@Autowired CartService cartService;
	@Autowired ProductService productService;
	@Autowired MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	//Ajax 加入購物車
	@PostMapping("/cartAdd")
	@ResponseBody
	public boolean add(@RequestParam("pid") Long pid,
					@RequestParam("account") String account,
					@RequestParam("count") int count) {
		
		//MemberBean memberBean = (MemberBean) session.getAttribute("user");
		//String account = memberBean.getAccount();
		boolean flag = false;
		if(cartService.add(account, pid, count)==true) {
			flag = true;
			return flag;
		}else {
			flag = false;
			return flag;
		}
		
	}
	
	@PostMapping("/cartUpdate/{id}")
	public String update(@RequestParam int quantity,
			   			 @PathVariable Long id) {
		
		cartService.update(quantity, id);
		
		return "redirect:/cart";
		
	}
	
	@PostMapping("/cartBuy")
	public String buy( @RequestParam("valCartId") List<Long> cart_id) {
		
			if(cartService.buy(cart_id)==true) {
				return "redirect:/cart";
			}else
				return "redirect:/cart";
	}
	
	@PostMapping("/cartDelete")
	public String delete(@RequestParam("valCartId") List<Long> cart_id) {

			cartService.delete(cart_id);
		
		return "redirect:/cart";
	}
}
