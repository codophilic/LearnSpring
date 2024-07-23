package com.simple.XMLBasedConfiguration;

public class Laptop {

	private int lines;
	private Code code;
	private ProgrammingLanguage programmingLanguage;

	
	public Code getCode() {
		return code;
	}
	
	public void setCode(Code code) {
		this.code = code;
	}

	public void compile() {
		System.out.println("Compiling...");
		code.coding();
		programmingLanguage.print();
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

	public ProgrammingLanguage getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
}
