package co.edu.uptc.view.game.players;

import co.edu.uptc.view.reusable.ImageButton;

import java.awt.*;
import javax.swing.*;

public class ActionsPanel extends JPanel {
    private JPanel firstLine;
    private JPanel secondLine;
    private JPanel thirdLine;
    private ImageButton pedir;
    private ImageButton doblar;
    private ImageButton rendirse;
    private ImageButton quedarse;
    private ImageButton dividir;
    private GridBagConstraints gbc;

    public ActionsPanel() {
        setBackground(new java.awt.Color(4, 45, 6));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        initComponents();
        this.setLayout(new GridBagLayout());
        firstLine.setLayout(new GridBagLayout());
        secondLine.setLayout(new GridBagLayout());
        thirdLine.setLayout(new GridBagLayout());

        gbc.insets = new Insets(0, 0, 10, 0);

        gbc.ipadx = 5;
        gbc.ipady = 15;

        firstLine.add(pedir, gbc);
        firstLine.add(doblar, gbc);
        firstLine.add(rendirse, gbc);
        firstLine.setOpaque(false);
        secondLine.add(quedarse,gbc);
        secondLine.add(dividir,gbc);
        secondLine.setOpaque(false);
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/icons/tokens.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 35, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel fichas = new JLabel(scaledIcon);
        fichas.setOpaque(false);
        thirdLine.add(fichas,gbc);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        this.add(firstLine,gbc);
        this.add(secondLine,gbc);
        this.add(thirdLine,gbc);

    }

    private void initComponents() {
        firstLine = new JPanel();
        secondLine = new JPanel();
        thirdLine = new JPanel();
        gbc = new GridBagConstraints();
        pedir = new ImageButton("Pedir", false, 16);
        doblar = new ImageButton("Doblar", false, 16);
        rendirse = new ImageButton("Rendirse", false, 16);
        quedarse = new ImageButton("Quedarse", true, 16);
        dividir = new ImageButton("Dividir", true, 16);

    }

}

