package io.sarc.bomboot.bank;

import java.util.HashMap;
import java.util.Map;

public class ExchangeData {
	public static Map<String, Float> RATE_DATA;
	public static Map<String, Long> RATE_LAST_TIMPSTAMP;
	
	public final static int CACHE_TIME_SEC = 300 * 1000;
	
	public static void initData() {
		RATE_DATA = new HashMap<String, Float>();
		RATE_LAST_TIMPSTAMP = new HashMap<String, Long>();
	}

	public static void print() {
		for (String key : RATE_DATA.keySet()) {
			System.out.println(key + " : " + RATE_DATA.get(key));
		}
	}

	public static Float get(String key) {
		return RATE_DATA.get(key);
	}

	public static long dataTimeGap(String key) {
		long timeGap = 0L;

		if (RATE_LAST_TIMPSTAMP.containsKey(key)) {
			timeGap = System.currentTimeMillis() - RATE_LAST_TIMPSTAMP.get(key);
		} else {
			timeGap = 0;
		}

		return timeGap;
	}

	public static boolean needUpdate(String key) {
		long timeGap = dataTimeGap(key);

		System.out.println("ExchangeData.timeGap=" + timeGap);

		if (timeGap > CACHE_TIME_SEC || timeGap == 0) {
			return true;
		} else {
			return false;
		}
	}
}