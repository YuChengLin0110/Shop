package com.shop.Controller;

import com.shop.Service.ProductService;
import com.shop.Service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileService fileService;


    @PostMapping("/productAdd")
    public String productAdd(@RequestParam("valName") String name,
                               @RequestParam("valCategory") String category,
                               @RequestParam("valPrice") int price,
                               @RequestParam("valQuantity") int quantity,
                               @RequestParam("valDetail") String detail,
                               @RequestParam("valSpec") String spec,
                               @RequestParam("valImage") MultipartFile file)  {

//        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
//        try {
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get("C:\\Users\\aa468\\Documents\\GitHub\\Shop\\Shop\\src\\main\\resources\\static\\images\\" + fileName);
//            Files.write(path, bytes);
//
//            String image = "/images/"+fileName;
//            productService.add(name,category,price,quantity,detail,spec,image);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    	String image = fileService.fileUpdate(file);
    	productService.add(name,category,price,quantity,detail,spec,image);
    	
        return "redirect:/product";
    }

    @PostMapping("/productUpdate/{id}")
    public String productUpdate(@PathVariable Long id,
                                  @RequestParam("valName") String name,
                                  @RequestParam("valCategory") String category,
                                  @RequestParam("valPrice") int price,
                                  @RequestParam("valQuantity") int quantity,
                                  @RequestParam("valDetail") String detail,
                                  @RequestParam("valSpec") String spec,
                                  @RequestParam("valImage") MultipartFile file)  {
//        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
//        String image="";
//        try {
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get("\"C:\\Users\\aa468\\Documents\\GitHub\\Shop\\Shop\\src\\main\\resources\\static\\images\\" + fileName);
//            Files.write(path, bytes);
//            image = "/images/"+fileName;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    	String image = fileService.fileUpdate(file);
        productService.update(name,category,price,quantity,detail,spec,image,id);
        
        return "redirect:/product";
    }
    
    @RequestMapping("/productDelete/{id}")
    public String productDelete(@PathVariable Long id) {
    	
    	productService.delete(id);
    	
    	return "redirect:/productAdmin";
    }

}
