package com.me.drl.demo.allowance;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by sqlxx on 7/6/16. Copyright to Maycur Tech.
 */
public class DailyAllowance {
	private DateTime date;
	private BigDecimal amount = BigDecimal.ZERO;

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public DailyAllowance(DateTime date, BigDecimal amount) {
		super();
		this.date = date;
		this.amount = amount;
	}
	

}
