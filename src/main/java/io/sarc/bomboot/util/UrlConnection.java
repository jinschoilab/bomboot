package io.sarc.bomboot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlConnection {
	private static Logger log = LoggerFactory.getLogger(UrlConnection.class);

	private final static int connectTimeout = 1000;
	private final static int baseSocketTimeout = 1000;
	private final static int minSocketTimeoutMs = 5;
	
	public static void httpConnection(String target) {
		log.info(target);

		int socketTimeout = RandomUtil.getRandomNumber(10);
		
		try {
			URL url = new URL(target);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
						
			http.setConnectTimeout(connectTimeout);
			
			if ( socketTimeout < minSocketTimeoutMs )
				socketTimeout = minSocketTimeoutMs;
			
            http.setReadTimeout(socketTimeout * baseSocketTimeout);
            
			BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
			
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				if ( inputLine.startsWith("<h2>")) {
					System.out.println(inputLine);
				}
			}
			
			in.close();
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static String getHttpConnectionResult(String target) {
		log.info(target);

		int socketTimeout = RandomUtil.getRandomNumber(10);
		
		StringBuffer sb = new StringBuffer();
		
		try {
			URL url = new URL(target);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
						
			http.setConnectTimeout(connectTimeout);
			
			if ( socketTimeout < minSocketTimeoutMs )
				socketTimeout = minSocketTimeoutMs;
			
            http.setReadTimeout(socketTimeout * baseSocketTimeout);
            
			BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
			
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine); 
			}
			
			in.close();
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}