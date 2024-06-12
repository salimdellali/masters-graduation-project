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

public class AllDataTeamsPanel extends TemplatePanel {

	public AllDataTeamsPanel(){
		//constructor
		super("List of all Team Data");
		
		//hide the useless buttons
		updateButton.hide();
		deleteButton.hide();
		cancelButton.hide();
		okButton.hide();
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.addDataTeamPanel);
				AddDataTeamPanel addDataTeamPanel = new AddDataTeamPanel();
				VpCISWindow.pVpCIS.add(addDataTeamPanel, "AddDataTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AddDataTeamPanel");
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.editDataTeamPanel);
				EditDataTeamPanel editDataTeamPanel = new EditDataTeamPanel();
				VpCISWindow.pVpCIS.add(editDataTeamPanel, "EditDataTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "EditDataTeamPanel");
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
		
		centerPanel.setLayout(new FlowLayout());

		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM DataTeam " + "WHERE idInformationTeam="
					+ AllInformationTeamsPanel.idInformationTeamEntred + "");
			// System.out.println(request);
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			ImageIcon dataTeamIcon = new ImageIcon("icons//Data.png");

			if (!Launcher.adbc.rs.next()) {
				centerPanel.add(new JLabel("No Team Data found"));
			} else {

				// becuase we moved forward in the if statement above
				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {
					// show all Teams
					// add mouseListener on each Label

					int idDataTeam = Launcher.adbc.rs.getInt("idDataTeam");
					// System.out.println(AbstractionLevelId);
					String nameDataTeam = Launcher.adbc.rs.getString("nameDataTeam");
					// System.out.println(AbstractionLevelName);
					final JLabel dataTeamLabel = new JLabel(idDataTeam + " : " + nameDataTeam,
							dataTeamIcon, JLabel.LEFT);
					dataTeamLabel.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent arg0) {
							// move to the AllAbstractionLevel but with
							// restriction on idAbstractionLevel =
							// idAbstractionLevel selected
							// System.out.println("you clicked me");
							/*
							idInformationTeamEntred = informationTeamId;
							nameInformationTeamEntred = informationTeamName;

							VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allInformationTeamItemsPanel);
							AllInformationTeamItemsPanel allInformationTeamItemsPanel = new AllInformationTeamItemsPanel();
							VpCISWindow.pVpCIS.add(allInformationTeamItemsPanel, "AllInformationTeamItemsPanel");
							VpCISWindow.pVpCIS.repaint();
							VpCISWindow.pVpCIS.revalidate();
							VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllInformationTeamItemsPanel");

							
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

							//informationTeamLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						}

						public void mouseExited(MouseEvent arg0) {

							//informationTeamLabel.setBorder(BorderFactory.createLineBorder(null, 0));
						}

						public void mousePressed(MouseEvent arg0) {

						}

						public void mouseReleased(MouseEvent arg0) {

						}

					});

					centerPanel.add(dataTeamLabel);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
