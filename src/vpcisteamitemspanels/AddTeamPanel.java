package vpcisteamitemspanels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.Launcher;
import gui.VpCISWindow;
import vpcispanels.*;

public class AddTeamPanel extends TemplatePanel {

	JLabel nameTeamLabel;
	JTextField nameTeamTextField;
	JLabel idAbstractionLevelLabel;
	JTextField idAbstractionLevelTextField;
	
	public AddTeamPanel(){
		//constructor
		super("Add a new Team");
		
		//hide the useless Buttons
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		backButton.hide();
		editButton.hide();
		
		//configuration of the centerPanel
		centerPanel.setLayout(new FlowLayout());
		
		nameTeamLabel = new JLabel ("Name Team :");
		nameTeamTextField = new JTextField();
		nameTeamTextField.setColumns(20);
		
		idAbstractionLevelLabel = new JLabel("Id Abstraction Level Related :");
		idAbstractionLevelTextField = new JTextField();
		idAbstractionLevelTextField.setColumns(20);
		idAbstractionLevelTextField.setEditable(false);
		idAbstractionLevelTextField.setText(String.valueOf(AllAbstractionLevelsPanel.idAbstractionLevelEntred));
		
		//show system message
		showSystemMessage("Name field is mendatory");
		
		//add to the centerPanel
		centerPanel.add(nameTeamLabel);
		centerPanel.add(nameTeamTextField);
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
					if (!nameTeamTextField.getText().equals("")) {
						// System.out.println(nameCISProjectTextField.get);
						String request = String.format("INSERT INTO Team (nameTeam,idAbstractionLevel) VALUES ( " + "'"
								+ nameTeamTextField.getText() + "'," + "'"
								+ idAbstractionLevelTextField.getText() + "')");
						//System.out.println(request);
						Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						Launcher.adbc.st.executeUpdate(request);
						// AddCISProjectPanel.

						// PanelSystemMessages.systemMessages.setText("CIS
						// project added successfully!");
						showSystemMessage("Team added successfully!");
						// VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS,
						// "AllCISProjectsPanel");
						
						VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allTeamsPanel);
						AllTeamsPanel allTeamsPanel = new AllTeamsPanel();
						VpCISWindow.pVpCIS.add(allTeamsPanel, "AllTeamsPanel");
						VpCISWindow.pVpCIS.repaint();
						VpCISWindow.pVpCIS.revalidate();
						VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllTeamsPanel");
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
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allTeamsPanel);
				AllTeamsPanel allTeamsPanel = new AllTeamsPanel();
				VpCISWindow.pVpCIS.add(allTeamsPanel, "AllTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllTeamsPanel");
			}
		});
	}
}
