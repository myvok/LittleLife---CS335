import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameSetupUI extends JFrame {
    public GameSetupUI() {
    	JFrame GameSetUp = new JFrame();
    	
    	//welcome to little life screen 
    	JLabel welcome = new JLabel(); //creates the label
    	welcome.setText("Welcome to Little Life!"); //sets text in label
    	welcome.setFont(new Font("Comic Sans MS", Font.BOLD, 16)); //sets the font and size of the text
    	welcome.setBounds(110, 20, 400, 50); //sets where it is on the frame
    	
    	//text to choose pets pronouns 
    	JLabel choosePronouns = new JLabel(); 
    	choosePronouns.setText("choose your pet's pronouns..."); //sets text in label
    	choosePronouns.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); //sets the font and size of the text
    	choosePronouns.setBounds(110, 70, 400, 50); //sets where it is on the frame
    	
    	
    	//he/him button 
    	JButton heHimButton = new JButton("he/him"); 
    	heHimButton.setBounds(25, 150, 100, 50);
    	heHimButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        heHimButton.setForeground(new Color(0x8B4513));
        heHimButton.setBackground(new Color(0xFFDAB9));
        heHimButton.setFocusPainted(false);
        heHimButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        heHimButton.setPreferredSize(new Dimension(200, 60));
        heHimButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        // Hover effects
        heHimButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                heHimButton.setBackground(new Color(0xFFB6C1)); // Light Pink
                heHimButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
            }
            public void mouseExited(MouseEvent e) {
                heHimButton.setBackground(new Color(0xFFDAB9)); // Soft Peach
                heHimButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            }
            public void mousePressed(MouseEvent e) {
                heHimButton.setBackground(new Color(0xFFA07A)); // Pale Orange
            }
            public void mouseReleased(MouseEvent e) {
                heHimButton.setBackground(new Color(0xFFDAB9));
            }
        });
        // Click Action
        heHimButton.addActionListener(e -> {
            dispose();
            new NameUI();
            //new GameSetUpUI(); 
        });
    	
      //they/them button 
    	JButton theyThemButton = new JButton("they/them"); 
    	theyThemButton.setBounds(130, 150, 120, 50);
    	theyThemButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    	theyThemButton.setForeground(new Color(0x8B4513));
    	theyThemButton.setBackground(new Color(0xFFDAB9));
    	theyThemButton.setFocusPainted(false);
    	theyThemButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        theyThemButton.setPreferredSize(new Dimension(200, 60));
        theyThemButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        // Hover effects
        theyThemButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	theyThemButton.setBackground(new Color(0xFFB6C1)); // Light Pink
                theyThemButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
            }
            public void mouseExited(MouseEvent e) {
            	theyThemButton.setBackground(new Color(0xFFDAB9)); // Soft Peach
            	theyThemButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            }
            public void mousePressed(MouseEvent e) {
            	theyThemButton.setBackground(new Color(0xFFA07A)); // Pale Orange
            }
            public void mouseReleased(MouseEvent e) {
            	theyThemButton.setBackground(new Color(0xFFDAB9));
            }
        });
        // Click Action
        theyThemButton.addActionListener(e -> {
            dispose();
            new NameUI();
            //new GameSetUpUI();
        });
        
        
      //she/her button 
    	JButton sheHerButton = new JButton("she/her"); 
    	sheHerButton.setBounds(255, 150, 100, 50);
    	sheHerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    	sheHerButton.setForeground(new Color(0x8B4513));
    	sheHerButton.setBackground(new Color(0xFFDAB9));
    	sheHerButton.setFocusPainted(false);
    	sheHerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	sheHerButton.setPreferredSize(new Dimension(200, 60));
    	sheHerButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        // Hover effects
    	sheHerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	sheHerButton.setBackground(new Color(0xFFB6C1)); // Light Pink
            	sheHerButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
            }
            public void mouseExited(MouseEvent e) {
            	sheHerButton.setBackground(new Color(0xFFDAB9)); // Soft Peach
            	sheHerButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            }
            public void mousePressed(MouseEvent e) {
            	sheHerButton.setBackground(new Color(0xFFA07A)); // Pale Orange
            }
            public void mouseReleased(MouseEvent e) {
            	sheHerButton.setBackground(new Color(0xFFDAB9));
            }
        });
        // Click Action
    	sheHerButton.addActionListener(e -> {
            dispose();
            new NameUI();
            //new GameSetUpUI(); 
        });
    	
    	
    	//setting up the frame
    	GameSetUp.setTitle("Game Set Up"); //titles the frame
    	GameSetUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure you can leave the frame 
    	GameSetUp.setResizable(false); //can't resize 
    	GameSetUp.setSize(400,300); //starting size will probably make it bigger later 
    	GameSetUp.setLocationRelativeTo(null); //sets in the middle
    	GameSetUp.getContentPane().setBackground(new Color(252, 252, 206)); //changes background color 
    	GameSetUp.setLayout(null); //so you can place your labels where you want 
    	GameSetUp.setVisible(true); //makes the frame visible 
    	GameSetUp.add(welcome); //adds label 
    	GameSetUp.add(choosePronouns); //adds label 
    	GameSetUp.add(heHimButton);// adds he/him button
    	GameSetUp.add(theyThemButton); //adds they/them button
    	GameSetUp.add(sheHerButton); // adds she/her button 
    	
    	
    	
    	
    }
}