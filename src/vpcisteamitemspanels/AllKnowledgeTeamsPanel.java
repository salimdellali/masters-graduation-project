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
import vpcispanels.AllAbstractionLevelsPanel;
import vpcispanels.TemplatePanel;

public class AllKnowledgeTeamsPanel extends TemplatePanel {

	public AllKnowledgeTeamsPanel() {
		super("List of all Team Knowledge");

		// hide the useless Buttons
		updateButton.hide();
		deleteButton.hide();
		cancelButton.hide();
		okButton.hide();

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.addKnowledgeTeamPanel);
				AddKnowledgeTeamPanel addKnowledgeTeamPanel = new AddKnowledgeTeamPanel();
				VpCISWindow.pVpCIS.add(addKnowledgeTeamPanel, "AddKnowledgeTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AddKnowledgeTeamPanel");
			}
		});

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.editKnowledgeTeamPanel);
				EditKnowledgeTeamPanel editKnowledgeTeamPanel = new EditKnowledgeTeamPanel();
				VpCISWindow.pVpCIS.add(editKnowledgeTeamPanel, "EditKnowledgeTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "EditKnowledgeTeamPanel");
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
			String request = String.format("SELECT * FROM KnowledgeTeam " + "WHERE idInformationTeam="
					+ AllInformationTeamsPanel.idInformationTeamEntred + "");
			// System.out.println(request);
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			ImageIcon knowledgeTeamIcon = new ImageIcon("icons//Knowledge.png");

			if (!Launcher.adbc.rs.next()) {
				centerPanel.add(new JLabel("No Team Knowledge found"));
			} else {

				// becuase we moved forward in the if statement above
				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {
					// show all Teams
					// add mouseListener on each Label

					int idKnowledgeTeam = Launcher.adbc.rs.getInt("idKnowledgeTeam");
					// System.out.println(AbstractionLevelId);
					String nameKnowledgeTeam = Launcher.adbc.rs.getString("nameKnowledgeTeam");
					// System.out.println(AbstractionLevelName);
					final JLabel knowledgeTeamLabel = new JLabel(idKnowledgeTeam + " : " + nameKnowledgeTeam,
							knowledgeTeamIcon, JLabel.LEFT);
					knowledgeTeamLabel.addMouseListener(new MouseListener() {

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

					centerPanel.add(knowledgeTeamLabel);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
