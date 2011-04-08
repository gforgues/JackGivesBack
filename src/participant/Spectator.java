package participant;

import table.Table;

/**
 * 
 * Spectator object: Spectator is player who can only observe a game in Table
 * @author JackGivesBack
 *
 */
public class Spectator { 
	
	private String userName;
	private String password;
	private String realName;
	private int age;
	
	/**
	 * 
	 * Constructs a new Spectator object using a usermname password
	 * real name and an age
	 * @param userName
	 * @param password
	 * @param realName
	 * @param age
	 */
	public Spectator(String userName, String password, String realName, int age){
		this.setUserName(userName);
		this.setPassword(password);
		this.setRealName(realName);
		this.setAge(age);
	}
	
	/**
	 * 
	 * Constructs a new Spectator object using just a username and password
	 * @param userName 
	 * @param password
	 */
	public Spectator(String userName, String password){
		new Spectator(userName, password, "", -1);
	}

	/**
	 * 
	 * Checks if Spectator can leave table
	 * @param table the table to request
	 * @param canLeave to check if spectator can leave
	 */
	public void requestLeave(Table table, boolean canLeave) {
		table.requestLeave(this, canLeave);
	}
	
	/**
	 * 
	 * Checks if Spectator can view table
	 * @param table the table to request
	 * @param canView to check if spectator can view 
	 */
	public void requestView(Table table, boolean canView) {
		table.requestView(this, canView);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealName() {
		return realName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUsername() {
		return userName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
}