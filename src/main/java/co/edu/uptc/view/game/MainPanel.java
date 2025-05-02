package co.edu.uptc.view.game;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private CrupierPanel crupierPanel;


    public MainPanel() {
        crupierPanel = new CrupierPanel();
        setLayout(new BorderLayout());
        add(crupierPanel, BorderLayout.NORTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon imagenFondo = new ImageIcon("data/view/images/game_background.jpg");
        g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

}
