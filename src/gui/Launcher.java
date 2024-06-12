package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JLabel;
import javax.swing.JTextField;

import databasemanager.AccessDataBaseConnector;

import javax.swing.JButton;

public class Launcher {

	public static final int width = 1200;
	public static final int height = 480;
	public static AccessDataBaseConnector adbc = new AccessDataBaseConnector();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// this is just to skip the autentification part
					adbc.connection();
					VpCISWindow vpcisw = new VpCISWindow();

					// AtomicInteger i = new AtomicInteger(0);
					/**
					 * addActionListener
					 * 
					this.getUpdateButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							// do something
						}
					});
					 */
					// System.out.println("thiiiiiiiiiiis is
					// i"+i.incrementAndGet());
					/*
					 * this is how to use the system message textArea globaly
					 * PanelSystemMessages.systemMessages.setText(" sdsd");
					 */
					/**
					 * to run the app from the beggining uncomment this 2 lines
					 * bellow
					 */

					// adbc.connection();
					// new AuthentificationWindow();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
