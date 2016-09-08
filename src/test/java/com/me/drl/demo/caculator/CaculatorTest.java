package com.me.drl.demo.caculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import com.me.drl.demo.caculator.model.Operand;
import com.me.drl.demo.caculator.model.Operands;
import com.me.drl.demo.caculator.model.Result;

public class CaculatorTest {

	private static StatelessKieSession kSession;

	@BeforeClass
	public static void setup() {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
		kSession = kContainer.newStatelessKieSession();
	}
	
	@Test
	public void testSum() {
		Operands ops = new Operands();
		for(int i = 0; i < 10; i++) {
			ops.addOperand(new Operand(i));
		}
		kSession.execute(ops);
	
		Result res = ops.getResult();
		assertNotNull(res);
		assertEquals(45, res.getValue());
	}
}
