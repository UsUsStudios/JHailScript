package com.ususstudios.hail.compile;

import com.ususstudios.hail.Bytecode;

import java.util.ArrayList;

import static com.ususstudios.hail.OpCode.*;

public class Compiler {
	String sourceCode;
	public Compiler(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	public Bytecode compile() {
		Lexer lexer = new Lexer(this.sourceCode);
		ArrayList<Token> tokens = lexer.makeTokens();
		System.out.println(tokens);
		return new Bytecode();
	}
}
