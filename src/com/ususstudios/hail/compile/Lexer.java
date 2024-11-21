package com.ususstudios.hail.compile;

import com.ususstudios.hail.error.*;

import java.util.ArrayList;

import static com.ususstudios.hail.compile.TokenType.*;

public class Lexer {
	String source;
	int i = 0;
	int line = 1;
	int charAtLine = 0;
	char currentChar;
	ArrayList<Token> tokens = new ArrayList<>();
	
	public Lexer(String source) {
		this.source = source;
	}
	
	public ArrayList<Token> makeTokens() {
		currentChar = source.charAt(i);
		while (true) {
			if (" \t".contains(Character.toString(currentChar))) {
			
			} else if ("1234567890".contains(Character.toString(currentChar))) {
				addNumberToken();
			} else if ("1234567890qwertyuiopadfghjklzxcvbnm".contains(Character.toString(currentChar))) {
			
			} else {
				boolean nextIsEqual = true;
				switch (currentChar) {
					case '\n':
						line += 1;
						charAtLine = 0;
						break;
					case '+':
						addToken(TT_PLUS);
						break;
					case '-':
						addToken(TT_MINUS);
						break;
					case '*':
						addToken(TT_MUL);
						break;
					case '/':
						addToken(TT_DIV);
						break;
					case '^':
						addToken(TT_POW);
						break;
					case '(':
						addToken(TT_LPAREN);
						break;
					case ')':
						addToken(TT_RPAREN);
						break;
					case '!':
						try {nextIsEqual = source.charAt(i + 1) == '=';}
						catch (StringIndexOutOfBoundsException e) {new UnexpectedCharacterError("Expected character " +
								"'=', got end of file", line, charAtLine).print();}
						if (nextIsEqual) {
							addToken(TT_NE);
							i++;
						} else {
							new UnexpectedCharacterError(("Expected character '=' after '!', got '" + source.charAt(i + 1)
									+ '\'').replace("\n", "\\n"), line, charAtLine).print();
						}
						break;
					case '<':
						try {nextIsEqual = source.charAt(i + 1) == '=';}
						catch (StringIndexOutOfBoundsException e) {addToken(TT_LT);}
						if (nextIsEqual) {
							addToken(TT_LTE);
							i++;
						} else {
							addToken(TT_LT);
						}
						break;
					case '>':
						try {nextIsEqual = source.charAt(i + 1) == '=';}
						catch (StringIndexOutOfBoundsException e) {addToken(TT_GT);}
						if (nextIsEqual) {
							addToken(TT_GTE);
							i++;
						} else {
							addToken(TT_GT);
						}
						break;
					case '=':
						try {nextIsEqual = source.charAt(i + 1) == '=';}
						catch (StringIndexOutOfBoundsException e) {addToken(TT_EQ);}
						if (nextIsEqual) {
							addToken(TT_EE);
							i++;
						} else {
							addToken(TT_EQ);
						}
						break;
					default:
						new UnexpectedCharacterError("Unknown character: '" + currentChar + "'", line, charAtLine).print();
				}
			}
			i++;
			charAtLine++;
			try {
				currentChar = source.charAt(i);
			} catch(IndexOutOfBoundsException e) {
				break;
			}
		}
		
		tokens.add(new Token(TT_EOF));
		return tokens;
	}
	
	private void addNumberToken() {
		StringBuilder stringBuilderNumber = new StringBuilder();
		while (true) {
			try {
				currentChar = source.charAt(i);
				if (!"1234567890.".contains(Character.toString(currentChar))) break;
			} catch(IndexOutOfBoundsException e) {
				break;
			}
			
			stringBuilderNumber.append(currentChar);
			
			i++;
			charAtLine++;
		}
		
		String stringNumber = stringBuilderNumber.toString();
		if (stringNumber.contains(".")) addToken(TT_FLOAT, Float.parseFloat(stringNumber));
		else addToken(TT_INT, Integer.parseInt(stringNumber));
	}
	
	private void addToken(String tokenType, Object value) {
		tokens.add(new Token(tokenType, value));
	}
	
	private void addToken(String tokenType) {
		tokens.add(new Token(tokenType));
	}
}
