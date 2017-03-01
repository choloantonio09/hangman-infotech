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

public class GameOverDialog extends JFrame {

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
	public GameOverDialog(int score) {
		setTitle("GAME OVER");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 583, 342);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(96,175,254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGameOver = new JLabel("GAME OVER!");
		lblGameOver.setForeground(Color.WHITE);
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setFont(new Font("Century Gothic", Font.BOLD, 60));
		lblGameOver.setBounds(10, 11, 557, 97);
		contentPane.add(lblGameOver);
		
		JButton btnMainMenu = new JButton("MAIN MENU");
		btnMainMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMainMenu.setEnabled(true);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome w = new Welcome();
				w.setLocationRelativeTo(null);
				w.setVisible(true);
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
		btnMainMenu.setBounds(175, 254, 211, 48);
		contentPane.add(btnMainMenu);
		
		JLabel lblStudyMoreNext = new JLabel("STUDY MORE NEXT TIME~");
		lblStudyMoreNext.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudyMoreNext.setForeground(Color.WHITE);
		lblStudyMoreNext.setFont(new Font("Century Gothic", Font.BOLD, 35));
		lblStudyMoreNext.setBounds(10, 119, 557, 58);
		contentPane.add(lblStudyMoreNext);
		
		JLabel label = new JLabel("SCORE: "+score);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Century Gothic", Font.BOLD, 35));
		label.setBounds(10, 185, 557, 58);
		contentPane.add(label);
	}

}
