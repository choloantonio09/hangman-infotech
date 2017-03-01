package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class Instructions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOverDialog frame = new GameOverDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Instructions() {
		setResizable(false);
		setTitle("INSTRUCTIONS");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 911, 542);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(96,175,254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGameOver = new JLabel("HOW TO PLAY?");
		lblGameOver.setForeground(Color.WHITE);
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setFont(new Font("Century Gothic", Font.BOLD, 45));
		lblGameOver.setBounds(10, 11, 867, 66);
		contentPane.add(lblGameOver);
		
		JButton btnMainMenu = new JButton("MAIN MENU");
		btnMainMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMainMenu.setEnabled(true);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Welcome w = new Welcome();
				w.setLocationRelativeTo(null);
				w.setVisible(true);*/
				dispose();
			}
		});
		
		btnMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnMainMenu.setForeground(new Color(25, 25, 112));
				btnMainMenu.setBackground(Color.WHITE);
				btnMainMenu.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMainMenu.setForeground(Color.WHITE);
				btnMainMenu.setBackground(new Color(25, 25, 112));
				btnMainMenu.setOpaque(true);
			}
		});
		
		btnMainMenu.setOpaque(true);
		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnMainMenu.setBackground(new Color(25, 25, 112));
		btnMainMenu.setBounds(339, 454, 211, 48);
		contentPane.add(btnMainMenu);
		
		JLabel lblStudyMoreNext = new JLabel("<html>\r\n*First, enter your name. It is a requirement before playing the game.\r\n<br><br>\r\n*Second, select your choice of difficulty. This will determine what questions will best fit for you.\r\n<br><br>\r\n*You must be able to guess the right letters that will fill the blanks to complete the right answer by choosing from the alphabet.\r\n<br><br>\r\n*You will be given 5 rounds of questions. For each round, you will be given SIX(6) lives to survive (6 tries only). Reaching a ZERO remaining life will automatically end your game and save your last accumulated points.\r\n<br><br>\r\n*Each time you pass a round, points will be added to your current score and your lives will be refreshed. Take note that the more you lose a life, the lower the points you can get for that round.\r\n<br><br>\r\n*Failing to finish a round will end your game. You can view your score on the \"VIEW HIGHSCORES\" in the main menu.\r\n</html>");
		lblStudyMoreNext.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudyMoreNext.setVerticalAlignment(SwingConstants.TOP);
		lblStudyMoreNext.setForeground(Color.WHITE);
		lblStudyMoreNext.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblStudyMoreNext.setBounds(39, 88, 826, 332);
		contentPane.add(lblStudyMoreNext);
		
	}

}
