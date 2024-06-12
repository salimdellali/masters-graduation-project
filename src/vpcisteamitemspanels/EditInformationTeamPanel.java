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
import vpcisteamitemspanels.EditActivityTeamPanel.ActivityTeamRecord;

public class EditInformationTeamPanel extends TemplatePanel {

	protected JLabel idInformationTeamLabel;
	protected JList allInformationTeamsList;
	protected JLabel nameInformationTeamLabel;
	protected JTextField nameInformationTeamTextField;
	protected JLabel idAbstractionLevelLabel;
	protected JTextField idAbstractionLevelTextField;
	protected ArrayList<InformationTeamRecord> informationTeams = new ArrayList<InformationTeamRecord>();

	public EditInformationTeamPanel() {
		// constructor
		super("Edit a Team Information ");

		// hide useless buttons
		okButton.hide();
		addButton.hide();
		backButton.hide();
		editButton.hide();

		// configure the centerPanel
		idInformationTeamLabel = new JLabel("Id Information Team :");
		allInformationTeamsList = new JList();
		nameInformationTeamLabel = new JLabel("Name Information Team :");
		nameInformationTeamTextField = new JTextField();
		nameInformationTeamTextField.setColumns(20);
		idAbstractionLevelLabel = new JLabel("Id Abstarction Level related :");
		idAbstractionLevelTextField = new JTextField();
		idAbstractionLevelTextField.setColumns(20);
		idAbstractionLevelTextField.setEditable(false);

		// showSystemMessage
		showSystemMessage(
				"Select an Id of an Information team. To update,choose an Id Information Team, change the name then click the 'Update' Button,"
						+ " to delete, simply choose the Id Information team then click 'Delete' Button");

		// fill the Jlist
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM InformationTeam");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			if (!Launcher.adbc.rs.next()) {
				// no CIS Records found
				// centerPanel.add(new JLabel("No CIS Projects found"));
				InformationTeamRecord itr = new InformationTeamRecord(0, "", 0);
				informationTeams.add(itr);
			} else {

				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {

					int idInformationTeam;
					String nameInformationTeam;
					int idAbstractionLevel;

					idInformationTeam = Launcher.adbc.rs.getInt("idInformationTeam");
					nameInformationTeam = Launcher.adbc.rs.getString("nameInformationTeam");
					idAbstractionLevel = Launcher.adbc.rs.getInt("idAbstractionLevel");

					InformationTeamRecord itr = new InformationTeamRecord(idInformationTeam, nameInformationTeam,
							idAbstractionLevel);
					informationTeams.add(itr);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> idNameInformationTeams = new ArrayList<String>();

		// copy the id name from viewpointList to viewpointsListIdName
		Iterator<InformationTeamRecord> it = informationTeams.iterator();
		while (it.hasNext()) {
			InformationTeamRecord itr = new InformationTeamRecord(it.next());
			String str = itr.idInformationTeam + " - " + itr.nameInformationTeam;
			// System.out.println(str);
			idNameInformationTeams.add(str);
		}

		allInformationTeamsList = new JList(idNameInformationTeams.toArray());

		allInformationTeamsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				nameInformationTeamTextField
						.setText(informationTeams.get(allInformationTeamsList.getSelectedIndex()).nameInformationTeam);
				idAbstractionLevelTextField.setText(String
						.valueOf(informationTeams.get(allInformationTeamsList.getSelectedIndex()).idAbstractionLevel));

			}
		});

		// add components to the centerpPanel
		centerPanel.add(idInformationTeamLabel);
		centerPanel.add(new JScrollPane(allInformationTeamsList));
		centerPanel.add(nameInformationTeamLabel);
		centerPanel.add(nameInformationTeamTextField);
		centerPanel.add(idAbstractionLevelLabel);
		centerPanel.add(idAbstractionLevelTextField);

		// configure the buttons
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					String request = "UPDATE InformationTeam " + "SET nameInformationTeam='"
							+ nameInformationTeamTextField.getText() + "' "
					// + "AND description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idInformationTeam='"
							+ informationTeams.get(allInformationTeamsList.getSelectedIndex()).idInformationTeam + "'";

					Launcher.adbc.st.executeUpdate(request);

					// end updating
				} catch (Exception e) {
					e.printStackTrace();
				}
				// show message
				showSystemMessage("the Team Information has been updated succesffully");

				// switch to allInformationTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allInformationTeamsPanel);
				AllInformationTeamsPanel allInformationTeamsPanel = new AllInformationTeamsPanel();
				VpCISWindow.pVpCIS.add(allInformationTeamsPanel, "AllInformationTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllInformationTeamsPanel");
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
					String request = "DELETE FROM InformationTeam "
							// + "SET nameCIS='"+nameCISTextField.getText()+"' "
							// + "AND
							// description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idInformationTeam='"
							+ informationTeams.get(allInformationTeamsList.getSelectedIndex()).idInformationTeam + "'";

					Launcher.adbc.st.executeUpdate(request);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// show message
				showSystemMessage("the Team Information has been deleted succesffully");

				// switch to allInformationTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allInformationTeamsPanel);
				AllInformationTeamsPanel allInformationTeamsPanel = new AllInformationTeamsPanel();
				VpCISWindow.pVpCIS.add(allInformationTeamsPanel, "AllInformationTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllInformationTeamsPanel");
			}
		});

		cancelButton.addActionListener(new ActionListener() {
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

	}

	class InformationTeamRecord {

		int idInformationTeam;
		String nameInformationTeam;
		int idAbstractionLevel;

		InformationTeamRecord(int idInformationTeam, String nameInformationTeam, int idAbstractionLevel) {
			this.idInformationTeam = idInformationTeam;
			this.nameInformationTeam = nameInformationTeam;
			this.idAbstractionLevel = idAbstractionLevel;
		}

		InformationTeamRecord(InformationTeamRecord itr) {
			this.idInformationTeam = itr.idInformationTeam;
			this.nameInformationTeam = itr.nameInformationTeam;
			this.idAbstractionLevel = itr.idAbstractionLevel;
		}
	}
}
