import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainGameUI extends JFrame {
    private Tamagotchi pet;
    private Player player;
    private Logic logic;
    
    // UI Components
    private JProgressBar healthBar;
    private JProgressBar happinessBar;
    private JProgressBar hungerBar;
    private JLabel statusLabel;
    private JLabel characterLabel;
    private JButton dateButton;
    
    public MainGameUI() {
        // Default constructor
        this.pet = new Tamagotchi("Default", "they/them");
        this.player = new Player("Player");
        this.logic = new Logic(pet, player);
        
        pet.startDayCycle();
        initializeUI();
        updateStats();
    }
    
    public MainGameUI(String petName, String pronouns, String character) {
        this.pet = new Tamagotchi(petName, pronouns);
        this.player = new Player("Player");
        this.logic = new Logic(pet, player);
        
        initializeUI();
        updateCharacterDisplay(character);
        updateStats();
        setTitle("Little Life - " + petName);
    }
    
    private void initializeUI() {
        setTitle("Little Life - Main Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(252, 252, 206));
        setLayout(new BorderLayout(10, 10));
        
        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }
    
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(252, 252, 206));
        
        statusLabel = new JLabel(pet.getName() + " Status: Happy");
        statusLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        statusLabel.setForeground(new Color(0x8B4513));
        panel.add(statusLabel);
        
        JLabel favFood = new JLabel("Favorite Food: " + pet.getFavoriteFood());
        favFood.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        favFood.setForeground(new Color(0x8B4513));
        panel.add(favFood);
        
        return panel;
    }
    
    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(252, 252, 206));
        
        characterLabel = new JLabel(pet.getName(), JLabel.CENTER);
        characterLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        characterLabel.setPreferredSize(new Dimension(200, 200));
        characterLabel.setIcon(createPlaceholderIcon(150, 150));
        panel.add(characterLabel, BorderLayout.CENTER);
        panel.add(createStatsPanel(), BorderLayout.EAST);
        return panel;
    }
    
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(3,1,10,10));
        panel.setBackground(new Color(252, 252, 206));
        panel.setBorder(BorderFactory.createEmptyBorder(20,10,20,20));
        
        panel.add(makeBarPanel("Health", healthBar = new JProgressBar(0,100), healthBar));
        panel.add(makeBarPanel("Happiness", happinessBar = new JProgressBar(0,100), happinessBar));
        panel.add(makeBarPanel("Hunger", hungerBar = new JProgressBar(0,100), hungerBar));
        
        healthBar.setValue(50);
        happinessBar.setValue(50);
        hungerBar.setValue(50);
        
        return panel;
    }
    
    private JPanel makeBarPanel(String label, JProgressBar bar, JProgressBar ref) {
        JPanel p = new JPanel(new BorderLayout(5,0));
        p.setBackground(new Color(252, 252, 206));
        JLabel l = new JLabel(label + ": ");
        l.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        p.add(l, BorderLayout.WEST);
        ref.setStringPainted(true);
        p.add(ref, BorderLayout.CENTER);
        return p;
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        panel.setBackground(new Color(252, 252, 206));
        panel.setBorder(BorderFactory.createEmptyBorder(10,0,20,0));
        
        JButton feed = createActionButton("Feed","🍎"); feed.addActionListener(e->showFeedOptions());
        JButton play = createActionButton("Play","🎮"); play.addActionListener(e->showPlayOptions());
        dateButton = createActionButton("Date","❤️"); dateButton.setEnabled(false); dateButton.addActionListener(e->showDateOptions());
        JButton next = createActionButton("Next Day","🌙"); next.addActionListener(e->{ pet.endOfDaySummary(); pet.startDayCycle(); updateStats(); checkDateEligibility(); checkPetStatus(); });
        
        panel.add(feed);
        panel.add(play);
        panel.add(dateButton);
        panel.add(next);
        return panel;
    }
    
    private JButton createActionButton(String text, String emoji) {
        JButton b = new JButton(emoji+" "+text);
        b.setFont(new Font("Comic Sans MS", Font.BOLD,16));
        b.setForeground(new Color(0x8B4513));
        b.setBackground(new Color(0xFFDAB9));
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(120,50));
        b.setBorder(BorderFactory.createLineBorder(Color.WHITE,2,true));
        b.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){ b.setBackground(new Color(0xFFB6C1)); b.setBorder(BorderFactory.createLineBorder(Color.WHITE,3,true)); }
            public void mouseExited(MouseEvent e){ b.setBackground(new Color(0xFFDAB9)); b.setBorder(BorderFactory.createLineBorder(Color.WHITE,2,true)); }
            public void mousePressed(MouseEvent e){ b.setBackground(new Color(0xFFA07A)); }
            public void mouseReleased(MouseEvent e){ b.setBackground(new Color(0xFFDAB9)); }
        });
        return b;
    }
    
    private void showFeedOptions() {
        if (pet.isSick()) {
            int choice = JOptionPane.showConfirmDialog(
                this,
                pet.getName() + " is sick! Give medicine?",
                "Pet is Sick",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            if (choice == JOptionPane.YES_OPTION) {
                pet.changeHealth(10);
                pet.changeHappiness(-5);
                JOptionPane.showMessageDialog(this, "Medicine given: Health +10, Happiness -5");
            } else {
                pet.changeHealth(-15);
                JOptionPane.showMessageDialog(this, "No medicine: Health -15");
            }
            updateStats();
            return;
        }
        String[] options = {"Apple 🍎","Vegetables 🥦","Water 💧","Soda 🥤","Candy 🍬","Junk Food 🍔"};
        String selection = (String) JOptionPane.showInputDialog(
            this,
            "What would you like to feed your pet?",
            "Feeding Time",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        if (selection != null) {
            if (pet.getPersonality().equals("playful") && pet.getDaysIgnored() >= 3) {
                JOptionPane.showMessageDialog(this, pet.getName() + " is angry and ignores you! 😠", "Ignored!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String food = selection.split(" ")[0].toLowerCase();
            logic.makeChoice("feed", food);
            showFeedbackMessage("You fed your pet " + selection);
            updateStats(); checkDateEligibility(); checkPetStatus();
        }
    }
    
    private void showPlayOptions() {
        if (pet.isSick()) {
            JOptionPane.showMessageDialog(
                this,
                pet.getName() + " is sick and refuses to play! Please give medicine first.",
                "Cannot Play",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        String[] options = {"Fetch 🎾","Walk 🚶","Watch Movie 🎬","Play Video Game 🎮"};
        String selection = (String) JOptionPane.showInputDialog(
            this,
            "What would you like to do with your pet?",
            "Play Time",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        if (selection != null) {
            if (pet.getPersonality().equals("playful") && pet.getDaysIgnored() >= 3) {
                JOptionPane.showMessageDialog(this, pet.getName() + " is angry and ignores you! 😠", "Ignored!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String activity;
            if (selection.startsWith("Watch") || selection.startsWith("Play Video")) {
                String[] parts = selection.split(" ");
                activity = parts[0].toLowerCase() + " " + parts[1].toLowerCase();
            } else {
                activity = selection.split(" ")[0].toLowerCase();
            }
            logic.makeChoice("play", activity);
            if (Math.random() < 0.2) {
                String gift = getGiftByPersonality(pet.getPersonality());
                showFeedbackMessage("🎁 Surprise! " + pet.getName() + " found a " + gift + "!");
                pet.changeHappiness(10);
            }
            showFeedbackMessage("You played " + selection + " with your pet");
            updateStats(); checkDateEligibility(); checkPetStatus();
        }
    }
    
    private String getGiftByPersonality(String p) {
        switch(p) {
            case "playful": return "squeaky toy";
            case "lazy": return "cozy blanket";
            case "grumpy": return "rare snack";
            default: return "treat";
        }
    }
    
    private void showDateOptions() {
        String[] dates = {"Movie Date 🎬","Coffee Date ☕","Dinner Date 🍽️","Grab Drinks 🍹"};
        String[] gifts = {"Flower 💐","Chocolates 🍫","No Gift"};
        String dateSelection = (String) JOptionPane.showInputDialog(this, "What kind of date?", "Date Time", JOptionPane.QUESTION_MESSAGE, null, dates, dates[0]);
        if (dateSelection != null) {
            String giftSelection = (String) JOptionPane.showInputDialog(this, "Would you like to bring a gift?", "Date Gift", JOptionPane.QUESTION_MESSAGE, null, gifts, gifts[0]);
            if (giftSelection != null) {
                logic.makeChoice("date", dateSelection.split(" ")[0].toLowerCase());
                showFeedbackMessage("You went on a " + dateSelection + " with " + pet.getName() + (giftSelection.equals("No Gift") ? "" : " and brought " + giftSelection));
                updateStats();
                dateButton.setEnabled(false);
                checkPetStatus();
            }
        }
    }
    
    private void showFeedbackMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Action Result", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void updateStats() {
        healthBar.setValue(pet.getHealth());
        happinessBar.setValue(pet.getHappiness());
        hungerBar.setValue(pet.getHunger());
        if (!pet.isAlive()) {
            statusLabel.setText(pet.getName() + " is dead! 😢");
            statusLabel.setForeground(Color.RED);
        } else if (pet.isSick()) {
            statusLabel.setText(pet.getName() + " is sick! Take better care!");
            statusLabel.setForeground(Color.RED);
        } else if (pet.canGoOnDate()) {
            statusLabel.setText(pet.getName() + " is ready for a date! ❤️");
            statusLabel.setForeground(new Color(0xFF69B4));
        } else {
            statusLabel.setText(pet.getName() + " is happy! (" + pet.getPersonality() + ")");
            statusLabel.setForeground(new Color(0x8B4513));
        }
    }
    
    private void checkDateEligibility() {
        if (pet.canGoOnDate()) {
            dateButton.setEnabled(true);
            showFeedbackMessage("Congratulations! " + pet.getName() + " is ready for a date!");
        }
    }
    
    private void checkPetStatus() {
        if (!pet.isAlive()) {
            int choice = JOptionPane.showConfirmDialog(this, "Oh no! " + pet.getName() + " has died! Game Over! Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
                new StartScreen();
            } else {
                System.exit(0);
            }
        }
    }
    
    private void updateCharacterDisplay(String character) {
        characterLabel.setText(pet.getName() + " (" + character + ")");
    }
    
    private Icon createPlaceholderIcon(int w, int h) {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(new Color(0xFFDAB9)); g.fillOval(x, y, w, h);
                g.setColor(new Color(0x8B4513)); g.drawOval(x, y, w, h);
                g.fillOval(x + w/4, y + h/3, 10, 10);
                g.fillOval(x + 3*w/4 - 10, y + h/3, 10, 10);
                g.drawArc(x + w/4, y + 2*h/3, w/2, h/6, 0, -180);
            }
            @Override public int getIconWidth() { return w; }
            @Override public int getIconHeight() { return h; }
        };
    }
}
