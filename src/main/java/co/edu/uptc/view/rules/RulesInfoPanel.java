/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package co.edu.uptc.view.rules;

import java.awt.*;
import java.io.*;
import javax.swing.*;

/**
 * @author sebas
 */
public class RulesInfoPanel extends JPanel {

	private final RulesMainPanel mainPanel;

	Image backgroundImg;

	public RulesInfoPanel(RulesMainPanel mainPanel) {
		this.mainPanel = mainPanel;
		setBackgroundImg();
		initLayout();
	}

	private void setBackgroundImg() {
		backgroundImg = new ImageIcon(Constants.BACKGROUND_NAME).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
	}

	private void initLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		initComponents(gbc);
	}

	private void initComponents(GridBagConstraints gbc) {
		// Intentamos cargar la fuente pixel art
		Font titleFont;
		Font rulesFont;
		try {
			titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("data/view/fonts/PressStart2P-Regular.ttf"))
				.deriveFont(30f);
			rulesFont = Font.createFont(Font.TRUETYPE_FONT, new File("data/view/fonts/PressStart2P-Regular.ttf"))
				.deriveFont(12f);
		}
		catch (FontFormatException | IOException ex) {
			titleFont = new Font("Monospaced", Font.BOLD, 30);
			rulesFont = new Font("Monospaced", Font.PLAIN, 12);
		}

		setBackground(new Color(0, 51, 51)); // Fondo general

		// Título BLACKJACK!
		JLabel titleLabel = new JLabel("BLACKJACK!");
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);

		// Configuración de título con fondo dorado
		JPanel titlePanel = new JPanel(new GridBagLayout());
		titlePanel.setBackground(new Color(255, 215, 0));
		titlePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 3),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));

		GridBagConstraints gbcTitle = new GridBagConstraints();
		gbcTitle.gridx = 0;
		gbcTitle.gridy = 0;
		gbcTitle.fill = GridBagConstraints.HORIZONTAL;
		gbcTitle.insets = new Insets(10, 10, 10, 10);
		titlePanel.add(titleLabel, gbcTitle);

		// Título de Reglas
		JLabel reglasTitleLabel = new JLabel("Reglas");
		reglasTitleLabel.setFont(rulesFont);
		reglasTitleLabel.setForeground(new Color(219, 112, 147));
		reglasTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		reglasTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		// Texto de reglas
		JTextArea rulesText = new JTextArea("Las reglas básicas del blackjack son las siguientes:\n\n"
				+ "• El objetivo es vencer la mano del crupier sin pasarse de 21.\n"
				+ "• Si obtienes exactamente 21 puntos, eso se llama un blackjack.\n"
				+ "• Las cartas del 2 al 10 valen su valor, las figuras valen 10, y el as vale 1 / 11.\n"
				+ "• Puedes pedir, doblar, dividir o plantarte.\n"
				+ "• Si consigues más de 21 puntos, ¡te has pasado!.");

		rulesText.setFont(rulesFont);
		rulesText.setForeground(new Color(200, 200, 200));
		rulesText.setBackground(new Color(0, 51, 51));
		rulesText.setEditable(false);
		rulesText.setOpaque(false);
		rulesText.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		// Panel de reglas
		JPanel rulesPanel = new JPanel(new GridBagLayout());
		rulesPanel.setBackground(new Color(0, 51, 51));

		GridBagConstraints gbcRulesTitle = new GridBagConstraints();
		gbcRulesTitle.gridx = 0;
		gbcRulesTitle.gridy = 0;
		gbcRulesTitle.anchor = GridBagConstraints.CENTER;
		gbcRulesTitle.insets = new Insets(10, 10, 5, 10);
		rulesPanel.add(reglasTitleLabel, gbcRulesTitle);

		GridBagConstraints gbcRulesText = new GridBagConstraints();
		gbcRulesText.gridx = 0;
		gbcRulesText.gridy = 1;
		gbcRulesText.fill = GridBagConstraints.HORIZONTAL;
		gbcRulesText.insets = new Insets(5, 10, 10, 10);
		rulesPanel.add(rulesText, gbcRulesText);

		// Iconos de cierre y flecha con posiciones específicas
		ImageIcon closeIconImg = new ImageIcon(Constants.CLOSE_ICON_NAME); // Cargar
																			// imagen del
																			// botón

		JButton closeIconBtn = new JButton(closeIconImg); // Crear botón con imagen
		closeIconBtn.setPreferredSize(new Dimension(closeIconImg.getIconWidth(),closeIconImg.getIconHeight())); // Ajustar tamaño
		closeIconBtn.setBorderPainted(false); // Ocultar borde si no lo quieres
		closeIconBtn.setContentAreaFilled(false); // Fondo transparente
		closeIconBtn.setFocusPainted(false);
		closeIconBtn.setContentAreaFilled(false);

		// Agregar evento de clic
		closeIconBtn.addActionListener(e -> System.exit(0)); // Cerrar la aplicación al
															// hacer clic

		JPanel arrowIconPanel = new JPanel();
		arrowIconPanel.setBackground(Color.WHITE);
		arrowIconPanel.setPreferredSize(new Dimension(20, 20));

		// Agregar los componentes al GridBagLayout general
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 20, 10, 20);
		add(titlePanel, gbc);

		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1.0;
		add(rulesPanel, gbc);

		// Ubicación del botón de cierre en la esquina superior derecha
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.insets = new Insets(5, 5, 0, 5);
		add(closeIconBtn, gbc);

		// Ubicación del icono de flecha en la esquina inferior derecha
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(arrowIconPanel, gbc);
	}

}
