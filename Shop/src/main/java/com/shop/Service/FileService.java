package com.shop.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String fileUpdate(MultipartFile file) {
		 String fileName = System.currentTimeMillis()+file.getOriginalFilename();
	        String image="";
	        try {
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get("C:/Users/aa468/Github_Shop/Shop/src/main/resources/static/images/" + fileName);
	            Files.write(path, bytes);
	            image = "/images/"+fileName;

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return image;
	}
}
