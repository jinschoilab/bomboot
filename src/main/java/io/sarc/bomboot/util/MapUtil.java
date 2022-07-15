package io.sarc.bomboot.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class MapUtil {
	public static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValuesAsc(
			Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				int res = e1.getValue().compareTo(e2.getValue());
				return res != 0 ? res : 1;
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	public static <K, V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValuesDesc(Map<K, V> map) {
		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());
		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		return sortedEntries;
	}
	
	public static void addMap(TreeMap<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			map.put(key, (map.get(key) + 1));
		} else {
			map.put(key, 1);
		}	
	}
	
	public static void addMap(HashMap<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			map.put(key, (map.get(key) + 1));
		} else {
			map.put(key, 1);
		}	
	}
}