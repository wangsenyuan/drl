package com.me.drl.demo.fire;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.me.drl.demo.fire.model.Fire;
import com.me.drl.demo.fire.model.House;
import com.me.drl.demo.fire.model.Room;
import com.me.drl.demo.fire.model.Sprinkler;

public class FireTest {
	private static KieSession kieSession;
	
	private static House house;
	@BeforeClass
	public static void setup() {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		kieSession = kContainer.newKieSession();
		
		house = new House();
		house.buildHouse();
	}

	
	@Test
	public void assertAlarmWorkingAndSprinklerWorkingOnFire() {
		System.out.println("** Testing if Sprinkler and Alarm is working **");
		Room room = new Room("testRoom");
		Sprinkler sprinkler = new Sprinkler(room);
		kieSession.insert(house);
		kieSession.insert(sprinkler);
		kieSession.insert(room);
		// Put fire in the room
		FactHandle fireHandle = kieSession.insert(new Fire(room));
		kieSession.fireAllRules();
		// Sprinkler should be On
		assertTrue(sprinkler.isOn());
		// Alarm should be On
		assertTrue(house.isAlarmOn());

		// Remove the fire
		kieSession.delete(fireHandle);
		// There's no fire on the House
		kieSession.fireAllRules();
		// Sprinkler should be Off
		assertFalse(sprinkler.isOn());
		// Alarm should be Off
		assertFalse(house.isAlarmOn());

	}

	@Test
	public void assertSprinklerOffWihoutFire() {
		System.out.println("** Testing if Sprinkler and Alarm is off without fire **");
		Room room = new Room("testRoom");
		Sprinkler sprinkler = new Sprinkler(room);
		kieSession.insert(house);
		kieSession.insert(sprinkler);
		kieSession.insert(room);
		// There's no fire on the House
		kieSession.fireAllRules();
		// Sprinkler should be Off
		assertFalse(sprinkler.isOn());
		// Alarm should be Off
		assertFalse(house.isAlarmOn());
	}
}
