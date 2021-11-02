package snake_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

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
	}

	private void setResetbtn() {
		this.reset.setBounds(1000-300, 700-150, 180, 50);
		this.reset.setText("RESET");
		this.reset.addActionListener(this);
		add(this.reset);
		
	}

	private void setbtn() {
		String[] text = {"¡ç","¡é","¡æ","¡è"};
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
		
		// ÁÂ
		if(this.dir == 1 && this.snake[0].getX() - SIZE > 0) {
			xx -= this.SIZE;
		}
		// ¿ì
		else if(this.dir == 3 && this.snake[0].getX() + this.SIZE < 700) {
			xx += SIZE;
		}
		// »ó
		else if(this.dir == 4 && this.snake[0].getY() - SIZE > 0) {
			yy -= SIZE;
		}
		// ÇÏ
		else if(this.dir == 2 && this.snake[0].getY() + this.SIZE < 700) {
			yy += SIZE;
		}
		
		///// test
		if(this.dir == 2) {
			this.snake[0].setY(yy);
			for(int i=1; i<4; i++) {
				this.snake[i].setY(this.snake[i-1].getY());
				this.snake[i].setX(this.snake[i-1].getX());
			}
		}
		else if(this.dir == 3) {
			this.snake[0].setX(xx);
		}
		
		
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
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
		}
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


