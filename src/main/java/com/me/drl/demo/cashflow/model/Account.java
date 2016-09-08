package com.me.drl.demo.cashflow.model;

import java.io.Serializable;

public class Account implements Serializable {
	private long accountNo;
	private double balance;
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
