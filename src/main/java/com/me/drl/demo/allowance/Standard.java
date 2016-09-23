package com.me.drl.demo.allowance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by JoeyXin on 8/14/16.
 */
public class Standard {
    private boolean unify;
    private BigDecimal standardAmount;
    private BigDecimal exceptionAmount;
    private boolean weekend;
    private Map<Date, Date> exceptionDates;

    

    public BigDecimal getStandardAmount() {
        return standardAmount;
    }

    public void setStandardAmount(BigDecimal standardAmount) {
        this.standardAmount = standardAmount;
    }

    public BigDecimal getExceptionAmount() {
        return exceptionAmount;
    }

    public void setExceptionAmount(BigDecimal exceptionAmount) {
        this.exceptionAmount = exceptionAmount;
    }

   
	public Map<Date, Date> getExceptionDates() {
		return exceptionDates;
	}

	public void setExceptionDates(Map<Date, Date> exceptionDates) {
		this.exceptionDates = exceptionDates;
	}

	public boolean isUnify() {
		return unify;
	}

	public void setUnify(boolean unify) {
		this.unify = unify;
	}

	public boolean isWeekend() {
		return weekend;
	}

	public void setWeekend(boolean weekend) {
		this.weekend = weekend;
	}
}
