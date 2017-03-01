package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Database.Connect;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import net.miginfocom.swing.MigLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome extends JFrame {

	private JPanel contentPane;
	private JTextField playerName;
	private JButton btnClear;
	private JButton btnPlay;
	private JComboBox selectDifficulty;
	private JLabel label;
	private JLabel lblWelcome;
	private JButton btnShowHighscores;
	private JButton btnHowToPlay;
	Connect c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Welcome() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/images/logo.png")));
		setResizable(false);
		setTitle("Hangman InfoTech");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 612);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(96,175,254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterYourName = new JLabel("ENTER YOUR NAME:");
		lblEnterYourName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourName.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblEnterYourName.setBounds(175, 132, 372, 48);
		contentPane.add(lblEnterYourName);
		
		playerName = new JTextField();
		
		playerName.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    changed();
			  }

			  public void changed() {
			     if (playerName.getText().equals("") || playerName.getText().trim().length() == 0){
			       btnPlay.setEnabled(false);
			     }
			     else {
			    	 btnPlay.setEnabled(true);
			    }

			  }
			});
		
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setFont(new Font("H&B Sketch Demo", Font.PLAIN, 20));
		playerName.setBounds(175, 191, 372, 48);
		contentPane.add(playerName);
		playerName.setColumns(10);
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				playerName.setText(null);
			}
		});
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnClear.setForeground(new Color(25, 25, 112));
				//btnStart.setContentAreaFilled(false);
				btnClear.setBackground(Color.WHITE);
				btnClear.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnClear.setForeground(Color.WHITE);
				//btnStart.setContentAreaFilled(false);
				btnClear.setBackground(new Color(25, 25, 112));
				btnClear.setOpaque(true);
			}
		});
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setForeground(Color.WHITE);
		//btnStart.setContentAreaFilled(false);
		btnClear.setBackground(new Color(25, 25, 112));
		btnClear.setOpaque(true);
		btnClear.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnClear.setBounds(175, 420, 173, 48);
		contentPane.add(btnClear);
		
		btnPlay = new JButton("PLAY");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int playerId = 0;
				
				try {
					c = new Connect();
					
					String query = "INSERT INTO users (username,score,user_level) VALUES (?,?,?)";
					
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, playerName.getText());
					c.pst.setInt(2, 0);
					c.pst.setString(3, selectDifficulty.getSelectedItem().toString()); 
					c.pst.execute();
					
					String query2 = "SELECT * FROM users ORDER BY user_id ASC";
					c.pst = c.con.prepareStatement(query2);
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					while(c.rs.next())
					{
						playerId = c.rs.getInt("user_id");
					}
					
					Game game = new Game(playerId, (String) selectDifficulty.getSelectedItem());
					game.setLocationRelativeTo(null);
					game.setVisible(true);
					dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlay.setEnabled(false);
		
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnPlay.setForeground(new Color(25, 25, 112));
				btnPlay.setBackground(Color.WHITE);
				btnPlay.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPlay.setForeground(Color.WHITE);
				//btnStart.setContentAreaFilled(false);
				btnPlay.setBackground(new Color(25, 25, 112));
				btnPlay.setOpaque(true);
			}
		});
		btnPlay.setOpaque(true);
		btnPlay.setForeground(Color.WHITE);
		btnPlay.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnPlay.setBackground(new Color(25, 25, 112));
		btnPlay.setBounds(374, 420, 173, 48);
		contentPane.add(btnPlay);
		
		JLabel lblDifficulty = new JLabel("DIFFICULTY:");
		lblDifficulty.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblDifficulty.setBounds(175, 250, 173, 41);
		contentPane.add(lblDifficulty);
		
		selectDifficulty = new JComboBox();
		selectDifficulty.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		//comboBox.setBackground(new Color(25, 25, 112));
		//comboBox.setForeground(Color.WHITE);
		
		selectDifficulty.setForeground(new Color(25, 25, 112));
		//btnStart.setContentAreaFilled(false);
		selectDifficulty.setBackground(Color.WHITE);
		
		selectDifficulty.setModel(new DefaultComboBoxModel(new String[] {"EASY", "MEDIUM", "HARD"}));
		selectDifficulty.setBounds(374, 250, 173, 41);
		contentPane.add(selectDifficulty);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/logo.png")).getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		label.setBounds(10, 11, 110, 110);
		contentPane.add(label);
		
		lblWelcome = new JLabel("HANGMAN INFO TECH EDITION");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Century Gothic", Font.BOLD, 36));
		lblWelcome.setBounds(130, 11, 560, 110);
		contentPane.add(lblWelcome);
		
		btnShowHighscores = new JButton("VIEW HIGHSCORES");
		btnShowHighscores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HighScores hs = new HighScores();
				hs.setLocationRelativeTo(null);
				hs.setVisible(true);
				
			}
		});
		btnShowHighscores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShowHighscores.setOpaque(true);
		btnShowHighscores.setForeground(Color.WHITE);
		btnShowHighscores.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnShowHighscores.setBackground(new Color(25, 25, 112));
		btnShowHighscores.setBounds(175, 302, 372, 48);
		
		btnShowHighscores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnShowHighscores.setForeground(new Color(25, 25, 112));
				btnShowHighscores.setBackground(Color.WHITE);
				btnShowHighscores.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnShowHighscores.setForeground(Color.WHITE);
				//btnStart.setContentAreaFilled(false);
				btnShowHighscores.setBackground(new Color(25, 25, 112));
				btnShowHighscores.setOpaque(true);
			}
		});
		
		contentPane.add(btnShowHighscores);
		
		btnHowToPlay = new JButton("HOW TO PLAY");
		btnHowToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instructions i = new Instructions();
				i.setLocationRelativeTo(null);
				i.setVisible(true);
			}
		});
		btnHowToPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHowToPlay.setOpaque(true);
		btnHowToPlay.setForeground(Color.WHITE);
		btnHowToPlay.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnHowToPlay.setBackground(new Color(25, 25, 112));
		btnHowToPlay.setBounds(175, 361, 372, 48);
		
		btnHowToPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnHowToPlay.setForeground(new Color(25, 25, 112));
				btnHowToPlay.setBackground(Color.WHITE);
				btnHowToPlay.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHowToPlay.setForeground(Color.WHITE);
				btnHowToPlay.setBackground(new Color(25, 25, 112));
				btnHowToPlay.setOpaque(true);
			}
		});
		
		contentPane.add(btnHowToPlay);
		
		JButton btnExitGame = new JButton("EXIT GAME");
		btnExitGame.setOpaque(true);
		btnExitGame.setForeground(Color.WHITE);
		btnExitGame.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnExitGame.setBackground(new Color(25, 25, 112));
		btnExitGame.setBounds(175, 479, 372, 48);
		
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnExitGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnExitGame.setForeground(new Color(25, 25, 112));
				btnExitGame.setBackground(Color.WHITE);
				btnExitGame.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExitGame.setForeground(Color.WHITE);
				btnExitGame.setBackground(new Color(25, 25, 112));
				btnExitGame.setOpaque(true);
			}
		});
		
		contentPane.add(btnExitGame);
	}
}
