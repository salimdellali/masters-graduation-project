package vpcisteamitemspanels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.Launcher;
import gui.VpCISWindow;
import vpcispanels.TemplatePanel;

public class AllNonFunctionalRequirementTeamsPanel extends TemplatePanel {

	public AllNonFunctionalRequirementTeamsPanel() {
		super("List of all Non Functional Requirement Team :");

		// hide the useless Buttons
		updateButton.hide();
		deleteButton.hide();
		cancelButton.hide();
		okButton.hide();

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.addNonFunctionalRequirementTeamPanel);
				AddNonFunctionalRequirementTeamPanel addNonFunctionalRequirementTeamPanel = new AddNonFunctionalRequirementTeamPanel();
				VpCISWindow.pVpCIS.add(addNonFunctionalRequirementTeamPanel, "AddNonFunctionalRequirementTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AddNonFunctionalRequirementTeamPanel");
				
			}
		});

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.editNonFunctionalRequirementTeamPanel);
				EditNonFunctionalRequirementTeamPanel editNonFunctionalRequirementTeamPanel = new EditNonFunctionalRequirementTeamPanel();
				VpCISWindow.pVpCIS.add(editNonFunctionalRequirementTeamPanel, "EditNonFunctionalRequirementTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "EditNonFunctionalRequirementTeamPanel");
			}
		});

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allInformationTeamItemsPanel);
				AllInformationTeamItemsPanel allInformationTeamItemsPanel = new AllInformationTeamItemsPanel();
				VpCISWindow.pVpCIS.add(allInformationTeamItemsPanel, "AllInformationTeamItemsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllInformationTeamItemsPanel");
			}
		});

		// configure the centerPanel

		centerPanel.setLayout(new FlowLayout());

		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM NonFunctionalRequirementTeam " + "WHERE idInformationTeam="
					+ AllInformationTeamsPanel.idInformationTeamEntred + "");
			// System.out.println(request);
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			ImageIcon nonFunctionalRequirementTeamIcon = new ImageIcon("icons//NFR.png");

			if (!Launcher.adbc.rs.next()) {
				centerPanel.add(new JLabel("No Team Non Functional Requirement found"));
			} else {

				// becuase we moved forward in the if statement above
				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {
					// show all Teams
					// add mouseListener on each Label

					int idNonFunctionalRequirementTeam = Launcher.adbc.rs.getInt("idNonFunctionalRequirementTeam");
					// System.out.println(AbstractionLevelId);
					String nameNonFunctionalRequirementTeam = Launcher.adbc.rs.getString("nameNonFunctionalRequirementTeam");
					// System.out.println(AbstractionLevelName);
					final JLabel nonFunctionalRequirementTeamLabel = new JLabel(idNonFunctionalRequirementTeam + " : " + nameNonFunctionalRequirementTeam,
							nonFunctionalRequirementTeamIcon, JLabel.LEFT);
					nonFunctionalRequirementTeamLabel.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent arg0) {
							// move to the AllAbstractionLevel but with
							// restriction on idAbstractionLevel =
							// idAbstractionLevel selected
							// System.out.println("you clicked me");
							/*
							 * idInformationTeamEntred = informationTeamId;
							 * nameInformationTeamEntred = informationTeamName;
							 * 
							 * VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.
							 * allInformationTeamItemsPanel);
							 * AllInformationTeamItemsPanel
							 * allInformationTeamItemsPanel = new
							 * AllInformationTeamItemsPanel();
							 * VpCISWindow.pVpCIS.add(
							 * allInformationTeamItemsPanel,
							 * "AllInformationTeamItemsPanel");
							 * VpCISWindow.pVpCIS.repaint();
							 * VpCISWindow.pVpCIS.revalidate();
							 * VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS,
							 * "AllInformationTeamItemsPanel");
							 * 
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

							// informationTeamLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,
							// 2));
						}

						public void mouseExited(MouseEvent arg0) {

							// informationTeamLabel.setBorder(BorderFactory.createLineBorder(null,
							// 0));
						}

						public void mousePressed(MouseEvent arg0) {

						}

						public void mouseReleased(MouseEvent arg0) {

						}

					});

					centerPanel.add(nonFunctionalRequirementTeamLabel);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
