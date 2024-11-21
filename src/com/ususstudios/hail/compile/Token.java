package com.ususstudios.hail.compile;

public class Token {
	String tokenType;
	Object value;
	int line;
	
	public Token(String tokenType, Object value) {
		this.tokenType = tokenType;
		this.value = value;
	}
	
	public Token(String tokenType) {
		this.tokenType = tokenType;
	}
	
	@Override
	public String toString() {
		if (value == null) {
			return tokenType;
		}
		return tokenType + ": " + value;
	}
}
