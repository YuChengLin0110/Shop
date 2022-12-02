package com.shop.Controller;

import com.shop.Model.MemberBean;
import com.shop.Model.OrderDetailVO;
import com.shop.Model.OrderVO;
import com.shop.Service.CartService;
import com.shop.Service.ProductService;
import com.shop.Service.MemberService;
import com.shop.Service.OrderService;
import com.shop.Model.CartVO;
import com.shop.Model.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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


	@RequestMapping("/")
	public String index(HttpSession session, Model model) {
//		boolean isLogin;
//		if (session.getAttribute("user") != null) {
//			isLogin = true;
//		} else {
//			isLogin = false;
//		}

		
		model.addAttribute("isLogin", memberService.isLogin(session));
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

	@GetMapping("/logout")
	public String logout(HttpSession session) {
//		if (session.getAttribute("user") != null) {
//			session.removeAttribute("user");
//		}
		memberService.logout(session);
		return "logout";
	}

	@GetMapping("/member")
	public String member(HttpSession session, Model model) {
		//Login的Service有setAttribute取名為user
		MemberBean detail = (MemberBean) session.getAttribute("user");
		model.addAttribute("detail", detail);
		return "member";
	}

	@GetMapping("/memberUpdate")
	public String memberUpdate(HttpSession session, Model model) {
		MemberBean detail = (MemberBean) session.getAttribute("user");
		model.addAttribute("detail", detail);
		return "memberUpdate";
	}

	@GetMapping("/product")
	public String product(HttpSession session,Model model) {
		List<ProductBean> allProduct = productService.findAll();
		model.addAttribute("allProduct", allProduct);
		model.addAttribute("isLogin", memberService.isLogin(session));

		return "product";
	}

	@GetMapping("/productDetail/{id}")
	public String productDetail(@PathVariable Long id,HttpSession session, Model model) {
		ProductBean productBean = productService.findProductById(id);
		model.addAttribute("product", productBean);
		model.addAttribute("isLogin", memberService.isLogin(session));
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		model.addAttribute("account",memberBean.getAccount());

		return "productDetail";
	}

	@GetMapping("/productAdd")
	public String productAdd(HttpSession session, Model model) {
		model.addAttribute("isLogin", memberService.isLogin(session));
		return "productAdd";
	}

	@GetMapping("/productUpdate/{id}")
	public String productUpdate(@PathVariable Long id,HttpSession session, Model model) {
		ProductBean productBean = productService.findProductById(id);
		model.addAttribute("product", productBean);
		model.addAttribute("isLogin", memberService.isLogin(session));

		return "productUpdate";
	}

	@GetMapping("/productAdmin")
	public String productAdmin(HttpSession session, Model model) {
		List<ProductBean> productBean = productService.findAll();
		model.addAttribute("allProduct", productBean);
		model.addAttribute("isLogin", memberService.isLogin(session));

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
		String account = this.getAccount(session);
		
		List<CartVO> cartVO = cartService.findCartVOByAccount(account);
		model.addAttribute("cartVO",cartVO);
		model.addAttribute("isLogin", memberService.isLogin(session));

		return "cart";
	}
	
	@GetMapping("/order")
	public String order(HttpSession session, Model model) {
		//MemberBean memberBean = (MemberBean) session.getAttribute("user");
		String account = this.getAccount(session);
//		List<OrderBean> orderBeanList = orderService.findOrderByAccount(account);
//		Map<String, List<OrderBean>> orderMap = orderService.creatOrderMap(orderBeanList);
//		
//		model.addAttribute("orderMap",orderMap);
		List<OrderVO> orderVO = orderService.findOrderVOByAccount(account);
		model.addAttribute("orderVO",orderVO);
		model.addAttribute("isLogin",memberService.isLogin(session));
		
		return "order";
	}
	
	@GetMapping("/orderDetail/{order_number}")
	public String orderDetail(@PathVariable String order_number, HttpSession session, Model model) {
		String account = this.getAccount(session);
		List<OrderDetailVO> orderDetailVO = orderService.findOrderDetailVOByOrderNumber(account, order_number);
		model.addAttribute("orderDetailVO",orderDetailVO);
		model.addAttribute("isLogin",memberService.isLogin(session));
		
		return "orderDetail";
	}
	
	public String getAccount(HttpSession session) {
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		String account = memberBean.getAccount();
		return account;
	}
}
