package com.epam.hujj.dateinterpreters;

public class VacationInterpreter extends AbstractDateInterpreter {

	private String year;

	public VacationInterpreter(String year) {
		super();
		this.year = year;
		identifier = "vacation";
		setConstants();
	}

	@Override
	protected void setFormatValue(String formatValue) {
		this.formatValue = "yyyy-" + formatValue;
	}

	@Override
	protected String createValueString(String valueString) {
		String returnString = year + "-" + valueString;
		return returnString;
	}

}
