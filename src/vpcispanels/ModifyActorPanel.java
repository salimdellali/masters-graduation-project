package vpcispanels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class ModifyActorPanel extends TemplatePanel {

	JLabel idActorLabel;
	JList allActorsList;
	JLabel qualityLabel;
	JList qualityList;
	JLabel nameActorLabel;
	JTextField nameActorTextField;
	JLabel idTeamLabel;
	JList allTeamsList;
	JLabel idAbstractionLevelLabel;
	JTextField idAbstractionLevelTextField;	
	
	
	public ModifyActorPanel() {
		//title
		super("Modify an Actor");
		
		//hide useless buttons
		okButton.hide();
		backButton.hide();
		addButton.hide();
		
		//show msg
		showSystemMessage("Choose an id Actor to Modify");
		
		//configuration of the centerPanel
		centerPanel.setLayout(new FlowLayout());
		
		idActorLabel = new JLabel("id Actor :");
		allActorsList = new JList();
		qualityLabel = new JLabel("Quality :");
		String[] qualities = {"Simple member","Leader of a team","Responsible of a CIS"}; 
		qualityList = new JList(qualities);
		nameActorLabel = new JLabel("Name Actor :");
		nameActorTextField = new JTextField();
		nameActorTextField.setColumns(20);
		idTeamLabel = new JLabel("Id Team :");
		allTeamsList = new JList();
		idAbstractionLevelLabel = new JLabel("Id Abstraction Level Related :");
		idAbstractionLevelTextField = new JTextField();
		idAbstractionLevelTextField.setColumns(20);
		idAbstractionLevelTextField.setEditable(false);
		
		//fill the JList allActorsList
		
		
		//fill the JList allTeamsList
		
		
		centerPanel.add(idActorLabel);
		centerPanel.add(allActorsList);
		centerPanel.add(qualityLabel);
		centerPanel.add(qualityList);
		centerPanel.add(nameActorLabel);
		centerPanel.add(nameActorTextField);
		centerPanel.add(idTeamLabel);
		centerPanel.add(allTeamsList);
		centerPanel.add(idAbstractionLevelLabel);
		centerPanel.add(idAbstractionLevelTextField);
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// do something
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// do something
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// do something
			}
		});
		
	}

}
