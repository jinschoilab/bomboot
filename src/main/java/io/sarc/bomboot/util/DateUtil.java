package io.sarc.bomboot.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	public static String getFromTodayYYYYMMDD(int d) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, d);
		return new SimpleDateFormat("yyyyMMdd").format(c.getTime());
	}
}