package core;

import java.util.ArrayList;

public class User_Info {
	private static ArrayList<String[]> __fileToUpload__ = new ArrayList<>();
	
	public User_Info(){
	}
	
	public void addFile(String [] file){
		__fileToUpload__.add(file);
	}
	
	public ArrayList<String[]> getFiles(){
		return new ArrayList<String[]>(__fileToUpload__);
	}
	
	public void setFilesEmpty(){
		__fileToUpload__.clear();
	}
}
