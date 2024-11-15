/*
	HailScript Java Implementation (JHailScript)
    Copyright (C) 2024 UsUsStudios

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package com.ususstudios.hail;

public class Main {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			// Shell
			System.out.print("REPL");
			REPL();
		} else {
			if (args[0].equals("exec")) {
				// Execute file
				if (args[1].equals("src")) {
					// Execute source file directly
					System.out.println("Execute Source");
					executeSource(args[2]);
				} else if (args[1].equals("byte")) {
					// Execute precompiled bytecode
					System.out.println("Execute Bytecode");
					executeBytecode(args[2]);
				}
			} else if (args[0].equals("compile")) {
				// Compile source file to bytecode
				System.out.println("Compile");
				compile(args[1], args[2]);
			} else {
				System.err.println("Invalid argument: '" + args[0] + "'");
				System.exit(2);
			}
		}
	}
	
	public static void REPL() {}
	
	public static void executeSource(String sourceFile) throws Exception {
		System.out.println(sourceFile);
	}
	
	public static void executeBytecode(String byteFile) throws Exception {
		System.out.println(byteFile);
	}
	
	public static void compile(String sourceFile, String byteFile) throws Exception{
		System.out.println(sourceFile);
		System.out.println(byteFile);
	}
}