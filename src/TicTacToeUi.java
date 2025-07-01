import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

// Main class implementing ActionListener for button clicks
public class TicTacToeUi implements ActionListener {
    
    // UI Components
    JFrame frame = new JFrame();
    JButton[] buttons = new JButton[9]; // 9 TicTacToe grid buttons
    JPanel topLeftPanel = new JPanel();  // Player O panel
    JLabel leftLabel = new JLabel();
    
    JPanel topRightPanel = new JPanel(); // Player X panel
    JLabel rightLabel = new JLabel();

    JPanel playPanel = new JPanel(); // Panel holding the game board (3x3 grid)

    // Icons for X and O
    ImageIcon oIcon = new ImageIcon("letter-o_9467531.png");
    ImageIcon xIcon = new ImageIcon("x_13932861.png");

    // Player names
    String otextField;
    String xtextField;

    boolean playOneTurn; // true = O's turn, false = X's turn
    Random random = new Random();

    // Constructor
    TicTacToeUi(String o, String x) {
        this.otextField = o;
        this.xtextField = x;
        
        // Set up main game frame
        frame.setSize(450, 600);
        frame.getContentPane().setBackground(new Color(25, 25, 75));
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Resize player icons
        oIcon = resizeImage(oIcon, 40, 40);
        xIcon = resizeImage(xIcon, 40, 40);

        // Configure top-left panel (Player O)
        topLeftPanel.setBounds(70, 60, 90, 90);
        topLeftPanel.setBackground(new Color(0, 0, 51));
        leftLabel.setText(otextField);
        leftLabel.setIcon(oIcon);
        leftLabel.setHorizontalTextPosition(JLabel.CENTER);
        leftLabel.setVerticalTextPosition(JLabel.TOP);
        leftLabel.setIconTextGap(20);
        leftLabel.setForeground(Color.white);
        leftLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        topLeftPanel.add(leftLabel);
        frame.add(topLeftPanel);

        // Configure top-right panel (Player X)
        topRightPanel.setBounds(280, 60, 90, 90);
        topRightPanel.setBackground(new Color(0, 0, 51));
        rightLabel.setText(xtextField);
        rightLabel.setForeground(Color.white);
        rightLabel.setIcon(xIcon);
        rightLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        rightLabel.setHorizontalTextPosition(JLabel.CENTER);
        rightLabel.setVerticalTextPosition(JLabel.TOP);
        rightLabel.setIconTextGap(20);
        topRightPanel.add(rightLabel);
        frame.add(topRightPanel);

        // Configure main game board (3x3 grid)
        playPanel.setLayout(new GridLayout(3, 3, 10, 10));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 125));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(0, 0, 51));
            buttons[i].addActionListener(this); // Add button click listener
            playPanel.add(buttons[i]);
        }
        playPanel.setBackground(new Color(102, 51, 153));
        playPanel.setBounds(60, 230, 320, 300);
        frame.add(playPanel);

        // Decide who plays first and place icon in center
        firstTurn();

        // Show frame
        frame.setVisible(true);
    }

    // Resize icon images for consistent display
    private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resized = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                Icon currentIcon = buttons[i].getIcon();

                // If it's O's turn
                if (playOneTurn) {
                    int oCount = countIcons(oIcon);
                    if (currentIcon == null) {
                        // Place new O icon if fewer than 3 on board
                        if (oCount < 3) {
                            buttons[i].setIcon(oIcon);
                            playOneTurn = false;
                            topLeftPanel.setBorder(null);
                            topRightPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
                            check();
                        } else {
                            JOptionPane.showMessageDialog(frame, "You already have 3 Os on the board. Remove one to move.");
                        }
                    } else if (currentIcon == oIcon) {
                        // Remove own icon
                        buttons[i].setIcon(null);
                    }
                } else {
                    // X's turn
                    int xCount = countIcons(xIcon);
                    if (currentIcon == null) {
                        if (xCount < 3) {
                            buttons[i].setIcon(xIcon);
                            playOneTurn = true;
                            topRightPanel.setBorder(null);
                            topLeftPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
                            check();
                        } else {
                            JOptionPane.showMessageDialog(frame, "You already have 3 Xs on the board. Remove one to move.");
                        }
                    } else if (currentIcon == xIcon) {
                        buttons[i].setIcon(null);
                    }
                }
            }
        }
    }

    // Randomly choose first player and set center icon
    void firstTurn() {
        if (random.nextInt(2) == 0) {
            buttons[4].setIcon(oIcon);
            playOneTurn = false;
            topRightPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
        } else {
            buttons[4].setIcon(xIcon);
            playOneTurn = true;
            topLeftPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
        }
    }

    // Check if there's a winner
    private void check() {
        // Check for X win (horizontal, vertical, diagonal)
        if ((buttons[0].getIcon() == xIcon) && (buttons[1].getIcon() == xIcon) && (buttons[2].getIcon() == xIcon)) xWins(0, 1, 2);
        if ((buttons[3].getIcon() == xIcon) && (buttons[4].getIcon() == xIcon) && (buttons[5].getIcon() == xIcon)) xWins(3, 4, 5);
        if ((buttons[6].getIcon() == xIcon) && (buttons[7].getIcon() == xIcon) && (buttons[8].getIcon() == xIcon)) xWins(6, 7, 8);
        if ((buttons[0].getIcon() == xIcon) && (buttons[3].getIcon() == xIcon) && (buttons[6].getIcon() == xIcon)) xWins(0, 3, 6);
        if ((buttons[1].getIcon() == xIcon) && (buttons[4].getIcon() == xIcon) && (buttons[7].getIcon() == xIcon)) xWins(1, 4, 7);
        if ((buttons[2].getIcon() == xIcon) && (buttons[5].getIcon() == xIcon) && (buttons[8].getIcon() == xIcon)) xWins(2, 5, 8);
        if ((buttons[0].getIcon() == xIcon) && (buttons[4].getIcon() == xIcon) && (buttons[8].getIcon() == xIcon)) xWins(0, 4, 8);
        if ((buttons[2].getIcon() == xIcon) && (buttons[4].getIcon() == xIcon) && (buttons[6].getIcon() == xIcon)) xWins(2, 4, 6);

        // Check for O win (horizontal, vertical, diagonal)
        if ((buttons[0].getIcon() == oIcon) && (buttons[1].getIcon() == oIcon) && (buttons[2].getIcon() == oIcon)) oWins(0, 1, 2);
        if ((buttons[3].getIcon() == oIcon) && (buttons[4].getIcon() == oIcon) && (buttons[5].getIcon() == oIcon)) oWins(3, 4, 5);
        if ((buttons[6].getIcon() == oIcon) && (buttons[7].getIcon() == oIcon) && (buttons[8].getIcon() == oIcon)) oWins(6, 7, 8);
        if ((buttons[0].getIcon() == oIcon) && (buttons[3].getIcon() == oIcon) && (buttons[6].getIcon() == oIcon)) oWins(0, 3, 6);
        if ((buttons[1].getIcon() == oIcon) && (buttons[4].getIcon() == oIcon) && (buttons[7].getIcon() == oIcon)) oWins(1, 4, 7);
        if ((buttons[2].getIcon() == oIcon) && (buttons[5].getIcon() == oIcon) && (buttons[8].getIcon() == oIcon)) oWins(2, 5, 8);
        if ((buttons[0].getIcon() == oIcon) && (buttons[4].getIcon() == oIcon) && (buttons[8].getIcon() == oIcon)) oWins(0, 4, 8);
        if ((buttons[2].getIcon() == oIcon) && (buttons[4].getIcon() == oIcon) && (buttons[6].getIcon() == oIcon)) oWins(2, 4, 6);
    }

    // Highlight X's win and show message
    private void xWins(int a, int b, int c) {
        buttons[a].setBorder(BorderFactory.createLineBorder(Color.orange));
        buttons[b].setBorder(BorderFactory.createLineBorder(Color.orange));
        buttons[c].setBorder(BorderFactory.createLineBorder(Color.orange));
        JOptionPane.showMessageDialog(null, "Congratulation", "X Win", JOptionPane.PLAIN_MESSAGE);
        resetGame();
    }

    // Highlight O's win and show message
    private void oWins(int a, int b, int c) {
        buttons[a].setBorder(BorderFactory.createLineBorder(Color.orange));
        buttons[b].setBorder(BorderFactory.createLineBorder(Color.orange));
        buttons[c].setBorder(BorderFactory.createLineBorder(Color.orange));
        JOptionPane.showMessageDialog(null, "Congratulation", "O Win", JOptionPane.PLAIN_MESSAGE);
        resetGame();
    }

    // Count how many of a specific icon are on the board
    private int countIcons(Icon icon) {
        int count = 0;
        for (JButton button : buttons) {
            if (button.getIcon() == icon) {
                count++;
            }
        }
        return count;
    }

    // Reset the board after a win
    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setIcon(null);
            buttons[i].setBorder(null);
        }
        topLeftPanel.setBorder(null);
        topRightPanel.setBorder(null);
        firstTurn(); // Start new game
    }
}
