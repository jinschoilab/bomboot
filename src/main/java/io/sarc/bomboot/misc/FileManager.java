package io.sarc.bomboot.misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.sarc.bomboot.util.RandomUtil;

public class FileManager {
	private static Logger log = LoggerFactory.getLogger(FileManager.class);

	private String ext = ".dat";
	
	public long file(String fileName, String value) {
		File file = create(fileName);
		
		long bytes = write(file, fileName, value);
		
		return bytes;
	}
	
	private File create(String fullFileName) {		
		File file = new File(fullFileName);
		
		log.debug(fullFileName + " exists : " + file.exists());

		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.debug(fullFileName + " exists : " + file.exists());
		
		return file;
	}
	
	private long write(File file, String fileName, String value) {
		String fullFileName = fileName + ext;
			
		long bytes = 0L;
		int fc = RandomUtil.getRandomNumber(10);
		
		for (int i = 0; i < fc; i++) {
			try (FileWriter fileWriter = new FileWriter(file)) {
				PrintWriter printWriter = new PrintWriter(fileWriter);

				for (int j = 0; j < fc; j++) {
					printWriter.print(value);
					printWriter.print("/");
					
					int intValue = 0;
					
					for ( int k = 0; k < value.length(); k++ ) {
						int x = (char)value.charAt(k);
						intValue += x;
					}

					printWriter.print(intValue);
					printWriter.println("/");
				}

				Path path = Paths.get(fileName);
				bytes = Files.size(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		log.debug(fullFileName + " exists : " + file.exists());
		
		return bytes;
	}
}