package vpcisteamitemspanels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.Launcher;
import gui.VpCISWindow;
import vpcispanels.TemplatePanel;

public class AddNonFunctionalRequirementTeamPanel extends TemplatePanel {

	JLabel nameNonFunctionalRequirementTeamLabel;
	JTextField nameNonFunctionalRequirementTeamTextField;
	JLabel idInformationTeamLabel;
	JTextField idInformationTeamTextField;

	public AddNonFunctionalRequirementTeamPanel() {
		super("Add a Team Non Functional Requirement");

		// hide the useless Buttons
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		backButton.hide();
		editButton.hide();

		// configure the centerPanel
		centerPanel.setLayout(new FlowLayout());

		nameNonFunctionalRequirementTeamLabel = new JLabel("Name Non Functional Requirement Team :");
		nameNonFunctionalRequirementTeamTextField = new JTextField();
		nameNonFunctionalRequirementTeamTextField.setColumns(20);

		idInformationTeamLabel = new JLabel("Id Information Team related :");
		idInformationTeamTextField = new JTextField();
		idInformationTeamTextField.setColumns(20);
		idInformationTeamTextField.setEditable(false);
		idInformationTeamTextField.setText(String.valueOf(AllInformationTeamsPanel.idInformationTeamEntred));

		// show system message
		showSystemMessage("Name field is mendatory");

		// add to the centerPanel
		centerPanel.add(nameNonFunctionalRequirementTeamLabel);
		centerPanel.add(nameNonFunctionalRequirementTeamTextField);
		centerPanel.add(idInformationTeamLabel);
		centerPanel.add(idInformationTeamTextField);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// test if the required field are filled
				// Insert into CIS
				// show system message
				// delete panel allCIS
				// recreate a new Panel AllCIS
				// add id to pVpCIS
				// repaint & revalidate
				// call cl.show ->switch to AllCISProjectsPanel

				try {
					if (!nameNonFunctionalRequirementTeamTextField.getText().equals("")) {
						// System.out.println(nameCISProjectTextField.get);
						String request = String
								.format("INSERT INTO NonFunctionalRequirementTeam (nameNonFunctionalRequirementTeam,idInformationTeam) VALUES ( "
										+ "'" + nameNonFunctionalRequirementTeamTextField.getText() + "'," + "'"
										+ idInformationTeamTextField.getText() + "')");
						// System.out.println(request);
						Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						Launcher.adbc.st.executeUpdate(request);
						// AddCISProjectPanel.

						// PanelSystemMessages.systemMessages.setText("CIS
						// project added successfully!");
						showSystemMessage("Team Non Functional Requirement added successfully!");
						// VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS,
						// "AllCISProjectsPanel");

						VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allNonFunctionalRequirementTeamsPanel);
						AllNonFunctionalRequirementTeamsPanel allNonFunctionalRequirementTeamsPanel = new AllNonFunctionalRequirementTeamsPanel();
						VpCISWindow.pVpCIS.add(allNonFunctionalRequirementTeamsPanel, "AllNonFunctionalRequirementTeamsPanel");
						VpCISWindow.pVpCIS.repaint();
						VpCISWindow.pVpCIS.revalidate();
						VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllNonFunctionalRequirementTeamsPanel");

					} else {

						// this.showSystemMessage("NOTE: the field description
						// is prefered to fill, but not mendatory.");
						// PanelSystemMessages.systemMessages.setText("Name
						// field required");
						showSystemMessage("Name field required");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allNonFunctionalRequirementTeamsPanel);
				AllNonFunctionalRequirementTeamsPanel allNonFunctionalRequirementTeamsPanel = new AllNonFunctionalRequirementTeamsPanel();
				VpCISWindow.pVpCIS.add(allNonFunctionalRequirementTeamsPanel, "AllNonFunctionalRequirementTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllNonFunctionalRequirementTeamsPanel");
			}
		});
	}
}
