package com.epam.hujj;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkdaysMain {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkdaysMain.class);
	private static final int YEAR = 2016;

	public static void main(String[] args) {

		logger.info("Application started..");
		
		logger.info("Workdays in {} are the following..", YEAR);
		WorkdayCalendar wdc = new WorkdayCalendar(YEAR);
		List<LocalDate> totalDays = wdc.getWorkdays();
		for (LocalDate date : totalDays) {
			System.out.println(date);
		}
		
		logger.info("Application closing..");

	}

}
