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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.Launcher;
import gui.VpCISWindow;

public class UpdateAbstractionLevelPanel extends TemplatePanel {

	protected JLabel idAbstractionLevelLabel;
	protected JList allAbstractionLevelsList;
	protected JLabel nameAbstractionLevelLabel;
	protected JTextField nameAbstractionLevelTextField;
	protected JLabel idViewpointRelatedLabel;
	protected JTextField idViewpointRelatedTextField;
	protected ArrayList<AbstractionLevelRecord> abstractionLevels = new ArrayList<AbstractionLevelRecord>();
	protected ActionListener okal;

	public UpdateAbstractionLevelPanel() {
		super("Update an existing Abstraction Level");
		// TODO Auto-generated constructor stub

		// hide useles butons
		addButton.hide();
		updateButton.hide();
		deleteButton.hide();
		backButton.hide();
		editButton.hide();

		// show system Message
		showSystemMessage("Choose from the list an Abstraction Level to update.");

		// configuration of the southPanel
		centerPanel.setLayout(new FlowLayout());

		idAbstractionLevelLabel = new JLabel("Id Abstraction Level :");
		allAbstractionLevelsList = new JList();
		allAbstractionLevelsList.setVisibleRowCount(5);
		allAbstractionLevelsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		nameAbstractionLevelLabel = new JLabel("Name :");
		nameAbstractionLevelTextField = new JTextField();
		nameAbstractionLevelTextField.setColumns(20);

		idViewpointRelatedLabel = new JLabel("Id CIS Project related :");
		idViewpointRelatedTextField = new JTextField();
		idViewpointRelatedTextField.setColumns(20);
		idViewpointRelatedTextField.setEditable(false);

		// fill the JList
		try {

			Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String request = String.format("SELECT * FROM AbstractionLevel ");
			Launcher.adbc.rs = Launcher.adbc.st.executeQuery(request);

			if (!Launcher.adbc.rs.next()) {
				// no CIS Records found
				// centerPanel.add(new JLabel("No CIS Projects found"));
				AbstractionLevelRecord abr = new AbstractionLevelRecord(0, "", 0);
				abstractionLevels.add(abr);
			} else {

				Launcher.adbc.rs.previous();

				while (Launcher.adbc.rs.next()) {

					int idAbstractionLevel;
					String nameAbstractionLevel;
					int idViewpointRelated;

					idAbstractionLevel = Launcher.adbc.rs.getInt("idAbstractionLevel");
					nameAbstractionLevel = Launcher.adbc.rs.getString("nameAbstractionLevel");
					idViewpointRelated = Launcher.adbc.rs.getInt("idViewpoint");

					AbstractionLevelRecord abr = new AbstractionLevelRecord(idAbstractionLevel, nameAbstractionLevel,
							idViewpointRelated);
					abstractionLevels.add(abr);
				}
				// end of the filling the ArrayList CISProjects
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// we have here an Array list containing idvp-namevp-idcis of Viewpoint

		// need to create a new Arraylist of Strings containing id-name CIS
		ArrayList<String> abstractionLevelsListIdName = new ArrayList<String>();

		// copy the id name from viewpointList to viewpointsListIdName
		Iterator<AbstractionLevelRecord> it = abstractionLevels.iterator();
		while (it.hasNext()) {
			AbstractionLevelRecord abr = new AbstractionLevelRecord(it.next());
			String str = abr.idAbstractionLevel + " - " + abr.nameAbstractionLevel;
			// System.out.println("aaaaaaaaaa"+str);
			abstractionLevelsListIdName.add(str);
		}

		allAbstractionLevelsList = new JList(abstractionLevelsListIdName.toArray());

		// now adding a listener on each Jlist item
		allAbstractionLevelsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				nameAbstractionLevelTextField.setText(
						abstractionLevels.get(allAbstractionLevelsList.getSelectedIndex()).nameAbstractionLevel);
				idViewpointRelatedTextField.setText(String.valueOf(
						abstractionLevels.get(allAbstractionLevelsList.getSelectedIndex()).idViewpointRelated));

			}
		});

		centerPanel.add(idAbstractionLevelLabel);
		centerPanel.add(new JScrollPane(allAbstractionLevelsList));
		centerPanel.add(nameAbstractionLevelLabel);
		centerPanel.add(nameAbstractionLevelTextField);
		centerPanel.add(idViewpointRelatedLabel);
		centerPanel.add(idViewpointRelatedTextField);

		// configure add button
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// update
				// systemMessage
				// go to AllCISPanel
				try {
					Launcher.adbc.st = Launcher.adbc.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

					String request = "UPDATE AbstractionLevel " + "SET nameAbstractionLevel='"
							+ nameAbstractionLevelTextField.getText() + "' "
					// + "AND description='"+descriptionTextArea.getText()+"'"
							+ "WHERE idAbstractionLevel='"
							+ abstractionLevels.get(allAbstractionLevelsList.getSelectedIndex()).idAbstractionLevel
							+ "'";

					Launcher.adbc.st.executeUpdate(request);

					// end updating
				} catch (Exception e) {
					e.printStackTrace();
				}
				// show message
				showSystemMessage("the Abstraction Level has been updated succesffully");

				// switch to allAbstractionLevelsPanel
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allAbstractionLevelsPanel);
				AllAbstractionLevelsPanel allAbstractionLevelsPanel = new AllAbstractionLevelsPanel();
				VpCISWindow.pVpCIS.add(allAbstractionLevelsPanel, "AllAbstractionLevelsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllAbstractionLevelsPanel");

			}
		});

		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				VpCISWindow.pVpCIS.remove(VpCISWindow.pVpCIS.allAbstractionLevelsPanel);
				AllAbstractionLevelsPanel allAbstractionLevelsPanel = new AllAbstractionLevelsPanel();
				VpCISWindow.pVpCIS.add(allAbstractionLevelsPanel, "AllAbstractionLevelsPanel");
				VpCISWindow.pVpCIS.repaint();
				VpCISWindow.pVpCIS.revalidate();
				VpCISWindow.pVpCIS.cl.show(VpCISWindow.pVpCIS, "AllAbstractionLevelsPanel");
			}
		});

	}

	class AbstractionLevelRecord {
		int idAbstractionLevel;
		String nameAbstractionLevel;
		int idViewpointRelated;

		AbstractionLevelRecord(int idAbstractionLevel, String nameAbstractionLevel, int idViewpointRelated) {
			this.idAbstractionLevel = idAbstractionLevel;
			this.nameAbstractionLevel = nameAbstractionLevel;
			this.idViewpointRelated = idViewpointRelated;
		}

		AbstractionLevelRecord(AbstractionLevelRecord abr) {
			this.idAbstractionLevel = abr.idAbstractionLevel;
			this.nameAbstractionLevel = abr.nameAbstractionLevel;
			this.idViewpointRelated = abr.idViewpointRelated;
		}
	}
}
