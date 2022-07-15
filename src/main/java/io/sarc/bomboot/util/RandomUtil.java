package io.sarc.bomboot.util;

import java.util.Random;

public class RandomUtil {
	public static int getRandomNumber(int i) {
		Random random = new Random();
		return random.nextInt(i);
	}
}