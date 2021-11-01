package Ex_Push;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyUtil extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class Rect{
	private int x,y,width, height;

	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
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

class PushPanel extends MyUtil{
	
	private int dir; // 0정지 1왼 2하 3오 4상 
	private boolean check;
	private int xx;
	private int yy;
	
	// 사각형이 두개
	private final int SIZE = 100;
	private Rect r1 = null;
	private Rect r2 = null;
	
	
	// 방향키
	private JButton[] btn = new JButton[4];
	
	public PushPanel() {
		setLayout(null);
		setBounds(0,0,700,700);
//		setBackground(Color.lightGray);
		
		setRect();
		setBtn();
//		addMouseListener(this);
		
	}

	
	private void setBtn() {
		String[] text = {"←","↓","→","↑"};
		
		int size = 50;
		int x = 700 - 200;
		int y = 700 - 100;
		for(int i=0; i<this.btn.length; i++) {
			this.btn[i] = new JButton();
			this.btn[i].setBounds(x,y,size,size);
			this.btn[i].setText(text[i]);
			this.btn[i].addMouseListener(this);
			add(this.btn[i]);
			
			x += size;
			if(i == this.btn.length -1 -1) {
				x = 700-200 + size;
				y -= size;
			}
		}
	}

	private void setRect() {
		Random rn = new Random();
		
		int x = rn.nextInt(700-100);
		int y = rn.nextInt(700-100);
		
		this.r1 = new Rect(x,y,SIZE,SIZE);
		
		while(true) {
			x = rn.nextInt(700-100)+100;
			y = rn.nextInt(700-100)+100;
			
			// ?
			if((x >= this.r1.getX() && x < this.r1.getX() + SIZE && y >= this.r1.getY() && y < this.r1.getY() + SIZE)) continue;
			if((x+SIZE >= this.r1.getX() && x+SIZE < this.r1.getX() + SIZE && y >= this.r1.getY() && y < this.r1.getY() + SIZE)) continue;
			if((x >= this.r1.getX() && x < this.r1.getX() + SIZE && y+SIZE >= this.r1.getY() && y+SIZE < this.r1.getY() + SIZE)) continue;
			if((x+SIZE >= this.r1.getX() && x+SIZE < this.r1.getX() + SIZE && y+SIZE >= this.r1.getY() && y+SIZE < this.r1.getY() + SIZE)) continue;
					
			
			break;
		}
		this.r2 = new Rect(x,y,SIZE,SIZE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		update();
		
		// r1, r2
		g.setColor(Color.black);
		g.drawRect(this.r1.getX(), this.r1.getY(), this.r1.getWidth(), this.r1.getHeight());
		if(check)
			g.setColor(Color.red);
		else
			g.setColor(Color.blue);
		g.drawRect(this.r2.getX(), this.r2.getY(), this.r2.getWidth(), this.r2.getHeight());
		
		repaint();	
	}
	
	private void update() {
		xx = this.r1.getX();
		yy = this.r1.getY();
		
		
		// 왼
		if(this.dir == 1 && this.r1.getX() > 0) {
			xx--;
		}
		// 하
		else if(this.dir == 2 && this.r1.getY() + SIZE < 700)
			yy++;
		// 오
		else if(this.dir == 3 && this.r1.getX() + SIZE < 700)
			xx++;
		// 상
		else if(this.dir == 4 && this.r1.getY() > 0)
			yy--;
		
		checkAnother();
		
		// 좌
		if(this.dir == 1) {
			if(check && this.r2.getX() +SIZE > SIZE) {
				this.r2.setX(this.r2.getX() -1);
				this.r1.setX(xx);
			}
			else if(!check && xx > 0){
				this.r1.setX(xx);
			}
		}
		// 하
		else if(this.dir == 2) {
			if(check && this.r2.getY() +SIZE < 700) {
				this.r2.setY(this.r2.getY() +1);
			}
			else if(!check && yy < 700){
				this.r1.setY(yy);
			}
		}
		// 우
		else if(this.dir == 3) {
			if(check && this.r2.getX() +SIZE < 700 ) {
				this.r2.setX(this.r2.getX() + 1);
			}
			else if(!check && xx < 700){
				this.r1.setX(xx);
			}
		}
		// 상
		else if(this.dir == 4) {
			if(check && this.r2.getY()+SIZE > SIZE) {
				this.r2.setY(this.r2.getY() -1);
			}
			else if(!check && yy > 0){
				this.r1.setY(yy);
			}
			
		}
		
	}
	
	private void checkAnother() {
		check = false;
		if(this.dir == 1) {
			if(this.r2.getX() +SIZE == xx 
					&& this.r2.getY() >= this.r1.getY() - SIZE 
					&& this.r2.getY() < this.r1.getY() + SIZE)
				check = true;
		}
		if(this.dir == 2) {
			if(this.r2.getY() == yy + SIZE 
					&& this.r2.getX() >= this.r1.getX() -SIZE 
					&& this.r2.getX() < this.r1.getX() +SIZE)
				check = true;
		}
		if(this.dir == 3) {
			if(this.r2.getX() == xx + SIZE
					&& this.r2.getY() >= this.r2.getY() -SIZE 
					&& this.r2.getY() < this.r1.getY() +SIZE)
				check = true;
		}
		if(this.dir == 4) {
			if(this.r2.getY() +SIZE == yy
					&& this.r2.getX() >= this.r1.getX() -SIZE
					&& this.r2.getX() < this.r1.getX() +SIZE)
				check = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == this.btn[0]) {
			this.dir = 1;
		}
		else if(e.getSource() == this.btn[1])
			this.dir = 2;
		else if(e.getSource() == this.btn[2])
			this.dir = 3;
		else if(e.getSource() == this.btn[3])
			this.dir = 4;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		this.dir = 0;
	}
	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println(e.getKeyCode()); // press 한 key의 고유코드
		
		// 좌
		if(e.getKeyCode() == e.VK_LEFT)		// 37
			this.dir = 1;
		// 하
		if(e.getKeyCode() == e.VK_DOWN)		// 40
			this.dir = 2;
		// 우
		if(e.getKeyCode() == e.VK_RIGHT)	// 39
			this.dir = 3;
		// 상
		if(e.getKeyCode() == e.VK_UP)		// 38
			this.dir = 4;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		this.dir = 0;
	}
}

class GamePush extends JFrame{
	
	private PushPanel pp = new PushPanel();
	
	public GamePush() {
		super("Push Push");
		setLayout(null);
		setBounds(50,50,700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(this.pp);
		
		setVisible(true);
		revalidate();
	}
}

public class Ex_PushPush {

	public static void main(String[] args) {
			
		GamePush game = new GamePush();

	}

}
