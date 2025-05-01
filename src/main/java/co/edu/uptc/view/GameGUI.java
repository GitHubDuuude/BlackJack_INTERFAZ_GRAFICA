/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * @author sebas
 */

public class GameGUI extends JFrame {

	public GameGUI() {
		setTitle("CRUPIER");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(0, 100, 0)); // Fondo verde oscuro

		// Panel principal con BorderLayout
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setOpaque(false);

		// Panel superior
		JPanel topPanel = createTopPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);

		// Panel central con cartas
		JPanel centerPanel = createCenterPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Panel inferior
		JPanel bottomPanel = createBottomPanel();
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		add(mainPanel);
		setVisible(true);
	}

	private JPanel createTopPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Esquinas superiores
		JPanel cornerPanel = new JPanel(new GridLayout(1, 2));
		cornerPanel.add(createCornerBox("?"));
		cornerPanel.add(createCornerBox("?"));
		panel.add(cornerPanel, BorderLayout.NORTH);

		// Título y hora
		JLabel title = new JLabel("CRUPIER", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 24));
		title.setForeground(Color.WHITE);
		panel.add(title, BorderLayout.CENTER);

		JLabel time = new JLabel("00:30", SwingConstants.CENTER);
		time.setForeground(Color.WHITE);
		panel.add(time, BorderLayout.SOUTH);

		return panel;
	}

	private JPanel createCornerBox(String text) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(30, 30));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridBagLayout());

		JLabel label = new JLabel(text);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(label);
		return panel;
	}

	private JPanel createCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Reglas del crupier
		JPanel rulesPanel = new JPanel();
		rulesPanel.setBackground(Color.WHITE);
		rulesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JLabel rules = new JLabel("El crupier debe pedir hasta alcanzar 16 y plantarse en 17");
		rules.setForeground(Color.YELLOW);
		rulesPanel.add(rules);
		panel.add(rulesPanel, BorderLayout.NORTH);

		// Paneles de apuestas
		JPanel betsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
		betsPanel.add(createRoundedPanel("Paga 2 a 1"));
		betsPanel.add(createRoundedPanel("SEGURO"));
		betsPanel.add(createRoundedPanel("Paga 2 a 1"));
		panel.add(betsPanel, BorderLayout.CENTER);

		// Jugadores
		JPanel playersPanel = new JPanel(new GridLayout(1, 2));
		playersPanel.add(createPlayerPanel("Jugador 1"));
		playersPanel.add(createPlayerPanel("Jugador 2"));
		panel.add(playersPanel, BorderLayout.SOUTH);

		return panel;
	}

	private JPanel createRoundedPanel(String text) {
		return new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.WHITE);
				g2d.setStroke(new BasicStroke(2));
				g2d.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, 20, 20));

				JLabel label = new JLabel(text, SwingConstants.CENTER);
				label.setBounds(0, 0, getWidth(), getHeight());
				add(label);
			}
		};
	}

	private JPanel createPlayerPanel(String name) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);

		// Cartas
		JPanel cardsPanel = new JPanel();
		cardsPanel.add(createCard(true));
		cardsPanel.add(createCard(false));
		panel.add(cardsPanel, BorderLayout.CENTER);

		// Nombre
		JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
		nameLabel.setForeground(Color.WHITE);
		panel.add(nameLabel, BorderLayout.SOUTH);

		return panel;
	}

	private JPanel createCard(boolean visible) {
		return new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (visible) {
					g.setColor(Color.RED);
					g.fillRect(0, 0, 50, 70);
				}
				else {
					g.setColor(Color.BLUE);
					g.fillRect(0, 0, 50, 70);
				}
			}
		};
	}

	private JPanel createBottomPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Acciones
		JPanel actionsPanel = new JPanel();
		actionsPanel.setBackground(new Color(0, 100, 0));
		String[] actions = { "Doblar", "Rendirse", "Quedarse" };
		for (String action : actions) {
			JButton btn = new JButton(action);
			btn.setBackground(Color.WHITE);
			actionsPanel.add(btn);
		}
		panel.add(actionsPanel, BorderLayout.WEST);

		// Fichas
		JPanel chipsPanel = new JPanel();
		chipsPanel.add(new JLabel("150"));
		chipsPanel.add(createChip());
		panel.add(chipsPanel, BorderLayout.EAST);

		return panel;
	}

	private JPanel createChip() {
		return new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.fillOval(0, 0, 30, 30);
			}
		};
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GameGUI());
	}

}
