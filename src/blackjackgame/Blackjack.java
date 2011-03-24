
package blackjackgame;

import cards.Card;
import cards.CardList;
import cards.Deck;

/**
 *
 * @author Client
 */
public class Blackjack {

    private Player player;
    private Player dealer;
    private Deck deckCards = new Deck();
    private CardList playerHand;
    private CardList dealerHand;

    public void deal(){
        for(int i=0; i>=2; i++){
        Card drawnCardPlayer = deckCards.draw();
        playerHand.add(drawnCardPlayer);
        Card drawnCardDealer = deckCards.draw();
        dealerHand.add(drawnCardDealer);
        }

    }
    public Card hit(CardList hand){
        Card hitCard = deckCards.draw();
        hand.add(hitCard);
        return hitCard;
    }
    public CardList stand(CardList hand){
        return hand;
    }

    public void doubleDown(){
    		testing;

    }

    public void split(){

    }

    public void dealerDoes(){

    }

    public void bet(){

    }
    public int getPoints(CardList hand){
        int points;
        
        return 5;
    }

    public void checkBust(CardList hand){
        
    }

    public void checkBlackjack(CardList hand){

    }

    public void checkWin(CardList hand){

    }


}
