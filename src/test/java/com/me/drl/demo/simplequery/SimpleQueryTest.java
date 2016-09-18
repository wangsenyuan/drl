package com.me.drl.demo.simplequery;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class SimpleQueryTest {

	private static KieSession kieSession;

	@BeforeClass
	public static void setup() {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		kieSession = kContainer.newKieSession();
	}

//	@Test
//	public void testSimpleQuery1() {
//		kieSession.insert(1);
//		kieSession.insert("1");
//		kieSession.fireAllRules();
//	}
//	
	@Test
	public void testSimpleQuery2() {
		kieSession.insert("1");
		kieSession.insert(1);
		kieSession.fireAllRules();
		kieSession.dispose();
	}

}
