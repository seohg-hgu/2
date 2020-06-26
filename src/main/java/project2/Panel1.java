package project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel1 extends JPanel {
	static JPanel p;
	static JButton btnLine;
	
	
	public Panel1() {
		setBackground(Color.black);
		setBounds(0,0,700,100);
		btnLine= new JButton("Line");
		btnLine.setBounds(5, 5, 40, 40);
		btnLine.setText("Line");
		
		add(btnLine);
		
	
	
		btnLine.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(e.getSource()==btnLine) {
					Frame.MODE="LINE";
					System.out.println("hello");
				}
			}	
		});
	}
}