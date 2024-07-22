package com.simple.springProject;

public class Laptop {

	private int lines;
	private Code code;
	
	public Code getCode() {
		return code;
	}
	
	public void setCode(Code code) {
		this.code = code;
	}

	public void compile() {
		System.out.println("Compiling...");
		code.coding();
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	
	/**
	 * Parameterize Constructor
	 */
	public Laptop(int lines) {
		setLines(lines);
	}
}
