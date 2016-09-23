package com.me.drl.demo.allowance;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sqlxx on 7/6/16.
 * Copyright to Maycur Tech.
 */
public class TravelDetail {
    private String userCode;
    private String rankName;
    private DateTime startDate;
    private DateTime endDate;
    private BigDecimal amount;

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<DateTime> getDays() {
        if (startDate == null || endDate == null) {
            return Collections.emptyList();
        }

        List<DateTime> days = new ArrayList<>();

        DateTime day = startDate;
        do {
            days.add(day);
            day = day.plusDays(1);
        } while(Days.daysBetween(day, endDate).getDays() >=0 );

        return days;
    }

    public int getDiffDays() {
        if (startDate == null || endDate == null) {
            return 1;
        } else {
            int diffDays = Days.daysBetween(startDate, endDate).getDays();
            return  diffDays >= 1? diffDays : 1;
        }
    }
}
