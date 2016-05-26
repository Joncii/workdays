package com.epam.hujj;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.epam.hujj.dateinterpreters.VacationInterpreter;

public class WorkdayCalendar {

	private List<DayOfWeek> validWorkdays = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
			DayOfWeek.FRIDAY);

	private static final String WORKDAYS_PATH = "workdays.properties";

	private Properties workdayProperties;

	private int year;

	private LocalDate firstDayOfYear;

	private LocalDate lastDayOfYear;

	private List<LocalDate> totalDays;

	private List<LocalDate> extraWorkdays;

	private List<LocalDate> extraVacationdays;

	private List<LocalDate> standardWorkdays;

	private List<LocalDate> vacationDays;

	public WorkdayCalendar(int year) {
		this.extraVacationdays = new ArrayList<LocalDate>();
		this.extraWorkdays = new ArrayList<LocalDate>();
		this.standardWorkdays = new ArrayList<LocalDate>();
		this.totalDays = new ArrayList<LocalDate>();
		this.vacationDays = new ArrayList<LocalDate>();
		this.year = year;
		this.firstDayOfYear = LocalDate.parse(year + "-01-01");
		this.lastDayOfYear = LocalDate.parse(year + "-12-31");

		try {
			workdayProperties = Utils.createProperties(WORKDAYS_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		populateAllDaysInYear();
		populateStandardWorkdays();
		substractVacationDays();
	}

	private void populateAllDaysInYear() {

		LocalDate temp = firstDayOfYear;
		while (!temp.isAfter(lastDayOfYear)) {
			totalDays.add(temp);
			temp = temp.plusDays(1);
		}

	}

	private void populateStandardWorkdays() {
		for (LocalDate day : totalDays) {
			DayOfWeek dayOfWeek = day.getDayOfWeek();
			if (validWorkdays.contains(dayOfWeek)) {
				standardWorkdays.add(day);
			}
		}
	}

	private void substractVacationDays() {
		if (workdayProperties != null) {
			VacationInterpreter vacInt = new VacationInterpreter(year + "");
			vacInt.readFromProperties(workdayProperties);
			vacationDays = vacInt.getDates();
		}
	}

	public List<LocalDate> getTotalDays() {
		return this.totalDays;
	}

	public List<LocalDate> getStandardWorkdays() {
		return this.standardWorkdays;
	}

}
