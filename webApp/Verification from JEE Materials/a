package core;

public class AuthenticationScheme {
	private static boolean visitedFromLogin;
	private static boolean returnedFromUpload;
	private static boolean freshUser = true;
	private static boolean isLogin;

	public AuthenticationScheme() {
		if (freshUser == true) {
			visitedFromLogin = false;
			returnedFromUpload = false;
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

	public void setVisitedLogin(boolean bool) {
		visitedFromLogin = bool;
	}

	// getter
	public boolean getReturnedFromUpload() {
		return returnedFromUpload;
	}

	public boolean getVisitedLogin() {
		return visitedFromLogin;
	}
}
