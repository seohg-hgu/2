package project2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JColorChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Panel1 extends JPanel {
	static JPanel p;
	static JButton btnLine,btnPen,btnPoly,btnRect,btnOval,btnColor,btnFill,btnEraser;
	
	static JLabel lThick;
	static JTextField tThick=null; 
	static JColorChooser colorChooser;

	
	public Panel1() {
		setBackground(Color.white);
		setBounds(0,0,700,50);
		btnLine= new JButton("Line");
		btnLine.setBounds(0, 0, 40, 40);
		btnLine.setText("Line");
		
		btnPen= new JButton("Pen");
		btnPen.setBounds(0, 0, 40, 40);
		btnPen.setText("Pen");
		
		btnPoly= new JButton("PolyLine");
		btnPoly.setBounds(0, 0, 40, 40);
		btnPoly.setText("PolyLine");
		
		btnRect= new JButton("Rect");
		btnRect.setBounds(0, 0, 40, 40);
		btnRect.setText("Rect");
		
		btnOval= new JButton("Oval");
		btnOval.setBounds(0, 0, 40, 40);
		btnOval.setText("Oval");
		
		btnEraser= new JButton("Eraser");
		btnEraser.setBounds(0, 0, 40, 40);
		btnEraser.setText("Eraser");
		
		
		lThick=new JLabel();
		lThick.setText("선 굵기");
		
		tThick= new JTextField("1");
		
		btnColor= new JButton(createIcon(Color.black, 20,20));
		btnColor.setBounds(0, 0, 40, 40);
		
		//btnColor.setBackground(colorChooser.getSelectionModel().getSelectedColor());
		
		colorChooser = new JColorChooser(Color.BLACK); // default color is black
		colorChooser.setBorder(null);
	
		btnFill= new JButton("Fill");
		btnFill.setBounds(0, 0, 40, 40);
		btnFill.setText("Fill");
		
		
		
		
		
		
		add(btnLine);
		add(btnPen);
		add(btnPoly);
		add(btnRect);
		add(btnOval);
		add(btnEraser);
		add(lThick);
		add(tThick);
		add(btnColor);
		add(btnFill);
		
		
	
		btnLine.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==btnLine) {
					Frame.MODE="LINE";
					System.out.println("LINE");
				}
			}	
		});
		
		btnPen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnPen) {
				Frame.MODE="PEN";
				System.out.println("PEN");
				}
			}	
		});
		
		btnPoly.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnPoly) {
				Frame.MODE="POLYLINE";
				System.out.println("POLYLINE");
				}
			}	
		});
		
		btnRect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnRect) {
				Frame.MODE="RECT";
				System.out.println("RECT");
				}
			}	
		});
		btnOval.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnOval) {
				Frame.MODE="OVAL";
				System.out.println("OVAL");
				}
			}	
		});
		
		tThick.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				Frame.lineWeight=Integer.parseInt(tThick.getText());	
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				Frame.lineWeight=1;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				//Frame.lineWeight=Integer.parseInt(tThick.getText());	
			}
			
		});
		btnColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				Color c = JColorChooser.showDialog(null, "Choose a Color",Color.black);
			    Frame.lineColor=c;
			    setSelectedColor(Frame.lineColor);
			    
			}	
		});
		
		btnFill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(Frame.oFill) {
					Frame.oFill=false;
					setSelectedColor(Frame.lineColor);
				}else {
					Frame.oFill=true;
					setSelectedColor(Frame.lineColor);
				}
			    
			}	
		});
		
		
		btnEraser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				if(e.getSource()==btnEraser) {
					Frame.MODE="ERASER";
					System.out.println("ERASER");
				}
			}	
		});
		
		
		
	
		
	}
	public static  ImageIcon createIcon(Color main, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        
        graphics.setStroke(new BasicStroke(5));
        if(Frame.oFill==false) {
        	graphics.setColor(Color.white);
        	graphics.fillRect(0, 0, width, height);
        }else {
        	graphics.setColor(main);
        	graphics.fillRect(0, 0, width, height);
        }
        graphics.setXORMode(main);
        graphics.drawRect(0, 0, width-1, height-1);
        image.flush();
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }
	public void setSelectedColor(Color newColor) {

        if (newColor == null) return;

        Frame.lineColor = newColor;
        btnColor.setIcon(createIcon(Frame.lineColor, 16, 16));
        repaint();

        
    }
	
	
}