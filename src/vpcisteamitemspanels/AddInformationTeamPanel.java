package vpcisteamitemspanels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.Launcher;
import gui.VpCISWindow;
import vpcispanels.AllAbstractionLevelsPanel;
import vpcispanels.TemplatePanel;

public class AddInformationTeamPanel extends TemplatePanel {

	JLabel nameInformationTeamLabel;
	JTextField nameInformationTeamTextField;
	JLabel idAbstractionLevelLabel;
	JTextField idAbstractionLevelTextField;

	public AddInformationTeamPanel() {
		// constructor
		super("Add a Team Information");

		// hide the useless buttons
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		backButton.hide();
		editButton.hide();

		// configure the centerPanel
		centerPanel.setLayout(new FlowLayout());

		nameInformationTeamLabel = new JLabel("Name Team Information :");
		nameInformationTeamTextField = new JTextField();
		nameInformationTeamTextField.setColumns(20);

		idAbstractionLevelLabel = new JLabel("Id Abstraction Level Related :");
		idAbstractionLevelTextField = new JTextField();
		idAbstractionLevelTextField.setColumns(20);
		idAbstractionLevelTextField.setEditable(false);
		idAbstractionLevelTextField.setText(String.valueOf(AllAbstractionLevelsPanel.idAbstractionLevelEntred));

		// show system message
		showSystemMessage("Name field is mendatory");
		
		// add to the centerPanel
		centerPanel.add(nameInformationTeamLabel);
		centerPanel.add(nameInformationTeamTextField);
		centerPanel.add(idAbstractionLevelLabel);
		centerPanel.add(idAbstractionLevelTextField);
		
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
					if (!nameInformationTeamTextField.getText().equals("")) {
						// System.out.println(nameCISProjectTextField.get);
						String request = String.format("INSERT INTO InformationTeam (nameInformationTeam,idAbstractionLevel) VALUES ( " + "'"
								+ nameInformationTeamTextField.getText() + "'," + "'"
								+ idAbstractionLevelTextField.getText() + "')");
						//System.out.println(request);
						Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						Launcher.adbc.st.executeUpdate(request);
						// AddCISProjectPanel.

						// PanelSystemMessages.systemMessages.setText("CIS
						// project added successfully!");
						showSystemMessage("Team Information added successfully!");
						// VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS,
						// "AllCISProjectsPanel");
						
						VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allInformationTeamsPanel);
						AllInformationTeamsPanel allInformationTeamsPanel = new AllInformationTeamsPanel();
						VpCISWindow.pVpCIS.add(allInformationTeamsPanel, "AllInformationTeamsPanel");
						VpCISWindow.pVpCIS.repaint();
						VpCISWindow.pVpCIS.revalidate();
						VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllInformationTeamsPanel");
						
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
				// do something
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allInformationTeamsPanel);
				AllInformationTeamsPanel allInformationTeamsPanel = new AllInformationTeamsPanel();
				VpCISWindow.pVpCIS.add(allInformationTeamsPanel, "AllInformationTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllInformationTeamsPanel");
			}
		});
	}
}
