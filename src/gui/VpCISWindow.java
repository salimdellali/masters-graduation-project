package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VpCISWindow extends JFrame{

	public static PanelShortcutPath psp;
	public static PanelVpCIS pVpCIS;
	public static PanelSystemMessages psm;
	
	public void hideThisFrame(){
		this.setVisible(false);
	}
	
	public VpCISWindow (){
		
		//configuration of the frame
		setLocation(250,250);
		setSize(Launcher.width + 20, Launcher.height + 60);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("VpCIS WorkSpace");
		setBounds(0, 0, Launcher.width + 20, Launcher.height + 60);
		//pack();
		setVisible(true);
		
		//setting up the MenuBar
		JMenuBar jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		JMenu menu1 = new JMenu("Account");
		jmb.add(menu1);
		
		JMenuItem menu1_1 = new JMenuItem("Log out");
		menu1_1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					//empty
					JOptionPane.showMessageDialog(null, "Logged out");
					AuthentificationWindow aw = new AuthentificationWindow();
					aw.setVisible(true);
					hideThisFrame();
			}
		});
		menu1.add(menu1_1);
		
		JMenuItem menu1_2 = new JMenuItem("Menu 1_2");
		menu1_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				//empty
			}
		});
		menu1.add(menu1_2);
		
		//configuring the JPanels
		JPanel contentPane = new JPanel();
		this.getContentPane().add(contentPane);
		
		//the top of the contentPane will be having shortcuts and the path
		//the center of the contentPane will have the VpCIS Panels
		//the bottom of contentPane for system messages
		
		contentPane.setLayout(new BorderLayout());
		
		psp = new PanelShortcutPath();
		contentPane.add(psp, BorderLayout.NORTH);
		
		pVpCIS = new PanelVpCIS();
		contentPane.add(pVpCIS,BorderLayout.CENTER);
		
		psm = new PanelSystemMessages();
		contentPane.add(psm,BorderLayout.SOUTH);
		/*
		JButton n = new JButton("Top");
		JButton e = new JButton("Right");
		JButton s = new JButton("Bottom");
		JButton w = new JButton("Left");
		JButton c = new JButton("Center"); 
		
		
		contentPane.add(n, BorderLayout.NORTH);
		contentPane.add(e, BorderLayout.EAST);
		contentPane.add(s, BorderLayout.SOUTH);
		contentPane.add(w, BorderLayout.WEST);
		contentPane.add(c, BorderLayout.CENTER);
		*/
	}
}
