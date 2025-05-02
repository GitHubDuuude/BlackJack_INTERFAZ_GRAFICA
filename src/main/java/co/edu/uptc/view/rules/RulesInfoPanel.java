/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package co.edu.uptc.view.rules;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author sebas
 */
public class RulesInfoPanel extends JPanel {
	private final RulesMainPanel mainPanel;

	public RulesInfoPanel(RulesMainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	private void initLayout(){
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
	}

	private void setBackgroundImg() {
		ImageIcon backgroundImg = new ImageIcon(RulesPanelConstants.BACKGROUND_NAME);
		JLabel background = new JLabel(backgroundImg);
		this.add(background);
	}

}
