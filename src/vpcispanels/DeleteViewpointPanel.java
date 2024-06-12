package vpcispanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import gui.Launcher;
import gui.VpCISWindow;

public class DeleteViewpointPanel extends UpdateViewpointPanel {

	public DeleteViewpointPanel() {
		super();

		titleLabel.setText("Delete an existing Viewpoint");

		nameViewpointTextField.setEditable(false);

		showSystemMessage("Choose from the list a Viewpoint to delete.");

		okButton.removeActionListener(okal);
		okButton.addActionListener(okal = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// delete
				// systemMessage
				// go to AllViewpointPanel
				try {

					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					// update the nameCIS
					String request = "DELETE FROM Viewpoint "
							+ "WHERE idViewpoint='" + viewpoints.get(allViewpointsList.getSelectedIndex()).idViewpoint
							+ "'";

					Launcher.adbc.st.executeUpdate(request);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				showSystemMessage("the Viewpoint has been deleted succesffully");
				
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allViewpointsPanel);
				AllViewpointsPanel allViewPointsPanel = new AllViewpointsPanel();
				VpCISWindow.pVpCIS.add(allViewPointsPanel, "AllViewPointsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllViewPointsPanel");
			}
		});
	}

}
