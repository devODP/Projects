package core;

import java.util.ArrayList;
import java.util.List;

public class User_Info {
	private static String fileToUpload[] = new String[2];
	
	public User_Info(){
	}
	
	public void setFile(String [] file){
		fileToUpload = file;
	}
	
	public String[] getFileName(){
		return fileToUpload;
	}
}
