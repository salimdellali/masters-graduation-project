package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelShortcuts extends JPanel {

	public void hideThisFrame(){
		this.setVisible(false);
	}
	
	public PanelShortcuts(){
		setBorder(BorderFactory.createLineBorder(Color.YELLOW,1));
		
		JButton disconnectButton = new JButton("Diconnect");
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				JOptionPane.showMessageDialog(null, "Disconnected");
				AuthentificationWindow aw = new AuthentificationWindow();
				aw.setVisible(true);
				hideThisFrame();
				*/
			}
		});
		this.add(disconnectButton);
	}
}
