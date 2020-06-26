package project2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{
	
	public Canvas(){
		setBackground(Color.white);
		setBounds(0,100,700,600);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
	

	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(Frame.MODE.equals("LINE")) {
        	//g.drawLine(start.x, start.y, end.x, end.y);;
        	g.drawLine(100,100,400, 400);
        }

    }
	
	
	
}
