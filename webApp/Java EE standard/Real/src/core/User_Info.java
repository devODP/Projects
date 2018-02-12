package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User_Info {
	private static Map<String, ArrayList<String[]>> __fileToUpload__ = getAttendance();

	public User_Info() {
	}

	public static Map<String, ArrayList<String[]>> getAttendance() {
		if (__fileToUpload__ == null) {
			synchronized (User_Info.class) {
				if (__fileToUpload__ == null) {
					__fileToUpload__ = new HashMap<>();
				}
			}
		}

		return __fileToUpload__;
	}

	synchronized public void addFile(String name, String[] file) {
		if (__fileToUpload__.containsKey(name)) {
			__fileToUpload__.get(name).add(file);
		}else {
			ArrayList<String[]> tmp = new ArrayList<>();
			tmp.add(file);
			__fileToUpload__.put(name, tmp);
		}
	}

	public ArrayList<String[]> getFiles(String name) {
		return new ArrayList<>(__fileToUpload__.get(name));
	}

	public void setFilesEmpty(String name) {
		__fileToUpload__.get(name).clear();
	}
	
	public void setPairEmpty(String name) {
		__fileToUpload__.remove(name);
	}
}
