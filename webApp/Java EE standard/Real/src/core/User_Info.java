package core;

import java.util.ArrayList;
import java.util.List;

public class User_Info {
	private static ArrayList<String[]> fileToUpload = new ArrayList<>();
	
	public User_Info(){
	}
	
	public void addFile(String [] file){
		fileToUpload.add(file);
	}
	
	public ArrayList<String[]> getFiles(){
		return new ArrayList<String[]>(fileToUpload);
	}
	
	public void setFilesEmpty(){
		fileToUpload.clear();
	}
}
