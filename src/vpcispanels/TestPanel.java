package vpcispanels;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TestPanel extends JPanel implements MouseListener, MouseMotionListener{

	int i=0;

	public TestPanel(){
		setLayout(new FlowLayout());
		JButton btn = new JButton("tiri bark");
		//btn.setBounds(100,100,100,100);
		add(btn);
		/*
		for(int i=0;i<100;i++){
			
			JButton jl =new JButton(Integer.toString(i));
			this.add(jl);
		}
		*/
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("you clicked !"+i);
		i++;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("you entered"+i);
		i++;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("you exited"+i);
		i++;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("you pressed"+i);
		i++;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("you released !"+i);
		i++;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("you dragged"+i);
		i++;
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("you moved"+i);
		i++;
	}
}
