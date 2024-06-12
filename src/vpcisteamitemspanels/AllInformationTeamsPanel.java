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
import vpcispanels.AllAbstractionLevelsPanel;
import vpcispanels.TemplatePanel;

public class AllInformationTeamsPanel extends TemplatePanel {

	public static int idInformationTeamEntred;
	public static String nameInformationTeamEntred;

	public AllInformationTeamsPanel() {
		// constructor
		super("List of all Team Information");

		// hide the useless Buttons
		updateButton.hide();
		deleteButton.hide();
		cancelButton.hide();
		okButton.hide();

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.addInformationTeamPanel);
				AddInformationTeamPanel addInformationTeamPanel = new AddInformationTeamPanel();
				VpCISWindow.pVpCIS.add(addInformationTeamPanel, "AddInformationTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AddInformationTeamPanel");
			}
		});

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.editInformationTeamPanel);
				EditInformationTeamPanel editInformationTeamPanel = new EditInformationTeamPanel();
				VpCISWindow.pVpCIS.add(editInformationTeamPanel, "EditInformationTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "EditInformationTeamPanel");
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
			String request = String.format("SELECT * FROM InformationTeam " + "WHERE idAbstractionLevel="
					+ AllAbstractionLevelsPanel.idAbstractionLevelEntred + "");
			// System.out.println(request);
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			ImageIcon informationTeamIcon = new ImageIcon("icons//InformationTeam.png");

			if (!Launcher.adbc.rs.next()) {
				centerPanel.add(new JLabel("No Team Information found"));
			} else {

				// becuase we moved forward in the if statement above
				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {
					// show all Teams
					// add mouseListener on each Label

					int informationTeamId = Launcher.adbc.rs.getInt("idInformationTeam");
					// System.out.println(AbstractionLevelId);
					String informationTeamName = Launcher.adbc.rs.getString("nameInformationTeam");
					// System.out.println(AbstractionLevelName);
					final JLabel informationTeamLabel = new JLabel(informationTeamId + " : " + informationTeamName,
							informationTeamIcon, JLabel.LEFT);
					informationTeamLabel.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent arg0) {
							// move to the AllAbstractionLevel but with
							// restriction on idAbstractionLevel =
							// idAbstractionLevel selected
							// System.out.println("you clicked me");

							idInformationTeamEntred = informationTeamId;
							nameInformationTeamEntred = informationTeamName;

							VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allInformationTeamItemsPanel);
							AllInformationTeamItemsPanel allInformationTeamItemsPanel = new AllInformationTeamItemsPanel();
							VpCISWindow.pVpCIS.add(allInformationTeamItemsPanel, "AllInformationTeamItemsPanel");
							VpCISWindow.pVpCIS.repaint();
							VpCISWindow.pVpCIS.revalidate();
							VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllInformationTeamItemsPanel");

							/*
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

							informationTeamLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						}

						public void mouseExited(MouseEvent arg0) {

							informationTeamLabel.setBorder(BorderFactory.createLineBorder(null, 0));
						}

						public void mousePressed(MouseEvent arg0) {

						}

						public void mouseReleased(MouseEvent arg0) {

						}

					});

					centerPanel.add(informationTeamLabel);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
