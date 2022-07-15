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

	private static int connectTimeout = 1000;
	private static int baseSocketTimeout = 1000;
	
	public static void httpConnection(String target) {
		log.info(target);

		int socketTimeout = RandomUtil.getRandomNumber(10);
		
		try {
			URL url = new URL(target);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
						
			http.setConnectTimeout(connectTimeout);
			
			if ( socketTimeout < 5 )
				socketTimeout = 5;
			
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
}