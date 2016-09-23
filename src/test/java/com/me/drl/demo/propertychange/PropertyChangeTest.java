package com.me.drl.demo.propertychange;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class PropertyChangeTest {

	

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
	
/*	@Test
	public void test1() {
		Person jim = new Person();
		jim.setName("jim");
		Car car = new Car();
		car.setOwner("jim");
		
		kieSession.insert(jim);
		kieSession.insert(car);
		kieSession.fireAllRules();
	}*/
	
	
	@Test
	public void test2() {
		Person jim = new Person();
		jim.setName("tom");
		Car car = new Car();
		car.setOwner("jim");
		
		FactHandle jimHandle = kieSession.insert(jim);
		kieSession.insert(car);
		kieSession.fireAllRules();
		/*System.out.println("update name from tom to jim");
		jim.setName("jim");
		kieSession.update(jimHandle, jim);
		kieSession.fireAllRules();*/
	}
}
