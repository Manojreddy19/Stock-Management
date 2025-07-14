package com.example.stockmanagement.utilities;

public enum Status {
	OPEN('O'), ACCEPT('A'), REJECT('R');

	private char value;

	private Status(char value) {
		this.value = value;
	}

	public char getValue() {
		return value;
	}
}