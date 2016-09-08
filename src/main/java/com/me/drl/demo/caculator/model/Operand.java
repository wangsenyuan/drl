package com.me.drl.demo.caculator.model;

import java.io.Serializable;

public class Operand implements Serializable {
	private int value;

	public Operand(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
