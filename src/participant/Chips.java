package participant;

public class Chips {
	//Player player;
	int chips;
	int bet;
	
	public Chips(int chips, int bet) throws IllegalArgumentException {
		this.chips = chips;
		if (bet <= this.chips && bet >= 0) {
			this.bet = bet;
		} else if (Math.abs(bet) > this.chips) {
				throw new IllegalArgumentException("Bet is greater than the amount of chips");
			} else if (bet < 0) {
				throw new IllegalArgumentException("The bet is negative");
			}		
	}
	
	public Chips(int chips) throws IllegalArgumentException {
		if (chips >= 0) {
			this.chips = chips;
		} else {
			throw new IllegalArgumentException("Starting chip amount is negative");
		}
	}
	
	public Chips() {
		this.chips = 0;
		this.bet = 0;
	}
	
	//Sets the new amount of chips for the Player
	public void setChips(Player player, int amount) {//throws IllegalArgumentException {
		player.setChips(amount);
	}
	
	//Adds the new amount (parameter) to the amount of chips already stored in
	//this object
	public boolean addChips(Player player, int amount) {
		return player.addChips(amount);
	}
	
	public int getChips() {
		return this.chips;
	}
	
	public void setBet(int amount) throws IllegalArgumentException {
		if (amount >= 0 && amount <= this.chips) {
			this.bet = amount;
		} else if (amount < 0) {
			throw new IllegalArgumentException("New bet amount is negative.");
		} else {
			throw new IllegalArgumentException("New bet is greater than the amount" +
					" of chips the player has.");
		}
	}
	
	public int getBet() {
		return this.bet;
	}
}