package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.Connect;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;



public class Game extends JFrame {

	private static String[] letters = { "A", "B", "C", "D", "E", "F",
		    "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
		    "S", "T", "U", "V", "W", "X", "Y", "Z" };
		
		
		private JPanel livesPanel;
		private JLabel lblLives;
		private JLabel lblQuestion;
		private JLabel currentQuestionLabel;
		private Connect c;
		private JPanel contentPane;
		private JLabel hangmanImage;
		private JLabel lblDifficultyLevel;
		private JPanel alphabetPanel;
		
		ArrayList<String> selectedLetters = new ArrayList<>();
		private boolean noMoreLives = false;
		private boolean wrongAnswer = false;
		private JButton[] letterButtons = new JButton[letters.length];
		private JLabel[] lifeLabel = new JLabel[6];
		private String[][] qa = new String[5][4];
		private String[] answerArray;
		private String[] secretAnswerArray;
		private int score = 0;
		private JLabel secretAnswer;
		private JLabel lblScore;
		private JLabel currentScore;
		private int currentRound = 1;
		private int row = 0;
		private boolean winRound = false;
		private int lifeRemaining = 6;
		private JButton btnQuit;
		private JButton btnNextRound;
		

		

	public Game(int userId, String chosenLevel) {
		
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("/images/logo.png")));
		setTitle("Hangman InfoTech");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1060, 684);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(96,175,254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		hangmanImage = new JLabel("");
		hangmanImage.setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/Hangman-6.png")).getImage().getScaledInstance(283, 460, Image.SCALE_SMOOTH)));
		hangmanImage.setBounds(761, 184, 283, 460);
		contentPane.add(hangmanImage);
		
		lblDifficultyLevel = new JLabel("DIFFICULTY LEVEL: " + chosenLevel);
		lblDifficultyLevel.setFont(new Font("Century Gothic", Font.BOLD, 35));
		lblDifficultyLevel.setBounds(10, 11, 546, 48);
		contentPane.add(lblDifficultyLevel);
		
		alphabetPanel = new JPanel();
		alphabetPanel.setOpaque(false);
		
		
		
		ActionListener al = new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	Object src = evt.getSource();
				
				if(!selectedLetters.contains(((JButton) src).getText()))
				{
					selectedLetters.add(((JButton) src).getText());
					((JButton) src).setForeground(Color.BLACK);
					((JButton) src).setBackground(new Color(0,255,153));
					((JButton) src).setEnabled(false);
				}
	        	
	        	String buttonValue = ((JButton) src).getText();
	        	
	        	if(containsCaseInsensitive(buttonValue, Arrays.asList(answerArray)))
	        	{
	        		for(int i = 0; i < answerArray.length ; i++)
	        		{
	        			if(answerArray[i].equalsIgnoreCase(buttonValue))
	        			{
	        				secretAnswerArray[i] = answerArray[i];
	        				//System.out.println(secretAnswerArray[i]);
	        				//System.out.println("answerArray[i] == buttonValue");
	        			}
	        		}
	        		String text = ""; 
	        		for(int i = 0; i < secretAnswerArray.length; i++){
	        		   text = text + secretAnswerArray[i];
	        		} 
	        		//text.replaceAll("..", "$0 ");
	        		secretAnswer.setText("<html><p style='letter-spacing: 100px;' >"+(text.replaceAll(" ","  ")).replaceAll("", "&nbsp;")+"</p> </html>");
	        		
	        		//System.out.println("1st if statement");
	        		
	        	}
	        	else
	        	{
	        		
					lifeRemaining--;
					changeLives(lifeRemaining);

					
					if(lifeRemaining == 0)
					{
						currentRound = 0;
						
						try {
							c = new Connect();
							String sql="UPDATE users SET score = score + ? WHERE user_id = ?;";
							c.pst = c.con.prepareStatement(sql);
							c.pst.setInt(1, score);
							c.pst.setString(2, Integer.toString(userId)); 
							c.pst.execute();
							c.con.close();
							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						GameOverDialog god = new GameOverDialog(Integer.parseInt(currentScore.getText()));
						god.setLocationRelativeTo(null);
						god.setVisible(true);
						dispose();
						
					}
					
	        	}
	        	
	        	if(Arrays.equals(answerArray, secretAnswerArray))
				{
					winRound = true;
					score = Integer.parseInt(qa[row][3])*lifeRemaining;
					
					try {
						c = new Connect();
						String sql="UPDATE users SET score = score + ? WHERE user_id = ?;";
						c.pst = c.con.prepareStatement(sql);
						c.pst.setInt(1, score);
						c.pst.setString(2, Integer.toString(userId)); 
						c.pst.execute();
						
						c.con.close();
						
						c = new Connect();
						String sql2="SELECT score FROM users WHERE user_id = ?;";
						c.pst = c.con.prepareStatement(sql2);
						c.pst.setString(1, Integer.toString(userId)); 
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						while(c.rs.next())
						{
							currentScore.setText(c.rs.getString("score"));
						}
						c.con.close();

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					currentRound++;
					row++;
					selectedLetters.clear();
					
					
					if(currentRound<=5)
		        	{
						JOptionPane.showMessageDialog(null, "You won the round! You may now proceed to the next round.", "ROUND PASSED!", JOptionPane.INFORMATION_MESSAGE);
		        		btnNextRound.setEnabled(true);
		        	}
					else
					{
						btnNextRound.setEnabled(false);
						
						YouWinDialog win = new YouWinDialog(Integer.parseInt(currentScore.getText()));
						win.setLocationRelativeTo(null);
						win.setVisible(true);
						dispose();
						
					}

				}
	        	
	        	
	        	
	        	
	        }
	    };
	    
	    MouseListener ml = new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent arg0) {
	    		Object src = arg0.getSource();
	    		
	    		if(!selectedLetters.contains(((JButton) src).getText()))
	    		{
	    			((JButton) src).setForeground(new Color(25, 25, 112));
					//btnStart.setContentAreaFilled(false);
		    		((JButton) src).setBackground(Color.WHITE);
		    		((JButton) src).setOpaque(true);
	    		}
	    		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Object src = e.getSource();
				
				if(!selectedLetters.contains(((JButton) src).getText()))
				{
					((JButton) src).setForeground(Color.WHITE);
					((JButton) src).setBackground(new Color(25, 25, 112));
					((JButton) src).setOpaque(true);
				}

			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object src = arg0.getSource();
				
				if(!selectedLetters.contains(((JButton) src).getText()))
				{
					selectedLetters.add(((JButton) src).getText());
					((JButton) src).setForeground(Color.BLACK);
					((JButton) src).setBackground(new Color(0,255,153));
					((JButton) src).setEnabled(false);
				}
				
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    };
	    
	    for (int i = 0; i < letters.length; ++i) 
	    {
	        letterButtons[i] = new JButton(letters[i]);
	        letterButtons[i].addActionListener(al);
	        letterButtons[i].addMouseListener(ml);
	        letterButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        letterButtons[i].setForeground(Color.WHITE);
	        letterButtons[i].setBackground(new Color(25, 25, 112));
	        letterButtons[i].setOpaque(true);
	        letterButtons[i].setFont(new Font("Century Gothic", Font.BOLD, 25));
	        
	        
	        alphabetPanel.add(letterButtons[i]);
	    }
		
		alphabetPanel.setBounds(10, 479, 813, 165);
		contentPane.add(alphabetPanel);
		alphabetPanel.setLayout(new GridLayout(2, 13, 0, 0));
		
		
		
		livesPanel = new JPanel();
		livesPanel.setOpaque(false);
		livesPanel.setBounds(781, 11, 263, 48);
		contentPane.add(livesPanel);
		livesPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblLives = new JLabel("LIVES:");
		lblLives.setFont(new Font("Century Gothic", Font.BOLD, 35));
		lblLives.setBounds(673, 11, 98, 48);
		contentPane.add(lblLives);
		
		lblQuestion = new JLabel("Question:");
		lblQuestion.setFont(new Font("Century Gothic", Font.BOLD, 35));
		lblQuestion.setBounds(10, 70, 162, 48);
		contentPane.add(lblQuestion);
		
		currentQuestionLabel = new JLabel("<html>n/a</html>");
		currentQuestionLabel.setFont(new Font("Century Gothic", Font.BOLD, 35));
		currentQuestionLabel.setVerticalAlignment(SwingConstants.TOP);
		currentQuestionLabel.setBounds(182, 72, 473, 186);
		contentPane.add(currentQuestionLabel);
		
		secretAnswer = new JLabel("<html>n/a</html>");
		secretAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		secretAnswer.setVerticalAlignment(SwingConstants.TOP);
		secretAnswer.setFont(new Font("Century Gothic", Font.BOLD, 40));
		secretAnswer.setBounds(74, 301, 598, 167);
		contentPane.add(secretAnswer);
		
		lblScore = new JLabel("SCORE:");
		lblScore.setFont(new Font("Century Gothic", Font.BOLD, 35));
		lblScore.setBounds(673, 70, 131, 48);
		contentPane.add(lblScore);
		
		currentScore = new JLabel(Integer.toString(score));
		currentScore.setForeground(Color.WHITE);
		currentScore.setHorizontalAlignment(SwingConstants.RIGHT);
		currentScore.setFont(new Font("Century Gothic", Font.BOLD, 25));
		currentScore.setBounds(814, 70, 230, 48);
		contentPane.add(currentScore);
		
		btnQuit = new JButton("QUIT");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				Welcome wc = new Welcome();
				wc.setLocationRelativeTo(null);
				wc.setVisible(true);
				
			}
			
		});
		
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnQuit.setForeground(new Color(25, 25, 112));
				btnQuit.setBackground(Color.WHITE);
				btnQuit.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnQuit.setForeground(Color.WHITE);
				btnQuit.setBackground(new Color(25, 25, 112));
				btnQuit.setOpaque(true);
			}
		});
		
		btnQuit.setOpaque(true);
		btnQuit.setForeground(Color.WHITE);
		btnQuit.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnQuit.setBackground(new Color(25, 25, 112));
		btnQuit.setBounds(672, 129, 151, 48);
		contentPane.add(btnQuit);
		
		btnNextRound = new JButton("NEXT ROUND");
		btnNextRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initGame();
				for (int i = 0; i < letters.length; ++i) 
			    {
			        letterButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			        letterButtons[i].setForeground(Color.WHITE);
			        letterButtons[i].setBackground(new Color(25, 25, 112));
			        letterButtons[i].setOpaque(true);
			        letterButtons[i].setFont(new Font("Century Gothic", Font.BOLD, 25));
			        letterButtons[i].setEnabled(true);
			        
			    }
        		btnNextRound.setEnabled(false);
        		lifeRemaining=6;
        		changeLives(lifeRemaining);
			}
		});
		btnNextRound.setEnabled(false);
		
		btnNextRound.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNextRound.setForeground(new Color(25, 25, 112));
				btnNextRound.setBackground(Color.WHITE);
				btnNextRound.setOpaque(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNextRound.setForeground(Color.WHITE);
				btnNextRound.setBackground(new Color(25, 25, 112));
				btnNextRound.setOpaque(true);
			}
		});
		
		btnNextRound.setOpaque(true);
		btnNextRound.setForeground(Color.WHITE);
		btnNextRound.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnNextRound.setBackground(new Color(25, 25, 112));
		btnNextRound.setBounds(833, 129, 211, 48);
		contentPane.add(btnNextRound);
		
		for(int i = 0; i < 6; i++)
		{
			lifeLabel[i] = new JLabel("");
			lifeLabel[i].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartBuhay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			livesPanel.add(lifeLabel[i]);
		}
		

		setQuestionsToArray(chosenLevel);
		
		initGame();
		
		
		
	}
	
	public void changeLives(int lifeCount)
	{
		
		if(lifeCount==0)
		{
			for(int i = 0; i < 6; i++)
			{
				lifeLabel[i].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartPatay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			}
			hangmanImage.setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/Hangman-6.png")).getImage().getScaledInstance(283, 460, Image.SCALE_SMOOTH)));
		}
		else if(lifeCount == 1)
		{
			for(int i = 0; i < 6; i++)
			{
				lifeLabel[i].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartPatay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			}
			
			lifeLabel[0].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartBuhay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			hangmanImage.setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/Hangman-5.png")).getImage().getScaledInstance(283, 460, Image.SCALE_SMOOTH)));
		}
		else if(lifeCount==2)
		{
			for(int i = 0; i < 6; i++)
			{
				lifeLabel[i].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartPatay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			}
			
			lifeLabel[0].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartBuhay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			lifeLabel[1].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartBuhay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			hangmanImage.setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/Hangman-4.png")).getImage().getScaledInstance(283, 460, Image.SCALE_SMOOTH)));
		}
		else if(lifeCount==3)
		{
			for(int i = 0; i < 6; i++)
			{
				lifeLabel[i].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartPatay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			}
			
			lifeLabel[0].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartBuhay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			lifeLabel[1].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartBuhay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			lifeLabel[2].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartBuhay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			hangmanImage.setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/Hangman-3.png")).getImage().getScaledInstance(283, 460, Image.SCALE_SMOOTH)));
		}
		else if(lifeCount==4)
		{
			for(int i = 0; i < 6; i++)
			{
				lifeLabel[i].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartBuhay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			}
			
			lifeLabel[4].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartPatay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			lifeLabel[5].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartPatay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			hangmanImage.setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/Hangman-2.png")).getImage().getScaledInstance(283, 460, Image.SCALE_SMOOTH)));
			
		}
		else if(lifeCount==5)
		{
			for(int i = 0; i < 6; i++)
			{
				lifeLabel[i].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartBuhay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			}
			lifeLabel[5].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartPatay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			hangmanImage.setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/Hangman-1.png")).getImage().getScaledInstance(283, 460, Image.SCALE_SMOOTH)));
		}
		else
		{
			for(int i = 0; i < 6; i++)
			{
				lifeLabel[i].setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/heartBuhay.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
				hangmanImage.setIcon(new ImageIcon(new ImageIcon(Welcome.class.getResource("/images/Hangman-0.png")).getImage().getScaledInstance(283, 460, Image.SCALE_SMOOTH)));
			}
		}
		
	}

	public void setQuestionsToArray(String level)
	{
		try {
			c = new Connect();
			
			if(level == "EASY")
			{
				int row = 0;
				String query = "SELECT * FROM easy_bank ORDER BY RAND() LIMIT 5;";
				c.pst = c.con.prepareStatement(query);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				while(c.rs.next())
				{
					//System.out.println(c.rs.getString("question"));
					
					qa[row][0] = c.rs.getString("question");
					
					qa[row][1] = c.rs.getString("answer");
					
					qa[row][2] = c.rs.getString("question_points");
					
					int reducePoints = c.rs.getInt("question_points")/6;
					qa[row][3] =  Integer.toString(reducePoints);
					
					row++;
				}
			}
			else if(level == "MEDIUM")
			{
				
				int row = 0;
				String query = "SELECT * FROM medium_bank ORDER BY RAND() LIMIT 5;";
				c.pst = c.con.prepareStatement(query);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				while(c.rs.next())
				{
					//System.out.println(c.rs.getString("question"));
					
					qa[row][0] = c.rs.getString("question");
					
					qa[row][1] = c.rs.getString("answer");
					
					qa[row][2] = c.rs.getString("question_points");
					
					int reducePoints = c.rs.getInt("question_points")/6;
					qa[row][3] =  Integer.toString(reducePoints);
					
					row++;
				}
			}
			else if(level == "HARD")
			{
				int row = 0;
				String query = "SELECT * FROM hard_bank ORDER BY RAND() LIMIT 5;";
				c.pst = c.con.prepareStatement(query);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				while(c.rs.next())
				{
					//System.out.println(c.rs.getString("question"));
					
					qa[row][0] = c.rs.getString("question");
					
					qa[row][1] = c.rs.getString("answer");
					
					qa[row][2] = c.rs.getString("question_points");
					
					int reducePoints = c.rs.getInt("question_points")/6;
					qa[row][3] =  Integer.toString(reducePoints);
					
					row++;
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void initGame()
	{
		if(currentRound!=0 || currentRound<6)
		{
			currentQuestionLabel.setText("<html>"+qa[row][0]+"</html>");
			
			
			
			answerArray = qa[row][1].split("");
			secretAnswerArray = new String[answerArray.length];
			
			for(int i = 0; i < answerArray.length; i++)
			{
				if(!answerArray[i].equals(" "))
				{
					secretAnswerArray[i] = "_";
				}
				else
				{
					secretAnswerArray[i] = " ";
				}
				
			}
			
			String text = ""; 
			for(int i = 0; i < secretAnswerArray.length; i++){
			   text = text + secretAnswerArray[i];
			} 
			
			//text.replaceAll("..", "$0 ");
			
			changeLives(lifeRemaining);
			
			secretAnswer.setText("<html><p style='letter-spacing: 4px' >"+(text.replaceAll(" ","  ")).replaceAll("", "&nbsp;")+"</p></html>");
		}
		else
		{
			System.out.println("GAME OVER: MEHEHE");
		}
		
		
	}
	
	public boolean containsCaseInsensitive(String s, List<String> l){
	     for (String string : l){
	        if (string.equalsIgnoreCase(s)){
	            return true;
	         }
	     }
	    return false;
	  }
	
	
}

