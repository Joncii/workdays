package com.epam.hujj.dateinterpreters;

public class ExtraVacationInterpreter extends AbstractDateInterpreter {

	public ExtraVacationInterpreter() {
		super();
		identifier = "extra-vacation";
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
