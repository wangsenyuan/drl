package com.me.drl.demo.allowance;

import java.math.BigDecimal;

public class Result {
	
	private Standard standart;
	private TravelDetail travelDetail;
	private BigDecimal allowance;

	public Result(Standard standart, TravelDetail travelDetail, BigDecimal allowance) {
		super();
		this.standart = standart;
		this.travelDetail = travelDetail;
		this.allowance = allowance;
	}

	public BigDecimal getAllowance() {
		return allowance;
	}

	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
	}
	
}
