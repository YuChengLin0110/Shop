本專案為2023年學習 Spring Boot 時所完成的練習作品，目前已停止維護。  
專案中的部分寫法可能與現行最佳實踐有所不同，但仍保留作為早期學習與實作經驗的紀錄。

This is a practice project from 2023 when I was learning Spring Boot.  
It's no longer updated, and some code might not match today’s best practices.  
Still, I keep it here as a record of early learning and development experience.

# Shop

## 專案技術  
Java  
Thymeleaf、JavaScript、Jquery、AJAX  
Spring boot、Spring Security  
MySQL、Redis、Hibernate、JPQL、MyBatis  
綠界金流Ecpay

## 功能簡介  
註冊
以AJAX方式驗證帳號是否已存在  
密碼經BCrypt加密後存入資料庫

登入
由Spring Security管理
CSRF驗證
![image](demonstration/registerLogin.gif)

新增商品、上傳圖片
![image](demonstration/uploadImage.gif)

加入購物車
![image](demonstration/addCart.gif)

加入訂單
![image](demonstration/addOrder.gif)

結帳串接綠界金流ecpay
![image](demonstration/checkout.gif)
