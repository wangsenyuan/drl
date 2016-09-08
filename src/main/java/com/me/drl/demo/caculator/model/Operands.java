package com.me.drl.demo.caculator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Operands implements Serializable {

	private List<Operand> operands;
	private Result result;

	public List<Operand> getOperands() {
		return operands;
	}

	public void addOperand(Operand op) {
		if (this.operands == null) {
			this.operands = new ArrayList<>();
		}
		this.operands.add(op);
	}

	public void sum() {
		result = new Result();
		int res = 0;
		for (Operand op : operands) {
			res += op.getValue();
		}
		result.setValue(res);
	}

	public Result getResult() {
		return result;
	}
	
	
}
