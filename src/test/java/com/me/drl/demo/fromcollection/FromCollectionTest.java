package com.me.drl.demo.fromcollection;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.drools.core.command.assertion.AssertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class FromCollectionTest {

	private static KieSession kieSession;

	@BeforeClass
	public static void setup() {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		kieSession = kContainer.newKieSession();
	}

	@AfterClass
	public static void dispose() {
		kieSession.dispose();
	}
	
	@Test
	public void test1() {
		Order order = new Order();
		List<Item> items = new ArrayList<>();
		Item a = new Item(100, 1.0);
		items.add(a);
		
		Item b = new Item(101, 2.0);
		items.add(b);
		order.setItems(items);
		
		kieSession.insert(order);
		kieSession.fireAllRules();
		
		assertEquals(1.0, a.getPrice(), 0.0);
		assertEquals(1.6, b.getPrice(), 0.0);
	}
}
