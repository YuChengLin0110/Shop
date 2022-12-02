package com.shop.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.DAO.ProductDAO;
import com.shop.Model.ProductBean;

@Service
public class ProductService {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ProductDAO productDAO;
	
	public List<ProductBean> findAll() {
		return productDAO.findAllproduct();
	}
	public ProductBean findProductById(Long id) {
		return productDAO.findProductById(id);
	}
	public List<ProductBean> findProductById(List<Long> id) {
		return productDAO.findProductById(id);
	}
	public void add(String name,
					String category,
					int price,
					int quantity,
					String detail,
					String spec,
					String image) {
		
		productDAO.add(name, category, price, quantity, detail, spec, image);
}
	
	public void update(String name,
			String category,
			int price,
			int quantity,
			String detail,
			String spec,
			String image,
			Long id) {
		
		productDAO.update(name, category, price, quantity, detail, spec, image, id);
}
	
	public void delete(Long id) {
		
		productDAO.delete(id);
	}
	
	public void productBuy(int quantity, Long id) {
		
		productDAO.productBuy(quantity,id);
	}

}
