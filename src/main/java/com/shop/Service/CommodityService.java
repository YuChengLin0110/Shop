package com.shop.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.DAO.CommodityDAO;
import com.shop.Model.CommodityBean;

@Service
public class CommodityService {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	CommodityDAO commodityDAO;
	
	public List<CommodityBean> findAll() {
		return commodityDAO.findAllCommodity();
	}
	public CommodityBean findCommodityById(Long id) {
		return commodityDAO.findCommodityById(id);
	}
	public void add(String name,
					String category,
					int price,
					int quantity,
					String detail,
					String spec,
					String image) {
		
		commodityDAO.add(name, category, price, quantity, detail, spec, image);
}
	
	public void update(String name,
			String category,
			int price,
			int quantity,
			String detail,
			String spec,
			String image,
			Long id) {
		
		commodityDAO.update(name, category, price, quantity, detail, spec, image, id);
}

}
