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
import vpcisteamitemspanels.EditDataTeamPanel.DataTeamRecord;

public class EditKnowledgeTeamPanel extends TemplatePanel {

	protected JLabel idKnowledgeTeamLabel;
	protected JList allKnowledgeTeamsList;
	protected JLabel nameKnowledgeTeamLabel;
	protected JTextField nameKnowledgeTeamTextField;
	protected JLabel idInformationTeamLabel;
	protected JTextField idInformationTeamTextField;
	protected ArrayList<KnowledgeTeamRecord> knowledgeTeams = new ArrayList<KnowledgeTeamRecord>();

	public EditKnowledgeTeamPanel() {
		super("Edit an existing Team Knowledge");

		// hide useless buttons
		okButton.hide();
		addButton.hide();
		backButton.hide();
		editButton.hide();

		// configure the centerPanel
		idKnowledgeTeamLabel = new JLabel("Id Knowledge Team :");
		allKnowledgeTeamsList = new JList();
		nameKnowledgeTeamLabel = new JLabel("Name Knowledge Team :");
		nameKnowledgeTeamTextField = new JTextField();
		nameKnowledgeTeamTextField.setColumns(20);
		idInformationTeamLabel = new JLabel("Id Information Team related :");
		idInformationTeamTextField = new JTextField();
		idInformationTeamTextField.setColumns(20);
		idInformationTeamTextField.setEditable(false);

		// showSystemMessage
		showSystemMessage(
				"Select an Id of a Knowledge team. To update,choose an Id Knowledge Team, change the name then click the 'Update' Button,"
						+ " to delete, simply choose the Id Knowledge Team then click 'Delete' Button");

		// fill the Jlist
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM KnowledgeTeam");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			if (!Launcher.adbc.rs.next()) {
				// no CIS Records found
				// centerPanel.add(new JLabel("No CIS Projects found"));
				KnowledgeTeamRecord ktr = new KnowledgeTeamRecord(0, "", 0);
				knowledgeTeams.add(ktr);
			} else {

				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {

					int idKnowledgeTeam;
					String nameKnowledgeTeam;
					int idInformationTeam;

					idKnowledgeTeam = Launcher.adbc.rs.getInt("idKnowledgeTeam");
					nameKnowledgeTeam = Launcher.adbc.rs.getString("nameKnowledgeTeam");
					idInformationTeam = Launcher.adbc.rs.getInt("idInformationTeam");

					KnowledgeTeamRecord ktr = new KnowledgeTeamRecord(idKnowledgeTeam, nameKnowledgeTeam, idInformationTeam);
					knowledgeTeams.add(ktr);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> idNameKnowledgeTeams = new ArrayList<String>();

		// copy the id name from viewpointList to viewpointsListIdName
		Iterator<KnowledgeTeamRecord> it = knowledgeTeams.iterator();
		while (it.hasNext()) {
			KnowledgeTeamRecord ktr = new KnowledgeTeamRecord(it.next());
			String str = ktr.idKnowledgeTeam + " - " + ktr.nameKnowledgeTeam;
			// System.out.println(str);
			idNameKnowledgeTeams.add(str);
		}

		allKnowledgeTeamsList = new JList(idNameKnowledgeTeams.toArray());

		allKnowledgeTeamsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				nameKnowledgeTeamTextField.setText(knowledgeTeams.get(allKnowledgeTeamsList.getSelectedIndex()).nameKnowledgeTeam);
				idInformationTeamTextField
						.setText(String.valueOf(knowledgeTeams.get(allKnowledgeTeamsList.getSelectedIndex()).idInformationTeam));

			}
		});

		// add components to the CenterPanel
		centerPanel.add(idKnowledgeTeamLabel);
		centerPanel.add(new JScrollPane(allKnowledgeTeamsList));
		centerPanel.add(nameKnowledgeTeamLabel);
		centerPanel.add(nameKnowledgeTeamTextField);
		centerPanel.add(idInformationTeamLabel);
		centerPanel.add(idInformationTeamTextField);

		// configure the buttons
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					String request = "UPDATE KnowledgeTeam " + "SET nameKnowledgeTeam='" + nameKnowledgeTeamTextField.getText() + "' "
					// + "AND description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idKnowledgeTeam='" + knowledgeTeams.get(allKnowledgeTeamsList.getSelectedIndex()).idKnowledgeTeam
							+ "'";

					Launcher.adbc.st.executeUpdate(request);

					// end updating
				} catch (Exception e) {
					e.printStackTrace();
				}
				// show message
				showSystemMessage("the Team Data has been updated succesffully");

				// switch to allActivityTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allKnowledgeTeamsPanel);
				AllKnowledgeTeamsPanel allKnowledgeTeamsPanel = new AllKnowledgeTeamsPanel();
				VpCISWindow.pVpCIS.add(allKnowledgeTeamsPanel, "AllKnowledgeTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllKnowledgeTeamsPanel");
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
					String request = "DELETE FROM KnowledgeTeam "
							// + "SET nameCIS='"+nameCISTextField.getText()+"' "
							// + "AND
							// description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idKnowledgeTeam='" + knowledgeTeams.get(allKnowledgeTeamsList.getSelectedIndex()).idKnowledgeTeam
							+ "'";

					Launcher.adbc.st.executeUpdate(request);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// show message
				showSystemMessage("the Team Knowledge has been deleted succesffully");

				// switch to allActivityTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allKnowledgeTeamsPanel);
				AllKnowledgeTeamsPanel allKnowledgeTeamsPanel = new AllKnowledgeTeamsPanel();
				VpCISWindow.pVpCIS.add(allKnowledgeTeamsPanel, "AllKnowledgeTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllKnowledgeTeamsPanel");
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// switch to allDataTeamsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allKnowledgeTeamsPanel);
				AllKnowledgeTeamsPanel allKnowledgeTeamsPanel = new AllKnowledgeTeamsPanel();
				VpCISWindow.pVpCIS.add(allKnowledgeTeamsPanel, "AllKnowledgeTeamsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllKnowledgeTeamsPanel");
			}
		});

	}

	class KnowledgeTeamRecord {

		int idKnowledgeTeam;
		String nameKnowledgeTeam;
		int idInformationTeam;

		KnowledgeTeamRecord(int idKnowledgeTeam, String nameKnowledgeTeam, int idInformationTeam) {
			this.idKnowledgeTeam = idKnowledgeTeam;
			this.nameKnowledgeTeam = nameKnowledgeTeam;
			this.idInformationTeam = idInformationTeam;
		}

		KnowledgeTeamRecord(KnowledgeTeamRecord ktr) {
			this.idKnowledgeTeam = ktr.idKnowledgeTeam;
			this.nameKnowledgeTeam = ktr.nameKnowledgeTeam;
			this.idInformationTeam = ktr.idInformationTeam;
		}
	}
}
