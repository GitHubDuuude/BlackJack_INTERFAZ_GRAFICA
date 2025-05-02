package co.edu.uptc.view.game.crupier;

import co.edu.uptc.view.game.GamePanel;
import co.edu.uptc.view.reusable.ArcadeButton;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CrupierPanel extends JPanel {
    private GamePanel gamePanel;
    private GridBagConstraints gbc;
    private JLabel crupierLabel, timerLabel, ruleLabel, leftPileLabel, rightPileLabel;
    private ArcadeButton pauseButton, helpButton;
    private JPanel cardsPanel;
    private Font customFont;

    public CrupierPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setOpaque(false);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        loadCustomFont();
        initComponents();
        firstLine();
        secondLine();
        thirdLine();
        applyFont(this);
        crupierLabel.setFont(customFont.deriveFont(32f));
        crupierLabel.setForeground(Color.WHITE);
    }

    private void loadCustomFont() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("SansSerif", Font.BOLD, 24);
        }
    }

    private void initComponents() {
        initButtons();
        ImageIcon pileIcon = new ImageIcon(getClass().getResource("/images/cards/cards_deck.png"));
        leftPileLabel = new JLabel(pileIcon);
        rightPileLabel = new JLabel(pileIcon);

        cardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        cardsPanel.setOpaque(false);
        for (int i = 0; i < 3; i++) {
            JLabel cardLabel = new JLabel(new ImageIcon(getClass().getResource("/images/cards/card_back.png")));
            cardsPanel.add(cardLabel);
        }

        initLabels();
    }

    private void initButtons() {
        pauseButton = new ArcadeButton("||", customFont, Color.WHITE, new Color(94, 54, 67, 255));
        helpButton = new ArcadeButton("?", customFont, Color.WHITE, new Color(94, 54, 67, 255));

        pauseButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "El crupier debe pedir hasta alcanzar 16 y plantarse en todos los 17", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
        });

        helpButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "El crupier debe pedir hasta alcanzar 16 y plantarse en todos los 17", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void initLabels() {
        crupierLabel = new JLabel("CRUPIER");

        timerLabel = new JLabel("30 s");
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        timerLabel.setOpaque(true);
        timerLabel.setBackground(new Color(49,41,41,255));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ruleLabel = new JLabel("El crupier debe pedir hasta alcanzar 16 y plantarse en todos los 17");
        ruleLabel.setOpaque(true);
        ruleLabel.setBackground(new Color(181,190,185,255));
        ruleLabel.setForeground(Color.YELLOW);
        ruleLabel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[]{7,5}, 0), new Color(25,45,34,255)));
    }

    private void firstLine() {
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_START;
        add(pauseButton, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridheight = 2;
        add(rightPileLabel, gbc);
        gbc.gridheight = 1;

        add(crupierLabel, gbc);

        gbc.gridheight = 2;
        add(leftPileLabel, gbc);
        gbc.gridheight = 1;

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(helpButton, gbc);
        gbc.gridwidth = 1;
    }

    private void secondLine() {
        // Timer
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 30;
        gbc.ipady = 35;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(timerLabel, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        add(cardsPanel, gbc);
        gbc.gridx = 0;
        gbc.gridheight = 1;
    }

    private void thirdLine() {
        gbc.gridy = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.ipadx = 0;
        gbc.ipady = 25;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        ruleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(ruleLabel, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
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
