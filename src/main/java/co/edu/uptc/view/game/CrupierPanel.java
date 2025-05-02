package co.edu.uptc.view.game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CrupierPanel extends JPanel {
    private GridBagConstraints gbc;
    private JLabel crupier, timerLabel, ruleLabel, leftPileLabel, rightPileLabel;
    private JButton pauseButton, helpButton;
    private JPanel cardsPanel;
    private Font customFont;

    public CrupierPanel() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        loadCustomFont();
        initComponents();
        firstLine();
        secondLine();
        thirdLine();
        fourthLine();
        applyFont(this);
    }

    private void decorateButton(JButton button, Color backgroundColor, Color textColor) {
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button.setFont(customFont.deriveFont(20f));
        button.setContentAreaFilled(true);
        button.setOpaque(true);
    }

    private void loadCustomFont() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("data/view/fonts/PressStart2P-Regular.ttf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            customFont = new Font("SansSerif", Font.BOLD, 24);
        }
    }

    private void initComponents() {
        pauseButton = new JButton("||");
        helpButton = new JButton("?");

        decorateButton(pauseButton, Color.WHITE, new Color(94,54,67,255));
        decorateButton(helpButton, Color.WHITE, new Color(94,54,67,255));

        crupier = new JLabel("CRUPIER");
        crupier.setFont(new Font("SansSerif", Font.BOLD, 34));
        crupier.setForeground(Color.WHITE);

        timerLabel = new JLabel("00:30");
        timerLabel.setForeground(Color.WHITE);

        ImageIcon pileIcon = new ImageIcon("data/view/images/cards/cards_deck.png");
        leftPileLabel = new JLabel(pileIcon);
        rightPileLabel = new JLabel(pileIcon);

        cardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        cardsPanel.setOpaque(false);
        for (int i = 0; i < 5; i++) {
            JLabel cardLabel = new JLabel(new ImageIcon("data/view/images/cards/card_back.png"));
            cardsPanel.add(cardLabel);
        }

        ruleLabel = new JLabel("El crupier debe pedir hasta alcanzar 16 y plantarse en todos los 17");
        ruleLabel.setForeground(Color.YELLOW);
        ruleLabel.setBackground(new Color(217, 217, 217, 209));
    }

    private void firstLine() {
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(pauseButton, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(crupier, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(helpButton, gbc);
    }

    private void secondLine() {
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(timerLabel, gbc);
    }

    private void thirdLine() {
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(leftPileLabel, gbc);

        gbc.gridx = 1;
        add(cardsPanel, gbc);

        gbc.gridx = 2;
        add(rightPileLabel, gbc);
    }

    private void fourthLine() {
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(ruleLabel, gbc);
    }

    private void applyFont(Component component) {
        if (component instanceof JLabel) {
            component.setFont(customFont);
        } else if (component instanceof JButton) {
            component.setFont(customFont.deriveFont(18f));
        } else if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                applyFont(child);
            }
        }
    }
}
