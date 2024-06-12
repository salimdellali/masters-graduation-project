package vpcispanels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.*;
import vpcisteamitemspanels.*;
import databasemanager.*;

public class AllAbstractionLevelsPanel extends TemplatePanel {

	public static int idAbstractionLevelEntred;
	public static String NameIdAbstractionLevelEntred;
	
	public AllAbstractionLevelsPanel() {
		// constructor
		super("List of all AbstractionLevels ");

		//hide the useless buttons
		cancelButton.hide();
		okButton.hide();
		editButton.hide();
		
		// configure Add Button
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//delete the panel Add
				//recreate a new Panel Add
				//add it to pVpCIS
				//repaint and revalidate
				//call cl.show
				
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.addAbstractionLevelPanel);
				AddAbstractionLevelPanel addAbstractionLevelPanel = new AddAbstractionLevelPanel();
				VpCISWindow.pVpCIS.add(addAbstractionLevelPanel,"AddAbstractionLevelPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AddAbstractionLevelPanel");
				//System.out.println(this.toString());
				 
				 

			}
		});

		//configure Update Button
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.updateAbstractionLevelPanel);
				UpdateAbstractionLevelPanel updateAbstractionLevelPanel = new UpdateAbstractionLevelPanel();
				VpCISWindow.pVpCIS.add(updateAbstractionLevelPanel, "UpdateAbstractionLevelPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "UpdateAbstractionLevelPanel");
			}
		});
		
		//configure Delete Button
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.deleteAbstractionLevelPanel);
				DeleteAbstractionLevelPanel deleteAbstractionLevelPanel = new DeleteAbstractionLevelPanel();
				VpCISWindow.pVpCIS.add(deleteAbstractionLevelPanel, "DeleteAbstractionLevelPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "DeleteAbstractionLevelPanel");
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allViewpointsPanel);
				AllViewpointsPanel allViewPointsPanel = new AllViewpointsPanel();
				VpCISWindow.pVpCIS.add(allViewPointsPanel, "AllViewPointsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllViewPointsPanel");
			}
		});
		/*configure of the centerPanel
		 * showing all the AbstractionLevel 
		 */
		
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM AbstractionLevel "
					+ "WHERE idViewpoint="+AllViewpointsPanel.idViewpointEntred+"");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);
			ImageIcon AbstractionLevelIcon = new ImageIcon("icons//AbstractionLevel.png");

			if (!Launcher.adbc.rs.next()) {
				centerPanel.add(new JLabel("No Abstraction Level  found"));
			} else {
				// becuase we moved forward in the if statement above
				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {
					// show all AbstractionLevels 
					// add mouseListener on each Label
					// 1- one click to select
					// 2- double click to enter the AbstractionLevel
					int AbstractionLevelId = Launcher.adbc.rs.getInt("idAbstractionLevel");
					//System.out.println(AbstractionLevelId);
					String AbstractionLevelName = Launcher.adbc.rs.getString("nameAbstractionLevel");
					//System.out.println(AbstractionLevelName);
					final JLabel AbstractionLevelLabel = new JLabel(AbstractionLevelId + " : " + AbstractionLevelName, AbstractionLevelIcon,
							JLabel.LEFT);
					AbstractionLevelLabel.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent arg0) {
							// move to the AllAbstractionLevel but with
							// restriction on idAbstractionLevel = idAbstractionLevel selected
							//System.out.println("you clicked me");
							idAbstractionLevelEntred = AbstractionLevelId;
							NameIdAbstractionLevelEntred = AbstractionLevelName;
							
							//go to AllItemsPanel
							VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allItemsPanel);
							AllItemsPanel allItemsPanel = new AllItemsPanel();
							VpCISWindow.pVpCIS.add(allItemsPanel, "AllItemsPanel");
							VpCISWindow.pVpCIS.repaint();
							VpCISWindow.pVpCIS.revalidate();
							VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllItemsPanel");
						}

						public void mouseEntered(MouseEvent arg0) {

							AbstractionLevelLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						}

						public void mouseExited(MouseEvent arg0) {

							AbstractionLevelLabel.setBorder(BorderFactory.createLineBorder(null, 0));
						}

						public void mousePressed(MouseEvent arg0) {

						}

						public void mouseReleased(MouseEvent arg0) {

						}

					});
					// HandlerClass handler = new HandlerClass();
					// AbstractionLevelLabel.addMouseListener(handler);

					// JLabelIcon AbstractionLevelLabelIcon = new
					// JLabelIcon(AbstractionLevelId,AbstractionLevelName,AbstractionLevelIcon);
					centerPanel.add(AbstractionLevelLabel);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

