package draw_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Grimpan extends JFrame implements ActionListener{

	private Content con = new Content();
	
	
	public Grimpan() {
		super("±×¸²ÆÇ");
		setLayout(null);
		setBounds(100,100,900,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		con.close.addActionListener(this);
		add(con);
		
		setVisible(true);
		revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton) e.getSource() == con.close) {
			this.dispose();
		}
	}


}
