package Server_side;

public class User_Info {
	private static boolean lock = true;
	private static String address = "";
	private static int portNumber = -1;
	private static String fileToUpload[] = new String[2];
	private static boolean isEmptyFile = true;

	User_Info(String addr, int port) {
		if (address.equals("")) {
			address = addr;
			portNumber = port;
			lock = false;
		}
	}
	
	public User_Info(){
	}
	
	public boolean isFileEmpty(){
		return isEmptyFile;
	}
	
	public void setFileEmpty(boolean bool){
		isEmptyFile = bool;
	}

	public void setFileName(String [] file){
		fileToUpload = file;
	}
	
	public String[] getFileName(){
		return fileToUpload;
	}
	
	public String getAddr() {
		return address;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setAddrVoid(){
		address = "";
	}
	
	public void setPortVoid(){
		portNumber = -1;
	}
	
	public void setLockStatus(boolean status) {
		lock = status;
	}

	public boolean getLockStatus() {
		return lock;
	}
}
