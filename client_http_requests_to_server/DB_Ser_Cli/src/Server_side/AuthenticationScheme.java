package Server_side;

public class AuthenticationScheme {
	private static boolean visitedFromLogin;
	private static boolean visitedFromReception;
	private static boolean returnedFromUpload;
	private static boolean returnedFromLogout;
	private static boolean freshUser = true;
	private static boolean isLogin;

	public AuthenticationScheme() {
		if (freshUser == true) {
			visitedFromLogin = false;
			visitedFromReception = false;
			returnedFromUpload = false;
			returnedFromLogout = false;
			freshUser = false;
			isLogin = false;
		}
	}
	
	public void setIsLogin(boolean bool){
		isLogin = bool;
	}
	
	public boolean getIsLogin(){
		return isLogin;
	}
	
	public void setFreshUser(boolean bool){
		freshUser = bool;
	}
	
	public boolean getFreshUser(){
		return freshUser;
	}

	public void setReturnedFromUpload(boolean bool) {
		returnedFromUpload = bool;
	}

	public void setReturnedFromLogout(boolean bool) {
		returnedFromLogout = bool;
	}

	public void setVisitedLogin(boolean bool) {
		visitedFromLogin = bool;
	}

	public void setVisitedByReception(boolean bool) {
		visitedFromReception = bool;
	}

	// getter
	public boolean getReturnedFromUpload() {
		return returnedFromUpload;
	}

	public boolean getReturnedFromLogout() {
		return returnedFromLogout;
	}

	public boolean getVisitedLogin() {
		return visitedFromLogin;
	}

	public boolean getVisitedFromReception() {
		return visitedFromReception;
	}
}
