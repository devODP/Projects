package Server_side;

public class EmployeeDTO {
	static String mainPath = System.getProperty("user.dir");
	//private properties
	private int empNo;
	private String eName;
	private String jobTitle;
	
	//setters
	public void setEmpNo(int val){
		empNo=val;
	}
	
	public void setEName(String val){
		eName = val;
	}
	
	public void setJobTitle(String val){
		jobTitle = val;
	}
	
	public int getEmpNo(){
		return empNo;
	}
	
	public String getEName(){
		return eName;
	}
	
	public String getJobTitle(){
		return jobTitle;
	}
}
