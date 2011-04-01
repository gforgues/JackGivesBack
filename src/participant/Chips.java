package participant;

public class Chips {
	int chips = 0;
	int bet = 0;
	
	public Chips(int chips, int bet) {
		this.chips = chips;
		this.bet = bet;
	}
	
	public Chips(int chips) {
		this.chips = chips;
	}
	
	public Chips() {
		this.chips = 0;
		this.bet = 0;
	}
	
	//Sets the new amount of chips for the Player
	public void setChips(int amount) {
		this.chips = amount;
	}
	
	//Adds the new amount (parameter) to the amount of chips already stored in
	//this object
	public void addChips(int amount) {
		if (amount > 0) {
			this.chips += amount;
		}
	}
	
	public boolean removeChips(int amount) {
		//should we throw exceptions for negative amounts or not enough chips?
		if (amount > this.chips || amount <= 0) {
			return false;
		} else {
			this.chips -= amount;
		}
		return true;
	}
	
	public int getChips() {
		return this.chips;
	}
	
	public void setBet(int amount) {
		this.bet = amount;
	}
	
	public int getBet() {
		return this.bet;
	}
}