package io.sarc.bomboot.aloft;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class IP {
	private static String ipApiUrlStr = "http://ip-api.com/json/";
	private static HttpURLConnection httpuc = null;
		
	public static boolean isIpv4(String ip) {
		String s;
		try {
			if ( ip.endsWith(",") ) s = ip.replace(",", "");
			else s = ip;
			
			if (s == null || s.isEmpty()) {
				return false;
			}

			String[] parts = s.split("\\.");
			if (parts.length != 4) {
				return false;
			}

			for (String p : parts) {
				int i = Integer.parseInt(p);
				if ((i < 0) || (i > 255)) {
					return false;
				}
			}
			if (s.endsWith(".")) {
				return false;
			}

			return true;
		} catch (NumberFormatException nfe) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isPrivate(String s) {
        InetAddress ia = null;

        try {
            InetAddress ad = InetAddress.getByName(s);
            byte[] ip = ad.getAddress();
            ia = InetAddress.getByAddress(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ia.isSiteLocalAddress();
    }
		
	public static String[] getInfo(String s) {		
		if (isIpv4(s)) {
			try {
				URL ipApiUrl = new URL(ipApiUrlStr + s);
				httpuc = (HttpURLConnection) ipApiUrl.openConnection();
				httpuc.setConnectTimeout(5000);
				httpuc.setReadTimeout(5000);

				if (httpuc.getResponseCode() == 200) {
					JSONObject ipJson = (JSONObject) JSONValue.parse(new InputStreamReader(httpuc.getInputStream()));
					String co = (String) ipJson.get("country");
					String ci = (String) ipJson.get("city");
					String or = (String) ipJson.get("org");
					return new String[]{co, ci, or};
				}
			} catch (IOException e) {
				//e.printStackTrace();
			} finally { 
				try {
					if (httpuc != null)
						httpuc.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
}