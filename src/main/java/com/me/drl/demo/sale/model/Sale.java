package com.me.drl.demo.sale.model;

import java.math.BigDecimal;

public class Sale {

	private Customer customer;

	private BigDecimal value;

	private double discount;

	private boolean approved;

	public Sale(Customer customer, BigDecimal value) {
		this.customer = customer;
		this.value = value;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Customer getCustomer() {
		return customer;
	}

	public BigDecimal getValue() {
		return value;
	}

}