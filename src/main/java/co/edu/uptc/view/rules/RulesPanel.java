/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package co.edu.uptc.view.rules;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;

/**
 * @author sebas
 */

public class RulesPanel extends JPanel {

	// Fuente personalizada
	private Font pixelFont;

	// Imágenes
	private Image backgroundImage;

	private ImageIcon closeIcon;

	private ImageIcon nextIcon;

	// Colores
	private final Color DARK_GREEN = new Color(0, 77, 38);

	private final Color TEXT_COLOR = new Color(220, 220, 220);

	private final Color TITLE_BG = new Color(150, 60, 60);

	public RulesPanel() {
		initComponents();
	}

	private void initComponents() {
		// Configuración del panel
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));

		// Cargar fuente personalizada
		try {
			InputStream is = getClass().getResourceAsStream("/resources/PressStart2P-Regular.ttf");
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(14f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(pixelFont);
		}
		catch (IOException | FontFormatException e) {
			System.err.println("Error al cargar la fuente: " + e.getMessage());
			pixelFont = new Font("Monospaced", Font.BOLD, 14); // Fuente de respaldo
		}

		// Cargar imágenes
		try {
			backgroundImage = ImageIO.read(getClass().getResource("/resources/bg_pattern.png"));
			closeIcon = new ImageIcon(ImageIO.read(getClass().getResource("/resources/close_button.png")));
			nextIcon = new ImageIcon(ImageIO.read(getClass().getResource("/resources/next_button.png")));
		}
		catch (IOException e) {
			System.err.println("Error al cargar imágenes: " + e.getMessage());
		}

		// Crear el panel de título
		JPanel titlePanel = createTitlePanel();
		add(titlePanel, BorderLayout.NORTH);

		// Crear el panel de contenido con las reglas
		JPanel contentPanel = createContentPanel();
		add(contentPanel, BorderLayout.CENTER);

		// Crear panel para los botones de navegación
		JPanel navigationPanel = createNavigationPanel();
		add(navigationPanel, BorderLayout.SOUTH);
	}

	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new BorderLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (backgroundImage != null) {
					// Dibujar el fondo con patrón
					for (int x = 0; x < getWidth(); x += backgroundImage.getWidth(null)) {
						for (int y = 0; y < getHeight(); y += backgroundImage.getHeight(null)) {
							g.drawImage(backgroundImage, x, y, null);
						}
					}
				}
			}
		};
		panel.setOpaque(false);

		// Crear título "BLACKJACK!" con borde dorado
		JLabel titleLabel = new JLabel("BLACKJACK!");
		titleLabel.setFont(pixelFont.deriveFont(30f));
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Efecto de borde dorado usando un panel con borde
		JPanel titleContainer = new JPanel(new BorderLayout());
		titleContainer.setOpaque(false);
		titleContainer
			.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(255, 183, 27), 4),
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		titleContainer.add(titleLabel, BorderLayout.CENTER);

		panel.add(titleContainer, BorderLayout.CENTER);

		// Botón de cerrar
		if (closeIcon != null) {
			JButton closeButton = new JButton(closeIcon);
			closeButton.setBorderPainted(false);
			closeButton.setContentAreaFilled(false);
			closeButton.setFocusPainted(false);
			closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			closeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Container parent = RulesPanel.this.getParent();
					if (parent != null) {
						parent.remove(RulesPanel.this);
						parent.revalidate();
						parent.repaint();
					}
				}
			});

			JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			topRightPanel.setOpaque(false);
			topRightPanel.add(closeButton);
			panel.add(topRightPanel, BorderLayout.EAST);
		}

		return panel;
	}

	private JPanel createContentPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Fondo verde oscuro para el panel principal
				g.setColor(DARK_GREEN);
				g.fillRect(0, 0, getWidth(), getHeight());

				// Borde
				g.setColor(Color.BLACK);
				g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
			}
		};
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Panel de título "Reglas"
		JLabel rulesLabel = new JLabel("Reglas");
		rulesLabel.setFont(pixelFont.deriveFont(20f));
		rulesLabel.setForeground(TEXT_COLOR);
		rulesLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel rulesLabelPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(TITLE_BG);
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		rulesLabelPanel.setLayout(new BorderLayout());
		rulesLabelPanel.add(rulesLabel, BorderLayout.CENTER);
		rulesLabelPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

		JPanel titleCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titleCenterPanel.setOpaque(false);
		titleCenterPanel.add(rulesLabelPanel);

		mainPanel.add(titleCenterPanel, BorderLayout.NORTH);

		// Panel de reglas del juego
		JTextArea rulesText = new JTextArea();
		rulesText.setFont(pixelFont.deriveFont(12f));
		rulesText.setForeground(TEXT_COLOR);
		rulesText.setBackground(DARK_GREEN);
		rulesText.setEditable(false);
		rulesText.setLineWrap(true);
		rulesText.setWrapStyleWord(true);
		rulesText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Texto de las reglas
		rulesText.setText("Las reglas básicas del blackjack son las siguientes:\n\n"
				+ "♦El objetivo es vencer la mano del crupier sin pasarse de 21.\n\n"
				+ "♦Si obtienes exactamente 21 puntos, eso se llama un blackjack.\n\n"
				+ "♦Las cartas del 2 al 10 valen su valor, las figuras valen 10, y el as vale 1 / 11 como le convenga al jugador.\n\n"
				+ "♦Puedes pedir, doblar, dividir o plantarte.\n\n"
				+ "♦Si consigues más de 21 puntos, ¡te has pasado!");

		mainPanel.add(rulesText, BorderLayout.CENTER);

		return mainPanel;
	}

	private JPanel createNavigationPanel() {
		JPanel panel = new JPanel(new BorderLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (backgroundImage != null) {
					// Dibujar el fondo con patrón
					for (int x = 0; x < getWidth(); x += backgroundImage.getWidth(null)) {
						for (int y = 0; y < getHeight(); y += backgroundImage.getHeight(null)) {
							g.drawImage(backgroundImage, x, y, null);
						}
					}
				}
			}
		};
		panel.setOpaque(false);

		// Botón siguiente
		if (nextIcon != null) {
			JButton nextButton = new JButton(nextIcon);
			nextButton.setBorderPainted(false);
			nextButton.setContentAreaFilled(false);
			nextButton.setFocusPainted(false);
			nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			nextButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Acción para el botón siguiente
					System.out.println("Siguiente página de reglas");
				}
			});

			JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			rightPanel.setOpaque(false);
			rightPanel.add(nextButton);
			panel.add(rightPanel, BorderLayout.EAST);
		}

		return panel;
	}

	// Para probar el panel
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (Exception e) {
					e.printStackTrace();
				}

				JFrame frame = new JFrame("BlackJack - Reglas");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(800, 600);
				frame.setLocationRelativeTo(null);

				RulesPanel rulesPanel = new RulesPanel();
				frame.add(rulesPanel);

				frame.setVisible(true);
			}
		});
	}

}
