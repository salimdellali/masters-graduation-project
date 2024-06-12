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
import databasemanager.*;

public class AllCISProjectsPanel extends TemplatePanel {

	public static int idCISEntred;
	
	public AllCISProjectsPanel() {
		// constructor
		super("List of all CIS Projects");

		//hide the useless buttons
		cancelButton.hide();
		okButton.hide();
		backButton.hide();
		editButton.hide();
		
		//showMessage
		//showSystemMessage("Welcome to VpCIS Frame Work, This section will show System Messages");
		
		// configure Add Button
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//delete the panel Add
				//recreate a new Panel Add
				//add it to pVpCIS
				//repaint and revalidate
				//call cl.show
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.addCISProjectPanel);
				AddCISProjectPanel addCISProjectPanel = new AddCISProjectPanel();
				VpCISWindow.pVpCIS.add(addCISProjectPanel,"AddCISProjectPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AddCISProjectPanel");
				//System.out.println(this.toString());

			}
		});

		//configure Update Button
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//do something
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.updateCISProjectPanel);
				UpdateCISProjectPanel updateCISProjectPanel = new UpdateCISProjectPanel();
				VpCISWindow.pVpCIS.add(updateCISProjectPanel,"UpdateCISProjectPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "UpdateCISProjectPanel");
			}
		});
		
		//configure Delete Button
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//do something
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.deleteCISProjectPanel);
				UpdateCISProjectPanel deleteCISProjectPanel = new DeleteCISProjectPanel();
				VpCISWindow.pVpCIS.add(deleteCISProjectPanel,"DeleteCISProjectPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "DeleteCISProjectPanel");
			}
		});
		
		/*configure of the centerPanel
		 * showing all the CIS Projects
		 */
		
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM CIS ");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);
			ImageIcon CISProjectIcon = new ImageIcon("icons\\CISProject.png");

			if (!Launcher.adbc.rs.next()) {
				centerPanel.add(new JLabel("No CIS Projects found"));
			} else {
				// becuase we moved forward in the if statement above
				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {
					// show all CIS Projects
					// add mouseListener on each Label
					// 1- one click to select
					// 2- double click to enter the CIS Project
					int CISProjectId = Launcher.adbc.rs.getInt("idCIS");
					//System.out.println(CISProjectId);
					String CISProjectName = Launcher.adbc.rs.getString("nameCIS");
					//System.out.println(CISProjectName);
					JLabel CISProjectLabel = new JLabel(CISProjectId + " : " + CISProjectName, CISProjectIcon,
							JLabel.LEFT);
					CISProjectLabel.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent arg0) {
							
							// save the idCIS somewhere
							idCISEntred = CISProjectId;
							
							// move to AllViewpointsPanel
							VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allViewpointsPanel);
							AllViewpointsPanel allViewpointsPanel = new AllViewpointsPanel();
							VpCISWindow.pVpCIS.add(allViewpointsPanel,"AllViewpointsPanel");
							VpCISWindow.pVpCIS.repaint();
							VpCISWindow.pVpCIS.revalidate();
							VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllViewpointsPanel");
							
						}

						public void mouseEntered(MouseEvent arg0) {

							CISProjectLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						}

						public void mouseExited(MouseEvent arg0) {

							CISProjectLabel.setBorder(BorderFactory.createLineBorder(null, 0));
						}

						public void mousePressed(MouseEvent arg0) {

						}

						public void mouseReleased(MouseEvent arg0) {

						}

					});
					// HandlerClass handler = new HandlerClass();
					// CISProjectLabel.addMouseListener(handler);

					// JLabelIcon CISProjectLabelIcon = new
					// JLabelIcon(CISProjectId,CISProjectName,CISProjectIcon);
					centerPanel.add(CISProjectLabel);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
