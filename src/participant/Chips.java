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
	
	public void setChips(int amount) {
		this.chips += amount;
	}
	
	//forget what this method means / does COME BACK
	public void sellChips(int amount) {
		if (amount >= this.chips) {
			//HOW DO YOU THROW EXCEPTIONS?
			//throw NotEnoughChipsException;
		} else if (amount < 0) {
			//throw NegativeAmountException;
		} else {
			this.chips -= amount;
		}
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