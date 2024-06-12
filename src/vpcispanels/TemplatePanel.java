package vpcispanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.PanelSystemMessages;
import gui.VpCISWindow;

public class TemplatePanel extends JPanel {

	protected JPanel northPanel = new JPanel();
	protected JPanel centerPanel = new JPanel();
	protected JPanel southPanel = new JPanel();

	protected JLabel titleLabel = new JLabel();
	protected JButton addButton = new JButton("Add");
	protected JButton updateButton = new JButton("Update");
	protected JButton deleteButton = new JButton("Delete");
	protected JButton cancelButton = new JButton("Cancel");
	protected JButton backButton = new JButton("Back");
	protected JButton okButton = new JButton("OK");
	protected JButton editButton = new JButton("Edit");
	private JScrollPane scrollSouthPanel;

	public TemplatePanel(String title) {
		setLayout(new BorderLayout());

		// panelNorth for the title
		// panelCenter for listing elements
		// panelSouth for buttons
		/*
		 * just adding scrollPanes so we can have scroll, and instead of usin
		 * the Panel, we use it respective ScrollPane
		 */

		scrollSouthPanel = new JScrollPane(southPanel);

		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(scrollSouthPanel, BorderLayout.SOUTH);

		// configuration of the nothPanel
		titleLabel.setText(title);
		northPanel.add(titleLabel);

		// configuration of the centerPanel

		// configuration of the southPanel
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		southPanel.add(addButton);
		southPanel.add(updateButton);
		southPanel.add(deleteButton);
		southPanel.add(okButton);
		southPanel.add(cancelButton);
		southPanel.add(editButton);
		southPanel.add(backButton);
		
	}

	protected void showSystemMessage(String message) {
		//PanelSystemMessages.systemMessages.setText(message);
		VpCISWindow.psm.systemMessages.setText(message);
	}

}
