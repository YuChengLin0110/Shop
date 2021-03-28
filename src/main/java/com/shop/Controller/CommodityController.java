package com.shop.Controller;

import com.shop.DAO.CommodityDAO;
import com.shop.Model.CommodityBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class CommodityController {

	@Autowired
    private CommodityDAO commodityDAO;

    private CommodityBean commodityBean = new CommodityBean();

    @PostMapping("/commodityAdd")
    public String commodityAdd(@RequestParam("valName") String name,
                               @RequestParam("valCategory") String category,
                               @RequestParam("valPrice") int price,
                               @RequestParam("valQuantity") int quantity,
                               @RequestParam("valDetail") String detail,
                               @RequestParam("valSpec") String spec,
                               @RequestParam("valImage") MultipartFile file)  {

        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("D:\\IDEA\\src\\main\\resources\\static\\images\\" + fileName);
            Files.write(path, bytes);

            String image = "/images/"+fileName;
            commodityDAO.add(name,category,price,quantity,detail,spec,image);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/commodity";
    }

    @PostMapping("/commodityUpdate/{id}")
    public String commodityUpdate(@PathVariable int id,
                                  @RequestParam("valName") String name,
                                  @RequestParam("valCategory") String category,
                                  @RequestParam("valPrice") int price,
                                  @RequestParam("valQuantity") int quantity,
                                  @RequestParam("valDetail") String detail,
                                  @RequestParam("valSpec") String spec,
                                  @RequestParam("valImage") MultipartFile file)  {
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("D:\\IDEA\\src\\main\\resources\\static\\images\\" + fileName);
            Files.write(path, bytes);
            String image = "/images/"+fileName;
            commodityDAO.update(name,category,price,quantity,detail,spec,image,id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/commodity";
    }

}
