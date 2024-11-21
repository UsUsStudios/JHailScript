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

import com.ususstudios.hail.compile.Compiler;

import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			// Shell
			REPL();
		} else {
			if (args[0].equals("exec")) {
				// Execute file
				if (args[1].equals("src")) {
					// Execute source file directly
					executeSource(args[2]);
				} else if (args[1].equals("byte")) {
					// Execute precompiled bytecode
					executeBytecode(args[2]);
				}
			} else if (args[0].equals("compile")) {
				// Compile source file to bytecode
				compile(args[1], args[2]);
			} else {
				System.err.println("Invalid argument: '" + args[0] + "'");
				System.exit(2);
			}
		}
	}
	
	public static void REPL() throws Exception {
		System.out.println("""
	HailScript Copyright (C) 2024 UsUsStudios
	This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
	This is free software, and you are welcome to redistribute it
	under certain conditions; type `show c' for details.
	To end the terminal, type "exit".
	""");
		while (true) {
			System.out.print("> ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			
			switch (input) {
				case "show w":
					System.out.println("THERE IS NO WARRANTY FOR THE PROGRAM, TO THE EXTENT PERMITTED BY APPLICABLE LAW. EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR OTHER PARTIES PROVIDE THE PROGRAM “AS IS” WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM IS WITH YOU. SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF ALL NECESSARY SERVICING, REPAIR OR CORRECTION.");
					break;
				case "show c":
					File licenseFile = new File("GNU General Public License.txt");
					Scanner fileScanner = new Scanner(licenseFile);
					while (fileScanner.hasNextLine()) {
						System.out.println(fileScanner.nextLine());
					}
					break;
				case "exit":
					System.exit(0);
			}
		}
	}
	
	public static void executeSource(String sourceFile) throws Exception {
		Scanner scanner = new Scanner(new File(sourceFile));
		StringBuilder sourceCode = new StringBuilder();
		while (scanner.hasNextLine()) {
			sourceCode.append(scanner.nextLine());
			sourceCode.append('\n');
		}
		
		Compiler compiler = new Compiler(sourceCode.toString());
		Bytecode bytecode = compiler.compile();
		System.out.println("Compilation complete successfully.");
		
		if (CONSTANTS.DEBUG_PRINT) System.out.println(bytecode);
		
		VM vm = new VM(bytecode);
		vm.execute();
		System.out.println("Execution complete successfully.");
	}
	
	public static void executeBytecode(String byteFile) throws Exception {
		try (FileInputStream fileIn = new FileInputStream(byteFile);
		     ObjectInputStream in = new ObjectInputStream(fileIn)) {
			Bytecode bytecode = (Bytecode) in.readObject();
			
			if (CONSTANTS.DEBUG_PRINT) System.out.println(bytecode);
			
			VM vm = new VM(bytecode);
			vm.execute();
		} catch (IOException i) {
			System.out.println("File not found: " + byteFile);
			System.exit(2);
		}
	}
	
	public static void compile(String sourceFile, String byteFile) throws Exception {
		Scanner scanner = new Scanner(new File(sourceFile));
		StringBuilder sourceCode = new StringBuilder();
		while (scanner.hasNextLine()) {
			sourceCode.append(scanner.nextLine());
		}
		
		Compiler compiler = new Compiler(sourceCode.toString());
		Bytecode bytecode = compiler.compile();
		
		try (FileOutputStream fileOut = new FileOutputStream(byteFile);
		     ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(bytecode);
			System.out.println("Compilation completed successfully.");
		} catch (IOException i) {
			System.out.println("File not found: " + byteFile);
			System.exit(2);
		}
	}
}