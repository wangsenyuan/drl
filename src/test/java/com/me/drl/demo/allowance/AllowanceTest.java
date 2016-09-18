package com.me.drl.demo.allowance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;

public class AllowanceTest {


	private static KieSession kieSession;

	@BeforeClass
	public static void setup() {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		kieSession = kContainer.newKieSession();
	}

	protected <T> Collection<T> findFacts(final KieSession session, final Class<T> factClass) {
		ObjectFilter filter = new ObjectFilter() {
			@Override
			public boolean accept(Object object) {
				return object.getClass().equals(factClass);
			}
		};

		@SuppressWarnings("unchecked")
		Collection<T> results = (Collection<T>) session.getObjects(filter);
		return results;
	}
	
	@Test
	public void test() {
		Standard standard = new Standard();
		standard.setDailyAmount(new BigDecimal(100));
		
		TravelDetail travelDetail = new TravelDetail();
		travelDetail.setDays(10);
		
		kieSession.insert(standard);
		kieSession.insert(travelDetail);
		
		kieSession.fireAllRules();
		
		Collection<Result> results = findFacts(kieSession, Result.class);
		assertTrue(results.size() == 1);
		for(Result result : results) {
			assertEquals(new BigDecimal(1000), result.getAllowance());
		}
		
		kieSession.dispose();
	}
}
