package com.ususstudios.hail.error;

public class UnexpectedCharacterError extends BaseError {
	public UnexpectedCharacterError(String description, int line, int charAtLine) {
		super("UnexpectedCharacterError", description, "Lexer", line, charAtLine);
	}
}

