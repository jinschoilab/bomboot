package io.sarc.bomboot.util.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.sarc.bomboot.util.RandomUtil;

public class FileManager {
	private static Logger log = LoggerFactory.getLogger(FileManager.class);

	private String ext = ".dat";
	private String fullFileName = null;

	public long file(String fileName, String value) {
		fullFileName = fileName + ext;

		File file = create(fullFileName);

		long bytes = write(file, fileName, value);
		
		read(fullFileName);

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
		long bytes = 0L;
		int fc = RandomUtil.getRandomNumber(10);

		for (int i = 0; i < fc; i++) {
			try (FileWriter fileWriter = new FileWriter(file)) {
				PrintWriter printWriter = new PrintWriter(fileWriter);

				for (int j = 0; j < fc; j++) {
					printWriter.print(value);
					printWriter.print("/");

					int intValue = 0;

					for (int k = 0; k < value.length(); k++) {
						int x = (char) value.charAt(k);
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

	private void read(String fullFileName) {
		try {
			List<String> allLines = Files.readAllLines(Paths.get(fullFileName));
			
			for (String line : allLines) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}