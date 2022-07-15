package io.sarc.bomboot.aloft;

public class Method {
	private static String[] methods = { "GET", "POST", "HEAD", "PUT", "DELETE", "OPTIONS", "CONNECT", "PROPFIND" };
	
	public static boolean valid(String s) {
		for ( String m : methods ) {
			if ( s.equals(m) ) {
				return true;
			}
		}
		return false;
	}
}