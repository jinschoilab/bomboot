package io.sarc.bomboot.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	public static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
	
	public static boolean isFile(String s) {
		File f = new File(s);

		if (f.exists() && !f.isDirectory()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isContain(String f, String s) {
		return isContain(f, s, "grep");
	}

	public static boolean isContain(String f, String s, String which) {
		boolean r = false;
		BufferedReader br = null;
		FileReader fr = null;

		if (isFile(f)) {

			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				String line;

				while ((line = br.readLine()) != null) {
					if (line.contains(s)) {
						if (which.equals("grep")) {
							r = true;
							break;
						} else if (which.equals("start") && line.startsWith(s)) {
							r = true;
							break;
						} else if (which.equals("end") && line.endsWith(s)) {
							r = true;
							break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null)
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				if (fr != null)
					try {
						fr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}

		return r;
	}
}