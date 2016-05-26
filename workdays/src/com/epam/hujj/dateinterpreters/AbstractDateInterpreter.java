package com.epam.hujj.dateinterpreters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.epam.hujj.interfaces.DateProvider;
import com.epam.hujj.interfaces.DateReader;

public abstract class AbstractDateInterpreter implements DateProvider, DateReader {

	protected String identifier;

	protected String formatKey;

	protected String instanceIdentifierPrefix;

	protected String formatValue;

	protected Map<String, LocalDate> dateMap;

	public AbstractDateInterpreter() {
		dateMap = new HashMap<String, LocalDate>();
	}

	@Override
	public void readFromProperties(Properties properties) {

		Iterator<Object> keySetIterator = properties.keySet().iterator();
		while (keySetIterator.hasNext() && formatValue == null) {
			Object key = keySetIterator.next();
			if (key instanceof String) {
				String keyString = (String) key;
				if (keyString.equalsIgnoreCase(formatKey)) {
					String formatValue = properties.getProperty(keyString);
					setFormatValue(formatValue);
				}
			}
		}

		if (this.formatValue != null) {
			for (Object key : properties.keySet()) {
				if (key instanceof String) {
					String keyString = (String) key;
					if (keyString.startsWith(instanceIdentifierPrefix)) {
						String valueString = properties.getProperty(keyString);
						String modifiedValueString = createValueString(valueString);
						LocalDate parsedDate = LocalDate.parse(modifiedValueString, DateTimeFormatter.ofPattern(formatValue));
						dateMap.put(keyString, parsedDate);
					}
				}
			}
		}

	}

	@Override
	public List<LocalDate> getDates() {
		return new ArrayList<LocalDate>(dateMap.values());
	}

	protected abstract void setFormatValue(String formatValue);

	protected abstract String createValueString(String valueString);

	protected void setConstants() {
		formatKey = identifier + "-format";
		instanceIdentifierPrefix = identifier + ".";
	}

}
