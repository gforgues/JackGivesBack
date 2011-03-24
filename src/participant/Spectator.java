package participant;

public class Spectator implements Participant {
	private String userName;
	private String password;
	private String realName;
	private int age;
	
	public void saveGame() {
		Table.saveGame(this);
	}
	
	public void closeGame() {
		Table.closeGame();
	}
	
	public void requestLeave() {
		Table.requestLeave(this);
	}
	
	public String getName() {
		return this.userName;
	}
	
	public void requestView() {
		Table.requestView(this);
	}
}