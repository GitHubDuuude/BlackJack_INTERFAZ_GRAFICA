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
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sebas
 */

public class BlackJackInfoPanel extends JPanel {

	// Fuente personalizada
	private Font pixelFont;

	// Imágenes
	private Image backgroundImage;

	private ImageIcon closeIcon;

	private ImageIcon nextIcon;

	private ImageIcon prevIcon;

	// Colores
	private final Color DARK_GREEN = new Color(0, 77, 38);

	private final Color TEXT_COLOR = new Color(220, 220, 220);

	private final Color TITLE_BG = new Color(150, 60, 60);

	private final Color YELLOW_TEXT = new Color(255, 255, 0);

	private final Color GOLD_COLOR = new Color(255, 183, 27);

	// Variables para la navegación entre paneles
	private CardLayout cardLayout;

	private JPanel contentCards;

	private int currentPageIndex = 0;

	private List<String> pageTitles;

	private List<String[]> pageContents;

	public BlackJackInfoPanel() {
		initializeData();
		initComponents();
	}

	private void initializeData() {
		// Inicializar los títulos de las páginas
		pageTitles = new ArrayList<>();
		pageTitles.add("Reglas");
		pageTitles.add("Movimientos");
		pageTitles.add("Valores");
		pageTitles.add("Estrategia");

		// Inicializar el contenido de las páginas
		pageContents = new ArrayList<>();

		// Página 1: Reglas básicas
		pageContents.add(new String[] { "Las reglas básicas del blackjack son las siguientes:",
				"♦El objetivo es vencer la mano del crupier sin pasarse de 21.",
				"♦Si obtienes exactamente 21 puntos, eso se llama un blackjack.",
				"♦Las cartas del 2 al 10 valen su valor, las figuras valen 10, y el as vale 1 / 11 como le convenga al jugador.",
				"♦Puedes pedir, doblar, dividir o plantarte.", "♦Si consigues más de 21 puntos, ¡te has pasado!" });

		// Página 2: Movimientos
		pageContents.add(new String[] {
				"Doblar: Jugada que consiste en realizar una apuesta extra a la inicial. Dependiendo de las reglas del casino se podrá doblar independientemente de cuales sean las dos primeras cartas o cuando suman 9/10 u 11.",
				"Asegurar: Se trata de una jugada en la que se apuesta a que el crupier obtendrá blackjack con un as en su primera carta.",
				"Separar: Se refiere a separar las 2 primeras cartas si estas tienen valores idénticos, entonces se podrá realizar una apuesta adicional. Por cada carta separada se abrirá un juego más.",
				"Rendición: Se utiliza cuando el jugador considera que es imposible vencer al crupier con sus 2 primeras cartas. Al rendirse sólo pagará la mitad de la apuesta.",
				"Plantarse: Continuar con las mismas cartas y no solicitar adicionales." });

		// Página 3: Valores de las cartas
		pageContents.add(new String[] { "Los valores de las cartas en el blackjack son:",
				"♦Cartas numéricas (2-10): Valen su valor nominal", "♦Figuras (J, Q, K): Valen 10 puntos cada una",
				"♦As: Vale 1 u 11 puntos, según convenga al jugador",
				"♦La mejor mano es un blackjack natural: un As con una figura o un 10",
				"♦Si el jugador y el crupier empatan, se considera 'push' y se devuelve la apuesta" });

		// Página 4: Estrategia
		pageContents.add(new String[] { "Consejos básicos de estrategia:", "♦Nunca pidas carta con 17 o más puntos",
				"♦Siempre pide carta con 11 o menos puntos",
				"♦Con 12-16 puntos, pide carta si el crupier muestra 7 o más", "♦Dobla con 11 puntos casi siempre",
				"♦Dobla con 10 puntos si el crupier no muestra 10 o As", "♦Separa siempre los Ases y los 8s",
				"♦Nunca separes figuras, 5s o 10s" });
	}

