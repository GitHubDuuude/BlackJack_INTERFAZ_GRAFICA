/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package co.edu.uptc.view.rules;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * @author sebas
 */
public class RulesMainPanel extends JPanel {

	private CardLayout cardLayout;

	private RulesInfoPanel rulesInfoPanel;

	private ActionsPanel actionsPanel;

	private CrupierPanel crupierPanel;

	private RewardsPanel rewardsPanel;

	public RulesMainPanel() {
		this.cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		initPanels();
	}

	private void initPanels() {
		rulesInfoPanel = new RulesInfoPanel(this);
		this.add(rulesInfoPanel, RulesPanelConstants.RULES_INFO_PANEL_NAME);
		actionsPanel = new ActionsPanel(this);
		this.add(actionsPanel, RulesPanelConstants.ACTIONS_PANEL_NAME);
		crupierPanel = new CrupierPanel(this);
		this.add(crupierPanel, RulesPanelConstants.CRUPIER_PANEL_NAME);
		rewardsPanel = new RewardsPanel(this);
		this.add(rewardsPanel, RulesPanelConstants.REWARDS_PANEL_NAME);
		showRulesInfoPanel();
	}

	public void showRulesInfoPanel() {
		cardLayout.show(this, RulesPanelConstants.RULES_INFO_PANEL_NAME);
	}

	public void showActionsPanel() {
		cardLayout.show(this, RulesPanelConstants.ACTIONS_PANEL_NAME);
	}

	public void showCrupierPanel() {
		cardLayout.show(this, RulesPanelConstants.CRUPIER_PANEL_NAME);
	}

	public void showRewardsPanel() {
		cardLayout.show(this, RulesPanelConstants.REWARDS_PANEL_NAME);
	}

}
