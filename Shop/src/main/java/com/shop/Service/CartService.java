package com.shop.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.DAO.CartDAO;
import com.shop.DAO.CartVoMapper;
import com.shop.Model.CartBean;
import com.shop.Model.CartVO;

@Service
public class CartService {

	@Autowired 
	CartDAO cartDAO;
	@Autowired
	CartVoMapper cartVoMapper;
	@Autowired 
	ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(CartService.class);
	
	public CartBean findCartById(Long id) {
		
		return cartDAO.findCartById(id);
	}
	
	public CartBean findBoughtCartById(Long id) {
		
		return cartDAO.findBoughtCartById(id);
	}
	
	public List<CartBean> findCartById(List<Long> id) {
		
		return cartDAO.findCartById(id);
	}
	
	public List<CartBean> findBoughtCartById(List<Long> id) {
		
		return cartDAO.findBoughtCartById(id);
	}
	
	public List<CartBean> findCartByAccount(String account) {
		
		return cartDAO.findCartByAccount(account);
	}
	
	public List<CartVO> findCartVOByAccount(String account) {
		
		//List<CartVO> cartBean = cartDAO.findCartVOByAccount(account);
		List<CartVO> cartBean = cartVoMapper.findcartVoByAccount(account);
				
		return cartBean;
	}
	
//	public List<CartVO> findCartVO(List<CartBean> cartBean){
//		
//		List<CartVO> cartVOList = new ArrayList<CartVO>();
//
//		for(CartBean cartList : cartBean) {
//			CartVO cartVO = new CartVO();
//			ProductBean productBean = productService.findProductById(cartList.getProduct_id());
//			if(productBean != null) {
//				cartVO.setName(productBean.getName());
//				cartVO.setSpec(productBean.getSpec());
//				cartVO.setPrice(productBean.getPrice());
//				cartVO.setImage(productBean.getImage());
//				cartVO.setCart_id(cartList.getId());
//				cartVO.setCart_Quantity(cartList.getQuantity());
//
//				cartVOList.add(cartVO);
//			}else {
//				continue;
//			}
//		}
//		
//		return cartVOList ;
//	}
	
	public boolean add(String account,
						Long product_id,
						int quantity) {
		
		
		CartBean cartBean = cartDAO.findCartByProductId(product_id);
		if(cartBean!=null && this.checkQuantity(quantity, product_id)==true) {
			quantity = cartBean.getQuantity()+quantity;
			cartDAO.update(quantity, product_id);
			
			return true;
			
		}else if(cartBean==null && this.checkQuantity(quantity, product_id)==true){
			
			cartDAO.add(account, product_id, quantity);
			return true;
		
		}else {	
			return false;
		}
	}
	
	public void update(int quantity,
					   Long id) {
		
		cartDAO.update(quantity, id);
		
	}
	
	public void delete(List<Long> id) {
		
		cartDAO.delete(id);
	}
	 
	public boolean buy(List<Long> id) {
		
		for(Long cid:id) {
			CartBean cartBean = new CartBean();
			cartBean = this.findCartById(cid);
			Long pid = cartBean.getProduct_id();
			int cartQuantity = cartBean.getQuantity();
			
			if(this.checkQuantity(cartQuantity, pid)==true) {
				int productQuantity = productService.findProductById(pid).getQuantity();
				productService.productBuy(productQuantity - cartQuantity,pid);
				cartDAO.buy(cid);
				
			}else {
				return false;
				
			}
			
		}
		return true;
	}
	
	//檢查實際商品數量與購物車商品數量
	public boolean checkQuantity(int cartQuantity, Long product_id) {
		int productQuantity = productService.findProductById(product_id).getQuantity();
		if(productQuantity >= cartQuantity) {
			return true;
		}else {
			return false;
		}
	}
}
