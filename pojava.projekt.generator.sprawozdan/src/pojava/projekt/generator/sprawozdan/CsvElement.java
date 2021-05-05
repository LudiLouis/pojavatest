package pojava.projekt.generator.sprawozdan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Ada Szymorek

public abstract class CsvElement extends TexElement {

	
	int numberOfLines;
	int numberOfColumns;
	//ArrayList<Double[]> data = new ArrayList<Double[]>();
	ArrayList<String[]> sData = new ArrayList<String[]>();
	
	public CsvElement(File file) {
		setsData(file);
		//setData(sData);
		setNumberOfColumns();
		setNumberOfLines();
	}
	
	
	public int getNumberOfLines() {
		return numberOfLines;
	}


	public void setNumberOfLines() {
		numberOfLines = sData.size();
	}


	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns() {
		numberOfColumns = sData.get(0).length;
	}


	/*public ArrayList<Double[]> getData() {
		return data;
	}*/


	/*public void setData(ArrayList<String[]> data1) {
		for(int i = 0; i<data1.size(); i++) {

			Double[] s = new Double[data1.get(i).length];
			for(int j = 0; j<data1.get(i).length; j++)
				{
				s[j] = Double.parseDouble(data1.get(i)[j]); 
				data.add(s);
				System.out.println(data.get(i)[j]);
				}
		}
		 
		 }*/


	public ArrayList<String[]> getsData() {
		return sData;
	}


	public void setsData(File file) {
		String line;
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null) {
				sData.add(line.split(","));
			}
			br.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}


	@Override
	String makeElementCode() {
		return null;
	}

}
