package com.epam.hujj;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.epam.hujj.dateinterpreters.ExtraVacationInterpreter;
import com.epam.hujj.dateinterpreters.ExtraWorkdayInterpreter;
import com.epam.hujj.dateinterpreters.VacationInterpreter;

public class WorkdayCalendar {

	private List<DayOfWeek> validWorkdays = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
			DayOfWeek.FRIDAY);

	private static final String WORKDAYS_PATH = "workdays.properties";

	private Properties workdayProperties;

	private int year;

	private LocalDate firstDayOfYear;

	private LocalDate lastDayOfYear;

	private List<LocalDate> extraWorkdays;

	private List<LocalDate> extraVacationdays;

	private List<LocalDate> standardWorkdays;

	private List<LocalDate> vacationDays;

	private List<LocalDate> workdays;

	public WorkdayCalendar(int year) {
		this.standardWorkdays = new ArrayList<LocalDate>();
		this.year = year;
		this.firstDayOfYear = LocalDate.parse(this.year + "-01-01");
		this.lastDayOfYear = LocalDate.parse(this.year + "-12-31");

		try {
			workdayProperties = Utils.createProperties(WORKDAYS_PATH);
			
			VacationInterpreter vacInt = new VacationInterpreter(year + "");
			vacInt.readFromProperties(workdayProperties);
			vacationDays = vacInt.getDates();
			
			ExtraVacationInterpreter extraVacInt = new ExtraVacationInterpreter();
			extraVacInt.readFromProperties(workdayProperties);
			extraVacationdays = extraVacInt.getDates();
			
			ExtraWorkdayInterpreter extraWorkInt = new ExtraWorkdayInterpreter();
			extraWorkInt.readFromProperties(workdayProperties);
			extraWorkdays = extraWorkInt.getDates();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		populateStandardWorkdays();
		removeVacationDays();
		removeExtraVacationDays();
		addExtraWorkday();
		Collections.sort(workdays);
		
	}

	private void addExtraWorkday() {
		for(LocalDate date : extraWorkdays){
			if(!workdays.contains(date)){
				workdays.add(date);
			}
		}
	}

	private void removeExtraVacationDays() {
		List<LocalDate> temp = new ArrayList<LocalDate>();
		temp.addAll(workdays);
		for(LocalDate date : workdays){
			if(extraVacationdays.contains(date)){
				temp.remove(date);
			}
		}
		workdays = temp;
	}

	private void removeVacationDays() {
		List<LocalDate> temp = new ArrayList<LocalDate>();
		temp.addAll(standardWorkdays);
		for(LocalDate date : standardWorkdays){
			if(vacationDays.contains(date)){
				temp.remove(date);
			}
		}
		workdays = temp;;
	}

	private void populateStandardWorkdays() {

		LocalDate temp = firstDayOfYear;
		while (!temp.isAfter(lastDayOfYear)) {
			DayOfWeek dayOfWeek = temp.getDayOfWeek();
			if(validWorkdays.contains(dayOfWeek)){				
				standardWorkdays.add(temp);
			}
			temp = temp.plusDays(1);
		}

	}
	
	public List<LocalDate> getWorkdays(){
		return workdays;
	}

}
