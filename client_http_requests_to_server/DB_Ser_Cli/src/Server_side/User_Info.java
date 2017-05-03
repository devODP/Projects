package Server_side;

public class User_Info {
	private static boolean lock = true;
	private static String address;
	private static int portNumber;

	User_Info(String addr, int port) {
		if (lock == true) {
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

	public void setLockStatus(boolean status) {
		lock = status;
	}

	public boolean getLockStatus() {
		return lock;
	}
}
