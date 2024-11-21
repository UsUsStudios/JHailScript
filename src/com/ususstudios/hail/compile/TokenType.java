package com.ususstudios.hail.compile;

public class TokenType {
	static String TT_INT = "INT";
	static String TT_FLOAT = "FLOAT";
	static String TT_IDENTIFIER = "IDENTIFIER";
	static String TT_KEYWORD = "KEYWORD";
	static String TT_EQ = "EQUALS";
	static String TT_PLUS = "PLUS";
	static String TT_MINUS = "MINUS";
	static String TT_MUL = "MUL";
	static String TT_DIV = "DIV";
	static String TT_POW = "POW";
	static String TT_LPAREN = "LPAREN";
	static String TT_RPAREN = "RPAREN";
	static String TT_EE = "EQUALS_EQUALS";
	static String TT_NE = "NOT_EQUALS";
	static String TT_LT = "LESS_THAN";
	static String TT_GT = "GREATER_THAN";
	static String TT_LTE = "LESS_THAN_EQUALS";
	static String TT_GTE = "GREATER_THAN_EQUALS";
	static String TT_EOF = "EOF";
	
	static String[] KEYWORDS = {
			"var"
	};
}
