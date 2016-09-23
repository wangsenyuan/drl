package com.me.drl.demo.discount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DiscountTest {


	private KieSession kieSession;

	@Before
	public void setup() {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		kieSession = kContainer.newKieSession();
	}

	@After
	public void tearDown() {
		kieSession.dispose();
	}
	@Test
	public void testGoldenCustomer() {
		Customer customer = new Customer();
		customer.setAge(61);
		customer.setName("jim");
		customer.setType("golden");
		Car car = new Car();
		car.setOwner("jim");
		
		kieSession.insert(customer);
		kieSession.insert(car);
		kieSession.fireAllRules();
		assertTrue(car.isFreeParking());
		assertEquals(10, customer.getDiscount(), 0.0);
	}
	
	@Test
	public void testSilverCustomer() {
		Customer customer = new Customer();
		customer.setAge(61);
		customer.setName("jim");
		customer.setType("silver");
		Car car = new Car();
		car.setOwner("jim");
		
		kieSession.insert(customer);
		kieSession.insert(car);
		kieSession.fireAllRules();
		assertFalse(car.isFreeParking());
		assertEquals(5, customer.getDiscount(), 0.0);
	}
}
