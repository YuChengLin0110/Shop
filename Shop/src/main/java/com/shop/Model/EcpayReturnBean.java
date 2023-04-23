package com.shop.Model;

import org.springframework.stereotype.Component;

//接收綠界回傳
@Component
public class EcpayReturnBean {


	private String MerchantID;
	private String MerchantTradeNo;
	private String StoreID;
	private int RtnCode;
	private String RtnMsg;
	private String TradeNo;
	private int TradeAmt;
	private String PaymentDate;
	private String PaymentType;
	private Number HandlingCharge;
	private Number PaymentTypeChargeFee;
	private String TradeDate;
	private int SimulatePaid;
	private String CustomField1;
	private String CustomField2;
	private String CustomField3;
	private String CustomField4;
	private String CheckMacValue;
	
	public String getMerchantID() {
		return MerchantID;
	}
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	public String getMerchantTradeNo() {
		return MerchantTradeNo;
	}
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}
	public String getStoreID() {
		return StoreID;
	}
	public void setStoreID(String storeID) {
		StoreID = storeID;
	}
	public int getRtnCode() {
		return RtnCode;
	}
	public void setRtnCode(int rtnCode) {
		RtnCode = rtnCode;
	}
	public String getRtnMsg() {
		return RtnMsg;
	}
	public void setRtnMsg(String rtnMsg) {
		RtnMsg = rtnMsg;
	}
	public String getTradeNo() {
		return TradeNo;
	}
	public void setTradeNo(String tradeNo) {
		TradeNo = tradeNo;
	}
	public int getTradeAmt() {
		return TradeAmt;
	}
	public void setTradeAmt(int tradeAmt) {
		TradeAmt = tradeAmt;
	}
	public String getPaymentDate() {
		return PaymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		PaymentDate = paymentDate;
	}
	public String getPaymentType() {
		return PaymentType;
	}
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	public Number getHandlingCharge() {
		return HandlingCharge;
	}
	public void setHandlingCharge(Number handlingCharge) {
		HandlingCharge = handlingCharge;
	}
	public Number getPaymentTypeChargeFee() {
		return PaymentTypeChargeFee;
	}
	public void setPaymentTypeChargeFee(Number paymentTypeChargeFee) {
		PaymentTypeChargeFee = paymentTypeChargeFee;
	}
	public String getTradeDate() {
		return TradeDate;
	}
	public void setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
	}
	public int getSimulatePaid() {
		return SimulatePaid;
	}
	public void setSimulatePaid(int simulatePaid) {
		SimulatePaid = simulatePaid;
	}
	public String getCustomField1() {
		return CustomField1;
	}
	public void setCustomField1(String customField1) {
		CustomField1 = customField1;
	}
	public String getCustomField2() {
		return CustomField2;
	}
	public void setCustomField2(String customField2) {
		CustomField2 = customField2;
	}
	public String getCustomField3() {
		return CustomField3;
	}
	public void setCustomField3(String customField3) {
		CustomField3 = customField3;
	}
	public String getCustomField4() {
		return CustomField4;
	}
	public void setCustomField4(String customField4) {
		CustomField4 = customField4;
	}
	public String getCheckMacValue() {
		return CheckMacValue;
	}
	public void setCheckMacValue(String checkMacValue) {
		CheckMacValue = checkMacValue;
	}
}
