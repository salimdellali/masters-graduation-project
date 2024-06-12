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
import vpcisteamitemspanels.EditKnowledgeTeamPanel.KnowledgeTeamRecord;

public class EditNonFunctionalRequirementTeamPanel extends TemplatePanel {
	
	protected JLabel idNonFunctionalRequirementTeamLabel;
	protected JList allNonFunctionalRequirementTeamsList;
	protected JLabel nameNonFunctionalRequirementTeamLabel;
	protected JTextField nameNonFunctionalRequirementTeamTextField;
	protected JLabel idInformationTeamLabel;
	protected JTextField idInformationTeamTextField;
	protected ArrayList<NonFunctionalRequirementTeamRecord> nonFunctionalRequirementTeams = new ArrayList<NonFunctionalRequirementTeamRecord>();
	
	public EditNonFunctionalRequirementTeamPanel(){
		super("Edit an existing Team Non Funtional Requirement");
		
		// hide useless buttons
				okButton.hide();
				addButton.hide();
				backButton.hide();
				editButton.hide();

				// configure the centerPanel
				idNonFunctionalRequirementTeamLabel = new JLabel("Id Non Functional Requirement Team :");
				allNonFunctionalRequirementTeamsList = new JList();
				nameNonFunctionalRequirementTeamLabel = new JLabel("Name Non Functional Requirement Team :");
				nameNonFunctionalRequirementTeamTextField = new JTextField();
				nameNonFunctionalRequirementTeamTextField.setColumns(20);
				idInformationTeamLabel = new JLabel("Id Information Team related :");
				idInformationTeamTextField = new JTextField();
				idInformationTeamTextField.setColumns(20);
				idInformationTeamTextField.setEditable(false);

				// showSystemMessage
				showSystemMessage(
						"Select an Id of a Non Functional Requirement team. To update,choose an Id Non Functional Requirement Team, change the name then click the 'Update' Button,"
								+ " to delete, simply choose the Id Non Functional Requirement Team then click 'Delete' Button");

				// fill the Jlist
				try {

					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					String request = String.format("SELECT * FROM NonFunctionalRequirementTeam");
					Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

					if (!Launcher.adbc.rs.next()) {
						// no CIS Records found
						// centerPanel.add(new JLabel("No CIS Projects found"));
						NonFunctionalRequirementTeamRecord nfrtr = new NonFunctionalRequirementTeamRecord(0, "", 0);
						nonFunctionalRequirementTeams.add(nfrtr);
					} else {

						Launcher.adbc.rs.previous();

						while (Launcher.adbc.rs.next()) {

							int idNonFunctionalRequirementTeam;
							String nameNonFunctionalRequirementTeam;
							int idInformationTeam;

							idNonFunctionalRequirementTeam = Launcher.adbc.rs.getInt("idNonFunctionalRequirementTeam");
							nameNonFunctionalRequirementTeam = Launcher.adbc.rs.getString("nameNonFunctionalRequirementTeam");
							idInformationTeam = Launcher.adbc.rs.getInt("idInformationTeam");

							NonFunctionalRequirementTeamRecord nfrtr = new NonFunctionalRequirementTeamRecord(idNonFunctionalRequirementTeam, nameNonFunctionalRequirementTeam, idInformationTeam);
							nonFunctionalRequirementTeams.add(nfrtr);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				ArrayList<String> idNameNonFunctionalRequirementTeams = new ArrayList<String>();

				// copy the id name from viewpointList to viewpointsListIdName
				Iterator<NonFunctionalRequirementTeamRecord> it = nonFunctionalRequirementTeams.iterator();
				while (it.hasNext()) {
					NonFunctionalRequirementTeamRecord ktr = new NonFunctionalRequirementTeamRecord(it.next());
					String str = ktr.idNonFunctionalRequirementTeam + " - " + ktr.nameNonFunctionalRequirementTeam;
					// System.out.println(str);
					idNameNonFunctionalRequirementTeams.add(str);
				}

				allNonFunctionalRequirementTeamsList = new JList(idNameNonFunctionalRequirementTeams.toArray());

				allNonFunctionalRequirementTeamsList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {
						nameNonFunctionalRequirementTeamTextField.setText(nonFunctionalRequirementTeams.get(allNonFunctionalRequirementTeamsList.getSelectedIndex()).nameNonFunctionalRequirementTeam);
						idInformationTeamTextField
								.setText(String.valueOf(nonFunctionalRequirementTeams.get(allNonFunctionalRequirementTeamsList.getSelectedIndex()).idInformationTeam));

					}
				});

				// add components to the CenterPanel
				centerPanel.add(idNonFunctionalRequirementTeamLabel);
				centerPanel.add(new JScrollPane(allNonFunctionalRequirementTeamsList));
				centerPanel.add(nameNonFunctionalRequirementTeamLabel);
				centerPanel.add(nameNonFunctionalRequirementTeamTextField);
				centerPanel.add(idInformationTeamLabel);
				centerPanel.add(idInformationTeamTextField);

				// configure the buttons
				updateButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_UPDATABLE);

							String request = "UPDATE NonFunctionalRequirementTeam " + "SET nameNonFunctionalRequirementTeam='" + nameNonFunctionalRequirementTeamTextField.getText() + "' "
							// + "AND description='"+descriptionTextArea.getText()+"'"
									+ "WHERE idNonFunctionalRequirementTeam='" + nonFunctionalRequirementTeams.get(allNonFunctionalRequirementTeamsList.getSelectedIndex()).idNonFunctionalRequirementTeam
									+ "'";

							Launcher.adbc.st.executeUpdate(request);

							// end updating
						} catch (Exception e) {
							e.printStackTrace();
						}
						// show message
						showSystemMessage("the Team Data has been updated succesffully");

