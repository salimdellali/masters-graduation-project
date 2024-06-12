package vpcispanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import gui.Launcher;
import gui.VpCISWindow;

public class DeleteCISProjectPanel extends UpdateCISProjectPanel {


	public DeleteCISProjectPanel() {
		// constructor
		super();
		
		titleLabel.setText("Delete an existing CIS Project");
		nameCISTextField.setEditable(false);
		descriptionTextArea.setEditable(false);
		
		//show message
		showSystemMessage("Choose from the list an Id CIS to delete.");
		okButton.removeActionListener(okal);
		okButton.addActionListener(okal = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// delete
					// systemMessage
					// go to AllCISPanel
					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					//update the nameCIS
					String request = "DELETE FROM CIS " 
					//+ "SET nameCIS='"+nameCISTextField.getText()+"' " 
					//+ "AND description='"+descriptionTextArea.getText()+"'"
					+ "WHERE idCIS='"+CISProjects.get(allCISProjectsList.getSelectedIndex()).idCISProject+"'";

					Launcher.adbc.st.executeUpdate(request);


				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//showSystemMessage("the CIS Project has been deleted succesffully");
				//showSystemMessage("the CIS Project has been updated succesffully");

				// move to AllCISProjectsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allCISProjectsPanel);
				AllCISProjectsPanel allCISProjectsPanel = new AllCISProjectsPanel();
				VpCISWindow.pVpCIS.add(allCISProjectsPanel, "AllCISProjectsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllCISProjectsPanel");
				
				//VpCISWindow.psm.systemMessages.setText("the CIS Project has been deleted succesffully");
				//System.out.println("the CIS Project has been deleted succesffully");
				showSystemMessage("the CIS Project has been deleted succesffully");
			}
		});

	}

}
