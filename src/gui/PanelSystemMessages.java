package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class PanelSystemMessages extends JPanel {
	
	public static JTextArea systemMessages = new JTextArea();;

	public PanelSystemMessages(){
		setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
		setLayout(new BorderLayout());
		//systemMessages 
		add(systemMessages,BorderLayout.CENTER);
		systemMessages.setEditable(false);
		systemMessages.setLineWrap(true);
		systemMessages.setWrapStyleWord(true);
		systemMessages.setText("Welcome to VpCIS Frame Work, This section will show System Messages");
		
		//systemMessages.setColumns(10);
		//systemMessages.setRows(2);
		/*
		systemMessages = new JTextField();
		
		//systemMessages.setEditable(false);
		systemMessages.setText("This section will show System Messages");
		this.setLayout(null);
		add(systemMessages);
		*/
	}
}
