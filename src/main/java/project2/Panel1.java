package project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel1 extends JPanel {
	static JPanel p;
	static JButton btnLine,btnPen,btnPoly,btnRect,btnOval;
	static JLabel lThick;
	static JTextField tThick=null; 
	
	
	public Panel1() {
		setBackground(Color.gray);
		setBounds(0,0,700,100);
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
		
		lThick=new JLabel();
		lThick.setText("선 굵기");
		
		
		add(btnLine);
		add(btnPen);
		add(btnPoly);
		add(btnRect);
		add(btnOval);
		add(lThick);
		add(getThick());
	
	
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
		
		
	}
	
	private JTextField getThick() {
		if(tThick==null) {
			tThick =new JTextField("1");
			tThick.setSize(40,40);
		}
		return tThick;
	}
	
}