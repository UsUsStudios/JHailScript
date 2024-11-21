package com.ususstudios.hail;

import java.util.Stack;

import static com.ususstudios.hail.OpCode.*;

public class VM {
	Bytecode bytecode;
	Stack<Character> stack = new Stack<>();
	public VM(Bytecode bytecode) {
		this.bytecode = bytecode;
	}
	
	public void execute() {
		boolean running = true;
		for (int i = 0; i < this.bytecode.bytecode.toArray().length; i++) {
			switch (this.bytecode.bytecode.get(i)) {
				case OP_CONST:
					i++;
					if (CONSTANTS.DEBUG_PRINT) System.out.println("OP_CONST: " + (int) push(this.bytecode.bytecode.get(i)));
					else push(this.bytecode.bytecode.get(i));
					break;
				case OP_ADD, OP_SUB, OP_MUL, OP_DIV:
					char b = pop();
					char a = pop();
					switch (this.bytecode.bytecode.get(i)) {
						case OP_ADD:
							if (CONSTANTS.DEBUG_PRINT) {
								System.out.println("OP_ADD: " + (int) a + " + " + (int) b + " = " + (int) push((char) (a + b)));
							}
							else push((char) (a + b));
							break;
						case OP_SUB:
							if (CONSTANTS.DEBUG_PRINT) {
								System.out.println("OP_SUB: " + (int) a + " - " + (int) b + " = " + (int) push((char) (a - b)));
							}
							else push((char) (a - b));
							break;
						case OP_MUL:
							if (CONSTANTS.DEBUG_PRINT) {
								System.out.println("OP_MUL: " + (int) a + " * " + (int) b + " = " + (int) push((char) (a * b)));
							}
							else push((char) (a * b));
							break;
						case OP_DIV:
							if (CONSTANTS.DEBUG_PRINT) {
								System.out.println("OP_DIV: " + (int) a + " / " + (int) b + " = " + (int) push((char) (a / b)));
							}
							else push((char) (a / b));
							break;
					}
					break;
				case OP_RET:
					System.out.println((int) pop());
					break;
			}
			if (CONSTANTS.STACK_DEBUG_PRINT) printStack();
			if (!running) break;
		}
	}
	
	private char push(char item) {
		return this.stack.push(item);
	}
	
	private char pop() {
		return this.stack.pop();
	}
	
	void printStack() {
		System.out.println("Stack:");
		for (Object value : stack.toArray()) {
			System.out.print((int) (char) value);
			System.out.println(',');
		}
		System.out.println();
	}
}
