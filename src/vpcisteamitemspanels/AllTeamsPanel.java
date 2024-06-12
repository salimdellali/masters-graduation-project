package vpcisteamitemspanels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.Launcher;
import gui.VpCISWindow;
import vpcispanels.*;

public class AllTeamsPanel extends TemplatePanel {

	public AllTeamsPanel() {
		// constructor
		super("List of all Teams");

		// hide the useless buttons
		updateButton.hide();
		deleteButton.hide();
		cancelButton.hide();
		okButton.hide();
		/*
		 * protected JButton addButton = new JButton("Add"); protected JButton
		 * updateButton = new JButton("Update"); protected JButton deleteButton
		 * = new JButton("Delete"); protected JButton cancelButton = new
		 * JButton("Cancel"); protected JButton backButton = new
		 * JButton("Back"); protected JButton okButton = new JButton("OK");
		 * protected JButton editButton = new JButton("Edit");
		 */
		// showSystem message
		//showSystemMessage("List of All Teams related to the Abstraction Level");

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.addTeamPanel);
				AddTeamPanel addTeamPanel = new AddTeamPanel();
				VpCISWindow.pVpCIS.add(addTeamPanel, "AddTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AddTeamPanel");
			}
		});

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.editTeamPanel);
				EditTeamPanel editTeamPanel = new EditTeamPanel();
				VpCISWindow.pVpCIS.add(editTeamPanel, "EditTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "EditTeamPanel");
			}
		});

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allItemsPanel);
				AllItemsPanel allItemsPanel = new AllItemsPanel();
				VpCISWindow.pVpCIS.add(allItemsPanel, "AllItemsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllItemsPanel");
			}
		});

		centerPanel.setLayout(new FlowLayout());

		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM Team " + "WHERE idAbstractionLevel="
					+ AllAbstractionLevelsPanel.idAbstractionLevelEntred + "");
			//System.out.println(request);
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			ImageIcon teamIcon = new ImageIcon("icons//Team.png");

			if (!Launcher.adbc.rs.next()) {
				centerPanel.add(new JLabel("No Team found"));
			} else {
				
				// becuase we moved forward in the if statement above
				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {
					// show all Teams
					// add mouseListener on each Label

					int teamId = Launcher.adbc.rs.getInt("idTeam");
					// System.out.println(AbstractionLevelId);
					String teamName = Launcher.adbc.rs.getString("nameTeam");
					// System.out.println(AbstractionLevelName);
					final JLabel teamLabel = new JLabel(teamId + " : " + teamName, teamIcon, JLabel.LEFT);
					teamLabel.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent arg0) {
							// move to the AllAbstractionLevel but with
							// restriction on idAbstractionLevel =
							// idAbstractionLevel selected
							// System.out.println("you clicked me");
							/*
							 * idAbstractionLevelEntred = AbstractionLevelId;
							 * NameIdAbstractionLevelEntred =
							 * AbstractionLevelName;
							 * 
							 * //go to AllItemsPanel
							 * VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.
							 * allItemsPanel); AllItemsPanel allItemsPanel = new
							 * AllItemsPanel();
							 * VpCISWindow.pVpCIS.add(allItemsPanel,
							 * "AllItemsPanel"); VpCISWindow.pVpCIS.repaint();
							 * VpCISWindow.pVpCIS.revalidate();
							 * VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS,
							 * "AllItemsPanel");
							 */
						}

						public void mouseEntered(MouseEvent arg0) {

							// AbstractionLevelLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,
							// 2));
						}

						public void mouseExited(MouseEvent arg0) {

							// AbstractionLevelLabel.setBorder(BorderFactory.createLineBorder(null,
							// 0));
						}

						public void mousePressed(MouseEvent arg0) {

						}

						public void mouseReleased(MouseEvent arg0) {

						}

					});

					 centerPanel.add(teamLabel);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
