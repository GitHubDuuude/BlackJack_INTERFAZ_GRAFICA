package co.edu.uptc.view.game.players;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayersPanel extends JPanel {

    private GridBagConstraints gbc;
    private JLabel leftPlayerLabel, centerPlayerLabel, rightPlayerLabel, decoration1, decoration2, decoration3;
    private JPanel leftPlayerPanel, centerPlayerPanel, rightPlayerPanel;
    private Image playerTokens;
    private JPanel TEMPactionsPanel;
    private Font customFont;

    public PlayersPanel() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        loadCustomFont();
        initComponents();
        firstLine();
        secondLine();
        thirdLine();
        fourthLine();
        fifthLine();
        applyFont(this);
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
        playerTokens = new ImageIcon(getClass().getResource("/images/icons/tokens.png")).getImage();

//        cardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
//        cardsPanel.setOpaque(false);
//        for (int i = 0; i < 3; i++) {
//            JLabel cardLabel = new JLabel(new ImageIcon(getClass().getResource("/images/cards/card_back.png")));
//            cardsPanel.add(cardLabel);
//        }

        initLabels();
    }

    private void initLabels() {
        leftPlayerLabel = new JLabel("Jugador Izq");
        centerPlayerLabel = new JLabel("Jugador Central");
        rightPlayerLabel = new JLabel("Jugador Der");

        decoration1 = new JLabel("Paga 2 a 1");
        decoration2 = new JLabel("SEGURO");
        decoration3 = new JLabel("Paga 2 a 1");

        decoration1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        decoration2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        decoration3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
    }

    private void firstLine() {
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridwidth = 3;
        add(decoration1, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;

        gbc.gridx = 4;
        add(decoration3, gbc);
        gbc.gridwidth = 1;
    }

    private void secondLine() {
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(decoration2, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
    }

    private void thirdLine() {
//        gbc.gridy = 2;
//        gbc.gridx = 1;
//
//        add(leftPlayerPanel, gbc);
//
//        gbc.gridx = 2;
//        gbc.gridwidth = 3;
//        add(centerPlayerPanel, gbc);
//        gbc.gridwidth = 1;
//
//        gbc.gridx = 5;
//        add(rightPlayerPanel, gbc);
    }

    private void fourthLine() {
        gbc.gridy = 3;
        gbc.gridx = 1;
        add(leftPlayerLabel, gbc);

        gbc.gridx = 3;
        add(centerPlayerLabel, gbc);

        gbc.gridx = 5;
        add(rightPlayerLabel, gbc);
    }

    private void fifthLine() {
//        gbc.gridy = 4;
//        gbc.gridx = 1;
//        gbc.anchor = GridBagConstraints.LINE_START;
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//        add(TEMPactionsPanel, gbc);
    }

    private void applyFont(Component component) {
        if (component instanceof JLabel) {
            component.setFont(customFont);
        } else if (component instanceof JButton) {
            component.setFont(customFont.deriveFont(18f));
            component.setForeground(Color.WHITE);
        } else if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                applyFont(child);
            }
        }
    }
}
