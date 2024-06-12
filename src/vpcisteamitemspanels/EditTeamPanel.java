package vpcisteamitemspanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.Launcher;
import gui.VpCISWindow;
import vpcispanels.AllAbstractionLevelsPanel;
import vpcispanels.TemplatePanel;

public class EditTeamPanel extends TemplatePanel {

	protected JLabel idTeamLabel;
	protected JList allTeamsList;
	protected JLabel nameTeamLabel;
	protected JTextField nameTeamTextField;
	protected JLabel idAbstractionLevelLabel;
	protected JTextField idAbstractionLevelTextField;
	protected ArrayList<TeamRecord> teams = new ArrayList<TeamRecord>();

	public EditTeamPanel() {
		// constructor
		super("Edit a Team");

		// hide the useless buttons
		okButton.hide();
		addButton.hide();
		backButton.hide();
		editButton.hide();

		// configure the centerPanel
		idTeamLabel = new JLabel("Id Team :");
		allTeamsList = new JList();
		nameTeamLabel = new JLabel("Name Team :");
		nameTeamTextField = new JTextField();
		nameTeamTextField.setColumns(20);
		idAbstractionLevelLabel = new JLabel("Id Abstarction Level related :");
		idAbstractionLevelTextField = new JTextField();
		idAbstractionLevelTextField.setColumns(20);
		idAbstractionLevelTextField.setEditable(false);

		// showSystemMessage
		showSystemMessage(
				"Select an Id of a team. To update,choose an Id Team, change the name then click the 'Update' Button,"
				+ " to delete, simply choose the Id team then click 'Delete' Button");
		// fill the Jlist
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM Team");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			if (!Launcher.adbc.rs.next()) {
				// no CIS Records found
				// centerPanel.add(new JLabel("No CIS Projects found"));
				TeamRecord tr = new TeamRecord(0, "", 0);
				teams.add(tr);
			} else {

				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {

					int idTeam;
					String nameTeam;
					int idAbstractionLevel;

					idTeam = Launcher.adbc.rs.getInt("idTeam");
					nameTeam = Launcher.adbc.rs.getString("nameTeam");
					idAbstractionLevel = Launcher.adbc.rs.getInt("idAbstractionLevel");

					TeamRecord tr = new TeamRecord(idTeam, nameTeam, idAbstractionLevel);
					teams.add(tr);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> idNameTeams = new ArrayList<String>();

		// copy the id name from viewpointList to viewpointsListIdName
		Iterator<TeamRecord> it = teams.iterator();
		while (it.hasNext()) {
			TeamRecord tr = new TeamRecord(it.next());
			String str = tr.idTeam + " - " + tr.nameTeam;
			// System.out.println(str);
			idNameTeams.add(str);
		}

		allTeamsList = new JList(idNameTeams.toArray());

		allTeamsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				nameTeamTextField.setText(teams.get(allTeamsList.getSelectedIndex()).nameTeam);
				idAbstractionLevelTextField
						.setText(String.valueOf(teams.get(allTeamsList.getSelectedIndex()).idAbstractionLevel));

			}
		});

		// add comonents to the centerpPanel

		centerPanel.add(idTeamLabel);
		centerPanel.add(new JScrollPane(allTeamsList));
		centerPanel.add(nameTeamLabel);
		centerPanel.add(nameTeamTextField);
		centerPanel.add(idAbstractionLevelLabel);
		centerPanel.add(idAbstractionLevelTextField);

		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					String request = "UPDATE Team " + "SET nameTeam='" + nameTeamTextField.getText() + "' "
					// + "AND description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idTeam='" + teams.get(allTeamsList.getSelectedIndex()).idTeam + "'";

					Launcher.adbc.st.executeUpdate(request);

					// end updating
				} catch (Exception e) {
					e.printStackTrace();
				}
				// show message
				showSystemMessage("the Team has been updated succesffully");

				// switch to allTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allTeamsPanel);
				AllTeamsPanel allTeamsPanel = new AllTeamsPanel();
				VpCISWindow.pVpCIS.add(allTeamsPanel, "AllTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllTeamsPanel");
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// delete
					// systemMessage
					// go to AllCISPanel
					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					// update the nameCIS
					String request = "DELETE FROM Team "
							// + "SET nameCIS='"+nameCISTextField.getText()+"' "
							// + "AND
							// description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idTeam='" + teams.get(allTeamsList.getSelectedIndex()).idTeam + "'";

					Launcher.adbc.st.executeUpdate(request);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// show message
				showSystemMessage("the Team has been deleted succesffully");

				// switch to allTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allTeamsPanel);
				AllTeamsPanel allTeamsPanel = new AllTeamsPanel();
				VpCISWindow.pVpCIS.add(allTeamsPanel, "AllTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllTeamsPanel");
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allTeamsPanel);
				AllTeamsPanel allTeamsPanel = new AllTeamsPanel();
				VpCISWindow.pVpCIS.add(allTeamsPanel, "AllTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllTeamsPanel");
			}
		});
	}

	class TeamRecord {

		int idTeam;
		String nameTeam;
		int idAbstractionLevel;

		TeamRecord(int idTeam, String nameTeam, int idAbstractionLevel) {
			this.idTeam = idTeam;
			this.nameTeam = nameTeam;
			this.idAbstractionLevel = idAbstractionLevel;
		}

		TeamRecord(TeamRecord tr) {
			this.idTeam = tr.idTeam;
			this.nameTeam = tr.nameTeam;
			this.idAbstractionLevel = tr.idAbstractionLevel;
		}

	}
}
