package com.shop.Controller;

import com.shop.Model.MemberBean;
import com.shop.Service.CommodityService;
import com.shop.Service.MemberService;
import com.shop.Model.CommodityBean;
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
	private CommodityService commodityService;


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

	@GetMapping("/commodity")
	public String commodity(HttpSession session,Model model) {
		List<CommodityBean> allCommodity = commodityService.findAll();
		model.addAttribute("allCommodity", allCommodity);
		model.addAttribute("isLogin", memberService.isLogin(session));

		return "commodity";
	}

	@GetMapping("/commodityDetail/{id}")
	public String commodityDetail(@PathVariable Long id,HttpSession session, Model model) {
		CommodityBean commodityBean = commodityService.findCommodityById(id);
		model.addAttribute("commodity", commodityBean);
		model.addAttribute("isLogin", memberService.isLogin(session));

		return "commodityDetail";
	}

	@GetMapping("/commodityAdd")
	public String commodityAdd() {
		return "commodityAdd";
	}

	@GetMapping("/commodityUpdate/{id}")
	public String commodityUpdate(@PathVariable Long id, Model model) {
		CommodityBean commodityBean = commodityService.findCommodityById(id);
		model.addAttribute("commodity", commodityBean);

		return "commodityUpdate";
	}

	@GetMapping("/commodityAdmin")
	public String commodityAdmin(Model model) {
		List<CommodityBean> allCommodity = commodityService.findAll();
		model.addAttribute("allCommodity", allCommodity);

		return "commodityAdmin";
	}

}
