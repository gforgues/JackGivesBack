package gui;

import gameEngine.GameEngine;

import java.awt.BorderLayout;
//import java.awt.Graphics;
import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.ArrayList;

//import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.text.html.Option;

import participant.Player;
//import cards.BlackjackHand;

/**
 * 
 * @author JackGivesBack
 *
 */
public class MainApplication
{	
	
	private static JComponent playArea;

	/**
	 * Start a new game
	 */
	private static void newGame(){
		
//		if (mainEngine.getNumberOfPlayers() == 0){
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Please add players into the game first!", 
//					"Oops",
//					JOptionPane.INFORMATION_MESSAGE);
//			mainEngine.reset();
//		} else {
			mainEngine.reset();
			mainEngine.gameStart();
//		}
			
	}


	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{		
		/*
		 * Game Initialization
		 */
		frame = new JFrame("JackGivesBack");		
		
		/*
		 * Menu Setup
		 */
		JMenuBar menuBar = new JMenuBar();
		final JMenu menuGame = new JMenu("Game");
		final JMenu menuLang = new JMenu("Language");
		final JMenu menuAbout = new JMenu("About");
		
		// menuGame
		final JMenuItem miNewGame = new JMenuItem("New Game");
		miNewGame.addActionListener (new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				newGame();
			}
		});
		
		final JMenuItem miHallOfFame = new JMenuItem("Hall of Fame");
		miHallOfFame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				HallOfFameDisplay.displayScore(mainEngine);
			}
		});
		
		final JMenuItem miAddPlayer = new JMenuItem("Add Player");
		miAddPlayer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AddPlayer.display(mainEngine);
			}
		});

		
		final JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		// menuAbout
		final JMenuItem miAbout = new JMenuItem("About");
		miAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(new JFrame(),
							"Credits\n\n" +
							"\tGabriel Forgues\n" +
							"\tSurbhi Gupta\n" +
							"\tJustin Kat\n" +
							"\tJessica Makucka\n" +
							"\tChandani Patel\n\n", 
						"About",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		final JMenuItem miHelp = new JMenuItem("Help");
		miHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
					BlackjackHelp.displayHelp();
			}
		});
		
		
		// menuLanguage
		final JMenuItem miEnglish = new JMenuItem("English");
		miEnglish.addActionListener(new ActionListener() {;
			public void actionPerformed(ActionEvent e) {

				//mainEngine.notifyObservers();
			}
		});
		
		final JMenuItem miFrench = new JMenuItem("French");
		miFrench.addActionListener(new ActionListener() {;
			public void actionPerformed(ActionEvent e) {

				//mainEngine.notifyObservers();
			}
		});
		
		menuGame.add(miNewGame);
		menuGame.add(miHallOfFame);
		menuGame.add(miAddPlayer);
		menuGame.add(miExit);
		menuLang.add(miEnglish);
		menuLang.add(miFrench);

		menuAbout.add(miAbout);
		menuAbout.add(miHelp);
		
		menuBar.add(menuGame);
		menuBar.add(menuLang);	
		menuBar.add(menuAbout);
		//Menu Setup End//
		
		
		/*
		 * Play Area Setup
		 */
		playArea.setOpaque(false);
		
//mainEngine.addDealer();
		
//		for (int i=0;i<mainEngine.getNumberOfPlayers();i++) {
		
/*			playerView = new HandGUI(mainEngine,mainEngine.getPlayers().get(0));
		mainEngine.addObservers(playerView);
	//	}
		
		mainEngine.addObservers(gui);
		
*/		
		GridBagConstraints c = new GridBagConstraints();
/*		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.8;
		c.weighty = 0.25;
		c.gridx = 0;
		c.gridy = 2;
		playArea.add(playerView.getJPanel(), c);
*/		
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.6;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		playArea.add(gui.getJPanel(), c);
		
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		frame.getContentPane().add(playArea, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(800,600);
		/*
		 * Appear on the center of the screen
		 */
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		//Frame setup end//
		
		
		mainEngine.gameStart();
		
	}
		
		protected void renewTable(JPanel a, JPanel b) {
			playArea.remove(a);
			playArea.invalidate();
			playArea.validate();
			playArea.repaint();
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0.6;
			c.weighty = 0.5;
			c.gridx = 1;
			c.gridy = 1;
			playArea.add(b, c);
			playArea.invalidate();
			playArea.validate();
			playArea.repaint();
		}
		
		protected void renewHand(JPanel a, JPanel b) {
			playArea.remove(a);
			playArea.invalidate();
			playArea.validate();
			playArea.repaint();
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = 2;
			c.gridheight = 1;
			c.weightx = 0.8;
			c.weighty = 0.25;
			c.gridx = 0;
			c.gridy = 2;
			playArea.add(b, c);
			playArea.invalidate();
			playArea.validate();
			playArea.repaint();
		}
		
		//temporary
		static Player wat= new Player("wat","omg");
		
		private final static GameEngine mainEngine = new GameEngine(wat);
		
		//private static HandGUI playerView = null;
		
		private final static GUI gui = new GUI(mainEngine);

		private static JFrame frame;
		
//		private final static JPanel playArea = new JPanel(new GridBagLayout()) {
//			 public void paintComponent(Graphics g) {
//				 super.paintComponent(g);
//				 ImageIcon img = new ImageIcon(this.getClass().getResource("/images/bg5.jpg"));
//				 g.drawImage(img.getImage(), 0, 0,img.getIconWidth(),img.getIconHeight(), null);
//			 }
//		};
		
	}
