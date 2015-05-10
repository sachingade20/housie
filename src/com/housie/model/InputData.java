package com.housie.model;


public class InputData {

	private String rows;
	private String column;
	private String sheet;


	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getColumn() {
		return column;
	}


	public void setColumn(String column) {
		this.column = column;
	}


	public String getSheet() {
		return sheet;
	}


	public void setSheet(String sheet) {
		this.sheet = sheet;
	}


	@Override
	public String toString() {
		return "InputData [rows=" + rows + ", column=" + column + ", sheet="
				+ sheet + "]";
	}
	
}
