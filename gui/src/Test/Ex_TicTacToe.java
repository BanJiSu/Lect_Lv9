package Test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class AlertFrame extends JFrame{
	
	JLabel text = new JLabel();
	
	public AlertFrame() {
		setLayout(null);
		setBounds(200,200,300,200);
		setVisible(true);
		
		text.setBounds(0,0,300,200);
		text.setText("test");
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setVisible(true);
		add(text);
	}
}

class Content extends JPanel implements ActionListener{

	private JButton[] map;
	private int mark[];
	private int turn = 1;
	private int win;
	private boolean prinWinner;
	
	private int cnt = 1;
	
	JButton reset = new JButton();
	
	public Content() {
		setLayout(null);
		setBounds(0,0,700,700);

		setHeader();
		setMap();
		addResetButton();
		
	}
	
	private void addResetButton() {
		reset.setBounds(800/2-300/2, 600, 300, 50);
		reset.setText("START");
		reset.addActionListener(this);
		add(reset);
	}
	
	private void setHeader() {
		JLabel head = new JLabel("Tic Tac Toe");
		head.setBounds(0,0,800,200);
		head.setFont(new Font("",Font.BOLD,40));
		head.setHorizontalAlignment(JLabel.CENTER);
		head.setVerticalAlignment(JLabel.BOTTOM);
		
		add(head);
	}
	
	private void setMap() {
		this.map = new JButton[9];
		this.mark = new int[9];
		
		int x = 800/2-300/2;
		int y = 800/2-300/2;
		for(int i=0; i<this.map.length; i++) {
			this.map[i] = new JButton();
			this.map[i].setBounds(x,y,100,100);
			this.map[i].setBackground(Color.lightGray);
			this.map[i].addActionListener(this);
			
			// on mac
//			this.map[i].setOpaque(true);
//			this.map[i].setBorderPainted(false);
			
			add(this.map[i]);
			x += 100 + 1;
			if(i % 3 == 2) {
				x = 800/2-300/2;
				y += 100 + 1;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton)e.getSource();
		
		if(target == this.reset) {
			resetMap();
		}
		else {
			this.reset.setText("RESET");
			for(int i=0; i<this.map.length; i++) {
				if(target == this.map[i] && this.mark[i] == 0) {
					if(this.turn == 1) {
						target.setBackground(Color.black);
					}
					else {
						target.setBackground(Color.orange);
					}
					this.mark[i] = this.turn;
					checkWin();
					
					this.turn = this.turn == 1 ? 2 : 1;
				}
			}
		}
	}
	
	private void resetMap() {
		for(int i=0; i<this.map.length; i++) 
			this.map[i].setBackground(Color.lightGray);
		this.mark = new int[9];
		this.turn = 1;
		this.win = 0;
		this.reset.setText("START");
		this.prinWinner = false;
	}
	
	private void checkWin() {
		this.win = this.win == 0 ? checkHori() : this.win;
		this.win = this.win == 0 ? checkVerti() : this.win;
		this.win = this.win == 0 ? checkDia() : this.win;
		this.win = this.win == 0 ? checkReverse() : this.win;
		if(!this.prinWinner && this.win != 0) {
			System.out.printf("%d번째 게임 : p%d의 승!\n", this.cnt, this.win);	
			
			AlertFrame alert = new AlertFrame();
			alert.text.setText(String.format("%d번째 게임 : p%d의 승!\n", this.cnt, this.win));
			add(alert);
			this.prinWinner = true;
			this.cnt++;
		}
	}
	/*
	 * 0 1 2
	 * 3 4 5 
	 * 6 7 8 
	 */

	private int checkReverse() {
		int cnt = 0;
		for(int i=0; i<3; i++) {
			if(this.mark[(i+1)*2] == this.turn)
				cnt++;
		}
		if(cnt == 3)
			return this.turn;
		return 0;
	}

	private int checkDia() {
		int cnt = 0;
		for(int i=0; i<3; i++) {
			if(this.mark[i*4] == this.turn)
				cnt++;
		}
		if(cnt == 3)
			return this.turn;
		
		return 0;
	}

	private int checkVerti() {
		for(int i=0; i<3; i++) {
			int cnt = 0;
			for(int j=0; j<3; j++) {
				if(this.mark[i+j*3] == this.turn) {
					cnt++;
				}
			}
			if(cnt == 3) {
				return this.turn;
			}
		}
		return 0;
	}

	private int checkHori() {
		for(int i=0; i<this.mark.length; i+=3) {
			int cnt = 0;
			for(int j=0; j<3; j++) {
				if(this.mark[i+j] == this.turn) {
					cnt++;
				}
			}
			if(cnt == 3) {
				return this.turn;
			}
		}
		return 0;
	}
}

class Tictactoe extends JFrame{
	public Tictactoe() {
		super("Tic Tac Toe");
		
		setLayout(null);
		setBounds(50,50,800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setBackground(Color.pink);
		
		add(new Content());
		
		setVisible(true);
		revalidate(); // 갱신	
	}
}
public class Ex_TicTacToe {

	public static void main(String[] args) {
		
		Tictactoe ttt = new Tictactoe();
		
	}

}
