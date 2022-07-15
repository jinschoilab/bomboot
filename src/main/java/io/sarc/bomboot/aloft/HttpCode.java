package io.sarc.bomboot.aloft;

import io.sarc.bomboot.util.TypeUtil;

public class HttpCode {
	public static boolean valid(String s) {
		try {
			int code = Integer.parseInt(s);

			System.out.println("Code : " + code);

			if (code >= 500) {
				if (code <= 505)
					return true;
			} else if (code >= 400) {
				if (code <= 417)
					return true;
			} else if (code >= 300) {
				if (code <= 307)
					return true;
			} else if (code >= 200) {
				if (code <= 206)
					return true;
			} else if (code >= 100) {
				if (code <= 101)
					return true;
			}
		} catch (Exception e) {
			//
		}

		return false;
	}

	public static String getName(String s) {
		String r = "";

		if (TypeUtil.isInteger(s)) {
			switch (Integer.parseInt(s)) {
			case 200:
				r = "OK";
				break;
			case 206:
				r = "Partial Content";
				break;
			case 301:
				r = "Moved Permanently";
				break;
			case 302:
				r = "Found";
				break;
			case 304:
				r = "Not Modified";
				break;
			case 400:
				r = "Bad Request";
				break;
			case 403:
				r = "Forbidden";
				break;
			case 404:
				r = "Not Found";
				break;
			case 405:
				r = "Method Not Allowed";
				break;
			case 406:
				r = "Not Acceptable";
				break;
			case 407:
				r = "Proxy Authentication Required";
				break;	
			case 408:
				r = "Request Timeout";
				break;
			case 411:
				r = "Length Required";
				break;
			case 412:
				r = "Precondition Failed";
				break;
			case 500:
				r = "Internal Server Error";
				break;
			case 501:
				r = "Not Implemented";
				break;
			case 502:
				r = "Bad Gateway";
				break;
			case 503:
				r = "Service Unavailable";
				break;
			case 504:
				r = "Gateway Timeout";
				break;
			case 505:
				r = "HTTP Version Not Supported";
				break;
			}
		}
		return r;
	}
	
	public static boolean isOddCode(String s) {
		if (s.contains(" \"-\" 408 - ")) {
			return true;
		}
		if (s.contains(" \"-\" 408 0 ")) {
			return true;
		} else if (s.contains(" \"\" 414 ")) {
			return true;
		} else if (s.contains(" 501 ")) {
			if (s.contains(" \"\" 501 ")) {
				return true;
			} else if (s.contains(" \" \" 501 ")) {
				return true;
			} else if (s.contains("\"\\x")) {
				return true;
			} else if (s.contains("SSH-2.0")) {
				return true;
			}
		}
		return false;
	}
}