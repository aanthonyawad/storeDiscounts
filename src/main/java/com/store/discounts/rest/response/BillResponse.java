package com.store.discounts.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BillResponse {
	@JsonProperty(value = "net_payable_amount")
	private double netPayableAmount;

	public BillResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BillResponse(double netPayableAmount) {
		super();
		this.netPayableAmount = netPayableAmount;
	}

	public double getNetPayableAmount() {
		return netPayableAmount;
	}

	public void setNetPayableAmount(double netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
	}
		
}
