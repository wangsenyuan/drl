package com.me.drl.demo.fromcollection;

public class Item {
	private int quantity;
	private double price;
	public Item(int quantity, double price) {
		this.quantity = quantity;
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void discount(double p) {
		this.price *= p;
	}
}
