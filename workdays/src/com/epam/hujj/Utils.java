package com.epam.hujj;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	public static Properties createProperties(String path) throws IOException {
		Properties workdayProperties = new Properties();
		InputStream is = ClassLoader.getSystemResourceAsStream(path);
		workdayProperties.load(is);
		is.close();
		return workdayProperties;
	}

}
