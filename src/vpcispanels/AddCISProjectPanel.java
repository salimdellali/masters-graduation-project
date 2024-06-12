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

public class AddCISProjectPanel extends TemplatePanel {

	private JLabel nameCISProjectLabel;
	private JTextField nameCISProjectTextField;
	private JLabel descriptionCISProjectLabel;
	private JTextArea descriptionCISProjectTextArea;

	public AddCISProjectPanel() {
		// constructor
		super("Add a new CIS Project");

		// Hide the useless buttons
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		backButton.hide();
		editButton.hide();

		// configuring the centerPanel
		centerPanel.setLayout(new FlowLayout());

		nameCISProjectLabel = new JLabel("Name :");
		nameCISProjectTextField = new JTextField();
		descriptionCISProjectLabel = new JLabel("Description :");
		descriptionCISProjectTextArea = new JTextArea(5, 20);

		nameCISProjectTextField.setColumns(20);
		descriptionCISProjectTextArea.setLineWrap(true);

		centerPanel.add(nameCISProjectLabel);
		centerPanel.add(nameCISProjectTextField);
		centerPanel.add(descriptionCISProjectLabel);
		centerPanel.add(descriptionCISProjectTextArea);

		// show system message "NOTE:Name field is mendatory, Description field
		// is prefered but not mendatory."
		// System.out.println("NOTE:Name field is mendatory, Description field
		// is prefered but not mendatory.");

		showSystemMessage("Name field is mendatory, Description field is prefered but not mendatory.");

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
				// Insert into CIS
				// show system message
				// delete panel allCIS
				// recreate a new Panel AllCIS
				// add id to pVpCIS
				// repaint & revalidate
				// call cl.show ->switch to AllCISProjectsPanel

				try {
					if (!nameCISProjectTextField.getText().equals("")) {
						// System.out.println(nameCISProjectTextField.get);
						String request = String.format("INSERT INTO CIS (nameCIS,description) VALUES ( " + "'"
								+ nameCISProjectTextField.getText() + "'," + "'"
								+ descriptionCISProjectTextArea.getText() + "')");
						System.out.println(request);
						Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						Launcher.adbc.st.executeUpdate(request);
						// AddCISProjectPanel.

						// PanelSystemMessages.systemMessages.setText("CIS
						// project added successfully!");
						showSystemMessage("CIS project added successfully!");
						// VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS,
						// "AllCISProjectsPanel");
						VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allCISProjectsPanel);
						AllCISProjectsPanel allCISProjectsPanel = new AllCISProjectsPanel();
						VpCISWindow.pVpCIS.add(allCISProjectsPanel, "AllCISProjectsPanel");
						VpCISWindow.pVpCIS.repaint();
						VpCISWindow.pVpCIS.revalidate();
						VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllCISProjectsPanel");
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
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allCISProjectsPanel);
				AllCISProjectsPanel allCISProjectsPanel = new AllCISProjectsPanel();
				VpCISWindow.pVpCIS.add(allCISProjectsPanel, "AllCISProjectsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllCISProjectsPanel");
			}
		});
		/*
		 * addButton.addActionListener(new ActionListener() {
		 * 
		 * public void actionPerformed(ActionEvent arg0) { // TODO
		 * Auto-generated method stub if (!nameTextField.getText().equals("")) {
		 * // add in database the new record // show JOptionPane message about
		 * succesful add // and switch to the all CISProjectPanel try {
		 * Launcher.adbc.st =
		 * Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		 * ResultSet.CONCUR_UPDATABLE); //String request =
		 * String.format("SELECT * FROM CIS "); String request = String.format(
		 * "INSERT INTO CIS (nameCIS,descriptionCIS) VALUES ('" +
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
