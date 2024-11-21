package com.ususstudios.hail;

import java.io.Serializable;
import java.util.ArrayList;

import static com.ususstudios.hail.OpCode.*;

public class Bytecode implements Serializable {
	ArrayList<Character> bytecode = new ArrayList<>();
	ArrayList<Integer> lines = new ArrayList<>();
	
	public void addByte(char opcode, int line) {
		bytecode.add(opcode);
		lines.add(line);
	}
	
	public void addByte(int opcode, int line) {
		bytecode.add((char) opcode);
		lines.add(line);
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		int prevLine = -1;
		for (int i = 0; i < bytecode.toArray().length; i++) {
			string.append(String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0'));
			string.append('\t');
			if (lines.get(i) != prevLine) {
				prevLine = lines.get(i);
				string.append(lines.get(i));
			} else {
				string.append('|');
			}
			string.append("\t\t");
			switch (bytecode.get(i)) {
				case OP_ADD -> simpleInstruction("OP_ADD", string, i);
				case OP_SUB -> simpleInstruction("OP_SUB", string, i);
				case OP_MUL -> simpleInstruction("OP_MUL", string, i);
				case OP_DIV -> simpleInstruction("OP_DIV", string, i);
				case OP_RET -> simpleInstruction("OP_RET", string, i);
				case OP_CONST -> i = constantInstruction("OP_CONST", string, i);
				default -> string.append("Unknown opcode: ").append((int) bytecode.get(i));
			}
			string.append('\n');
		}
		return string.toString();
	}
	
	private void simpleInstruction(String opCode, StringBuilder string, int i) {
		string.append(opCode);
	}
	
	private int constantInstruction(String opCode, StringBuilder string, int i) {
		string.append(opCode);
		string.append('\t');
		string.append((int) bytecode.get(i + 1));
		return i + 1;
	}
}
