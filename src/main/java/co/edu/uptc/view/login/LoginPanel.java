package co.edu.uptc.view.login;

import co.edu.uptc.view.MainPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoginPanel extends JPanel {
    private MainPanel mainPanel;

    public LoginPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.25;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 1;
        add(crearFranjaCampoTexto(), gbc);

        gbc.gridy = 2;
        add(crearFranjaBoton("JUGAR"), gbc);

        gbc.gridy = 3;
        add(crearFranjaBoton("SALIR"), gbc);
    }

    private JPanel crearFranjaCampoTexto() {
        JPanel franja = new JPanel(new GridBagLayout());
        franja.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(200, 20, 0, 20);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;

        JTextFieldConFondo campoTexto = new JTextFieldConFondo();

        franja.add(campoTexto, gbc);
        return franja;
    }

    private JPanel crearFranjaBoton(String textoBoton) {
        JPanel franja = new JPanel(new GridBagLayout());
        franja.setOpaque(false);

        JButton boton = new JButton() {
            private Image imgBoton;

            {
                try {
                    String imagenPath = textoBoton.equals("JUGAR") ? "/images/icons/button_play.png" : "/images/icons/button_to_back.png";

                    InputStream imgStream = getClass().getResourceAsStream(imagenPath);
                    if (imgStream == null) {
                        throw new IOException("No se encontró la imagen en la ruta: " + imagenPath);
                    }

                    BufferedImage imgOriginal = ImageIO.read(imgStream);
                    int nuevoAncho = 250;
                    int nuevoAlto = 80;
                    imgBoton = imgOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
                    setPreferredSize(new Dimension(nuevoAncho, nuevoAlto));

                } catch (IOException e) {
                    System.err.println("Error al cargar imagen: " + e.getMessage());
                }


                setContentAreaFilled(false);
                setBorderPainted(false);
                setFocusPainted(false);
                setText("");

                addActionListener(e -> {
                    if (textoBoton.equals("JUGAR")) {
                        mainPanel.updatePanel("game", false);
                    } else {
                        mainPanel.updatePanel("menu", true);
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                if (imgBoton != null) {
                    g.drawImage(imgBoton, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        boton.setPreferredSize(new Dimension(250, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        franja.add(boton, gbc);

        return franja;
    }
}
