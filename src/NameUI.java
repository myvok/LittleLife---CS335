import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class NameUI extends JFrame implements ActionListener {
    
    JTextField nameBox; // makes it global  
    JLabel l; // makes it global 
    JButton b; // makes it global 
    String pronouns; // Store the selected pronouns
    
    public NameUI() {
        this("they/them"); // Default pronouns if none provided
    }
    
    public NameUI(String pronouns) {
        this.pronouns = pronouns;
        
        JFrame NameUI = new JFrame(); // makes new frame
        
        // text to choose pets name 
        JLabel chooseName = new JLabel();  // makes the label
        chooseName.setText("choose your pet's name..."); // sets text in label
        chooseName.setFont(new Font("Comic Sans MS", Font.BOLD, 16)); // sets the font and size of the text
        chooseName.setBounds(110, 70, 400, 50); // sets where it is on the frame
        
        // text box 
        nameBox = new JTextField(15); // makes text box with 15 characters 
        nameBox.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); // sets the font and size of the text
        nameBox.setBounds(50, 130, 300, 50); // sets where it is on the frame
        nameBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        
        l = new JLabel("nothing"); // label, dont know if i want to keep it 
        l.setVisible(false); // Hide this label since it's not being used effectively
        
        // submit button
        b = new JButton("submit"); // makes the button
        b.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); // sets the font and size of the text
        b.setBounds(120, 195, 150, 35); // places it on frame 
        b.setForeground(new Color(0x8B4513)); // foreground color
        b.setBackground(new Color(0xFFDAB9)); // background color 
        b.setFocusPainted(false); 
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(200, 60)); // button size
        b.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));  
        
        // Hover effects
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(new Color(0xFFB6C1)); // Light Pink
                b.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
            }
            public void mouseExited(MouseEvent e) {
                b.setBackground(new Color(0xFFDAB9)); // Soft Peach
                b.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            }
            public void mousePressed(MouseEvent e) {
                b.setBackground(new Color(0xFFA07A)); // Pale Orange
            }
            public void mouseReleased(MouseEvent e) {
                b.setBackground(new Color(0xFFDAB9));
            }
        });
        
        // Add action listener to submit button
        b.addActionListener(this);
        
        // setting up the frame
        NameUI.setTitle("Name Set Up"); // titles the frame
        NameUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes sure you can leave the frame 
        NameUI.setResizable(false); // can't resize 
        NameUI.setSize(400, 300); // starting size will probably make it bigger later 
        NameUI.setLocationRelativeTo(null); // sets in the middle
        NameUI.getContentPane().setBackground(new Color(252, 252, 206)); // changes background color 
        NameUI.setLayout(null); // so you can place your labels where you want 
        NameUI.setVisible(true); // makes the frame visible 
        NameUI.add(chooseName); // adds label 
        NameUI.add(nameBox); // adds text box
        NameUI.add(b); // adds button 
        NameUI.add(l); // label sort of 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("submit")) {
            String petName = nameBox.getText().trim();
            if (!petName.isEmpty()) {
                // Pass the pet name and pronouns to CharacterSelectionUI
                dispose();
                new CharacterSelectionUI(petName, pronouns);
            } else {
                // Show error if name is empty
                JOptionPane.showMessageDialog(this, 
                    "Please enter a name for your pet!", 
                    "Empty Name", 
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
