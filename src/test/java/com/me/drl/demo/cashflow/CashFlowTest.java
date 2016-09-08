package com.me.drl.demo.cashflow;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import com.me.drl.demo.cashflow.model.Account;
import com.me.drl.demo.cashflow.model.AccountPeriod;
import com.me.drl.demo.cashflow.model.CashFlow;

public class CashFlowTest {

	private static StatelessKieSession kSession;

	@BeforeClass
	public static void setup() {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		kSession = kContainer.newStatelessKieSession();
	}

	@Test
	public void testCredit() {
		AccountPeriod ap = new AccountPeriod();
		Date now = new Date();
		ap.setStart(new Date(now.getTime() - 1000000));
		ap.setEnd(new Date(now.getTime() + 1000000));
		
		Account account = new Account();
		account.setAccountNo(1);
		account.setBalance(1000d);
		
		CashFlow cashFlow = new CashFlow();
		cashFlow.setAccountNo(1);
		cashFlow.setType(1);
		cashFlow.setAmount(100d);
		cashFlow.setDate(now);
		
		kSession.execute(Arrays.asList(ap, account, cashFlow));
		
		assertEquals(1100.0d, account.getBalance(), 0.0d);
	}
	
	@Test
	public void testDeposit() {
		AccountPeriod ap = new AccountPeriod();
		Date now = new Date();
		ap.setStart(new Date(now.getTime() - 1000000));
		ap.setEnd(new Date(now.getTime() + 1000000));
		
		Account account = new Account();
		account.setAccountNo(1);
		account.setBalance(1000d);
		
		CashFlow cashFlow = new CashFlow();
		cashFlow.setAccountNo(1);
		cashFlow.setType(2);
		cashFlow.setAmount(100d);
		cashFlow.setDate(now);
		
		kSession.execute(Arrays.asList(ap, account, cashFlow));
		
		assertEquals(900.0d, account.getBalance(), 0.0d);
	}
}
