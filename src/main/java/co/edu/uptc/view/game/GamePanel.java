
package co.edu.uptc.view.game;

import co.edu.uptc.view.game.crupier.CrupierPanel;
import co.edu.uptc.view.game.players.PlayersPanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private CrupierPanel crupierPanel;
    private PlayersPanel playersPanel;

    public GamePanel() {
        crupierPanel = new CrupierPanel(this);
        playersPanel = new PlayersPanel();
        setLayout(new BorderLayout());
        add(crupierPanel, BorderLayout.NORTH);
        add(playersPanel, BorderLayout.CENTER);
        setOpaque(false);
    }

    public void openRulesMenu() {
    }
}
