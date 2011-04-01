package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BlackjackHelp
{

	public BlackjackHelp() {
	
	}
	
	public static void displayHelp() {
		final JFrame frame = new JFrame("Blackjack Help");
	
		
		String gamePlayInstr = "\n - bla - \n\n"
			+ "\n\n";
		
		String terminology = "\n - bla - \n\n"
			+ "\n\n";
		
		frame.setLayout(new FlowLayout());

		JTextArea gamePlayInstrText =
			new JTextArea(gamePlayInstr,25,25);

		gamePlayInstrText.setBackground(Color.BLACK);
		Font textFont = new Font("Verdana", Font.BOLD, 12);
		gamePlayInstrText.setFont(textFont);
		gamePlayInstrText.setEnabled(false);

		JTextArea terminologyText = new JTextArea(terminology,25,25);

		terminologyText.setBackground(Color.BLACK);
		terminologyText.setFont(textFont);
		terminologyText.setEnabled(false);

		JScrollPane scrollPane =
			new JScrollPane(gamePlayInstrText,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JScrollPane scrollPane2 =
			new JScrollPane(terminologyText,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		gamePlayInstrText.setLineWrap(true);
		terminologyText.setLineWrap(true);

		frame.add(scrollPane);
		frame.add(scrollPane2);



		frame.setSize(600, 480);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
        frame.pack();
		frame.setVisible(true);

	}
}
