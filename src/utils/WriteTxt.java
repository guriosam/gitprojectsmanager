package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteTxt {
	private String path;
	private String fileName;

	public WriteTxt(String path, String fileName) {
		this.path = path;
		this.fileName = fileName;
	}

	public void writeFile(String data) {

		try {
			String d = path;

			d = d.trim();

			File dir = new File(d);

			if (!dir.exists()) {
				dir.mkdir();
			}

			File file = new File(d + "/" + fileName + ".txt");

			System.out.println(d + "/" + fileName);

			// if file doesnt exists, then create it if (!file.exists()) {
			file.createNewFile();

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();

			//System.out.println("File " + fileName + " saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
