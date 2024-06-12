package vpcisactoritemspanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import gui.Launcher;
import gui.VpCISWindow;
import vpcispanels.AllAbstractionLevelsPanel;
import vpcispanels.TemplatePanel;

public class AddActorPanel extends TemplatePanel {

	JLabel qualityLabel;
	JList qualityList;
	JLabel nameActorLabel;
	JTextField nameActorTextField;
	JLabel idTeamLabel;
	JList teamsList;
	JLabel idAbstractionLevelLabel;
	JTextField idAbstractionLevelTextField;
	ArrayList<TeamRecord> teams = new ArrayList<TeamRecord>();

	public AddActorPanel() {
		// constructor
		super("Add an Actor");

		// hide the useless buttons
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		backButton.hide();
		editButton.hide();

		// configure the centerPanel
		qualityLabel = new JLabel("Quality :");
		String[] qualities = { "Simple member", "Leader of a team", "Responsible of the CIS" };
		qualityList = new JList(qualities);
		qualityList.setVisibleRowCount(3);
		qualityList.setSelectedIndex(0);
		nameActorLabel = new JLabel("Name Actor :");
		nameActorTextField = new JTextField();
		nameActorTextField.setColumns(20);
		idTeamLabel = new JLabel("From Team :");
		teamsList = new JList();
		idAbstractionLevelLabel = new JLabel("Id Abstraction Level :");
		idAbstractionLevelTextField = new JTextField();
		idAbstractionLevelTextField.setColumns(20);
		idAbstractionLevelTextField.setEditable(false);
		idAbstractionLevelTextField.setText(String.valueOf(AllAbstractionLevelsPanel.idAbstractionLevelEntred));

		// show system message
		showSystemMessage("Quality : can be a simple member, or leader of a team but only one in the team, or "
				+ "Responsible of the CIS but only one in the whole CIS Project, Name field is mendatory, "
				+ "Team field can be empty");

		// fill the teamsList
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM Team");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			if (!Launcher.adbc.rs.next()) {
				// no CIS Records found
				// centerPanel.add(new JLabel("No CIS Projects found"));
				TeamRecord tr = new TeamRecord(-1, "-----");
				teams.add(tr);
			} else {

				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {

					int idTeam;
					String nameTeam;

					idTeam = Launcher.adbc.rs.getInt("idTeam");
					nameTeam = Launcher.adbc.rs.getString("nameTeam");

					TeamRecord tr = new TeamRecord(idTeam, nameTeam);
					teams.add(tr);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> idNameTeams = new ArrayList<String>();
		// add an empty record
		idNameTeams.add(" ");
		// copy the id name from viewpointList to viewpointsListIdName
		Iterator<TeamRecord> it = teams.iterator();
		while (it.hasNext()) {
			TeamRecord tr = new TeamRecord(it.next());
			String str = tr.idTeam + " - " + tr.nameTeam;
			// System.out.println(str);
			idNameTeams.add(str);
		}

		teamsList = new JList(idNameTeams.toArray());
		teamsList.setVisibleRowCount(5);
		teamsList.setSelectedIndex(0);

		// add component to the centerPanel
		centerPanel.add(qualityLabel);
		centerPanel.add(new JScrollPane(qualityList));
		centerPanel.add(nameActorLabel);
		centerPanel.add(nameActorTextField);
		centerPanel.add(idTeamLabel);
		centerPanel.add(new JScrollPane(teamsList));
		centerPanel.add(idAbstractionLevelLabel);
		centerPanel.add(idAbstractionLevelTextField);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (nameActorTextField.getText().equals("")){
					//name field required
					showSystemMessage("Name field required");
				} else {
					//name field filled
					switch (qualityList.getSelectedIndex()) {
					case 0:
						//simple member
						//insert
						//show message
						//switch to all
						try{
						
							if (teamsList.getSelectedIndex()>0){
								//insert an actor with a team
						String request = String.format("INSERT INTO Actor (quality,nameActor,idTeam,idAbstractionLevel) VALUES ( "
								+ "'Simple member' " + "," + "'"
								+ nameActorTextField.getText() + "'," + "'"
								+ teams.get(teamsList.getSelectedIndex()-1).idTeam + "'," + "'"
								+ idAbstractionLevelTextField.getText() + "')");
						System.out.println(request);
						Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						Launcher.adbc.st.executeUpdate(request);
							} else {
								//insert an actor without a team
								String request = String.format("INSERT INTO Actor (quality,nameActor,idAbstractionLevel) VALUES ( "
										+ "'Simple member' " + "," + "'"
										+ nameActorTextField.getText() + "'," + "'"
										+ idAbstractionLevelTextField.getText() + "')");
								System.out.println(request);
								Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
								Launcher.adbc.st.executeUpdate(request);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case 1:
						//leader of a team
						//test if there is no leader in the team
						//if no leader
							//insert
							//show message
							//switch to all
						//if yes
							//show message
						break;
					case 2:
						//responsible of the CIS
						//test if there is no leader in the team
						//if no responsible in the whole CIS
							//insert
							//show message
							//switch to all
						//if yes
							//show message
						break;
					}
					
				}
				
				
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allActorsPanel);
				AllActorsPanel allActorsPanel = new AllActorsPanel();
				VpCISWindow.pVpCIS.add(allActorsPanel, "AllActorsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllActorsPanel");
			}
		});

	}

	class TeamRecord {

		int idTeam;
		String nameTeam;

		TeamRecord(int idTeam, String nameTeam) {
			this.idTeam = idTeam;
			this.nameTeam = nameTeam;
		}

		TeamRecord(TeamRecord tr) {
			this.idTeam = tr.idTeam;
			this.nameTeam = tr.nameTeam;
		}
	}
}
