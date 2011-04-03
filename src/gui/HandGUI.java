package gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import participant.Player;

import cards.Hand;

import game.Observer;
import gameEngine.GameEngine;

public class HandGUI extends MainApplication implements Observer 
{

	public HandGUI (final GameEngine ge) {
		currentGameEngine = ge;
		handPanel.setOpaque(false);
	}
	
	
	private void renewPanel() {
		
	}
	
	@Override
	public void handleEvent()
	{
		// TODO Auto-generated method stub
		
	}

	private GameEngine currentGameEngine;

	private JPanel handPanel = new JPanel();

	private JLabel aCard;
	
	private Hand hand;
	
	private Player player;
	
	private static final Dimension CARD_SIZE = new Dimension(80, 100);

	private static final Dimension NAME_SIZE = new Dimension(80, 20);
	
	private static final Dimension AUTOBUTTON_SIZE = new Dimension(80, 20);
	
}
