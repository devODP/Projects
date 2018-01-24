package core;

import java.util.ArrayList;

public final class SQLChecker {
	private final StringBuilder __input__;

	public SQLChecker(StringBuilder input) {
		this.__input__ = new StringBuilder(input);
	}

	public String getInput() {
		return new StringBuilder(__input__.toString()).toString();
	}

	public boolean isSQLInjection() {
		boolean result = false;

		String[] tokens = __input__.toString().split(" ");

		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].contains("=")) {
				
				StringBuilder identifier = new StringBuilder(tokens[i].substring(0, tokens[i].indexOf("=")));
				StringBuilder value = new StringBuilder(tokens[i].substring(tokens[i].indexOf("=") + 1));
				
				if(identifier.length()==0 && value.length()==0) {
					identifier = new StringBuilder(tokens[i-1]);
					value = new StringBuilder(tokens[i+1]);
				}
				else if(identifier.length()>1 && value.length() == 0){
					value = new StringBuilder(tokens[i+1]);
				}
				else if(identifier.length()==0 && value.length() > 1) {
					identifier = new StringBuilder(tokens[i-1]);
				}
				
				if(identifier.toString().equals(value.toString())){
					result = true;
					break;
				}
				
				System.out.println("The token is: " + tokens[i] + ", the length is " + tokens[i].length());
				System.out.println("Identifier is: " + identifier + ", the length is " + identifier.length());
				System.out.println("value is: " + value + ", the length is " + value.length());
			}
		}
	
		return result;
	}
}
