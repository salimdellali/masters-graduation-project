package vpcispanels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.Launcher;
import gui.VpCISWindow;
import vpcispanels.UpdateCISProjectPanel.CISProjectRecord;

public class UpdateViewpointPanel extends TemplatePanel {

	protected JLabel idViewpointLabel;
	protected JList allViewpointsList;
	protected JLabel nameViewpointLabel;
	protected JTextField nameViewpointTextField;
	protected JLabel idCISProjectRelatedLabel;
	protected JTextField idCISProjectRelatedTextField;
	protected ArrayList<ViewpointRecord> viewpoints = new ArrayList<ViewpointRecord>();
	protected ActionListener okal;

	public UpdateViewpointPanel() {
		// constructor
		super("Update an existing Viewpoint ");

		// Hide the useless buttons
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		backButton.hide();
		editButton.hide();
		
		// show system Message
		showSystemMessage("Choose from the list an Id Viewpoint to update.");

		// configure the centerPanel
		centerPanel.setLayout(new FlowLayout());

		idViewpointLabel = new JLabel("Id Viewpoint :");
		allViewpointsList = new JList();
		allViewpointsList.setVisibleRowCount(5);
		allViewpointsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		nameViewpointLabel = new JLabel("Name :");
		nameViewpointTextField = new JTextField();
		nameViewpointTextField.setColumns(20);

		idCISProjectRelatedLabel = new JLabel("Id CIS Project related :");
		idCISProjectRelatedTextField = new JTextField();
		idCISProjectRelatedTextField.setColumns(20);
		idCISProjectRelatedTextField.setEditable(false);

		// charge the JList
		// filling the JList with values
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM Viewpoint ");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			if (!Launcher.adbc.rs.next()) {
				// no CIS Records found
				// centerPanel.add(new JLabel("No CIS Projects found"));
				ViewpointRecord vr = new ViewpointRecord(0, "", 0);
				viewpoints.add(vr);
			} else {

				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {

					int idViewpoint;
					String nameViewpoint;
					int idCISProject;

					idViewpoint = Launcher.adbc.rs.getInt("idViewpoint");
					nameViewpoint = Launcher.adbc.rs.getString("nameViewpoint");
					idCISProject = Launcher.adbc.rs.getInt("idCIS");

					ViewpointRecord vr = new ViewpointRecord(idViewpoint, nameViewpoint, idCISProject);
					viewpoints.add(vr);
				}
				// end of the filling the ArrayList CISProjects
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// we have here an Array list containing idvp-namevp-idcis of Viewpoint

		// need to create a new Arraylist of Strings containing id-name CIS
		ArrayList<String> viewpointsListIdName = new ArrayList<String>();

		// copy the id name from viewpointList to viewpointsListIdName
		Iterator<ViewpointRecord> it = viewpoints.iterator();
		while (it.hasNext()) {
			ViewpointRecord vr = new ViewpointRecord(it.next());
			String str = vr.idViewpoint + " - " + vr.nameViewpoint;
			// System.out.println("aaaaaaaaaa"+str);
			viewpointsListIdName.add(str);
		}

		allViewpointsList = new JList(viewpointsListIdName.toArray());

		// now adding a listener on each Jlist item
		allViewpointsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				nameViewpointTextField.setText(viewpoints.get(allViewpointsList.getSelectedIndex()).nameViewpoint);
				idCISProjectRelatedTextField
						.setText(String.valueOf(viewpoints.get(allViewpointsList.getSelectedIndex()).idCISProject));

			}
		});

		centerPanel.add(idViewpointLabel);
		centerPanel.add(new JScrollPane(allViewpointsList));
		centerPanel.add(nameViewpointLabel);
		centerPanel.add(nameViewpointTextField);
		centerPanel.add(idCISProjectRelatedLabel);
		centerPanel.add(idCISProjectRelatedTextField);

		// configuring the buttons
		okButton.addActionListener(okal = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// update
				// systemMessage
				// go to AllCISPanel
				try {
					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					
					String request = "UPDATE Viewpoint " + "SET nameViewpoint='" + nameViewpointTextField.getText() + "' "
							// + "AND description='"+descriptionTextArea.getText()+"'"
									+ "WHERE idViewpoint='" + viewpoints.get(allViewpointsList.getSelectedIndex()).idViewpoint
									+ "'";
					
					Launcher.adbc.st.executeUpdate(request);

					// no need to udate the idCIS related because the user can't change it
					/* update the idCISRelated
					request = "UPDATE Viewpoint " + "SET idCIS='" + idCISProjectRelatedTextField.getText() + "'"
							+ "WHERE idViewpoint='" + viewpoints.get(allViewpointsList.getSelectedIndex()).idCISProject
							+ "'";

					Launcher.adbc.st.executeUpdate(request);
					*/
					//end updating
				} catch (Exception e) {
					e.printStackTrace();
				}
				//show message
				showSystemMessage("the Viewpoint has been updated succesffully");
				
				//switch to allViewpointsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allViewpointsPanel);
				AllViewpointsPanel allViewPointsPanel = new AllViewpointsPanel();
				VpCISWindow.pVpCIS.add(allViewPointsPanel, "AllViewPointsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllViewPointsPanel");
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allViewpointsPanel);
				AllViewpointsPanel allViewPointsPanel = new AllViewpointsPanel();
				VpCISWindow.pVpCIS.add(allViewPointsPanel, "AllViewPointsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllViewPointsPanel");
			}
		});
	}

	class ViewpointRecord {
		int idViewpoint;
		String nameViewpoint;
		int idCISProject;

		public ViewpointRecord(int idViewpoint, String nameViewpoint, int idCISProject) {
			this.idViewpoint = idViewpoint;
			this.nameViewpoint = nameViewpoint;
			this.idCISProject = idCISProject;
		}

		public ViewpointRecord(ViewpointRecord vr) {
			this.idViewpoint = vr.idViewpoint;
			this.nameViewpoint = vr.nameViewpoint;
			this.idCISProject = vr.idCISProject;
		}
	}
}
