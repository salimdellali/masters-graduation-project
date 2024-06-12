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

public class AddAbstractionLevelPanel extends TemplatePanel {

	protected JLabel nameAbstractionLevelLabel;
	protected JTextField nameAbstractionLevelTextField;
	protected JLabel idViewpointRelatedLabel;
	protected JTextField idViewpointRelatedTextField;

	public AddAbstractionLevelPanel() {
		// constructor
		super("Add a new Abstraction Level");

		// Hide the useless buttons
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		backButton.hide();
		editButton.hide();

		// configuring the centerPanel
		centerPanel.setLayout(new FlowLayout());

		nameAbstractionLevelLabel = new JLabel("Name Abstraction Level :");
		nameAbstractionLevelTextField = new JTextField();
		idViewpointRelatedLabel = new JLabel("Id Viewpoint Related :");
		idViewpointRelatedTextField = new JTextField();

		nameAbstractionLevelTextField.setColumns(20);
		idViewpointRelatedTextField.setColumns(20);
		idViewpointRelatedTextField.setEditable(false);
		idViewpointRelatedTextField.setText(String.valueOf(AllViewpointsPanel.idViewpointEntred));
		
		centerPanel.add(nameAbstractionLevelLabel);
		centerPanel.add(nameAbstractionLevelTextField);
		centerPanel.add(idViewpointRelatedLabel);
		centerPanel.add(idViewpointRelatedTextField);

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
				// Insert into AbstractionLevel
				// show system message
				// delete panel allAbstractionLevel
				// recreate a new Panel AllAbstractionLevel
				// add id to pVpCIS
				// repaint & revalidate
				// call cl.show ->switch to AllAbstractionLevelsPanel

				try {
					if (!nameAbstractionLevelTextField.getText().equals("")) {
						
						// System.out.println(nameAbstractionLevelTextField.get);
						String request = String.format("INSERT INTO AbstractionLevel (nameAbstractionLevel,idViewpoint) VALUES ( " + "'"
								+ nameAbstractionLevelTextField.getText() + "'," + "'"
								+ idViewpointRelatedTextField.getText() + "')");
						//System.out.println(request);
						Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						Launcher.adbc.st.executeUpdate(request);
						// AddAbstractionLevelPanel.

						// PanelSystemMessages.systemMessages.setText("AbstractionLevel
						// project added successfully!");
						showSystemMessage("Abstraction Level added successfully!");
						// VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS,
						// "AllAbstractionLevelsPanel");
						
						VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.addAbstractionLevelPanel);
						AllAbstractionLevelsPanel allAbstractionLevelsPanel = new AllAbstractionLevelsPanel();
						VpCISWindow.pVpCIS.add(allAbstractionLevelsPanel, "AllAbstractionLevelsPanel");
						VpCISWindow.pVpCIS.repaint();
						VpCISWindow.pVpCIS.revalidate();
						VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllAbstractionLevelsPanel");
						
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
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allAbstractionLevelsPanel);
				AllAbstractionLevelsPanel allAbstractionLevelsPanel = new AllAbstractionLevelsPanel();
				VpCISWindow.pVpCIS.add(allAbstractionLevelsPanel, "AllAbstractionLevelsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllAbstractionLevelsPanel");
			}
		});
		/*
		 * addButton.addActionListener(new ActionListener() {
		 * 
		 * public void actionPerformed(ActionEvent arg0) { // TODO
		 * Auto-generated method stub if (!nameTextField.getText().equals("")) {
		 * // add in database the new record // show JOptionPane message about
		 * succesful add // and switch to the all AbstractionLevelPanel try {
		 * Launcher.adbc.st =
		 * Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		 * ResultSet.CONCUR_UPDATABLE); //String request =
		 * String.format("SELECT * FROM AbstractionLevel "); String request = String.format(
		 * "INSERT INTO AbstractionLevel (nameAbstractionLevel,descriptionAbstractionLevel) VALUES ('" +
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

