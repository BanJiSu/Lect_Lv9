package Game_1to50;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class AlertEnd extends JFrame{
	
	private JLabel text = new JLabel();
	
	public AlertEnd(int ms) {
		super("GAME CLEAR");
		
		setLayout(null);
		setBounds(Otf.W/2-150,Otf.H/2-100,300,200);
		setVisible(true);
		
		this.text.setBounds(0,0,300,200);
		this.text.setText(String.format("기록 : %d.%3d", ms/1000, ms%1000));
		this.text.setVerticalAlignment(JLabel.CENTER);
		this.text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
	}
}

class Map extends JPanel implements ActionListener, Runnable{

	Random rn = new Random();
	private final int SIZE = 5;
	JButton map[][] = new JButton[SIZE][SIZE];
	
	private int gamenum = 1;
	private int front[][];
	private int back[][];
	
	private final int BUTTON = 60;
	
	private JLabel timer = new JLabel();
	private int ms;
	private boolean isRun;
	
	private JButton reset = new JButton();
	
	public Map() {
		setLayout(null);
		setBounds(0,0,Otf.WIDTH,Otf.HEIGHT);
		setBackground(new Color(205, 242, 202));
//		setHeader();
		setTimer();
		setMap();
		setResetButton();
	}
	
	private void setResetButton() {
		this.reset.setBounds(Otf.WIDTH/2-150, Otf.HEIGHT-200, 300, 50);
		this.reset.setText("RESET");
		this.reset.addActionListener(this);
		add(this.reset);
	}
	
	private void setTimer() {
		
		this.timer.setBounds(0,0,Otf.WIDTH,200);
		this.timer.setText(String.format("%d.%3d", this.ms/1000, this.ms%1000)); // 0.000
		this.timer.setVerticalAlignment(JLabel.BOTTOM);
		this.timer.setHorizontalAlignment(JLabel.CENTER);
		this.timer.setFont(new Font("Arial", Font.BOLD,40));
		add(this.timer);
	}
//	private void setHeader() {
//		JLabel head = new JLabel("1 to 50");
//		head.setBounds(0,0,Test1to50.WIDTH,200);
//		head.setFont(new Font("",Font.BOLD,40));
//		head.setHorizontalAlignment(JLabel.CENTER);
//		head.setVerticalAlignment(JLabel.BOTTOM);
//		
//		add(head);
//	}
	
	private void setMap() {
		shuffle();
		
		int x = Otf.WIDTH/2-BUTTON*SIZE/2;
		int y = Otf.HEIGHT/2-BUTTON*SIZE/2;
		
		for(int i=0; i<this.SIZE; i++) {
			for(int j=0; j<this.SIZE; j++) {
				this.map[i][j] = new JButton();
				this.map[i][j].setBounds(x, y, BUTTON, BUTTON);;
				this.map[i][j].setText(front[i][j] + "");
				this.map[i][j].addActionListener(this);
				
				this.map[i][j].setBackground(new Color(249, 243, 223));;
				this.map[i][j].setFont(new Font("Arial", Font.BOLD,15));
//				this.map[i][j].setForeground(Color.white);
				
				add(this.map[i][j]);
				x += BUTTON + 3;
			}
			x = Otf.WIDTH/2-BUTTON*SIZE/2;
			y += BUTTON + 3;
		}
	}
	
	public void setNum() {
		int n = 1;
		this.front = new int[SIZE][SIZE];
		this.back = new int[SIZE][SIZE];
		for(int i=0; i<this.SIZE; i++) {
			for(int j=0; j<this.SIZE; j++) {
				front[i][j] = n;
				back[i][j] = n + this.SIZE*this.SIZE;
				n++;
			}
		}
	}
	public void shuffle() {
		setNum();
		for(int i=0; i<500; i++) {
			int r1 = rn.nextInt(5);
			int r2 = rn.nextInt(5);
			
			int temp = front[0][0];
			front[0][0] = front[r1][r2];
			front[r1][r2] = temp;
			
			r1 = rn.nextInt(5);
			r2 = rn.nextInt(5);
			
			temp = back[0][0];
			back[0][0] = back[r1][r2];
			back[r1][r2] = temp;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		
		if(target == this.reset) {
			resetMap();
		}
		else {
			for(int i=0; i<this.SIZE; i++) {
				for(int j=0; j<this.SIZE; j++) {
					if(target == this.map[i][j] && this.front[i][j] == this.gamenum) {
						
						if(this.gamenum == 1)
							this.isRun = true;
						
						this.front[i][j] = this.back[i][j];
						this.back[i][j] = 0;
						
						if(this.front[i][j] == 0) {
							target.setText("");
							target.setBackground(Color.white);
						}
						else {
							target.setText(this.front[i][j] + "");
							target.setBackground(new Color(255, 200, 152));
						}
						this.gamenum++;
					}
				}
				if(this.gamenum > this.SIZE*this.SIZE*2) {
					// 게임종료
					new AlertEnd(this.ms);
				}
			}
		}
	}
	
	private void resetMap() {
		this.gamenum = 1;
		this.isRun = false;
		this.ms = 0;
		
		shuffle();
		
		for(int i=0; i<this.SIZE; i++) {
			for(int j=0; j<this.SIZE; j++) {
				this.map[i][j].setBackground(new Color(249, 243, 223));
				this.map[i][j].setText(this.front[i][j] + "");
			}
		}
		setTimer();
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(this.isRun) {
				this.ms++;
				this.timer.setText(String.format("%d.%3d", this.ms/1000, this.ms%1000));
			}
		}
	}
}

class Otf extends JFrame{
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int W = dm.width;
	public static final int H = dm.height;
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	private Map map = new Map();
	
	public Otf() {
		super("1 to 50");
		setLayout(null);
		setBounds(W/2-WIDTH/2,H/2-HEIGHT/2,WIDTH,HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
		
		add(map);
		
		map.run();
	}
}

public class Ex_Game1to50 {

	public static void main(String[] args) {
		
		Otf game = new Otf();
		
		// 스탑워치 추가
		
	}

}
