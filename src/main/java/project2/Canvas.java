package project2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{
	
	private boolean isDrawing = false;
	
	public Canvas(){
		setBackground(Color.white);
		setBounds(0,100,700,600);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Frame.MODE.equals("LINE")) {
			Frame.end=e.getPoint();
			repaint();
		}
		if(Frame.MODE.equals("PEN")) {
			Frame.start=e.getPoint();
			Frame.end=e.getPoint();
			Frame.pointsP.add(Frame.start);
			Frame.pointsP.add(Frame.end);
			repaint();
		}
		if(Frame.MODE.equals("RECT")) {
			Frame.end=e.getPoint();
			repaint();
		}
		if(Frame.MODE.equals("OVAL")) {
			Frame.end=e.getPoint();
			repaint();
		}
		
			
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Frame.MODE.equals("POLYLINE")) {	
			Frame.end = e.getPoint();
	        repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Frame.lineWeight=Integer.parseInt(Panel1.tThick.getText());
		
		if(Frame.MODE.equals("LINE")) {
			Frame.start=e.getPoint();
		}
		if(Frame.MODE.equals("POLYLINE")) {
			if(!isDrawing) {
				Frame.start = e.getPoint();
	            isDrawing = !isDrawing;
	            
	        }else {
	            Frame.pointsPL.add(Frame.start);
	            Frame.pointsPL.add(Frame.end);
	            Frame.start = Frame.end;
	        }
		}
		if(Frame.MODE.equals("RECT")) {
			Frame.start=e.getPoint();
		}
		if(Frame.MODE.equals("OVAL")) {
			Frame.start=e.getPoint();
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Frame.MODE.equals("LINE")) {	
			Frame.pointsL.add(Frame.start);
			Frame.pointsL.add(Frame.end);
			Frame.start = null;
			Frame.end   = null;
		}
		
		if(Frame.MODE.equals("POLYLINE")) {	
			if (e.getClickCount() == 2) {
				Frame.start = null;
				Frame.end = null;
	            isDrawing = false;
	        }
		}
		if(Frame.MODE.equals("RECT")) {	
			Frame.pointsR.add(Frame.start);
			Frame.pointsR.add(Frame.end);
			Frame.start = null;
			Frame.end   = null;
		}
		if(Frame.MODE.equals("OVAL")) {	
			Frame.pointsO.add(Frame.start);
			Frame.pointsO.add(Frame.end);
			Frame.start = null;
			Frame.end   = null;
		}
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
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(Frame.lineWeight));
        if(Frame.MODE.equals("LINE")) {
        	if(Frame.start!=null){
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                for (int i = 0; i < Frame.pointsPL.size(); i+=2) {
                    g2.drawLine(Frame.pointsPL.get(i).x, Frame.pointsPL.get(i).y, Frame.pointsPL.get(i+1).x, Frame.pointsPL.get(i+1).y);
                }
                
                for (int i = 0; i < Frame.pointsL.size(); i+=2) {
                    g2.drawLine(Frame.pointsL.get(i).x, Frame.pointsL.get(i).y, Frame.pointsL.get(i+1).x, Frame.pointsL.get(i+1).y);
                }

                //Draw the current line if there is one
                if(Frame.start != null && Frame.end != null){
                    g2.drawLine(Frame.start.x, Frame.start.y, Frame.end.x, Frame.end.y);
                }
            }
        	
        }
        if(Frame.MODE.equals("POLYLINE")) {
        	if(Frame.start!=null) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                for (int i = 0; i < Frame.pointsL.size(); i+=2) {
                    g2.drawLine(Frame.pointsL.get(i).x, Frame.pointsL.get(i).y, Frame.pointsL.get(i+1).x, Frame.pointsL.get(i+1).y);
                }
                
                for (int i = 0; i < Frame.pointsPL.size()-1; i+=2) {
                    g2.drawLine(Frame.pointsPL.get(i).x, Frame.pointsPL.get(i).y, Frame.pointsPL.get(i+1).x, Frame.pointsPL.get(i+1).y);
                }

                //Draw the current line if there is one
                if(Frame.start != null){
                    g2.drawLine(Frame.start.x, Frame.start.y, Frame.end.x, Frame.end.y);
                }
            }
        	
        }
        if(Frame.MODE.equals("PEN")) {
        	if(Frame.start!=null) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                for (int i = 0; i < Frame.pointsL.size(); i+=2) {
                    g2.drawLine(Frame.pointsL.get(i).x, Frame.pointsL.get(i).y, Frame.pointsL.get(i+1).x, Frame.pointsL.get(i+1).y);
                }
                for (int i = 0; i < Frame.pointsPL.size(); i+=2) {
                    g2.drawLine(Frame.pointsPL.get(i).x, Frame.pointsPL.get(i).y, Frame.pointsPL.get(i+1).x, Frame.pointsPL.get(i+1).y);
                }
                for (int i = 0; i < Frame.pointsP.size(); i++) {
                    g2.drawLine(Frame.pointsP.get(i).x, Frame.pointsP.get(i).y, Frame.pointsP.get(i).x, Frame.pointsP.get(i).y);
                }
                if(Frame.start != null){
                    g2.drawLine(Frame.start.x, Frame.start.y, Frame.end.x, Frame.end.y);
                }
            }
        }
        if(Frame.MODE.equals("RECT")) {
        	if(Frame.start!=null) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                for (int i = 0; i < Frame.pointsL.size(); i+=2) {
                    g2.drawLine(Frame.pointsL.get(i).x, Frame.pointsL.get(i).y, Frame.pointsL.get(i+1).x, Frame.pointsL.get(i+1).y);
                }
                for (int i = 0; i < Frame.pointsPL.size(); i+=2) {
                    g2.drawLine(Frame.pointsPL.get(i).x, Frame.pointsPL.get(i).y, Frame.pointsPL.get(i+1).x, Frame.pointsPL.get(i+1).y);
                }
                for (int i = 0; i < Frame.pointsP.size(); i++) {
                    g2.drawLine(Frame.pointsP.get(i).x, Frame.pointsP.get(i).y, Frame.pointsP.get(i).x, Frame.pointsP.get(i).y);
                }
                for (int i = 0; i < Frame.pointsR.size(); i+=2) {
                	//int distance = (int)Math.sqrt((Frame.pointsR.get(i+1).y - Frame.pointsR.get(i).y) * (Frame.pointsR.get(i+1).y - Frame.pointsR.get(i).y) + (Frame.pointsR.get(i+1).x - Frame.pointsR.get(i).x) * (Frame.pointsR.get(i+1).x - Frame.pointsR.get(i).x));
                    g2.drawRect(Frame.pointsR.get(i).x, Frame.pointsR.get(i).y, Math.abs(Frame.pointsR.get(i+1).x - Frame.pointsR.get(i).x),Math.abs(Frame.pointsR.get(i+1).y - Frame.pointsR.get(i).y));
                }
                if(Frame.start != null){
                    g2.drawRect(Frame.start.x, Frame.start.y,Math.abs(Frame.end.x-Frame.start.x), Math.abs(Frame.end.y-Frame.start.y));
                }
            }
        }
        
        if(Frame.MODE.equals("OVAL")) {
        	if(Frame.start!=null) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                for (int i = 0; i < Frame.pointsL.size(); i+=2) {
                    g2.drawLine(Frame.pointsL.get(i).x, Frame.pointsL.get(i).y, Frame.pointsL.get(i+1).x, Frame.pointsL.get(i+1).y);
                }
                for (int i = 0; i < Frame.pointsPL.size(); i+=2) {
                    g2.drawLine(Frame.pointsPL.get(i).x, Frame.pointsPL.get(i).y, Frame.pointsPL.get(i+1).x, Frame.pointsPL.get(i+1).y);
                }
                for (int i = 0; i < Frame.pointsP.size(); i++) {
                    g2.drawLine(Frame.pointsP.get(i).x, Frame.pointsP.get(i).y, Frame.pointsP.get(i).x, Frame.pointsP.get(i).y);
                }
                for (int i = 0; i < Frame.pointsR.size(); i+=2) {
                	//int distance = (int)Math.sqrt((Frame.pointsR.get(i+1).y - Frame.pointsR.get(i).y) * (Frame.pointsR.get(i+1).y - Frame.pointsR.get(i).y) + (Frame.pointsR.get(i+1).x - Frame.pointsR.get(i).x) * (Frame.pointsR.get(i+1).x - Frame.pointsR.get(i).x));
                    g2.drawRect(Frame.pointsR.get(i).x, Frame.pointsR.get(i).y, Math.abs(Frame.pointsR.get(i+1).x - Frame.pointsR.get(i).x),Math.abs(Frame.pointsR.get(i+1).y - Frame.pointsR.get(i).y));
                }
                for (int i = 0; i < Frame.pointsO.size(); i+=2) {
                	//int distance = (int)Math.sqrt((Frame.pointsR.get(i+1).y - Frame.pointsR.get(i).y) * (Frame.pointsR.get(i+1).y - Frame.pointsR.get(i).y) + (Frame.pointsR.get(i+1).x - Frame.pointsR.get(i).x) * (Frame.pointsR.get(i+1).x - Frame.pointsR.get(i).x));
                    g2.drawOval(Frame.pointsO.get(i).x, Frame.pointsO.get(i).y, Math.abs(Frame.pointsO.get(i+1).x - Frame.pointsO.get(i).x),Math.abs(Frame.pointsO.get(i+1).y - Frame.pointsO.get(i).y));
                }
                if(Frame.start != null){
                    g2.drawOval(Frame.start.x, Frame.start.y,Math.abs(Frame.end.x-Frame.start.x), Math.abs(Frame.end.y-Frame.start.y));
                }
            }
        }

    }
	
	
	
}
