package vpcispanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import gui.Launcher;
import gui.VpCISWindow;

public class DeleteAbstractionLevelPanel extends UpdateAbstractionLevelPanel {

	public DeleteAbstractionLevelPanel(){
		super();
		
		titleLabel.setText("Delete an existing Abstraction");
		
		nameAbstractionLevelTextField.setEditable(false);
		
		showSystemMessage("Choose from the list an Abstraction Level to delete.");
		
		okButton.removeActionListener(okal);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// delete
				// systemMessage
				// go to AllCISPanel
				try {

					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					// update the nameCIS
					String request = "DELETE FROM AbstractionLevel "
							+ "WHERE idAbstractionLevel='" + abstractionLevels.get(allAbstractionLevelsList.getSelectedIndex()).idAbstractionLevel
							+ "'";

					Launcher.adbc.st.executeUpdate(request);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				showSystemMessage("the Abstraction Level has been deleted succesffully");
				
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.deleteAbstractionLevelPanel);
				DeleteAbstractionLevelPanel deleteAbstractionLevelPanel = new DeleteAbstractionLevelPanel();
				VpCISWindow.pVpCIS.add(deleteAbstractionLevelPanel, "DeleteAbstractionLevelPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "DeleteAbstractionLevelPanel");
			}
		});
	}
}
