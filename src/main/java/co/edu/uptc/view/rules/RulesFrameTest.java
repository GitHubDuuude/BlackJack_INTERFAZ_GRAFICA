/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package co.edu.uptc.view.rules;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author sebas
 */
public class RulesFrameTest extends JFrame {

	public RulesFrameTest() {
		initComponents();
		this.setSize(1280, 720);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(rootPane,
					"No se pudo aplicar el Look and Feel de Windows. Usando el predeterminado.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void initComponents(){
		setLookAndFeel();
		RulesMainPanel mainPanel = new RulesMainPanel();
		this.setContentPane(mainPanel);
	}
	
	public static void main(String[] args) {
		new RulesFrameTest();
	}

}
