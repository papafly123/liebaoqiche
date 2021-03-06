package com.ibest.recon.dto.input;

import com.ibest.framework.common.persistence.BaseInputDTO;

public class ThreePartyReconInputDTO extends BaseInputDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String orderId;
	
	private String tradeTime;
	
	private String renconTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getRenconTime() {
		return renconTime;
	}

	public void setRenconTime(String renconTime) {
		this.renconTime = renconTime;
	}
	
}
