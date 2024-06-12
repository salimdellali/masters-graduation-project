package vpcispanels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import gui.Launcher;
import gui.VpCISWindow;

public class AllViewpointsPanel extends TemplatePanel {

	JButton linkButton = new JButton("Link Viewpoint - Viewpoint");
	public static int idViewpointEntred;
	
	public AllViewpointsPanel() {
		// constructor
		super("List of all Viewpoints ");

		//hide the useless buttons
		cancelButton.hide();
		okButton.hide();
		editButton.hide();
		
		//add the linkButton
		linkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//go to panel link
			}
		});
		southPanel.add(linkButton);
		
		// configure Add Button
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//delete the panel Add
				//recreate a new Panel Add
				//add it to pVpCIS
				//repaint and revalidate
				//call cl.show
				
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.addViewpointPanel);
				AddViewpointPanel addViewPointPanel = new AddViewpointPanel();
				VpCISWindow.pVpCIS.add(addViewPointPanel,"AddViewPointPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AddViewPointPanel");
				
				//System.out.println(this.toString());

			}
		});

		//configure Update Button
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.updateViewpointPanel);
				UpdateViewpointPanel updateViewpointPanel = new UpdateViewpointPanel();
				VpCISWindow.pVpCIS.add(updateViewpointPanel,"UpdateViewpointPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "UpdateViewpointPanel");
			}
		});
		
		//configure Delete Button
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.deleteViewpointPanel);
				DeleteViewpointPanel deleteViewpointPanel = new DeleteViewpointPanel();
				VpCISWindow.pVpCIS.add(deleteViewpointPanel, "DeleteViewpointPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "DeleteViewpointPanel");
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allCISProjectsPanel);
				AllCISProjectsPanel allCISProjectsPanel = new AllCISProjectsPanel();
				VpCISWindow.pVpCIS.add(allCISProjectsPanel, "AllCISProjectsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllCISProjectsPanel");
			}
		});
		/*configure of the centerPanel
		 * showing all the Viewpoint 
		 */
		
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM Viewpoint "
					+ "WHERE idCIS="+AllCISProjectsPanel.idCISEntred+"");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);
			ImageIcon ViewPointIcon = new ImageIcon("icons//Viewpoint.png");

			if (!Launcher.adbc.rs.next()) {
				centerPanel.add(new JLabel("No Viewpoints found"));
			} else {
				// becuase we moved forward in the if statement above
				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {
					// show all Viewpoint 
					// add mouseListener on each Label
					// 1- one click to select
					// 2- double click to enter the Viewpoint 
					int viewpointId = Launcher.adbc.rs.getInt("idViewpoint");
					//System.out.println(ViewPointId);
					String viewpointName = Launcher.adbc.rs.getString("nameViewpoint");
					//System.out.println(ViewPointName);
					final JLabel ViewPointLabel = new JLabel(viewpointId + " : " + viewpointName, ViewPointIcon,
							JLabel.LEFT);
					ViewPointLabel.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent arg0) {
							// move to the AllAbstractionLevel but with
							// restriction on idViewpoint = idViewpoint selected
							
							idViewpointEntred = viewpointId;
							
							VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allAbstractionLevelsPanel);
							AllAbstractionLevelsPanel allAbstractionLevelsPanel = new AllAbstractionLevelsPanel();
							VpCISWindow.pVpCIS.add(allAbstractionLevelsPanel, "AllAbstractionLevelsPanel");
							VpCISWindow.pVpCIS.repaint();
							VpCISWindow.pVpCIS.revalidate();
							VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllAbstractionLevelsPanel");
							
						}

						public void mouseEntered(MouseEvent arg0) {

							ViewPointLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						}

						public void mouseExited(MouseEvent arg0) {

							ViewPointLabel.setBorder(BorderFactory.createLineBorder(null, 0));
						}

						public void mousePressed(MouseEvent arg0) {

						}

						public void mouseReleased(MouseEvent arg0) {

						}

					});
					// HandlerClass handler = new HandlerClass();
					// ViewPointLabel.addMouseListener(handler);

					// JLabelIcon ViewPointLabelIcon = new
					// JLabelIcon(ViewPointId,ViewPointName,ViewPointIcon);
					centerPanel.add(ViewPointLabel);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
