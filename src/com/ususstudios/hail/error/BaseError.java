package com.ususstudios.hail.error;

public class BaseError {
	String name;
	String description;
	String type;
	int line;
	int charAtLine;
	
	public BaseError(String name, String description, String type, int line, int charAtLine) {
		this.name = name;
		this.description = description.replace("\n", "\\n");
		this.type = type;
		this.line = line;
		this.charAtLine = charAtLine;
	}
	
	public void print() {
		System.err.println("Ran into error at position " + line + ":" + charAtLine + " of type " + type + ":");
		System.err.println(name);
		System.err.println(description);
		System.exit(5);
	}
}
