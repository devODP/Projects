package core;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Attendance {
	private Map<String, ArrayList<Boolean>> attendanceList = new HashMap<>();
	
	public Attendance(){}
	
	@Lock
	public void putIPStatus(String ip, ArrayList<Boolean> statList){
		attendanceList.put(ip, statList);
	}
	
	public ArrayList<Boolean> getIPStatus(String ip){
		return new ArrayList<Boolean>(attendanceList.get(ip));
	}
	
	@Lock
	public void removeIP(String ip){
		attendanceList.remove(ip);
	}

	public boolean containsIP(String ip){
		return attendanceList.containsKey(ip);
	}

	@Lock
	public void updateStatus(String ip, ArrayList<Boolean> statList){
		attendanceList.replace(ip, statList);
	}
}
