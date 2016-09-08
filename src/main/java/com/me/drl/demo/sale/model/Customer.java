package com.me.drl.demo.sale.model;

public class Customer {

	private String name;

	private CustomerType type;

	public Customer(String name, CustomerType type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public CustomerType getType() {
		return type;
	}

}