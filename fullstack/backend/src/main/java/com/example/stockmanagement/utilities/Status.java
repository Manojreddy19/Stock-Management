package com.example.stockmanagement.utilities;

public enum Status {
	OPEN('O'), CLOSE('C'),WITHDRAWN('W');
	
	private char value;
	private Status(char value)
	{
		this.value=value;
	}

}
