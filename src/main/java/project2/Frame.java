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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame {
	static JFrame f;
	static Panel1 p1;
	static Canvas can;
	
	static Point start,end;
	static Graphics graphics;
	static Graphics2D g;
	
	
	static ArrayList<Point> pointsL = new ArrayList<Point>();
	static ArrayList<Point> pointsPL = new ArrayList<Point>();
	static ArrayList<Point> pointsP = new ArrayList<Point>();
	static ArrayList<Point> pointsR = new ArrayList<Point>();
	static ArrayList<Point> pointsO = new ArrayList<Point>();
	
	
	static String MODE = new String("empty");
	static Color lineColor=null;
	static int lineWeight=1;
	

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
		
	}
	public static void createFrame(JFrame f) {
		f.setSize(700,700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);	
	}

}



		
		
		
