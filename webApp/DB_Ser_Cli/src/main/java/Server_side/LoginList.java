package Server_side;

import java.util.ArrayList;
import java.util.List;

public class LoginList{
	private static List<String> accessList;
	
	public static List<String> getInstance(){
		//double check locking
		if(accessList == null){
			synchronized(LoginList.class){
				if(accessList == null){
					accessList = new ArrayList<>(); 
				}
			}
		}
		return new ArrayList<>(accessList);
	}
	
	public static synchronized void add(String s){
		accessList.add(s);
	}
}