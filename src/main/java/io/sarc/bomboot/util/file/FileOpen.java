package io.sarc.bomboot.util.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileOpen {
	protected BufferedReader br;
	private FileInputStream fis;

	public BufferedReader openRead(String s) {
		try {
			fis = new FileInputStream(s);
			br = new BufferedReader(new InputStreamReader(fis));
			return br;
		} catch (Exception e) {
			closeRead();
		}
		return null;
	}

	public void closeRead() {
		if (br != null)
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if (fis != null)
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}