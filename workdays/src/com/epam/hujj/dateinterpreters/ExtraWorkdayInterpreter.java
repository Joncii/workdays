package com.epam.hujj.dateinterpreters;

public class ExtraWorkdayInterpreter extends AbstractDateInterpreter {

	public ExtraWorkdayInterpreter() {
		super();
		identifier = "extra-workday";
		setConstants();
	}

	@Override
	protected void setFormatValue(String formatValue) {
		this.formatValue = formatValue;
	}

	@Override
	protected String createValueString(String valueString) {
		return valueString;
	}

}
