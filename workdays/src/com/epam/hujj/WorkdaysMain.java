package com.epam.hujj;

import java.time.LocalDate;
import java.util.List;

public class WorkdaysMain {

	private static final String WORKDAYS_PATH = "workdays.properties";

	public static void main(String[] args) {

		// try {
		// Properties workdaysProp = Utils.createProperties(WorkdaysMain.WORKDAYS_PATH);
		// VacationInterpreter vacation = new VacationInterpreter("2016");
		// vacation.readFromProperties(workdaysProp);
		// Iterator<LocalDate> dates = vacation.getDates();
		// while (dates.hasNext()) {
		// LocalDate nextDate = dates.next();
		// System.out.println(nextDate);
		// }
		//
		// ExtraVacationInterpreter extraVac = new ExtraVacationInterpreter();
		// extraVac.readFromProperties(workdaysProp);
		// Iterator<LocalDate> extraVacIter = extraVac.getDates();
		// while (extraVacIter.hasNext()) {
		// LocalDate nextDate = extraVacIter.next();
		// System.out.println(nextDate);
		// }
		//
		// ExtraWorkdayInterpreter extraWorkday = new ExtraWorkdayInterpreter();
		// extraWorkday.readFromProperties(workdaysProp);
		// Iterator<LocalDate> extraWorkdayIter = extraWorkday.getDates();
		// while (extraWorkdayIter.hasNext()) {
		// LocalDate nextDate = extraWorkdayIter.next();
		// System.out.println(nextDate);
		// }
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		WorkdayCalendar wdc = new WorkdayCalendar(2016);
		List<LocalDate> totalDays = wdc.getStandardWorkdays();
		for (LocalDate date : totalDays) {
			System.out.println(date);
		}

	}

}
