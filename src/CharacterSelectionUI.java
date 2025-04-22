import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CharacterSelectionUI extends JFrame {
    // Store the pet name and pronouns
    private String petName;
    private String pronouns;
  
    public CharacterSelectionUI(String petName, String pronouns) {
        this.petName = petName;
        this.pronouns = pronouns;
        
        JFrame CharacterSelection = new JFrame();
      
        // Header label for character selection
        JLabel chooseCharacter = new JLabel();
        chooseCharacter.setText("choose your pet's character...");
        chooseCharacter.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        chooseCharacter.setBounds(100, 20, 400, 50);
      
        // Panel to hold character options
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new GridLayout(1, 3, 20, 0));
        characterPanel.setBounds(40, 80, 320, 120);
        characterPanel.setBackground(new Color(252, 252, 206)); // Same background as frame
      
        // Create character buttons
        JButton kuchipatchi = createCharacterButton("Kuchipatchi", "/images/Kuchipatchi.png");
        JButton togetchi = createCharacterButton("Togetchi", "/images/Togetchi.png");
        JButton debatchi = createCharacterButton("Debatchi", "/images/Debatchi.png");
      
        // Add character buttons to panel
        characterPanel.add(kuchipatchi);
        characterPanel.add(togetchi);
        characterPanel.add(debatchi);
      
        // Set up the frame
        CharacterSelection.setTitle("Character Selection");
        CharacterSelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CharacterSelection.setResizable(false);
        CharacterSelection.setSize(400, 300);
        CharacterSelection.setLocationRelativeTo(null);
        CharacterSelection.getContentPane().setBackground(new Color(252, 252, 206));
        CharacterSelection.setLayout(null);
        CharacterSelection.setVisible(true);
        CharacterSelection.add(chooseCharacter);
        CharacterSelection.add(characterPanel);
    }
  
    private JButton createCharacterButton(String character, String imagePath) {
        // Create the button with character name
        JButton button = new JButton(character);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        button.setForeground(new Color(0x8B4513));
        button.setBackground(new Color(0xFFDAB9));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
      
        // Try to load and set the image icon
        try {
            // Note: You'll need to create these image files in your project
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
        } catch (Exception e) {
            System.out.println("Image not found: " + imagePath);
            // If image not found, use text only
        }
      
        // Add hover effects
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0xFFB6C1)); // Light Pink
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0xFFDAB9)); // Soft Peach
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            }
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(0xFFA07A)); // Pale Orange
            }
            public void mouseReleased(MouseEvent e) {
                button.setBackground(new Color(0xFFDAB9));
            }
        });
      
        // Add action listener
        button.addActionListener(e -> {
            // Store the selected character and proceed to main game
            String selectedCharacter = character;
            dispose();
            // Pass pet name, pronouns, and character to MainGameUI
            new MainGameUI(petName, pronouns, selectedCharacter);
        });
      
        return button;
    }
}
