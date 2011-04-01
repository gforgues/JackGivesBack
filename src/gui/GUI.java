package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import cards.Card;
import cards.CardImages;

import game.Observer;



public class GUI extends MainApplication implements Observer
{

	@Override
	public void handleEvent()
	{
		// TODO Auto-generated method stub
		
	}

	
	
	public GUI() {
		table.setOpaque(false);
		table.setLayout(null);
	}
	
	public JPanel getJPanel() {
		return table;
	}
	
	public void updateCards() {
		
	}
	
	private void renewPanel() {
		renewTable(table, this.createTable());
		table.invalidate();
		table.validate();
		table.repaint();
	}
	
	private JPanel createTable() {
		
		/*
		 * Reset panel.
		 */
		table.removeAll();
		table.setOpaque(false);
		
		

		
		if (dealerCard == null){
			dealerCards = new JLabel("");
		} else {
			dealerCards = new JLabel(CardImages.getCard(dealerCard));
		}
		dealerCards.setSize(CARD_SIZE);
		table.add(dealerCards);
		
		dealerCards.setLocation(0, 0);
		
		if (playerCard == null){
			playerCards = new JLabel("");
		} else {
			dealerCards = new JLabel(CardImages.getCard(playerCard));
		}
		playerCards.setSize(CARD_SIZE);
		table.add(playerCards);
		
		playerCards.setLocation(0, 0);
		
		
		return table;
		
	}
	
	
	
	private final static JPanel table = new JPanel(); 
	
	/**
	 * Cards of dealer, player
	 */
	private static Card dealerCard, playerCard;
	
	private static JLabel dealerCards, playerCards;
	
	/**
	 * Store the dimension of each card.
	 */
	private static final Dimension CARD_SIZE = new Dimension(73, 97);
	
	private static final Color borderColor = new Color(255,0,0);

	
}
