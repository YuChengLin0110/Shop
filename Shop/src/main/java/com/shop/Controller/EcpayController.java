package com.shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.Model.EcpayReturnBean;
import com.shop.Service.EcpayService;
import com.shop.Service.OrderService;

@Controller
public class EcpayController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	EcpayService ecpayService;
	
	//建立綠界訂單付款，導向綠界api進行付款
	@PostMapping("/ecpayCheckOut")
	public String ecpayCheckOut(@RequestParam("orderNumber") String orderNumber, Model model) {

		String aioCheckOutForm = ecpayService.createEcpayForm(orderNumber);
		model.addAttribute("aioCheckOutForm",aioCheckOutForm);
			
			return "ecpay";
		}
	
	//接收綠界API回傳付款結果並更新訂單狀態
	@PostMapping("ecpayReturn")
	@ResponseBody
	public String ecpayReturn(@RequestParam("MerchantID") String merchantID,
							  @RequestParam("MerchantTradeNo") String merchantTradeNo,
							  @RequestParam(name = "StoreID", required = false) String storeID,
							  @RequestParam("RtnCode") int rtnCode,
							  @RequestParam("RtnMsg") String rtnMsg,
							  @RequestParam("TradeNo") String tradeNo,
							  @RequestParam("TradeAmt") int tradeAmt,
							  @RequestParam("PaymentDate") String paymentDate,
							  @RequestParam("PaymentType") String paymentType,
							  @RequestParam(name = "HandlingCharge", required = false) Number handlingCharge,
							  @RequestParam(name = "PaymentTypeChargeFee", required = false) Number paymentTypeChargeFee,
							  @RequestParam("TradeDate") String tradeDate,
							  @RequestParam("SimulatePaid") int simulatePaid,
							  @RequestParam(name = "CustomField1", required = false) String customField1,
							  @RequestParam(name = "CustomField2", required = false) String customField2,
							  @RequestParam(name = "CustomField3", required = false) String customField3,
							  @RequestParam(name = "CustomField4", required = false) String customField4,
							  @RequestParam("CheckMacValue") String checkMacValue) {
		
		EcpayReturnBean ecpayReturnBean = new EcpayReturnBean();
		
		ecpayReturnBean.setMerchantID(merchantID);
		ecpayReturnBean.setMerchantTradeNo(merchantTradeNo);
		ecpayReturnBean.setStoreID(storeID);
		ecpayReturnBean.setRtnCode(rtnCode);
		ecpayReturnBean.setRtnMsg(rtnMsg);
		ecpayReturnBean.setTradeNo(tradeNo);
		ecpayReturnBean.setTradeAmt(tradeAmt);
		ecpayReturnBean.setPaymentDate(paymentDate);
		ecpayReturnBean.setPaymentType(paymentType);
		ecpayReturnBean.setHandlingCharge(handlingCharge);
		ecpayReturnBean.setPaymentTypeChargeFee(paymentTypeChargeFee);
		ecpayReturnBean.setTradeDate(tradeDate);
		ecpayReturnBean.setSimulatePaid(simulatePaid);
		ecpayReturnBean.setCustomField1(customField1);
		ecpayReturnBean.setCustomField2(customField2);
		ecpayReturnBean.setCustomField3(customField3);
		ecpayReturnBean.setCustomField4(customField4);
		ecpayReturnBean.setCheckMacValue(checkMacValue);
		
		int code = ecpayReturnBean.getRtnCode();
		String merchId = ecpayReturnBean.getMerchantID();
		String orderNumber = ecpayReturnBean.getMerchantTradeNo();
		
		//判斷有成功接收到綠界回傳資訊，成功return給綠界 "1|OK" 
		if(merchId != null && !merchId.isEmpty()) {
			orderService.updateOrderStatus(code, orderNumber);
			return "1|OK";
		}else {
			return"";
		}	
	}
}
