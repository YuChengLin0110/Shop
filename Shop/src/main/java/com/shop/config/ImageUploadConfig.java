package com.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//指定靜態路徑使圖片上傳後不須重新佈署就能顯示

@Configuration
public class ImageUploadConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:C:\\Users\\aa468\\Github_Shop\\Shop\\src\\main\\resources\\static\\images\\");
	}
	
}
