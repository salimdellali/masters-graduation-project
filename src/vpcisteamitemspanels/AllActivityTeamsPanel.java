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

public class AllActivityTeamsPanel extends TemplatePanel {

	
	public AllActivityTeamsPanel() {
		//constructor
		super("list of all Team Activities");
		
		//hide the useless Buttons
		updateButton.hide();
		deleteButton.hide();
		cancelButton.hide();
		okButton.hide();
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.addActivityTeamPanel);
				AddActivityTeamPanel addActivityTeamPanel = new AddActivityTeamPanel();
				VpCISWindow.pVpCIS.add(addActivityTeamPanel, "AddActivityTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AddActivityTeamPanel");
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.editActivityTeamPanel);
				EditActivityTeamPanel editActivityTeamPanel = new EditActivityTeamPanel();
				VpCISWindow.pVpCIS.add(editActivityTeamPanel, "EditActivityTeamPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "EditActivityTeamPanel");
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
		
		try{
			
			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM ActivityTeam " + "WHERE idAbstractionLevel="
					+ AllAbstractionLevelsPanel.idAbstractionLevelEntred + "");
			//System.out.println(request);
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);
			
			ImageIcon activityTeamIcon = new ImageIcon("icons//ActivityTeam.png");

			if (!Launcher.adbc.rs.next()) {
				centerPanel.add(new JLabel("No Team Activities found"));
			} else {
				
				// becuase we moved forward in the if statement above
				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {
					// show all Teams
					// add mouseListener on each Label

					int activityTeamId = Launcher.adbc.rs.getInt("idActivityTeam");
					// System.out.println(AbstractionLevelId);
					String activityTeamName = Launcher.adbc.rs.getString("nameActivityTeam");
					// System.out.println(AbstractionLevelName);
					final JLabel activityTeamLabel = new JLabel(activityTeamId + " : " + activityTeamName, activityTeamIcon, JLabel.LEFT);
					activityTeamLabel.addMouseListener(new MouseListener() {

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

					 centerPanel.add(activityTeamLabel);

				}
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
