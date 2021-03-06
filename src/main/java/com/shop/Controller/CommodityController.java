package com.shop.Controller;

import com.shop.Service.CommodityService;
import com.shop.Service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class CommodityController {

	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private FileService fileService;


    @PostMapping("/commodityAdd")
    public String commodityAdd(@RequestParam("valName") String name,
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
//            commodityService.add(name,category,price,quantity,detail,spec,image);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    	String image = fileService.fileUpdate(file);
    	commodityService.add(name,category,price,quantity,detail,spec,image);
    	
        return "redirect:/commodity";
    }

    @PostMapping("/commodityUpdate/{id}")
    public String commodityUpdate(@PathVariable Long id,
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
        commodityService.update(name,category,price,quantity,detail,spec,image,id);
        
        return "redirect:/commodity";
    }

}
