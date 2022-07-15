package io.sarc.bomboot.util;

import io.sarc.bomboot.Constants;

public class StringUtil {
	public static boolean isNull(String s) {
		boolean result = false;

		if (s == null || s.equals("undefined") || s.equals("null") || s.equals("NULL") || s.equals("")
				|| s.equals("blank"))
			result = true;

		return result;
	}

	public static String isNull(String s, String v) {
		String result = null;

		if (s == null || s.equals("undefined") || s.equals("null") || s.equals("NULL") || s.equals("")
				|| s.equals("blank"))
			result = v;
		else
			result = s;

		return result;
	}

	public static String subStr(String s, int startIdx, int bytes) {
		return new String(s.getBytes(), startIdx, bytes);
	}

	public static String removeQuote(String s) {
		try {
			return s.replace("\"", "");
		} catch (Exception e) {
			return s;
		}
	}

	public static String lpad(String s, int l) {
		String r = isNull(s, "-");
		if (r.getBytes().length > (l - 1))
			r = subStr(r, 0, (l - 1));

		int templen = l - r.getBytes().length;

		for (int i = 0; i < templen; i++) {
			r = " " + r;
		}

		return r;
	}

	public static String rpad(String s, int l) {
		String r = isNull(s, "-");
		if (r.getBytes().length > (l - 1))
			r = subStr(r, 0, (l - 1));

		int templen = l - r.getBytes().length;

		for (int i = 0; i < templen; i++) {
			r = r + " ";
		}

		return r;
	}

	public static String getYourLine(String s, int w) {
		return repeatChar(s, w);
	}
	
	public static void printTableLine(int kw, int v1w) {
		System.out.print("+");
		System.out.print(repeatChar("-", kw + 2));
		System.out.print("+");
		System.out.print(repeatChar("-", v1w + 2));
		System.out.print("+" + Constants.NL);
	}

	public static void printTableLine(int kw, int v1w, int v2w) {
		System.out.print("+");
		System.out.print(repeatChar("-", kw + 2));
		System.out.print("+");
		System.out.print(repeatChar("-", v1w + 2));
		System.out.print("+");
		System.out.print(repeatChar("-", v2w + 2));
		System.out.print("+" + Constants.NL);
	}

	public static String repeatChar(String s, int i) {
		return new String(new char[i]).replace("\0", s);
	}

	public static boolean isContainInt(String s) {
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				return true;
			}
		}
		return false;
	}
}