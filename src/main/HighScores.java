package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.Connect;
import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class HighScores extends JFrame {

	private JPanel contentPane;
	private JTable easyTable;
	private JTable hardTable;
	private JTable mediumTable;
	Connect c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HighScores frame = new HighScores();
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
	public HighScores() {
		setType(Type.UTILITY);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(HighScores.class.getResource("/images/logo.png")));
		setTitle("HIGHSCORES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1011, 630);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(96,175,254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 77, 324, 442);
		contentPane.add(scrollPane);
		
		easyTable = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // This is how we disable editing:
		        return false;
		    }
		};
		easyTable.setFont(new Font("Century Gothic", Font.BOLD, 17));
		easyTable.setForeground(Color.WHITE);
		easyTable.setBackground(new Color(34, 139, 34));
		easyTable.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 17));
		easyTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(easyTable);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(669, 77, 324, 442);
		contentPane.add(scrollPane_2);
		
		hardTable = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // This is how we disable editing:
		        return false;
		    }
		};
		hardTable.setFont(new Font("Century Gothic", Font.BOLD, 17));
		hardTable.setForeground(new Color(255, 255, 255));
		hardTable.setBackground(new Color(255, 0, 0));
		hardTable.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 17));
		hardTable.setAutoCreateRowSorter(true);
		scrollPane_2.setViewportView(hardTable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(340, 77, 325, 442);
		contentPane.add(scrollPane_1);
		
		mediumTable = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // This is how we disable editing:
		        return false;
		    }
		};
		mediumTable.setBackground(new Color(255, 140, 0));
		mediumTable.setForeground(Color.WHITE);
		mediumTable.setFont(new Font("Century Gothic", Font.BOLD, 17));
		mediumTable.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 17));
		mediumTable.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(mediumTable);
		
		JLabel lblEasy = new JLabel("EASY");
		lblEasy.setBounds(12, 12, 324, 61);
		lblEasy.setHorizontalAlignment(SwingConstants.CENTER);
		lblEasy.setForeground(Color.WHITE);
		lblEasy.setFont(new Font("Century Gothic", Font.BOLD, 35));
		contentPane.add(lblEasy);
		
		JLabel lblMedium = new JLabel("MEDIUM");
		lblMedium.setBounds(340, 12, 325, 61);
		lblMedium.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedium.setForeground(Color.WHITE);
		lblMedium.setFont(new Font("Century Gothic", Font.BOLD, 35));
		contentPane.add(lblMedium);
		
		JLabel lblHard = new JLabel("HARD");
		lblHard.setBounds(669, 12, 324, 61);
		lblHard.setHorizontalAlignment(SwingConstants.CENTER);
		lblHard.setForeground(Color.WHITE);
		lblHard.setFont(new Font("Century Gothic", Font.BOLD, 35));
		contentPane.add(lblHard);
		
		try {
			c = new Connect();
			String query = "SELECT username AS 'PLAYER NAME', score AS 'SCORE' FROM users WHERE user_level = 'EASY' ORDER BY score DESC;";
			
			c.pst = c.con.prepareStatement(query);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
				getEasyTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			
			c.con.close();
			
			c = new Connect();
			String query2 = "SELECT username AS 'PLAYER NAME', score AS 'SCORE' FROM users WHERE user_level = 'MEDIUM' ORDER BY score DESC;";
			
			c.pst = c.con.prepareStatement(query2);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
				getMediumTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			
			c.con.close();
			
			c = new Connect();
			String query3 = "SELECT username AS 'PLAYER NAME', score AS 'SCORE' FROM users WHERE user_level = 'HARD' ORDER BY score DESC;";
			
			c.pst = c.con.prepareStatement(query3);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
				getHardTable().setModel(DbUtils.resultSetToTableModel(c.rs));
				
				JButton button = new JButton("MAIN MENU");
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*Welcome w = new Welcome();
						w.setLocationRelativeTo(null);
						w.setVisible(true);*/
						dispose();
					}
				});
				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						button.setForeground(new Color(25, 25, 112));
						button.setBackground(Color.WHITE);
						button.setOpaque(true);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						button.setForeground(Color.WHITE);
						button.setBackground(new Color(25, 25, 112));
						button.setOpaque(true);
					}
				});
				button.setOpaque(true);
				button.setForeground(Color.WHITE);
				button.setFont(new Font("Century Gothic", Font.BOLD, 25));
				button.setEnabled(true);
				button.setBackground(new Color(25, 25, 112));
				button.setBounds(393, 542, 211, 48);
				contentPane.add(button);
			
			
			c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public JTable getEasyTable() {
		return easyTable;
	}

	public JTable getHardTable() {
		return hardTable;
	}

	public JTable getMediumTable() {
		return mediumTable;
	}
}
