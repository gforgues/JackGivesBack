package gui;

import gameEngine.GameEngine;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.html.Option;

public class MainApplication
{
	
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

				mainEngine.notifyObservers();
			}
		});
		
		final JMenuItem miFrench = new JMenuItem("French");
		miFrench.addActionListener(new ActionListener() {;
			public void actionPerformed(ActionEvent e) {

				mainEngine.notifyObservers();
			}
		});
		
		menuGame.add(miNewGame);
		menuGame.add(miHallOfFame);
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
		
	}
		
		protected void renewTable(JPanel a, JPanel b) {
			playArea.remove(a);
			playArea.invalidate();
			playArea.validate();
			playArea.repaint();

		}
		
		protected void renewHand(JPanel a, JPanel b) {
			playArea.remove(a);
			playArea.invalidate();
			playArea.validate();
			playArea.repaint();
		}
		
		
		
		private final static GameEngine mainEngine = new GameEngine();

		private static JFrame frame;
		
		private final static JPanel playArea = new JPanel(new GridBagLayout()) {
			 public void paintComponent(Graphics g) {
				 super.paintComponent(g);
				 ImageIcon img = new ImageIcon(this.getClass().getResource("/images/bg5.jpg"));
				 g.drawImage(img.getImage(), 0, 0,img.getIconWidth(),img.getIconHeight(), null);
			 }
		};
		
	}
