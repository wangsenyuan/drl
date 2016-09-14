package com.me.drl.demo.buspass;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.Row;
import org.kie.api.runtime.rule.ViewChangedEventListener;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.me.drl.demo.buspass.model.IsAdult;
import com.me.drl.demo.buspass.model.IsChild;
import com.me.drl.demo.buspass.model.Person;

public class BusPassTest {

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
	public void testGrowth() {
		kieSession.addEventListener(new DebugAgendaEventListener());
		kieSession.addEventListener(new DebugRuleRuntimeEventListener());
		Person jim = new Person();
		jim.setName("jim");
		jim.setAge(15);

		FactHandle jimHandle = kieSession.insert(jim);

		kieSession.fireAllRules();

		Collection<IsChild> facts = findFacts(kieSession, IsChild.class);
		assertTrue(facts.size() > 0);
		
		for(IsChild isChild : facts) {
			System.out.println(isChild.getPerson().getName());
		}
		
		jim.setAge(16);
		
		kieSession.update(jimHandle, jim);
		kieSession.fireAllRules();
		
		
		facts = findFacts(kieSession, IsChild.class);

		assertTrue(facts.size() == 0);

		Collection<IsAdult> adultFacts = findFacts(kieSession, IsAdult.class);
		assertTrue(adultFacts.size() > 0);
	}

	
}
