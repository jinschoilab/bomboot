package io.sarc.bomboot.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MapData {
	public static HashMap<Integer, String> map = new HashMap<Integer, String>();
	
	public static void loadList(ArrayList<String> list) {
		System.out.println("list.size="+list.size());
		map.clear();
		
		for ( int i=0; i<list.size(); i++ ) {
			map.put(i, list.get(i));
		}
		
		System.out.println("loadList.map.size="+map.size());
	}
	
	public static String getRandom() {
		System.out.println("map.size="+map.size());
		Random generator = new Random();
		Object[] values = map.values().toArray();
		Object randomValue = values[generator.nextInt(values.length)];
		
		return randomValue + "";
	}
}