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

public class EditDataTeamPanel extends TemplatePanel {

	protected JLabel idDataTeamLabel;
	protected JList allDataTeamsList;
	protected JLabel nameDataTeamLabel;
	protected JTextField nameDataTeamTextField;
	protected JLabel idInformationTeamLabel;
	protected JTextField idInformationTeamTextField;
	protected ArrayList<DataTeamRecord> dataTeams = new ArrayList<DataTeamRecord>();

	public EditDataTeamPanel() {
		super("Edit an existing Data Team");

		// hide useless buttons
		okButton.hide();
		addButton.hide();
		backButton.hide();
		editButton.hide();

		// configure the centerPanel
		idDataTeamLabel = new JLabel("Id Data Team :");
		allDataTeamsList = new JList();
		nameDataTeamLabel = new JLabel("Name Data Team :");
		nameDataTeamTextField = new JTextField();
		nameDataTeamTextField.setColumns(20);
		idInformationTeamLabel = new JLabel("Id Information Team related :");
		idInformationTeamTextField = new JTextField();
		idInformationTeamTextField.setColumns(20);
		idInformationTeamTextField.setEditable(false);

		// showSystemMessage
		showSystemMessage(
				"Select an Id of a Data team. To update,choose an Id Data Team, change the name then click the 'Update' Button,"
						+ " to delete, simply choose the Id Data Team then click 'Delete' Button");

		// fill the Jlist
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM DataTeam");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			if (!Launcher.adbc.rs.next()) {
				// no CIS Records found
				// centerPanel.add(new JLabel("No CIS Projects found"));
				DataTeamRecord dtr = new DataTeamRecord(0, "", 0);
				dataTeams.add(dtr);
			} else {

				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {

					int idDataTeam;
					String nameDataTeam;
					int idInformationTeam;

					idDataTeam = Launcher.adbc.rs.getInt("idDataTeam");
					nameDataTeam = Launcher.adbc.rs.getString("nameDataTeam");
					idInformationTeam = Launcher.adbc.rs.getInt("idInformationTeam");

					DataTeamRecord dtr = new DataTeamRecord(idDataTeam, nameDataTeam, idInformationTeam);
					dataTeams.add(dtr);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> idNameDataTeams = new ArrayList<String>();

		// copy the id name from viewpointList to viewpointsListIdName
		Iterator<DataTeamRecord> it = dataTeams.iterator();
		while (it.hasNext()) {
			DataTeamRecord dtr = new DataTeamRecord(it.next());
			String str = dtr.idDataTeam + " - " + dtr.nameDataTeam;
			// System.out.println(str);
			idNameDataTeams.add(str);
		}

		allDataTeamsList = new JList(idNameDataTeams.toArray());

		allDataTeamsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				nameDataTeamTextField.setText(dataTeams.get(allDataTeamsList.getSelectedIndex()).nameDataTeam);
				idInformationTeamTextField
						.setText(String.valueOf(dataTeams.get(allDataTeamsList.getSelectedIndex()).idInformationTeam));

			}
		});

		// add components to the CenterPanel
		centerPanel.add(idDataTeamLabel);
		centerPanel.add(new JScrollPane(allDataTeamsList));
		centerPanel.add(nameDataTeamLabel);
		centerPanel.add(nameDataTeamTextField);
		centerPanel.add(idInformationTeamLabel);
		centerPanel.add(idInformationTeamTextField);

		// configure the buttons
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					String request = "UPDATE DataTeam " + "SET nameDataTeam='"
							+ nameDataTeamTextField.getText() + "' "
					// + "AND description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idDataTeam='"
							+ dataTeams.get(allDataTeamsList.getSelectedIndex()).idDataTeam + "'";

					Launcher.adbc.st.executeUpdate(request);

					// end updating
				} catch (Exception e) {
					e.printStackTrace();
				}
				// show message
				showSystemMessage("the Team Data has been updated succesffully");

				// switch to allActivityTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allDataTeamsPanel);
				AllDataTeamsPanel allDataTeamsPanel = new AllDataTeamsPanel();
				VpCISWindow.pVpCIS.add(allDataTeamsPanel, "AllDataTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllDataTeamsPanel");
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
					String request = "DELETE FROM DataTeam "
							// + "SET nameCIS='"+nameCISTextField.getText()+"' "
							// + "AND
							// description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idDataTeam='"
							+ dataTeams.get(allDataTeamsList.getSelectedIndex()).idDataTeam + "'";

					Launcher.adbc.st.executeUpdate(request);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// show message
				showSystemMessage("the Team Data has been deleted succesffully");

				// switch to allActivityTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allDataTeamsPanel);
				AllDataTeamsPanel allDataTeamsPanel = new AllDataTeamsPanel();
				VpCISWindow.pVpCIS.add(allDataTeamsPanel, "AllDataTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllDataTeamsPanel");
			}
		});
		
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// switch to allDataTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allDataTeamsPanel);
				AllDataTeamsPanel allDataTeamsPanel = new AllDataTeamsPanel();
				VpCISWindow.pVpCIS.add(allDataTeamsPanel, "AllDataTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllDataTeamsPanel");
			}
		});
	}

	class DataTeamRecord {

		int idDataTeam;
		String nameDataTeam;
		int idInformationTeam;

		DataTeamRecord(int idDataTeam, String nameDataTeam, int idInformationTeam) {
			this.idDataTeam = idDataTeam;
			this.nameDataTeam = nameDataTeam;
			this.idInformationTeam = idInformationTeam;
		}

		DataTeamRecord(DataTeamRecord dtr) {
			this.idDataTeam = dtr.idDataTeam;
			this.nameDataTeam = dtr.nameDataTeam;
			this.idInformationTeam = dtr.idInformationTeam;
		}
	}
}