						// switch to allActivityTeamsPanel
						VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allNonFunctionalRequirementTeamsPanel);
						AllNonFunctionalRequirementTeamsPanel allNonFunctionalRequirementTeamsPanel = new AllNonFunctionalRequirementTeamsPanel();
						VpCISWindow.pVpCIS.add(allNonFunctionalRequirementTeamsPanel, "AllNonFunctionalRequirementTeamPanel");
						VpCISWindow.pVpCIS.repaint();
						VpCISWindow.pVpCIS.revalidate();
						VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllNonFunctionalRequirementTeamPanel");
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
							String request = "DELETE FROM NonFunctionalRequirementTeam "
									// + "SET nameCIS='"+nameCISTextField.getText()+"' "
									// + "AND
									// description='"+descriptionTextArea.getText()+"'"
									+ "WHERE idNonFunctionalRequirementTeam='" + nonFunctionalRequirementTeams.get(allNonFunctionalRequirementTeamsList.getSelectedIndex()).idNonFunctionalRequirementTeam
									+ "'";

							Launcher.adbc.st.executeUpdate(request);

						} catch (Exception e) {
							e.printStackTrace();
						}

						// show message
						showSystemMessage("the Team Non Functional Requirement has been deleted succesffully");

						// switch to allActivityTeamsPanel
						VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allNonFunctionalRequirementTeamsPanel);
						AllNonFunctionalRequirementTeamsPanel allNonFunctionalRequirementTeamsPanel = new AllNonFunctionalRequirementTeamsPanel();
						VpCISWindow.pVpCIS.add(allNonFunctionalRequirementTeamsPanel, "AllNonFunctionalRequirementTeamPanel");
						VpCISWindow.pVpCIS.repaint();
						VpCISWindow.pVpCIS.revalidate();
						VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllNonFunctionalRequirementTeamPanel");
					}
				});

				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// switch to allDataTeamsPanel
						VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allNonFunctionalRequirementTeamsPanel);
						AllNonFunctionalRequirementTeamsPanel allNonFunctionalRequirementTeamsPanel = new AllNonFunctionalRequirementTeamsPanel();
						VpCISWindow.pVpCIS.add(allNonFunctionalRequirementTeamsPanel, "AllNonFunctionalRequirementTeamPanel");
						VpCISWindow.pVpCIS.repaint();
						VpCISWindow.pVpCIS.revalidate();
						VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllNonFunctionalRequirementTeamsPanel");
					}
				});

	}
	
	class NonFunctionalRequirementTeamRecord {

		int idNonFunctionalRequirementTeam;
		String nameNonFunctionalRequirementTeam;
		int idInformationTeam;

		NonFunctionalRequirementTeamRecord(int idNonFunctionalRequirementTeam, String nameNonFunctionalRequirementTeam, int idInformationTeam) {
			this.idNonFunctionalRequirementTeam = idNonFunctionalRequirementTeam;
			this.nameNonFunctionalRequirementTeam = nameNonFunctionalRequirementTeam;
			this.idInformationTeam = idInformationTeam;
		}

		NonFunctionalRequirementTeamRecord(NonFunctionalRequirementTeamRecord nfrtr) {
			this.idNonFunctionalRequirementTeam = nfrtr.idNonFunctionalRequirementTeam;
			this.nameNonFunctionalRequirementTeam = nfrtr.nameNonFunctionalRequirementTeam;
			this.idInformationTeam = nfrtr.idInformationTeam;
		}
	}
}
