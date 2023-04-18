package com.shop.Controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.Model.CartVO;
import com.shop.Model.MemberBean;
import com.shop.Model.OrderDetailVO;
import com.shop.Model.OrderVO;
import com.shop.Model.ProductBean;
import com.shop.Service.CartService;
import com.shop.Service.MemberService;
import com.shop.Service.OrderService;
import com.shop.Service.ProductService;


@Controller
public class WebController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@RequestMapping("/")
	public String index() {
//		boolean isLogin;
//		if (session.getAttribute("user") != null) {
//			isLogin = true;
//		} else {
//			isLogin = false;
//		}
		logger.info("登入"+memberService.isLogin());
		return "index";
	}

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}


//	@GetMapping("/logout")
//	public String logout() {
//		
//		改用Spring Security
//		
//		if (session.getAttribute("user") != null) {
//			session.removeAttribute("user");
//		}
//		改用Spring Security
//		memberService.logout(session);
//		return "logout";
//	}

	@GetMapping("/member")
	public String member(Model model) {
		
		String userName = memberService.getUserName();
		MemberBean detail = memberService.findByAccount(userName);
		model.addAttribute("detail", detail);
		return "member";
	}

	@GetMapping("/memberUpdate")
	public String memberUpdate(Model model) {
		String userName = memberService.getUserName();
		MemberBean detail = memberService.findByAccount(userName);
		model.addAttribute("detail", detail);
		return "memberUpdate";
	}

	@GetMapping("/product")
	public String product(Model model) {
		List<ProductBean> allProduct = productService.findAll();
		model.addAttribute("allProduct", allProduct);

		return "product";
	}

	@GetMapping("/productDetail/{id}")
	public String productDetail(@PathVariable Long id,Model model) {
		ProductBean productBean = productService.findProductById(id);
		model.addAttribute("product", productBean);
		String userName = memberService.getUserName();
		
		model.addAttribute("account",userName);

		return "productDetail";
	}

	@GetMapping("/productAdd")
	public String productAdd(HttpSession session) {

		return "productAdd";
	}

	@GetMapping("/productUpdate/{id}")
	public String productUpdate(@PathVariable Long id,HttpSession session, Model model) {
		ProductBean productBean = productService.findProductById(id);
		model.addAttribute("product", productBean);

		return "productUpdate";
	}

	@GetMapping("/productAdmin")
	public String productAdmin(HttpSession session, Model model) {
		List<ProductBean> productBean = productService.findAll();
		model.addAttribute("allProduct", productBean);

		return "productAdmin";
	}
	
	@GetMapping("/cart")
	public String cart(HttpSession session, Model model) {
//		MemberBean memberBean =  (MemberBean) session.getAttribute("user");
//		String account = memberBean.getAccount();
		
//		List<CartBean> cartBean = cartService.findCartByAccount(account);
//		
//		List<CartVO> cartVO = cartService.findCartVO(cartBean);
//		
//		model.addAttribute("cartBean",cartBean);
//		model.addAttribute("cartVO",cartVO);
//		model.addAttribute("isLogin", memberService.isLogin(session));
		String userName = memberService.getUserName();
		
		List<CartVO> cartVO = cartService.findCartVOByAccount(userName);
		model.addAttribute("cartVO",cartVO);

		return "cart";
	}
	
	@GetMapping("/order")
	public String order(HttpSession session, Model model) {
		//MemberBean memberBean = (MemberBean) session.getAttribute("user");
		String userName = memberService.getUserName();
//		List<OrderBean> orderBeanList = orderService.findOrderByAccount(account);
//		Map<String, List<OrderBean>> orderMap = orderService.creatOrderMap(orderBeanList);
//		
//		model.addAttribute("orderMap",orderMap);
		List<OrderVO> orderVO = orderService.findOrderVOByAccount(userName);
		model.addAttribute("orderVO",orderVO);
		
		return "order";
	}
	
	@GetMapping("/orderDetail/{order_number}")
	public String orderDetail(@PathVariable String order_number, Model model) {
		String userName = memberService.getUserName();
		List<OrderDetailVO> orderDetailVO = orderService.findOrderDetailVOByOrderNumber(userName, order_number);
		model.addAttribute("orderDetailVO",orderDetailVO);
		
		return "orderDetail";
	}
	
	
	@ModelAttribute("isLogin")
	public boolean isLogin() {
		boolean isLogin = memberService.isLogin();
		
		return isLogin;
	}
	
	//設定所有頁面的title變數
	//以用於左側menu進行邏輯判斷
	//menu.html replace至每個html
	@ModelAttribute("pageTitle")
	public String setTitle(HttpServletRequest request) {
		String path = request.getRequestURI();
		if(path.equals("/cart")) {
			return "購物車";
		}else if(path.startsWith("/cartUpdate")) {
			return "更新購物車";
		}else if(path.startsWith("/login")) {
			return "會員登入";
		}else if(path.startsWith("/logout")) {
			return "登出";
		}else if(path.equals("/member")) {
			return "會員資料";
		}else if(path.startsWith("/memberUpdate")) {
			return "會員資料更新";
		}else if(path.equals("/order")) {
			return "訂單";
		}else if(path.startsWith("/orderDetail")) {
			return "訂單詳情";
		}else if(path.equals("/product")) {
			return "瀏覽商品";
		}else if(path.equals("/productAdd")) {
			return "新增商品";
		}else if(path.equals("/productAdmin")) {
			return "商品管理";
		}else if(path.startsWith("/productDetail")) {
			return "商品詳情";
		}else if(path.startsWith("/productUpdate")) {
			return "商品更新";
		}else if(path.startsWith("/register")) {
			return "會員註冊";
		}else{
			return "Shop";
		}
		
	}
	
}
