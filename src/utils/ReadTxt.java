package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTxt {

	private String path;

	public ReadTxt(String path) {
		this.path = path;
	}

	public List<String> readFile() {

		try {
			FileReader arq = new FileReader(path);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			List<String> arquivo = new ArrayList<String>();
			
			while(linha != null){
				arquivo.add(linha);
				linha = lerArq.readLine();
			}
			
			return arquivo;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
