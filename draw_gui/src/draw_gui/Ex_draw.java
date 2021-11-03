package draw_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

class Rect01{
	private int x,y,width,height;
	
	public Rect01(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}

class ExPanel extends MyUtil{
	
	private Rect01 rect;
	private boolean check;
	private int gapX, gapY;
	private int gapW = 0;
	private int gapH = 0;
	
	
	public ExPanel() {
		setBounds(0,0,500,500);
		setBackground(Color.orange);
		
		addMouseMotionListener(this);
		addMouseListener(this);
//		addKeyListener(this);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		gapX = e.getX();
		gapY = e.getY();
		gapW = 0;
		gapH = 0;
		
		check = false;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		gapW = e.getX() - gapX;
		gapH = e.getY() - gapY;
		check = true;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		gapW = e.getX() - gapX;
		gapH = e.getY() - gapY;
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar());
		if(e.isShiftDown() == true) {
			gapW = gapX;
			gapH = gapY;
		}
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
		
		repaint();
	}
}

class ExdrawFrame extends JFrame{
	public ExdrawFrame() {
		super("draw");
		setLayout(null);
		setBounds(100,100,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new ExPanel());
		
		setVisible(true);
		revalidate();
	}
	
}

public class Ex_draw {

	public static void main(String[] args) {
		
		ExdrawFrame frame = new ExdrawFrame();
	}

}
