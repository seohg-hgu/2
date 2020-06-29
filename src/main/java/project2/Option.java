package project2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Option {
	boolean select;
	Shape shapeO;
	public String shape;
	public Point start, end;
	public int[] pointX, pointY;
	public Color color=Color.black;
	public int thick=5;
	public boolean fill;
	public Vector<Point> P;
	public Vector<Point> E;
	public Option() {
		color=new Color(0,0,0);
		fill=false;
		pointX=new int[1000];
		pointY=new int[1000];
		P= new Vector<Point>();
		E= new Vector<Point>();
		shape="SELECT";
		select=false;
	}
}




