package DB_entries_randomization;

public class ITableInfo {
	private static String tName;
	private static String aNames;
	
	public ITableInfo(String str1, String str2){
		tName = str1;
		aNames = str2;
	}
	
	public ITableInfo(){
	}
	
	public void setTName (String str){
		tName = str;
	}
	
	public void setaNames (String str){
		aNames = str;
	}
	
	public String getTName(){
		return tName;
	}
	
	public String getANames(){
		return aNames;
	}
}
