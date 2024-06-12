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
import vpcispanels.TemplatePanel;
import vpcisteamitemspanels.EditTeamPanel.TeamRecord;

public class EditActivityTeamPanel extends TemplatePanel {

	protected JLabel idActivityTeamLabel;
	protected JList allActivityTeamsList;
	protected JLabel nameActivityTeamLabel;
	protected JTextField nameActivityTeamTextField;
	protected JLabel idAbstractionLevelLabel;
	protected JTextField idAbstractionLevelTextField;
	protected ArrayList<ActivityTeamRecord> activityTeams = new ArrayList<ActivityTeamRecord>();

	public EditActivityTeamPanel() {
		// constructor
		super("Edit a Team Activity");

		// hide useless buttons
		okButton.hide();
		addButton.hide();
		backButton.hide();
		editButton.hide();

		// configure the centerPanel
		idActivityTeamLabel = new JLabel("Id Activity Team :");
		allActivityTeamsList = new JList();
		nameActivityTeamLabel = new JLabel("Name Activity Team :");
		nameActivityTeamTextField = new JTextField();
		nameActivityTeamTextField.setColumns(20);
		idAbstractionLevelLabel = new JLabel("Id Abstarction Level related :");
		idAbstractionLevelTextField = new JTextField();
		idAbstractionLevelTextField.setColumns(20);
		idAbstractionLevelTextField.setEditable(false);

		// showSystemMessage
		showSystemMessage(
				"Select an Id of an Activity team. To update,choose an Id Activity Team, change the name then click the 'Update' Button,"
						+ " to delete, simply choose the Id Activity team then click 'Delete' Button");

		// fill the Jlist
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM ActivityTeam");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			if (!Launcher.adbc.rs.next()) {
				// no CIS Records found
				// centerPanel.add(new JLabel("No CIS Projects found"));
				ActivityTeamRecord atr = new ActivityTeamRecord(0, "", 0);
				activityTeams.add(atr);
			} else {

				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {

					int idActivityTeam;
					String nameActivityTeam;
					int idAbstractionLevel;

					idActivityTeam = Launcher.adbc.rs.getInt("idActivityTeam");
					nameActivityTeam = Launcher.adbc.rs.getString("nameActivityTeam");
					idAbstractionLevel = Launcher.adbc.rs.getInt("idAbstractionLevel");

					ActivityTeamRecord atr = new ActivityTeamRecord(idActivityTeam, nameActivityTeam,
							idAbstractionLevel);
					activityTeams.add(atr);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> idNameActivityTeams = new ArrayList<String>();

		// copy the id name from viewpointList to viewpointsListIdName
		Iterator<ActivityTeamRecord> it = activityTeams.iterator();
		while (it.hasNext()) {
			ActivityTeamRecord atr = new ActivityTeamRecord(it.next());
			String str = atr.idActivityTeam + " - " + atr.nameActivityTeam;
			// System.out.println(str);
			idNameActivityTeams.add(str);
		}

		allActivityTeamsList = new JList(idNameActivityTeams.toArray());

		allActivityTeamsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				nameActivityTeamTextField
						.setText(activityTeams.get(allActivityTeamsList.getSelectedIndex()).nameActivityTeam);
				idAbstractionLevelTextField.setText(
						String.valueOf(activityTeams.get(allActivityTeamsList.getSelectedIndex()).idAbstractionLevel));

			}
		});

		// add components to the centerpPanel

		centerPanel.add(idActivityTeamLabel);
		centerPanel.add(new JScrollPane(allActivityTeamsList));
		centerPanel.add(nameActivityTeamLabel);
		centerPanel.add(nameActivityTeamTextField);
		centerPanel.add(idAbstractionLevelLabel);
		centerPanel.add(idAbstractionLevelTextField);

		// configure the buttons
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					String request = "UPDATE ActivityTeam " + "SET nameActivityTeam='"
							+ nameActivityTeamTextField.getText() + "' "
					// + "AND description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idActivityTeam='"
							+ activityTeams.get(allActivityTeamsList.getSelectedIndex()).idActivityTeam + "'";

					Launcher.adbc.st.executeUpdate(request);

					// end updating
				} catch (Exception e) {
					e.printStackTrace();
				}
				// show message
				showSystemMessage("the Team Activity has been updated succesffully");

				// switch to allActivityTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allActivityTeamsPanel);
				AllActivityTeamsPanel allActivityTeamsPanel = new AllActivityTeamsPanel();
				VpCISWindow.pVpCIS.add(allActivityTeamsPanel, "AllActivityTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllActivityTeamsPanel");
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
					String request = "DELETE FROM ActivityTeam "
							// + "SET nameCIS='"+nameCISTextField.getText()+"' "
							// + "AND
							// description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idActivityTeam='"
							+ activityTeams.get(allActivityTeamsList.getSelectedIndex()).idActivityTeam + "'";

					Launcher.adbc.st.executeUpdate(request);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// show message
				showSystemMessage("the Team Activity has been deleted succesffully");

				// switch to allActivityTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allActivityTeamsPanel);
				AllActivityTeamsPanel allActivityTeamsPanel = new AllActivityTeamsPanel();
				VpCISWindow.pVpCIS.add(allActivityTeamsPanel, "AllActivityTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllActivityTeamsPanel");
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// switch to allActivityTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allActivityTeamsPanel);
				AllActivityTeamsPanel allActivityTeamsPanel = new AllActivityTeamsPanel();
				VpCISWindow.pVpCIS.add(allActivityTeamsPanel, "AllActivityTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllActivityTeamsPanel");
			}
		});

	}

	class ActivityTeamRecord {

		int idActivityTeam;
		String nameActivityTeam;
		int idAbstractionLevel;

		ActivityTeamRecord(int idActivityTeam, String nameActivityTeam, int idAbstractionLevel) {
			this.idActivityTeam = idActivityTeam;
			this.nameActivityTeam = nameActivityTeam;
			this.idAbstractionLevel = idAbstractionLevel;
		}

		ActivityTeamRecord(ActivityTeamRecord atr) {
			this.idActivityTeam = atr.idActivityTeam;
			this.nameActivityTeam = atr.nameActivityTeam;
			this.idAbstractionLevel = atr.idAbstractionLevel;
		}
	}
}
