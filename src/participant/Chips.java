package participant;

/**
* @author JackGivesBack
*/

public class Chips {
	//Player player;
	int chips;
	int bet;
	private final int MIN_BET = 10;
	private final int MAX_BET = 100;
	
	/**
	 * Constructs a new Chips object.
	 * @param chips - The amount of chips
	 * @param bet - The amount of bet
	 * @throws  IllegalArgumentException if the entered bet is either: negative, 
	 * 									 not a a multiple of 10 or 
	 * 									 more than the amount of chips left
	 * 
	 */
	public Chips(int chips, int bet) throws IllegalArgumentException {
		this.chips = chips;
		if (bet <= this.chips && bet >= 0) {
			this.bet = bet;
		} else if (bet > this.chips) {
				throw new IllegalArgumentException("Bet is greater than the amount of chips");
			} else if (bet < 0) {
				throw new IllegalArgumentException("The bet is negative");
			}	else if( bet % 10!= 0){
				throw new IllegalArgumentException("The bet should be a multiple of 10");
			}
	}

	/**
	 * Constructs a new Chips object
	 * @param chips - The amount of chips
	 * @throws  IllegalArgumentException if the entered bet is negative 
	 * 
	 */
	public Chips(int chips) throws IllegalArgumentException {
		if (chips >= 0) {
			this.chips = chips;
		} else {
			throw new IllegalArgumentException("Starting chip amount is negative");
		}
	}
	/**
	 * Constructs a new empty Chips object
	 *   
	 */
	public Chips() {
		this.chips = 0;
		this.bet = 0;
	}
	
	/**
	 * Sets the new amount of chips for the Player
	 * @param player - The player to which the chips need to be set to
	 * @param amount - The amount of chips 
	 */
	public void setChips(Player player, int amount) {//throws IllegalArgumentException {
		player.setChips(amount);
	}
	
	/**
	 * Replaces the new amount of chips for the Player to the amount of chips 
	 * already stored in this object
	 * @param player - The player to which the chips need to be set to
	 * @param amount - The amount of chips 
	 */
	public boolean addChips(Player player, int amount) {
		return player.addChips(amount);
	}
	
	/**
	 * Returns the amount of chips
	 * @return the amount of chips
	 */
	public int getChips() {
		return this.chips;
	}
	
	/**
	 * Sets the amount of bets
	 * @throws IllegalArgumentException if the entered bet is either: negative, 
	 * 									not at least 10
	 *									not a a multiple of 10 or 
	 *									more than the amount of chips left
	 */
	public void setBet(int amount) throws IllegalArgumentException {
		if (amount >= MIN_BET && amount <= this.chips && amount <= MAX_BET && (amount % 10 == 0)) {
			this.bet = amount;
		} else if (amount < 0) {
			throw new IllegalArgumentException("New bet amount is negative.");
		} else if (amount < MIN_BET) {
			throw new IllegalArgumentException("You must bet at least 10.");
		}else if (amount> MAX_BET){
			throw new IllegalArgumentException("You must bet less than 100.");
		} else if (amount % 10 != 0) {
			throw new IllegalArgumentException("Bet amount must be a multiple of 10.");
			
		} else {
			throw new IllegalArgumentException("New bet is greater than the amount" +
					" of chips the player has.");
		}
	}
	
	/**
	 * Returns the amount of bet
	 * @return the amount of bet
	 */
	public int getBet() {
		return this.bet;
	}
}