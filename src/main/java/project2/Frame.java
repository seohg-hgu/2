package project2;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame {
	static JFrame f;
	static Panel1 p1;
	static Canvas can;
	static Point start,end;
	static Graphics graphics;
	static Graphics2D g;
	
	static String MODE = new String("empty");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		f=new Frame();
		
		p1= new Panel1();
		can= new Canvas();
		Container contentPane = f.getContentPane();
		contentPane.setLayout(null);
		contentPane.add(p1);
		contentPane.add(can);
		createFrame(f);
		
		//graphics= getGraphics();
		//g=(Graphics2D)graphics;
		/*
		can.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				start=e.getPoint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		});
		can.addMouseMotionListener(new PaintDraw());
		
				*/
		
		
		
	}
	public static void createFrame(JFrame f) {
		f.setSize(700,700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);	
	}

}
/*
public class PaintDraw implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Frame.end=e.getPoint();
		Frame.g.drawLine(100,100,400,400);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}*/



		
		
		
