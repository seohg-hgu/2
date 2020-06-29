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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame {
	static JFrame f;
	static Panel1 p1;
	static Canvas can;
	
	static Point start,end;
	static Graphics graphics;
	static Graphics2D g;
	static int x, y;

	static Point tempS,tempE;
	static Option newOption;
	static Option o;
	
	static Vector<Option> pointsL = new Vector<Option>();
	static Vector<Option> pointsR = new Vector<Option>();
	static Vector<Option> pointsO = new Vector<Option>();

	static ArrayList<Option> list = new ArrayList<Option>();
	static Vector<Option> list2 = new Vector<Option>();
	static ArrayList<Option> copy = new ArrayList<Option>();
	
	static Option re = new Option();
	
	static Vector<Point> pointsPL = new Vector<Point>();
	static Vector<Point> pointsP = new Vector<Point>();
	static Vector<Point> pointsE = new Vector<Point>();

	
	static boolean sel;
	static Option cShape;
	static String MODE;
	static Color lineColor=Color.BLACK;
	static int lineWeight=1;
	static boolean oFill=false;
	static boolean clear=false;
	
	static boolean selected=false;
	static Point s1, e1;
	
	
	
	public Frame(){
		MODE= new String("empty");
		x=y=0;
		

	}

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



		
		
		
