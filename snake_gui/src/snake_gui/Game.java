package snake_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class SnakePanel extends MyUtil{
	
	private Rect[][] map = new Rect[10][10];
	
	private final int SIZE = 50;
	private Rect[] snake;
	
	private JButton[] btn = new JButton[4];
	private JButton reset = new JButton();
	private int dir;
	private int xx;
	private int yy;
	private boolean check;
	
	public SnakePanel() {
		setLayout(null);
		setBounds(0,0,1000,700);
		setBackground(Color.white);
		setmap();
		setSnake();
		setbtn();
		setResetbtn();
		setHeader();
	}

	private void setHeader() {
		JLabel head = new JLabel("SNAKE GAME");
		head.setBounds(100, 0, 800, 80);
		head.setFont(new Font("",Font.BOLD,40));
		head.setVerticalAlignment(JLabel.BOTTOM);
		
		add(head);
	}

	private void setResetbtn() {
		this.reset.setBounds(1000-300, 700-150, 180, 50);
		this.reset.setText("RESET");
		this.reset.addActionListener(this);
		add(this.reset);
		
	}

	private void setbtn() {
		String[] text = {"║Г","║И","║Ф","║Х"};
		int size = 60;
		int x = 1000 - 300;
		int y = 700 - 250;
		
		for(int i=0; i<this.btn.length; i++) {
			this.btn[i] = new JButton();
			this.btn[i].setBounds(x,y,size,size);
			this.btn[i].setText(text[i]);
			this.btn[i].addMouseListener(this);
			
			add(this.btn[i]);
			x += size;
			
			if(i == this.btn.length -1 -1) {
				x = 1000 - 300 + size;
				y -= size;
			}
			
		}
		
	}

	private void setSnake() {
		snake = new Rect[4];
		
		int x = 700/2 - SIZE*10/2;
		int y = 700/2 - SIZE*10/2;
		
		for(int i=0; i<this.snake.length; i++) {
			this.snake[i] = new Rect(x,y,SIZE,SIZE);
			x += SIZE;
			
		}
		
		
	}

	private void setmap() {
	
		int x = 700/2 - 50*10/2;
		int y = 700/2 - 50*10/2;
		
		for(int i=0; i<this.map.length; i++) {
			for(int j=0; j<this.map[i].length; j++) {
				this.map[i][j] = new Rect(x, y, SIZE, SIZE);
				x += SIZE;
			}
			x = 700/2 - SIZE*10/2;
			y += SIZE;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		update();
		
		for(int i=0; i<this.map.length; i++) {
			for(int j=0; j<this.map[i].length; j++) {
				Rect rect = this.map[i][j];
				g.setColor(Color.black);
				g.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
			}
		}
		
		for(int i=0; i<this.snake.length; i++) {
			Rect sn = this.snake[i];
			if(i == 0) {
				g.setColor(Color.blue);
			}
			else {
				g.setColor(Color.cyan);
			}
			g.fillRect(sn.getX(), sn.getY(), sn.getWidth(), sn.getHeight());
			g.setColor(Color.black);
			g.drawRect(sn.getX(), sn.getY(), sn.getWidth(), sn.getHeight());
		}
		
		
		repaint();
	}
	
	private void update() {
		xx = this.snake[0].getX();
		yy = this.snake[0].getY();
		
		// аб
		if(this.dir == 1 && this.snake[0].getX() - SIZE > SIZE) {
			xx -= this.SIZE;
		}
		// ©Л
		else if(this.dir == 3 && this.snake[0].getX() + this.SIZE < 600) {
			xx += SIZE;
		}
		// ╩С
		else if(this.dir == 4 && this.snake[0].getY() - SIZE > SIZE) {
			yy -= SIZE;
		}
		// го
		else if(this.dir == 2 && this.snake[0].getY() + this.SIZE < 600) {
			yy += SIZE;
		}
		
		///// test
		// аб
//		if(this.dir == 1) {
//			this.snake[0].setX(xx);
//		}
//		// ©Л
//		else if(this.dir == 3) {
//			this.snake[0].setX(xx);
//			for(int i=0; i<4; i++) {
//				int temp = this.snake[i].getX();
//				this.snake[i].setX(temp+SIZE);
//			}
//		}
//		// ╩С
//		else if(this.dir == 4) {
//			this.snake[0].setY(yy);
//		}
//		// го
//		else if(this.dir == 2) {
//			this.snake[0].setY(yy);
//			for(int i=1; i<4; i++) {
//				if(this.snake[i-1].getY()-SIZE > SIZE) {
//					this.snake[i].setY(this.snake[i-1].getY()-SIZE);	
//					this.snake[i].setX(this.snake[i-1].getX());
//				}
//			}
//		}
		
		// test2
		if(this.dir != 0) {
			for(int i=this.snake.length-1; i > 0; i--) {
				this.snake[i].setX(this.snake[i-1].getX());
				this.snake[i].setY(this.snake[i-1].getY());
			}
			this.snake[0].setX(xx);
			this.snake[0].setY(yy);
				
//			this.snake[3].setX(this.snake[2].getX());
//			this.snake[2].setX(this.snake[1].getX());
//			this.snake[1].setX(this.snake[0].getX());
//			this.snake[0].setX(xx);
//			
//			this.snake[3].setY(this.snake[2].getY());
//			this.snake[2].setY(this.snake[1].getY());
//			this.snake[1].setY(this.snake[0].getY());
//			this.snake[0].setY(yy);
		}
		
		
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			Thread.sleep(150);
		} catch (Exception e2) {
			
		}
		if(e.getSource() == this.btn[0]) {
			this.dir = 1;
		}
		else if(e.getSource() == this.btn[1]) {
			this.dir = 2;
		}
		else if(e.getSource() == this.btn[2]) {
			this.dir = 3;
		}
		else if(e.getSource() == this.btn[3]) {
			this.dir = 4;
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		this.dir = 0;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.reset) {
			setSnake();
			this.dir = 0;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// аб
		if (e.getKeyCode() == e.VK_LEFT)
			this.dir = 1;
		// ©Л
		if (e.getKeyCode() == e.VK_RIGHT)
			this.dir = 3;
		// ╩С
		if (e.getKeyCode() == e.VK_UP)
			this.dir = 4;
		// го
		if (e.getKeyCode() == e.VK_DOWN)
			this.dir = 2;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		this.dir = 0;
	}
}

public class Game extends JFrame{

	private SnakePanel panel = new SnakePanel();

	public Game() {
		super("Snake Game");
		setLayout(null);
		setBounds(50,50,1000,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(this.panel);
		
		setVisible(true);
		revalidate();
	}
}


