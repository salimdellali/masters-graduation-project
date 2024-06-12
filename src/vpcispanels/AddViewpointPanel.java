package vpcispanels;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.*;

public class AddViewpointPanel extends TemplatePanel {

	protected JLabel nameViewPointLabel;
	protected JTextField nameViewPointTextField;
	protected JLabel idCISRelatedLabel;
	protected JTextField idCISRelatedTextField;

	public AddViewpointPanel() {
		// constructor
		super("Add a new Viewpoint");

		// Hide the useless buttons
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		backButton.hide();
		editButton.hide();

		// configuring the centerPanel
		centerPanel.setLayout(new FlowLayout());

		nameViewPointLabel = new JLabel("Name :");
		nameViewPointTextField = new JTextField();
		//descriptionViewPointLabel = new JLabel("Description :");
		//descriptionViewPointTextArea = new JTextArea(5, 20);
		idCISRelatedLabel = new JLabel("id CIS Project Related :");
		idCISRelatedTextField = new JTextField();

		nameViewPointTextField.setColumns(20);
		//descriptionViewPointTextArea.setLineWrap(true);
		idCISRelatedTextField.setColumns(20);
		idCISRelatedTextField.setEditable(false);
		idCISRelatedTextField.setText(String.valueOf(AllCISProjectsPanel.idCISEntred));

		centerPanel.add(nameViewPointLabel);
		centerPanel.add(nameViewPointTextField);
		//centerPanel.add(descriptionViewPointLabel);
		//centerPanel.add(descriptionViewPointTextArea);
		centerPanel.add(idCISRelatedLabel);
		centerPanel.add(idCISRelatedTextField);

		// show system message "NOTE:Name field is mendatory, Description field
		// is prefered but not mendatory."
		// System.out.println("NOTE:Name field is mendatory, Description field
		// is prefered but not mendatory.");

		showSystemMessage("Name field is mendatory");

		// PanelSystemMessages.systemMessages.setText("NOTE:Name field is
		// mendatory, Description field is prefered but not mendatory.");
		// PanelSystemMessages.systemMessages.
		// VpCISWindow.psm.systemMessages.setText("NOTE: the field description
		// is prefered to fill, but not mendatory.");
		// PanelSystemMessages.systemMessages.setText("NOTE:Name field is
		// mendatory, Description field is prefered but not mendatory.");

		// configuring the Ok button
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// test if the required field are filled
				// Insert into Viewpoint
				// show system message
				// delete panel allViewpoint
				// recreate a new Panel AllViewpoint
				// add id to pVpCIS
				// repaint & revalidate
				// call cl.show ->switch to AllViewPointsPanel

				try {
					if (!nameViewPointTextField.getText().equals("")) {
						// System.out.println(nameViewPointTextField.get);
						String request = String.format("INSERT INTO Viewpoint (nameViewpoint,idCIS) VALUES ( " + "'"
								+ nameViewPointTextField.getText() + "'," + "'"
								+ idCISRelatedTextField.getText() + "')");
						System.out.println(request);
						Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						Launcher.adbc.st.executeUpdate(request);
						// AddViewPointPanel.

						// PanelSystemMessages.systemMessages.setText("Viewpoint
						// project added successfully!");
						showSystemMessage("Viewpoint project added successfully!");
						// VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS,
						// "AllViewPointsPanel");
						VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allViewpointsPanel);
						AllViewpointsPanel allViewPointsPanel = new AllViewpointsPanel();
						VpCISWindow.pVpCIS.add(allViewPointsPanel, "AllViewPointsPanel");
						VpCISWindow.pVpCIS.repaint();
						VpCISWindow.pVpCIS.revalidate();
						VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllViewPointsPanel");
					} else {

						// this.showSystemMessage("NOTE: the field description
						// is prefered to fill, but not mendatory.");
						// PanelSystemMessages.systemMessages.setText("Name
						// field required");
						showSystemMessage("Name field required");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// do something
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allViewpointsPanel);
				AllViewpointsPanel allViewPointsPanel = new AllViewpointsPanel();
				VpCISWindow.pVpCIS.add(allViewPointsPanel, "AllViewPointsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllViewPointsPanel");
			}
		});
		/*
		 * addButton.addActionListener(new ActionListener() {
		 * 
		 * public void actionPerformed(ActionEvent arg0) { // TODO
		 * Auto-generated method stub if (!nameTextField.getText().equals("")) {
		 * // add in database the new record // show JOptionPane message about
		 * succesful add // and switch to the all ViewPointPanel try {
		 * Launcher.adbc.st =
		 * Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		 * ResultSet.CONCUR_UPDATABLE); //String request =
		 * String.format("SELECT * FROM Viewpoint "); String request = String.format(
		 * "INSERT INTO Viewpoint (nameViewpoint,descriptionViewpoint) VALUES ('" +
		 * nameTextField.getText()+"','" + descriptionTextArea.getText()+"')");
		 * //+ "'idTeam_0000'," /* + "'Vanessa'," + "'Vanessa'" + ")");
		 * 
		 * Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);
		 * 
		 * JOptionPane.showMessageDialog(null, "Adding completed successfully");
		 * 
		 * 
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } } else { // name empty
		 * show message error JOptionPane.showMessageDialog(null,
		 * "Empty fields existing, fill the needed field please", "Warning",
		 * JOptionPane.ERROR_MESSAGE); } } });
		 */
	}
}
