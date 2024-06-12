package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import databasemanager.AccessDataBaseConnector;
import javax.swing.JPasswordField;
import java.awt.Font;

public class AuthentificationWindow extends JFrame {

	public void hideThisFrame(){
		this.setVisible(false);
	}
	
	//static AccessDataBaseConnector adbc = new AccessDataBaseConnector();

	public AuthentificationWindow() {

		setLocation(250, 250);
		// setSize(Launcher.width , Launcher.height - 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Authentification to VpCIS");
		setBounds(50, 50, 313, 200);
		setVisible(true);

		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane);
		getContentPane().setLayout(null);

		JLabel labelUserName = new JLabel("Username :");
		labelUserName.setBounds(20, 22, 66, 14);
		
		getContentPane().add(labelUserName);

		JLabel labelPassword = new JLabel("Password :");
		labelPassword.setBounds(20, 47, 66, 14);
		getContentPane().add(labelPassword);

		JTextField textFieldUserName = new JTextField();
		textFieldUserName.setBounds(86, 19, 190, 20);
		getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);

		JPasswordField textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(86, 44, 190, 20);
		getContentPane().add(textFieldPassword);

		JButton buttonLogIn = new JButton("Log in");
		buttonLogIn.setMnemonic('n');
		buttonLogIn.setBounds(20, 72, 256, 28);
		getContentPane().add(buttonLogIn);
		
		JLabel labelVpCIS = new JLabel("VpCIS");
		labelVpCIS.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 34));
		labelVpCIS.setBounds(86, 111, 137, 35);
		
		
		getContentPane().add(labelVpCIS);
		
		// connection to the database
		/*
		try {
			adbc.connection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 */
		// button "log in" configuration when pressed
		buttonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName;
				String password;
				Boolean authentificated = false;
				userName = textFieldUserName.getText();
				password = textFieldPassword.getText();
				
				
				try {

					authentificated = Launcher.adbc.authentificate(userName, password);

				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if (authentificated) {
					// VpCISWindow Frame
					JOptionPane.showMessageDialog(null, "Authentificated");
					new VpCISWindow().setVisible(true);
					hideThisFrame();
				} else {
					// UserName / Password incorrect
					JOptionPane.showMessageDialog(null, "UserName / Password incorrect", "Warning",
							JOptionPane.ERROR_MESSAGE);

					// reset the textFields
					textFieldUserName.setText("");
					textFieldPassword.setText("");
				}

			}
		});

	}
}
