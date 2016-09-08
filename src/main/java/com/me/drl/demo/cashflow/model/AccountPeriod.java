package com.me.drl.demo.cashflow.model;

import java.io.Serializable;
import java.util.Date;

public class AccountPeriod implements Serializable {
	private Date start;
	private Date end;
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
}
