package project2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
	static JButton btnClear,btnUndo,btnRedo,btnSelect;
	private boolean isDrawing = false;
	Dimension preferredSize= new Dimension(400,300);
	

    private Point pos=null;
    boolean resize=false;
    Rectangle rect;
	public Canvas() {
		setBackground(Color.white);
		setBounds(0, 50, 700, 650);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		
		btnClear= new JButton("Clear");
		btnClear.setBounds(0, 0, 40, 40);
		btnClear.setText("Clear");
		
		btnUndo= new JButton("Undo");
		btnUndo.setBounds(0, 0, 40, 40);
		btnUndo.setText("Undo");
		
		btnRedo= new JButton("Redo");
		btnRedo.setBounds(0, 0, 40, 40);
		btnRedo.setText("Redo");
		
		btnSelect= new JButton("Select");
		btnSelect.setBounds(0, 0, 40, 40);
		btnSelect.setText("Select");
		
		add(btnClear);
		add(btnUndo);
		add(btnRedo);
		add(btnSelect);
		
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnClear) {
					Frame.list.clear();	
					Frame.list2.clear();
					repaint();
				}
			}
				
		});
		
		btnUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnUndo) {
					Frame.list2.add(Frame.list.get(Frame.list.size()-1));
					Frame.list.remove(Frame.list.size()-1);
					repaint();
					System.out.println("UNDO");

				}
			}	
		});
		btnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnRedo) {
					if(Frame.list2.size()!=0) {
						Frame.list.add(Frame.list2.get(Frame.list2.size()-1));
						Frame.list2.remove(Frame.list2.size()-1);
					}
					repaint();
					System.out.println("REDO");
				}
			}	
		});
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnSelect) {
					Frame.MODE="SELECT";
					System.out.println("SELECT");
				}
			}	
		});
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {

		// TODO Auto-generated method stub

		if (Frame.newOption.shape.equals("LINE")) {
			Frame.list.get(Frame.list.size() - 1).end = e.getPoint();
			repaint();
		}
		if (Frame.MODE.equals("PEN")) {
			Frame.start = e.getPoint();
			Frame.end = e.getPoint();
			Frame.pointsP.add(Frame.end);
			repaint();
		}
		if (Frame.MODE.equals("ERASER")) {
			Frame.start = e.getPoint();
			Frame.end = e.getPoint();
			Frame.pointsE.add(Frame.end);
			repaint();
		}

		if (Frame.newOption.shape.equals("RECT")) {
			Frame.list.get(Frame.list.size() - 1).end = e.getPoint();
			repaint();
		}

		if (Frame.newOption.shape.equals("OVAL")) {
			Frame.list.get(Frame.list.size() - 1).end = e.getPoint();
			repaint();
		}
		
		if (Frame.newOption.shape.equals("SELECT")) {
			Frame.tempE = e.getPoint();
			repaint();
			//System.out.println("end"+Frame.end);
			Shape sel = new Rectangle2D.Double(Math.min(Frame.tempS.x,Frame.tempE.x), Math.min(Frame.tempS.y,Frame.tempE.y),
					Math.abs(Frame.tempE.x - Frame.tempS.x), Math.abs(Frame.tempE.y - Frame.tempS.y));
			for(int i=Frame.list.size()-1;i>=0;i--) {
				if(selection(Frame.list.get(i),sel)) {
					Frame.list.get(i).select=true;
					System.out.println(i);
					//Frame.sel2=true;
				}else {
					Frame.list.get(i).select=false;
				}
			}
			repaint();
		}
	}
	

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		/*
		if (Frame.newOption.shape.equals("POLYLINE")) {
			// Frame.pointsPL.add(e.getPoint());
			Frame.end = e.getPoint();
			repaint();
		}*/

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (Frame.MODE.equals("SELECT")) {
			Frame.tempE = e.getPoint();
			//repaint();
			System.out.println("end"+Frame.tempE);
			Shape sel = new Rectangle2D.Double(Math.min(Frame.tempS.x,Frame.tempE.x), Math.min(Frame.tempS.y,Frame.tempE.y),
					Math.abs(Frame.tempE.x - Frame.tempS.x), Math.abs(Frame.tempE.y - Frame.tempS.y));
			for(int i=Frame.list.size()-1;i>=0;i--) {
				if(selection(Frame.list.get(i),sel)) {
					System.out.println(i);
					Frame.list.get(i).select=true;
					//Frame.sel2=true;
				}else {
					Frame.list.get(i).select=false;
				}
			}
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Frame.tempS=e.getPoint();
		if (!isDrawing) {
			Frame.newOption = new Option();
			Frame.newOption.thick = Frame.lineWeight;
			Frame.newOption.color = new Color(Frame.lineColor.getRGB());  
			Frame.newOption.fill = Frame.oFill;
			Frame.newOption.select=Frame.sel;
		}

		if (Frame.MODE.equals("LINE")) {
			Frame.newOption.shape = "LINE";
		}
		if (Frame.MODE.equals("RECT")) {
			Frame.newOption.shape = "RECT";
		}
		if (Frame.MODE.equals("OVAL")) {
			Frame.newOption.shape = "OVAL";
		}
		if (Frame.MODE.equals("POLYLINE")) {
			Frame.newOption.shape = "POLYLINE";
		}
		if (Frame.MODE.equals("PEN")) {
			Frame.newOption.shape = "PEN";
		}

		if (Frame.MODE.equals("ERASER")) {
			Frame.newOption.shape = "ERASER";
		}
		if (Frame.MODE.equals("SELECT")) {
			//Frame.newOption.shape = "RESIZE";
			Frame.sel=true;
			Frame.tempS=e.getPoint();
			System.out.println(Frame.start);
		}
		
		
		if (Frame.newOption.shape.equals("LINE")) {
			Frame.newOption.start = e.getPoint();
			Frame.list.add(Frame.newOption);
		}
/*
		if (Frame.newOption.shape.equals("POLYLINE")) {
			if (!isDrawing) {
				// Frame.newOption.start=e.getPoint();
				Frame.list.add(Frame.newOption);
				// start point
				Frame.start = e.getPoint();
				// pressed값 저장
				Frame.pointsPL.add(Frame.start);
				isDrawing = !isDrawing;

			} else {
				// Frame.cShape.start=Frame.start;
				// Frame.cShape.end=Frame.end;
				// Frame.pointsPL.add(Frame.cShape);
				// Frame.pointsPL.add(Frame.start);
				// Frame.pointsPL.add(Frame.end);

				// Frame.newOption.start=Frame.list.get(Frame.list.size()-1).start;
				// Frame.newOption.end=Frame.list.get(Frame.list.size()-1).end;
				// Frame.list.get(Frame.list.size()-1).start =
				// Frame.list.get(Frame.list.size()-1).start;

				Frame.pointsPL.add(Frame.end);
				Frame.pointsPL.add(Frame.start);
				Frame.start = Frame.end;
			}
		}
*/
		if (Frame.newOption.shape.equals("RECT")) {
			Frame.newOption.start = e.getPoint();
			Frame.list.add(Frame.newOption);
		}

		if (Frame.newOption.shape.equals("OVAL")) {
			Frame.newOption.start = e.getPoint();
			Frame.list.add(Frame.newOption);
		}
		if (Frame.newOption.shape.equals("PEN")) {
			Frame.list.add(Frame.newOption);
		}
		if (Frame.newOption.shape.equals("ERASER")) {
			Frame.list.add(Frame.newOption);
			
		}
		
		
		/*if (Frame.newOption.shape.equals("SELECT")) {
			Frame.start = e.getPoint();
			
			
		}*/
		
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		if (Frame.newOption.shape.equals("LINE")) {
			Frame.start = null;
			Frame.end = null;
		}
/*
		if (Frame.newOption.shape.equals("POLYLINE")) {
			if (e.getClickCount() == 2) {
				// Frame.start = null;
				// Frame.end = null;
				isDrawing = false;

				Frame.pointsPL.add(Frame.end);

				Frame.start = null;
				Frame.end = null;

				int i = 0;
				for (Point p : Frame.pointsPL) {
					Frame.newOption.pointX[i] = (int) p.getX();
					Frame.newOption.pointY[i] = (int) p.getY();
					i++;
				}
				Frame.pointsPL.clear();
			}
	
*/
		if (Frame.newOption.shape.equals("RECT")) {
			Frame.start = null;
			Frame.end = null;
			//Frame.newOption.select=Frame.sel;
		}

		if (Frame.newOption.shape.equals("OVAL")) {
			Frame.start = null;
			Frame.end = null;
		}
		if (Frame.newOption.shape.equals("PEN")) {
			Frame.start = null;
			Frame.end = null;

			for (Point p : Frame.pointsP) {
				Frame.newOption.P.add(p);
			}
			Frame.pointsP.clear();

		}
		if (Frame.newOption.shape.equals("ERASER")) {
			Frame.start = null;
			Frame.end = null;

			for (Point el : Frame.pointsE) {
				Frame.newOption.E.add(el);
			}
			
			Frame.pointsE.clear();

		}
		if (Frame.newOption.shape.equals("SELECT")) {
			//Frame.tempS=null;
			//Frame.tempE=null;
			Frame.sel=false;
			repaint();
		}
		 
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		setCursor(Cursor.getDefaultCursor());
	}

    

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(Frame.sel) {
			g2.setColor(Color.BLUE);
			g2.drawRect(Math.min(Frame.tempS.x, Frame.tempE.x), Math.min(Frame.tempS.y, Frame.tempE.y),
								Math.abs(Frame.tempE.x - Frame.tempS.x), Math.abs(Frame.tempE.y - Frame.tempS.y));
		}
		
			
		for (Option l : Frame.list) {
			if (l.shape.equals("LINE")) {
				g2.setStroke(new BasicStroke(l.thick));
				g2.setColor(l.color);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.drawLine(l.start.x, l.start.y, l.end.x, l.end.y);
			}
			if (l.shape.equals("POLYLINE")) {
				
				g2.setStroke(new BasicStroke(l.thick));
				g2.setColor(l.color);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				if (isDrawing) {
					for (int i = 0; i < Frame.pointsPL.size() - 1; i++) {
						g2.drawLine(Frame.pointsPL.get(i).x, Frame.pointsPL.get(i).y, Frame.pointsPL.get(i + 1).x,
								Frame.pointsPL.get(i + 1).y);
					}
					if (Frame.start != null) {
						g2.drawLine(Frame.start.x, Frame.start.y, Frame.end.x, Frame.end.y);
					}
				}
				g2.drawPolyline(l.pointX, l.pointY, l.pointX.length - 1);

			}
			if (l.shape.equals("RECT")) {
				g2.setStroke(new BasicStroke(l.thick));
				g2.setColor(l.color);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				if (l.fill == false) {
					g2.drawRect(Math.min(l.start.x, l.end.x), Math.min(l.start.y, l.end.y),
							Math.abs(l.end.x - l.start.x), Math.abs(l.end.y - l.start.y));
					
					
				} else {			
					g2.fillRect(Math.min(l.start.x, l.end.x), Math.min(l.start.y, l.end.y),
							Math.abs(l.end.x - l.start.x), Math.abs(l.end.y - l.start.y));
				}
				if(l.select==true) {
					g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER,1f,new float[] {10},2f));
					g2.setColor(Color.red);
					g2.drawRect(Math.min(l.start.x, l.end.x), Math.min(l.start.y, l.end.y),
							Math.abs(l.end.x - l.start.x), Math.abs(l.end.y - l.start.y));
				}

			}
			if (l.shape.equals("OVAL")) {
				
				g2.setStroke(new BasicStroke(l.thick));
				g2.setColor(l.color);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				if (l.fill == false) {
					g2.drawOval(Math.min(l.start.x, l.end.x), Math.min(l.start.y, l.end.y),
							Math.abs(l.end.x - l.start.x), Math.abs(l.end.y - l.start.y));
				} else {
					g2.fillOval(Math.min(l.start.x, l.end.x), Math.min(l.start.y, l.end.y),
							Math.abs(l.end.x - l.start.x), Math.abs(l.end.y - l.start.y));
				}
				if(l.select==true) {
					g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER,1f,new float[] {10},2f));
					g2.setColor(Color.red);
					g2.drawRect(Math.min(l.start.x, l.end.x), Math.min(l.start.y, l.end.y),
							Math.abs(l.end.x - l.start.x), Math.abs(l.end.y - l.start.y));
				}
			}
			if (l.shape.equals("PEN")) {
				
				g2.setStroke(new BasicStroke(l.thick));
				g2.setColor(l.color);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				for (int i = 0; i < l.P.size() - 1; i++) {
					g2.drawLine(l.P.get(i).x, l.P.get(i).y, l.P.get(i + 1).x, l.P.get(i + 1).y);
				}
				
				for (int i = 0; i < Frame.pointsP.size() - 1; i++) {
					g2.drawLine(Frame.pointsP.get(i).x, Frame.pointsP.get(i).y, Frame.pointsP.get(i + 1).x,
							Frame.pointsP.get(i + 1).y);
				}
				if (Frame.start != null) {
					g2.drawLine(Frame.start.x, Frame.start.y, Frame.end.x, Frame.end.y);
				}
			}
			if (l.shape.equals("ERASER")) {
				
				g2.setStroke(new BasicStroke(l.thick));
				g2.setColor(Color.white);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				for (int i = 0; i < l.E.size() - 1; i++) {
					g2.drawLine(l.E.get(i).x, l.E.get(i).y, l.E.get(i + 1).x, l.E.get(i + 1).y);
				}
				
				for (int i = 0; i < Frame.pointsE.size() - 1; i++) {
					g2.drawLine(Frame.pointsE.get(i).x, Frame.pointsE.get(i).y, Frame.pointsE.get(i + 1).x,
							Frame.pointsE.get(i + 1).y);
				}
				if (Frame.start != null) {
					g2.drawLine(Frame.start.x, Frame.start.y, Frame.end.x, Frame.end.y);
				}

			}
			
		}
		
	}
	public boolean selection(Option temp, Shape s) {
		if(temp.shape.equals("RECT")) {
			temp.shapeO=new Rectangle2D.Double(Math.min(temp.start.x,temp.end.x), Math.min(temp.start.y,temp.end.y),
					Math.abs(temp.end.x -temp.start.x), Math.abs(temp.end.y - temp.start.y));
		}else if(temp.shape.equals("OVAL")) {	
			temp.shapeO=new Rectangle2D.Double(Math.min(temp.start.x,temp.end.x), Math.min(temp.start.y,temp.end.y),
						Math.abs(temp.end.x -temp.start.x), Math.abs(temp.end.y - temp.start.y));
		}
		else {
			return false;
		}
		if(s.contains(temp.shapeO.getBounds2D())) {
			return true;
		}else {
			return false;
		}
	}
}
