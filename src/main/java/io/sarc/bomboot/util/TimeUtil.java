package io.sarc.bomboot.util;

public class TimeUtil {
	public static void sleep(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}