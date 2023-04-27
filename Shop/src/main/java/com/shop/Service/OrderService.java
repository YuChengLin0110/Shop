package com.shop.Service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.DAO.CartDAO;
import com.shop.DAO.OrderDAO;
import com.shop.DAO.OrderVoMapper;
import com.shop.Model.OrderAddDataVO;
import com.shop.Model.OrderBean;
import com.shop.Model.OrderDetailVO;
import com.shop.Model.OrderVO;

@Service
public class OrderService {
	
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	OrderVoMapper orderVoMapper;

	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;
	
	public List<OrderBean> findOrderByAccount(String account) {
//		Map<String, List<OrderBean>> orderMap = new HashMap<String, List<OrderBean>>();
		
//		List<OrderBean> orderBeans = orderDAO.findOrderByAccount(account);
		
//		for(OrderBean orderBean : orderBeans) {
//			
//			
//			String key = orderBean.getOrderNumber();
//			
//
//			if(!orderMap.containsKey(key)) {
//				orderMap.put(key, new ArrayList<OrderBean>());
//			}else {
//				orderMap.get(key).add(orderBean);
//			}
//		}
		
		return orderDAO.findOrderByAccount(account);
	}
	
	public List<OrderVO> findOrderVOByAccount(String account){
		return orderVoMapper.findOrderVoByAccount(account);
	}
	
	public List<OrderDetailVO> findOrderDetailVOByOrderNumber(String account, String order_number){
		return orderVoMapper.findOrderDetailVoByOrderNumber(account, order_number);
	}
	
	public OrderVO findOrderByOrderNumber(String order_number) {
		return orderVoMapper.findOrderVoByOrderNumber(order_number);
	}
	
	public Map<String,List<OrderBean>> creatOrderMap(List<OrderBean> orderBeanList){
		Map<String, List<OrderBean>> orderMap = new HashMap<String,List<OrderBean>>();
		
		for(OrderBean orderBean : orderBeanList) {
			
			String key = orderBean.getOrderNumber();
			
			if(!orderMap.containsKey(key)) {
				orderMap.put(key, new ArrayList<OrderBean>());
			}else {
				orderMap.get(key).add(orderBean);
			}
			
		}
		
		return orderMap;
	}
	
	public void addOrder(String account,List<Long> cart_id) {
		
//		if(cartService.buy(cart_id)==true) {
//			String orderNumber = this.createOrderNumber();
//			int productPrice=0;
//			Long cid=0L;
//			List<CartBean> cartBean = new ArrayList<CartBean>();
//			List<ProductBean> productBean =  new ArrayList<ProductBean>();
//			Map<Long,Long> cartMap = new TreeMap<Long,Long>();
//			Map<Long,Integer> productMap = new TreeMap<Long,Integer>();
//			String createDate = this.createDate();
//			
//			for(Long id:cart_id) {
//				cartBean.add(cartService.findBoughtCartById(id));
//			}
//			
//			for(CartBean cartBeanList:cartBean) {
//				cartMap.put(cartBeanList.getId(), cartBeanList.getProduct_id()); 
//				
//				productBean.add(productService.findProductById(cartBeanList.getProduct_id()));
//			}
//			
//			for(ProductBean productBeanList : productBean) {
//				productMap.put(productBeanList.getId(), productBeanList.getPrice());
//			}
//			
//
//			for(Entry<Long,Long> cartEntry : cartMap.entrySet()) {
//				cid=cartEntry.getKey();
//				productPrice = productMap.get(cartEntry.getValue());
//				
//				
//				this.addOrder(orderNumber, account, productPrice, createDate, cid);
//				
//			}
//		}
		
		if(cartService.buy(cart_id)==true) {
			
			String orderNumber = this.createOrderNumber();
			String createDate = this.createDate();
			List<OrderAddDataVO> orderAddDataVO = this.prepareOrderAddDataVO(cart_id, account);
			
			for(OrderAddDataVO orderData : orderAddDataVO) {
				int price = orderData.getPrice();
				Long c_id = orderData.getCart_id();
				
				
				this.addOrder(orderNumber, account, price, createDate,c_id, 0);
			}
		}
		
		
	}
	
	public void addOrder(String orderNumber,String account,int productPrice,String createDate,Long cart_id, int status) {
		orderDAO.add(orderNumber, account, productPrice, createDate, cart_id, status);
		
	}
	
	public Map<String,OrderVO> prepareOrderVO(String account){
		Map<String,OrderVO> orderVOMap = new TreeMap<String,OrderVO>();
		
		return orderVOMap; 
	}
	
	public List<OrderAddDataVO> prepareOrderAddDataVO(List<Long> cart_id, String account){
		return orderVoMapper.prepareAddOrderData(cart_id, account);
	}
	
	public void updateOrderStatus(int rtnCode, String orderNumber) {
		orderDAO.updateOrderStatus(rtnCode, orderNumber);
	}
	
	
	public String createOrderNumber(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		String type = "OR";
		String number = sdf.format(date);
		return type+number;
	}
	
	public String createDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String createDate = sdf.format(date);
		return createDate;
	}
	
	//檢查實際商品數量與購物車商品數量
	public boolean checkQuqntity(int cartQuantity, Long product_id) {
		int productQuantity = productService.findProductById(product_id).getQuantity();
		if(productQuantity >= cartQuantity) {
			return true;
		}else {
			return false;
		}
	}
	

}
