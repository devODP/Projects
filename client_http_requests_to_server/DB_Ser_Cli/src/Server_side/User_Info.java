package Server_side;

public class User_Info {
	private static boolean lock = true;
	private static String address = "";
	private static int portNumber = -1;

	User_Info(String addr, int port) {
		if (lock == true && address.equals("")) {
			address = addr;
			portNumber = port;
			lock = false;
		}
	}
	
	User_Info(){
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