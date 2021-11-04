package draw_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Content extends MyUtil{
	
	public JButton close = new JButton("close");
	
	private Nemo nemo = null;

	private boolean check;
	private int gapX, gapY;
	private int gapXX, gapYY;
	private int gapW = 0;
	private int gapH = 0;
	private boolean renemo1, renemo2;

	public Content() {
		setLayout(null);
		setBounds(0,0,900,700);
		setBackground(Color.orange);
		
		
		setFocusable(true);
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		
		setButton();
	}

	private void setButton() {
		this.close.setBounds(750,580,100,50);
		this.close.addActionListener(this);
		add(this.close);
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		gapX = e.getX();
		gapY = e.getY();
		gapW = 0;
		gapH = 0;

		gapXX = e.getX();
		gapYY = e.getY();
		
		check = false;
		
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(renemo1) {
			this.gapW = this.gapXX - e.getX();
		}
		else if(renemo2) {
			this.gapH = this.gapYY - e.getY();
		}
		else {
			gapW = e.getX() - gapX;
			gapH = e.getY() - gapY;
			
		}
		
		check = true;
		
		// 왜 깜박일까?
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		// 역방향일때 
		if(e.getX() < this.gapX) {
			this.gapW = this.gapXX - e.getX();
			this.gapX = e.getX();
			renemo1 = true;
			
		}
		else if(e.getY() < this.gapY) {
			this.gapH = this.gapYY - e.getY();
			this.gapY = e.getY();
			renemo2 = true;
		}
		
		else {
			this.gapX = this.gapXX;
			this.gapY = this.gapYY;
			
			this.gapW = e.getX() - this.gapX;
			this.gapH = e.getY() - this.gapY;
			renemo1 = false;
			renemo2 = false;
		}

		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar());
		if(e.isShiftDown() == true) {
			if(this.gapW > this.gapH) {
				this.gapH  = this.gapW;
			}
			else {
				this.gapW = this.gapH;
			}
			
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
//	this.dispose(); // this : JFrame
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(!check) {
			g.setColor(Color.black);			
		}
		else {
			g.setColor(Color.blue);
		}
		
		g.drawRect(gapX, gapY, gapW, gapH);
//		g.fillRect(gapX, gapY, gapW, gapH);
		
		
		requestFocusInWindow();	// keyListener 에 대한 포커스 다시 요청
		repaint();
	}
}
