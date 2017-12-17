package by.htp.speq.logic;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static Date createDate(int year, int month, int day, int hour, int minute) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hour, minute);
		Date date = cal.getTime();
		
		return date;
	}
}
