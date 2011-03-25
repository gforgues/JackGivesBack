package storage;

public interface StorageInterface {
	
	public boolean savePlayer();
	
	public boolean validatePassword(String inputPassword);
	public void changePassword(String password);
	
	public void setRealName(String realName);
	public String getRealName();
	
	public void setAge(int age);
	public int getAge();
	
	public int getChips();
	public void addChips(int betValue);
	
	public void addWin();
	public int getNumWins();
	
	public void addLoss();
	public int getNumLosses();
	
	public int getNumGames();
}
