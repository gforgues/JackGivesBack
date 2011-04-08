package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import participant.Player;

import cards.BlackjackHand;
import cards.Card;
import cards.Hand;
import cards.CardImages;

import game.Observer;
import gameEngine.GameEngine;

/**
 * 
 * @author JackGivesBack
 *
 */
public class HandGUI extends MainApplication implements Observer 
{

	public HandGUI (final GameEngine ge, Player pPlayer) {
		currentGameEngine = ge;
		player = pPlayer;
		handPanel.setOpaque(false);
	}
	
	public final JPanel createHand(final GameEngine ge) {
		handPanel = new JPanel();
		handPanel.setLayout(null);
		handPanel.setOpaque(false);
		
		playerLabel = new JLabel (player.getUsername());
		playerLabel.setForeground(Color.orange);
		playerLabel.setFont(new Font(playerLabel.getFont().getFamily(), Font.BOLD,16));
		
		playerLabel.setSize(NAME_SIZE);
		handPanel.add(playerLabel);
		
		playerLabel.setLocation(HEIGHT * TWO - 6, HEIGHT);
		
		for (int i=0 ; i < ge.getPlayerHand(player).size(); i++) {
			hand = (BlackjackHand) ge.getPlayerHand(player).get(i).clone();
			for (Card tmp : hand.getHand()) {
				aCard = new JLabel(CardImages.getCard(tmp));
				aCard.setSize(CARD_SIZE);
				aCard.setName(tmp.toCompactString());
			}
			handPanel.add(aCard);
		}
		
		handPanel.setPreferredSize(new Dimension(0, 0));
		return handPanel;
	}
	
	/**
	 * Getter for handPanel.
	 * @return handPanel
	 */
	public final JPanel getJPanel() {
		return handPanel;
	}
	
	/**
	 * Redraw the panel.
	 * @param ge GameEngine
	 * @param player Player of Hand
	 */
	private void renewPanel(final GameEngine ge, final Player pPlayer) {
		renewHand(handPanel, createHand(ge));
		refresh();
	}

	
	@Override
	public void handleEvent()
	{
		// TODO Auto-generated method stub
		renewPanel(currentGameEngine, player);
		refresh();
	}
	
	/**
	 * Refresh Panel.
	 */
	private void refresh() {
		handPanel.invalidate();
		handPanel.validate();
		handPanel.repaint();
	}
	
	
	

	private GameEngine currentGameEngine;

	private JPanel handPanel = new JPanel();

	private JLabel aCard, playerLabel;
	
	private BlackjackHand hand;
	
	private Player player;
	
	private final int HEIGHT = 20;
	
	private final int TWO = 2;
	
	private static final Dimension CARD_SIZE = new Dimension(80, 100);

	private static final Dimension NAME_SIZE = new Dimension(80, 20);
	
	private static final Dimension AUTOBUTTON_SIZE = new Dimension(80, 20);
	
}