	private void initComponents() {
		// Configuración del panel
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));

		// Cargar fuente personalizada
		try {
			InputStream is = getClass().getResourceAsStream("fonts/PressStart2P-Regular.ttf");
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
			backgroundImage = ImageIO.read(getClass().getResource("/images/backgrounds/menu_background.png"));
			closeIcon = new ImageIcon(ImageIO.read(getClass().getResource("/images/icons/exit_btn_icon.png")));
			nextIcon = new ImageIcon(ImageIO.read(getClass().getResource("/images/icons/arrow_next.png")));
			prevIcon = new ImageIcon(ImageIO.read(getClass().getResource("images/icons/arrow_prev.png")));
		}
		catch (IOException e) {
			System.err.println("Error al cargar imágenes: " + e.getMessage());
		}

		// Crear el panel de título
		JPanel titlePanel = createTitlePanel();
		add(titlePanel, BorderLayout.NORTH);

		// Crear panel de contenido con CardLayout para navegar entre páginas
		cardLayout = new CardLayout();
		contentCards = new JPanel(cardLayout);

		// Crear los paneles de contenido para cada página
		for (int i = 0; i < pageTitles.size(); i++) {
			JPanel pagePanel = createContentPanel(pageTitles.get(i), pageContents.get(i));
			contentCards.add(pagePanel, String.valueOf(i));
		}

		add(contentCards, BorderLayout.CENTER);

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
		titleContainer.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(GOLD_COLOR, 4),
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
					Container parent = BlackJackInfoPanel.this.getParent();
					if (parent != null) {
						parent.remove(BlackJackInfoPanel.this);
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

	private JPanel createContentPanel(String title, String[] contentLines) {
		JPanel mainPanel = new JPanel(new BorderLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Fondo verde oscuro para el panel principal
				g.setColor(DARK_GREEN);
				g.fillRect(0, 0, getWidth(), getHeight());

				// Añadir borde
				g.setColor(Color.BLUE);
				g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
			}
		};
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Panel de título
		JLabel panelTitleLabel = new JLabel(title);
		panelTitleLabel.setFont(pixelFont.deriveFont(20f));
		panelTitleLabel.setForeground(TEXT_COLOR);
		panelTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel titleLabelPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(TITLE_BG);
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		titleLabelPanel.setLayout(new BorderLayout());
		titleLabelPanel.add(panelTitleLabel, BorderLayout.CENTER);
		titleLabelPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

		JPanel titleCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titleCenterPanel.setOpaque(false);
		titleCenterPanel.add(titleLabelPanel);

		mainPanel.add(titleCenterPanel, BorderLayout.NORTH);

		// Panel para el contenido con formato especial
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(5, 5, 5, 5);

		// Agregar cada línea de contenido con formato
		for (int i = 0; i < contentLines.length; i++) {
			String line = contentLines[i];
			gbc.gridy = i;

			// Si la línea empieza con una viñeta o es un título, dar formato diferente
			if (line.startsWith("♦")) {
				// Para líneas con viñetas
				JTextPane textPane = new JTextPane();
				textPane.setOpaque(false);
				textPane.setEditable(false);
				textPane.setForeground(TEXT_COLOR);
				textPane.setFont(pixelFont.deriveFont(12f));
				textPane.setText(line);

				// Resaltar palabras clave en amarillo si es necesario
				if (title.equals("Movimientos")) {
					String keyWord = line.split(":")[0];
					if (keyWord.contains(":")) {
						textPane.setForeground(YELLOW_TEXT);
					}
				}

				contentPanel.add(textPane, gbc);
			}
			else if (i == 0) {
				// Para el primer elemento (título o descripción principal)
				JLabel titleLine = new JLabel(line);
				titleLine.setFont(pixelFont.deriveFont(14f));
				titleLine.setForeground(TEXT_COLOR);
				contentPanel.add(titleLine, gbc);
			}
			else {
				// Para otras líneas
				JTextPane textPane = new JTextPane();
				textPane.setOpaque(false);
				textPane.setEditable(false);
				textPane.setForeground(TEXT_COLOR);
				textPane.setFont(pixelFont.deriveFont(12f));
				textPane.setText(line);

				// Aplicar formato especial según sea necesario
				if (title.equals("Movimientos")) {
					String[] parts = line.split(":");
					if (parts.length > 1) {
						textPane.setText(line);
						textPane.setForeground(YELLOW_TEXT);
					}
				}
				contentPanel.add(textPane, gbc);
			}
		}

		// Scroll pane para el contenido
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

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

		// Botón anterior (izquierda)
		if (prevIcon != null) {
			JButton prevButton = new JButton(prevIcon);
			prevButton.setBorderPainted(false);
			prevButton.setContentAreaFilled(false);
			prevButton.setFocusPainted(false);
			prevButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			prevButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (currentPageIndex > 0) {
						currentPageIndex--;
						cardLayout.show(contentCards, String.valueOf(currentPageIndex));
					}
				}
			});

			JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			leftPanel.setOpaque(false);
			leftPanel.add(prevButton);
			panel.add(leftPanel, BorderLayout.WEST);
		}

		// Botón siguiente (derecha)
		if (nextIcon != null) {
			JButton nextButton = new JButton(nextIcon);
			nextButton.setBorderPainted(false);
			nextButton.setContentAreaFilled(false);
			nextButton.setFocusPainted(false);
			nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			nextButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (currentPageIndex < pageTitles.size() - 1) {
						currentPageIndex++;
						cardLayout.show(contentCards, String.valueOf(currentPageIndex));
					}
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

				JFrame frame = new JFrame("BlackJack - Información");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(800, 600);
				frame.setLocationRelativeTo(null);

				BlackJackInfoPanel infoPanel = new BlackJackInfoPanel();
				frame.add(infoPanel);

				frame.setVisible(true);
			}
		});
	}

}
