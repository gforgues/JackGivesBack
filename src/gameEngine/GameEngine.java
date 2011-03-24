package gameEngine;

import game.*;
import cards.*;

	public class GameEngine	{
		
		public GameEngine() {
			myGame = new Blackjack();
			
		}
		
		public void reset() {
			myGame = new Blackjack();
			
		}
	

		/*
		 * Initialize game (Blackjack)
		 */
		private Game myGame;
		
	}
