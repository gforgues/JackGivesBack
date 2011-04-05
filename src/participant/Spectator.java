package participant;

import table.Table;

public class Spectator { //implements Participant {
	
	private String userName;
	private String password;
	private String realName;
	private int age;
	
	public Spectator(String userName, String password, String realName, int age){
		this.userName=userName;
		this.password=password;
		this.realName=realName;
		this.age=age;
	}
	
	public Spectator(String userName, String password){
		new Spectator(userName, password, "", -1);
	}
	
//	public void saveGame() {
//		Table.saveGame(this);
//	}
	
	public void requestLeave(Table table, boolean canLeave) {
		table.requestLeave(this, canLeave);
	}
	
	public String getUsername() {
		return this.userName;
	}
	
	public void requestView(Table table, boolean canView) {
		table.requestView(this, canView);
	}
}