package com.me.drl.demo.allowance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.util.Collection;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;

public class AllowanceTest {


	private static KieSession kieSession;

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
	public void testWeekendStandard() {
		Standard std = new Standard();
		std.setWeekend(true);
		std.setExceptionAmount(new BigDecimal(100));
		std.setStandardAmount(new BigDecimal(80));
		
		TravelDetail td = new TravelDetail();
		td.setAmount(new BigDecimal(120));
		DateTimeFormatter dtFmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		td.setStartDate(DateTime.parse("2016-09-23", dtFmt));
		td.setEndDate(DateTime.parse("2016-09-24", dtFmt));
		kieSession.insert(std);
		kieSession.insert(td);
		kieSession.fireAllRules();
		
		Collection<TotalAllowance> total = findFacts(kieSession, TotalAllowance.class);
		assertNotNull(total);
		assertEquals(1, total.size());
		for(TotalAllowance x : total) {
			assertEquals(180, x.total, 0.0d);
			assertEquals(60, x.allowance, 0.0d);
		}
	}
}
