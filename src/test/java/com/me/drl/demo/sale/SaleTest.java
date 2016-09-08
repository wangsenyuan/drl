package com.me.drl.demo.sale;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import com.me.drl.demo.sale.model.Customer;
import com.me.drl.demo.sale.model.CustomerType;
import com.me.drl.demo.sale.model.Sale;


public class SaleTest {

	private static StatelessKieSession kSession;

	private Customer vipCustomer = new Customer("Mr. Good Customer", CustomerType.VIP);
	private Customer regularCustomer = new Customer("Mr. Typical Customer", CustomerType.REGULAR);
	private Customer badCustomer = new Customer("Mr. Bad Customer", CustomerType.BAD);

	// A sale for a VIP customer
	private Sale vipSale = new Sale(vipCustomer, new BigDecimal(1000));
	// A sale for a regular customer
	private Sale regularSale = new Sale(regularCustomer, new BigDecimal(1000));
	// A sale for a Bad customer
	private Sale badSale = new Sale(badCustomer, new BigDecimal(50));

	@BeforeClass
	public static void setup() {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		kSession = kContainer.newStatelessKieSession();
	}

	@Test
	public void testGoodCustomer() {
		System.out.println("** Testing VIP customer **");
		kSession.execute(vipSale);
		// Sale approved
		assertTrue(vipSale.isApproved());
		// Discount of 0.5
		assertEquals(vipSale.getDiscount(), 0.50F, 0.0);
	}

	@Test
	public void testRegularCustomer() {
		System.out.println("** Testing regular customer **");
		kSession.execute(regularSale);
		// Sale approved
		assertTrue(regularSale.isApproved());
		// No Discount
		assertEquals(regularSale.getDiscount(), 0.0, 0.0);
	}

	@Test
	public void testBadCustomer() {
		System.out.println("** Testing BAD customer **");
		kSession.execute(badSale);
		// Sale denied
		assertFalse(badSale.isApproved());
	}

}
