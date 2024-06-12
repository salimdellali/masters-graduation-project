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

public class UpdateCISProjectPanel extends TemplatePanel {

	protected JLabel idCISLabel;
	protected JList allCISProjectsList;
	protected JLabel nameCISLabel;
	protected JTextField nameCISTextField;
	protected JLabel descriptionLabel;
	protected JTextArea descriptionTextArea;
	protected ArrayList<CISProjectRecord> CISProjects = new ArrayList<CISProjectRecord>();
	protected ActionListener okal;

	public UpdateCISProjectPanel() {
		// constructor
		super("Update an existing CIS Project");

		// Hide the useless buttons
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		backButton.hide();
		editButton.hide();
		
		showSystemMessage("Choose from the list a CIS project to update.");

		// configure the centerPanel
		centerPanel.setLayout(new FlowLayout());

		idCISLabel = new JLabel("Id CIS :");
		allCISProjectsList = new JList();
		allCISProjectsList.setVisibleRowCount(5);
		allCISProjectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		nameCISLabel = new JLabel("Name :");
		nameCISTextField = new JTextField();

		descriptionLabel = new JLabel("Description :");
		descriptionTextArea = new JTextArea(5, 20);

		nameCISTextField.setColumns(20);
		descriptionTextArea.setLineWrap(true);

		centerPanel.add(idCISLabel);

		// filling the JList with values

		try {
			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM CIS ");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);
			if (!Launcher.adbc.rs.next()) {
				// no CIS Records found
				// centerPanel.add(new JLabel("No CIS Projects found"));
				CISProjectRecord CISpr = new CISProjectRecord(0, "", "");
				CISProjects.add(CISpr);
			} else {
				// becuase we moved forward in the if statement above
				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {

					int idCISProject;
					String nameCISProject;
					String descriptionCISProject;

					idCISProject = Launcher.adbc.rs.getInt("idCIS");
					nameCISProject = Launcher.adbc.rs.getString("nameCIS");
					descriptionCISProject = Launcher.adbc.rs.getString("description");

					CISProjectRecord CISpr = new CISProjectRecord(idCISProject, nameCISProject, descriptionCISProject);
					CISProjects.add(CISpr);
				}
				// end of the filling the ArrayList CISProjects
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// we have here an Array list containing id-name-desc of CISProject

		// need to create a new Arraylist of Strings containing id-name CIS
		ArrayList<String> CISProjectsListIdName = new ArrayList<String>();

		// copy the id name from CISProjectsList to CISProjectsListIdName
		Iterator<CISProjectRecord> it = CISProjects.iterator();
		while (it.hasNext()) {
			CISProjectRecord CISpr = new CISProjectRecord(it.next());
			String str = CISpr.idCISProject + " - " + CISpr.nameCISProject;
			// System.out.println("aaaaaaaaaa"+str);
			CISProjectsListIdName.add(str);
		}

		allCISProjectsList = new JList(CISProjectsListIdName.toArray());

		// now adding a listener on each Jlist item
		allCISProjectsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				nameCISTextField.setText(CISProjects.get(allCISProjectsList.getSelectedIndex()).nameCISProject);
				descriptionTextArea
						.setText(CISProjects.get(allCISProjectsList.getSelectedIndex()).descriptionCISProject);

			}
		});
		centerPanel.add(new JScrollPane(allCISProjectsList));
		centerPanel.add(nameCISLabel);
		centerPanel.add(nameCISTextField);
		centerPanel.add(descriptionLabel);
		centerPanel.add(descriptionTextArea);

		// show system Message
		showSystemMessage("Choose from the list an Id CIS to update.");

		// configuring the buttons
		okButton.addActionListener(okal = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// update
					// systemMessage
					// go to AllCISPanel
					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					// update the nameViewpoint
					String request = "UPDATE CIS " + "SET nameCIS='" + nameCISTextField.getText() + "' "

							+ "WHERE idCIS='" + CISProjects.get(allCISProjectsList.getSelectedIndex()).idCISProject
							+ "'";

					Launcher.adbc.st.executeUpdate(request);

					// update the idCIS
					request = "UPDATE CIS " + "SET description='" + descriptionTextArea.getText() + "'"
							+ "WHERE idCIS='" + CISProjects.get(allCISProjectsList.getSelectedIndex()).idCISProject
							+ "'";

					Launcher.adbc.st.executeUpdate(request);

				} catch (Exception e) {
					e.printStackTrace();
				}

				showSystemMessage("the CIS Project has been updated succesffully");

				// move to AllCISProjectsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allCISProjectsPanel);
				AllCISProjectsPanel allCISProjectsPanel = new AllCISProjectsPanel();
				VpCISWindow.pVpCIS.add(allCISProjectsPanel, "AllCISProjectsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllCISProjectsPanel");
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allCISProjectsPanel);
				AllCISProjectsPanel allCISProjectsPanel = new AllCISProjectsPanel();
				VpCISWindow.pVpCIS.add(allCISProjectsPanel, "AllCISProjectsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllCISProjectsPanel");
			}
		});
	}

	protected void showSystemMessage(String message) {
		// PanelSystemMessages.systemMessages.setText(message);
		VpCISWindow.psm.systemMessages.setText(message);
	}

	class CISProjectRecord {

		int idCISProject;
		String nameCISProject;
		String descriptionCISProject;

		CISProjectRecord(int idCISProject, String nameCISProject, String descriptionCISProject) {
			this.idCISProject = idCISProject;
			this.nameCISProject = nameCISProject;
			this.descriptionCISProject = descriptionCISProject;
		}

		CISProjectRecord(CISProjectRecord CISpr) {
			this.idCISProject = CISpr.idCISProject;
			this.nameCISProject = CISpr.nameCISProject;
			this.descriptionCISProject = CISpr.descriptionCISProject;
		}
	}
}
