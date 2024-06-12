package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelShortcutPath extends JPanel {

	public PanelShortcutPath(){
		setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
		setLayout(new BorderLayout());
		
		PanelShortcuts ps = new PanelShortcuts();
		PanelPath pp = new PanelPath();
		
		add(ps, BorderLayout.CENTER);
		add(pp, BorderLayout.SOUTH);
	}
}
