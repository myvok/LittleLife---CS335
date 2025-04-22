import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen extends JFrame {
    public StartScreen() {
        setTitle("LittleLife - Start");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(252, 252, 206)); //changes background color 
        JButton startButton = new JButton("🐾 Start Game 🐾");
        startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        startButton.setForeground(new Color(0x8B4513));
        startButton.setBackground(new Color(0xFFDAB9));
        startButton.setFocusPainted(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.setPreferredSize(new Dimension(200, 60));
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        // Hover effects
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(new Color(0xFFB6C1)); // Light Pink
                startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
            }
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(new Color(0xFFDAB9)); // Soft Peach
                startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            }
            public void mousePressed(MouseEvent e) {
                startButton.setBackground(new Color(0xFFA07A)); // Pale Orange
            }
            public void mouseReleased(MouseEvent e) {
                startButton.setBackground(new Color(0xFFDAB9));
            }
        });
        // Click Action
        startButton.addActionListener(e -> {
            dispose();
            new GameSetupUI(); // Launch the next GUI part (we’ll create this next)
        });
        add(startButton);
        setVisible(true);
    }
}