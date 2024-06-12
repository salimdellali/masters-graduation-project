package vpcisteamitemspanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.VpCISWindow;
import vpcispanels.AllAbstractionLevelsPanel;
import vpcispanels.TemplatePanel;

public class AllInformationTeamItemsPanel extends TemplatePanel {

	
	
	JLabel idNameInformationTeamEntredLabel;
	JTextField idNameInformationTeamEntredTextField;
	JLabel idNameAbstractionLevelEntredLabel;
	JTextField idNameAbstractionLevelEntredTextField;
	JButton dataButton;
	JButton knowledgeButton;
	JButton nonFunctionalRequirementButton;
	ImageIcon ii;

	public AllInformationTeamItemsPanel() {
		super("All Information items related to the Team information and the Abstraction Level");

		// hide the useless buttons in the southPanel
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		okButton.hide();
		cancelButton.hide();
		editButton.hide();

		// show Message
		showSystemMessage(
				"Find all Information items related to the Information and Abstraction Level related that you can manipulate");

		ii = new ImageIcon("icons/Data.png");
		dataButton = new JButton("Data", ii);

		ii = new ImageIcon("icons//Knowledge.png");
		knowledgeButton = new JButton("Knowledge", ii);

		ii = new ImageIcon("icons//NFR.png");
		nonFunctionalRequirementButton = new JButton("Non Functional Requirement", ii);

		// configure the behavior of the center Panel Buttons
		dataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allDataTeamsPanel);
				AllDataTeamsPanel allDataTeamsPanel = new AllDataTeamsPanel();
				VpCISWindow.pVpCIS.add(allDataTeamsPanel, "AllDataTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllDataTeamsPanel");
			}
		});

		knowledgeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allKnowledgeTeamsPanel);
				AllKnowledgeTeamsPanel allKnowledgeTeamsPanel = new AllKnowledgeTeamsPanel();
				VpCISWindow.pVpCIS.add(allKnowledgeTeamsPanel, "AllKnowledgeTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllKnowledgeTeamsPanel");
			}
		});

		nonFunctionalRequirementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allNonFunctionalRequirementTeamsPanel);
				AllNonFunctionalRequirementTeamsPanel allNonFunctionalRequirementTeamsPanel = new AllNonFunctionalRequirementTeamsPanel();
				VpCISWindow.pVpCIS.add(allNonFunctionalRequirementTeamsPanel, "AllNonFunctionalRequirementTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllNonFunctionalRequirementTeamsPanel");
			}
		});

		// add the buttons to the centerPanel
		centerPanel.add(dataButton);
		centerPanel.add(knowledgeButton);
		centerPanel.add(nonFunctionalRequirementButton);

		// configure the southPanel
		
		idNameInformationTeamEntredLabel = new JLabel("Id - Name Information Team related :");
		idNameInformationTeamEntredTextField = new JTextField();
		idNameInformationTeamEntredTextField.setColumns(20);
		idNameInformationTeamEntredTextField.setText(String.valueOf(AllInformationTeamsPanel.idInformationTeamEntred)
				+ " - " + AllInformationTeamsPanel.nameInformationTeamEntred);
		idNameInformationTeamEntredTextField.setEditable(false);
		
		idNameAbstractionLevelEntredLabel = new JLabel("Id - Name AbstractionLevel related :");
		idNameAbstractionLevelEntredTextField = new JTextField();
		idNameAbstractionLevelEntredTextField.setColumns(20);
		idNameAbstractionLevelEntredTextField.setText(String.valueOf(AllAbstractionLevelsPanel.idAbstractionLevelEntred)
				+ " - " + AllAbstractionLevelsPanel.NameIdAbstractionLevelEntred);
		idNameAbstractionLevelEntredTextField.setEditable(false);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// switch to allInformationTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allInformationTeamsPanel);
				AllInformationTeamsPanel allInformationTeamsPanel = new AllInformationTeamsPanel();
				VpCISWindow.pVpCIS.add(allInformationTeamsPanel, "AllInformationTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllInformationTeamsPanel");
			}
		});
		
		//add to the southPanel
		southPanel.add(idNameInformationTeamEntredLabel,1);
		southPanel.add(idNameInformationTeamEntredTextField,2);
		southPanel.add(idNameAbstractionLevelEntredLabel,3);
		southPanel.add(idNameAbstractionLevelEntredTextField,4);
		

	}
}
