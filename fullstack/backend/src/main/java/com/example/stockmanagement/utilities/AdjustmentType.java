package com.example.stockmanagement.utilities;

public enum AdjustmentType {
	UP('U'), DOWN('D');

	private char value;

	private AdjustmentType(char value) {
		this.value = value;
	}

	public char getValue() {
		return value;
	}

}