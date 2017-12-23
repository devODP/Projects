package core;

public final class SQLChecker {
	private final String input;
	
	public SQLChecker(String input){
		this.input = input;
	}
	
	public String getInput(){
		return input;
	}
	
	public boolean isValid(){
		return false;
	}
}
