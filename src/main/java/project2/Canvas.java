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
	static JButton btnClear,btnUndo,btnRedo,btnSelect,btnEraseO;
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
		
		
		btnEraseO= new JButton("Erase");
		btnEraseO.setBounds(0, 0, 40, 40);
		btnEraseO.setText("Erase");
		
		
		add(btnClear);
		add(btnUndo);
		add(btnRedo);
		add(btnSelect);
		add(btnEraseO);
		
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
					////System.out.println("UNDO");

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
					////System.out.println("REDO");
				}
			}	
		});
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnSelect) {
					Frame.MODE="SELECT";
					//////System.out.println("SELECT");
				}
			}	
		});
		btnEraseO.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnEraseO) {
					Frame.MODE="ERASE";
					//////System.out.println("SELECT");
				}
				for(int i=Frame.list.size()-1;i>=0;i--) {
					if(Frame.list.get(i).select==true){
						Frame.list2.add(Frame.list.get(i));
						Frame.list.remove(i);
						
					}

					repaint();
					
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
		if(Frame.selected==true) {
			for(int i=Frame.list.size()-1;i>=0;i--) {
				if(Frame.list.get(i).select==true) {
					Frame.e1=e.getPoint();
					if(!Frame.list.get(i).shape.equals("PEN")) {
						Frame.list.get(i).start.x+=(Frame.e1.x-Frame.s1.x);
						Frame.list.get(i).start.y+=(Frame.e1.y-Frame.s1.y);
						Frame.list.get(i).end.x+=(Frame.e1.x-Frame.s1.x);
						Frame.list.get(i).end.y+=(Frame.e1.y-Frame.s1.y);
					}else {
						for(int j=0;j<Frame.list.get(i).P.size();j++){
							Frame.list.get(i).P.get(j).x+=(Frame.e1.x-Frame.s1.x);
							Frame.list.get(i).P.get(j).y+=(Frame.e1.y-Frame.s1.y);
							
							
						}
							
					}
					repaint();
					
				}
			}
			Frame.s1=Frame.e1;
		}
		else if (Frame.newOption.shape.equals("SELECT")) {
			Frame.tempE = e.getPoint();
			//repaint();
			//////System.out.println("end"+Frame.end);
			Shape sel = new Rectangle2D.Double(Math.min(Frame.tempS.x,Frame.tempE.x), Math.min(Frame.tempS.y,Frame.tempE.y),
					Math.abs(Frame.tempE.x - Frame.tempS.x), Math.abs(Frame.tempE.y - Frame.tempS.y));
			for(int i=Frame.list.size()-1;i>=0;i--) {
				if(selection(Frame.list.get(i),sel)) {
					Frame.list.get(i).select=true;
					System.out.println("sel");
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
		
			Shape sel = new Rectangle2D.Double(Math.min(Frame.tempS.x,Frame.tempE.x), Math.min(Frame.tempS.y,Frame.tempE.y),
					Math.abs(Frame.tempE.x - Frame.tempS.x), Math.abs(Frame.tempE.y - Frame.tempS.y));
			for(int i=Frame.list.size()-1;i>=0;i--) {
				if(selection(Frame.list.get(i),sel)) {
					Frame.list.get(i).select=true;
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
		//System.out.println("Mode: "+Frame.MODE+"Selected: "+Frame.selected);
		if (Frame.MODE.equals("SELECT")) {
			if(Frame.selected==false) {
				//System.out.println("Inside");
				Frame.sel=true;
				//System.out.println("sel: ");
				boolean s=false;
				////System.out.println(Frame.start);
				//Shape rectShape = new Rectangle2D.Double();
				for(int i=Frame.list.size()-1;i>=0;i--) {
					Point p;
					p=e.getPoint();
					Frame.e1=e.getPoint();
					Frame.s1=e.getPoint();
					Shape sel= new Rectangle2D.Double(Math.min(Frame.list.get(i).start.x,Frame.list.get(i).end.x), Math.min(Frame.list.get(i).start.y,Frame.list.get(i).end.y),
					Math.abs(Frame.list.get(i).end.x - Frame.list.get(i).start.x), Math.abs(Frame.list.get(i).end.x - Frame.list.get(i).start.x));
				
					if(sel.contains(p)) {
						System.out.println("Contains");
						s=true;
					}
//					if(selection(Frame.list.get(i),sel)){
//						s=true;
//						//System.out.println("error");
//					}
				}
				if(s==true) {
					System.out.println("inside s==true");
					Frame.selected=true;
					Frame.sel = false;
				}
			}
			//Frame.newOption.shape = "RESIZE";
			
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
			Frame.newOption.start=Frame.pointsP.get(0);
			Frame.newOption.end=Frame.pointsP.get(Frame.pointsP.size()-1);
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
		if(Frame.selected==true) {
			Frame.selected=false;
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
		//System.out.println(Frame.sel);
		if(Frame.sel) {
			////System.out.printf("%d %d %d %d\n", Frame.tempS.x, Frame.tempS.y, Frame.tempE.x, Frame.tempE.y);
			System.out.println("selector box");
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
				if(l.select==true) {
					g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER,1f,new float[] {10},2f));
					g2.setColor(Color.red);
					g2.drawRect(Math.min(l.start.x, l.end.x), Math.min(l.start.y, l.end.y),
							Math.abs(l.end.x - l.start.x), Math.abs(l.end.y - l.start.y));
				}
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
				if(l.select==true) {
					g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER,1f,new float[] {10},2f));
					g2.setColor(Color.red);
					for (int i = 0; i < Frame.pointsP.size() - 1; i++) {
						g2.drawLine(Frame.pointsP.get(i).x, Frame.pointsP.get(i).y, Frame.pointsP.get(i + 1).x,
								Frame.pointsP.get(i + 1).y);
					}
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
		}else if(temp.shape.equals("LINE")) {	
			temp.shapeO=new Rectangle2D.Double(Math.min(temp.start.x,temp.end.x), Math.min(temp.start.y,temp.end.y),
					Math.abs(temp.end.x -temp.start.x), Math.abs(temp.end.y - temp.start.y));
		}else if(temp.shape.equals("PEN")) {	
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
