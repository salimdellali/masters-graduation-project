package vpcisteamitemspanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.VpCISWindow;
import vpcisactoritemspanels.AllActorsPanel;
import vpcispanels.AllAbstractionLevelsPanel;
import vpcispanels.TemplatePanel;

public class AllItemsPanel extends TemplatePanel {

	JLabel idNameAbstractionLevelEntredLabel;
	JTextField idNameAbstractionLevelEntredTextField;
	JButton actorButton;
	JButton teamButton;
	JButton informationActorButton;
	JButton informationTeamButton;
	JButton activityActorButton;
	JButton activityTeamButton;
	JButton linkButton;
	ImageIcon ii;

	public AllItemsPanel() {
		super("All items related to the Abstraction Level");

		// hide the useless buttons in the southPanel
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		okButton.hide();
		cancelButton.hide();
		editButton.hide();

		// show Message
		showSystemMessage("Find all items related to the Abstraction Level that you can manipulate");

		// configure the centerPanel
		ii = new ImageIcon("icons//Actor.png");
		actorButton = new JButton("Actor", ii);

		ii = new ImageIcon("icons//Team.png");
		teamButton = new JButton("Team", ii);

		ii = new ImageIcon("icons//InformationActor.png");
		informationActorButton = new JButton("Information Actor", ii);

		ii = new ImageIcon("icons//InformationTeam.png");
		informationTeamButton = new JButton("Information Team", ii);

		ii = new ImageIcon("icons//ActivityActor.png");
		activityActorButton = new JButton("Activity Actor", ii);

		ii = new ImageIcon("icons//ActivityTeam.png");
		activityTeamButton = new JButton("Activity Team", ii);

		// configure the behavior of the center Panel Buttons
		actorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allActorsPanel);
				AllActorsPanel allActorsPanel = new AllActorsPanel();
				VpCISWindow.pVpCIS.add(allActorsPanel, "AllActorsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllActorsPanel");
			}
		});

		teamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allTeamsPanel);
				AllTeamsPanel allTeamsPanel = new AllTeamsPanel();
				VpCISWindow.pVpCIS.add(allTeamsPanel, "AllTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllTeamsPanel");
			}
		});

		informationActorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// do something
			}
		});

		informationTeamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allInformationTeamsPanel);
				AllInformationTeamsPanel allInformationTeamsPanel = new AllInformationTeamsPanel();
				VpCISWindow.pVpCIS.add(allInformationTeamsPanel, "AllInformationTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllInformationTeamsPanel");
			}
		});

		activityActorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// do something
			}
		});

		activityTeamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allActivityTeamsPanel);
				AllActivityTeamsPanel allActivityTeamsPanel = new AllActivityTeamsPanel();
				VpCISWindow.pVpCIS.add(allActivityTeamsPanel, "AllActivityTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllActivityTeamsPanel");
			}
		});

		// add the buttons to the centerPanel
		centerPanel.add(actorButton);
		centerPanel.add(informationActorButton);
		centerPanel.add(activityActorButton);
		centerPanel.add(teamButton);
		centerPanel.add(informationTeamButton);
		centerPanel.add(activityTeamButton);

		// configure the southPanel
		idNameAbstractionLevelEntredLabel = new JLabel("Id - Name AbstractionLevel related :");
		idNameAbstractionLevelEntredTextField = new JTextField();
		idNameAbstractionLevelEntredTextField.setColumns(20);
		idNameAbstractionLevelEntredTextField.setText(String.valueOf(AllAbstractionLevelsPanel.idAbstractionLevelEntred)
				+ " - " + AllAbstractionLevelsPanel.NameIdAbstractionLevelEntred);
		idNameAbstractionLevelEntredTextField.setEditable(false);

		linkButton = new JButton("Link Item - Item");

		linkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// do something
			}
		});

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allAbstractionLevelsPanel);
				AllAbstractionLevelsPanel allAbstractionLevelsPanel = new AllAbstractionLevelsPanel();
				VpCISWindow.pVpCIS.add(allAbstractionLevelsPanel, "AllAbstractionLevelsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllAbstractionLevelsPanel");
			}
		});

		// add the button to the southPanel
		southPanel.add(linkButton);
		southPanel.add(idNameAbstractionLevelEntredLabel, 1);
		southPanel.add(idNameAbstractionLevelEntredTextField, 2);

	}

}
