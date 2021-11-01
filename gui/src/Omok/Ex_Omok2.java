package Omok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class AlertWin extends JFrame{
	private JLabel text = new JLabel();
	
	public AlertWin(String text) {
		super("Game Clear!");
		setLayout(null);
		setBounds(100,100,300,200);
		setVisible(true);
		
		this.text.setBounds(0,0,300,200);
		this.text.setText(text);
		this.text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
	}
}

class Nemo{
	boolean click;
	int id;
	int x, y, width, height;
	
	public Nemo(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
}

class Board extends JPanel implements MouseListener, ActionListener, KeyListener{
	
	private Nemo[][] map = new Nemo[10][10];
	private int turn = 1;
	private int win;
	
	private boolean play;
	private JButton reset = new JButton();
	
//	int xx = 0;
	
	public Board() {
		setLayout(null);
		setBounds(0,0,700,700);
		setBackground(new Color(229, 220, 195));
		
		addMouseListener(this);	// �гο� ���콺������ �߻�
		setMap();
		setResetButton();
	}
	
	private void setResetButton() {
		this.reset.setBounds(10,10,100,50);
		this.reset.setText("RESET");
		this.reset.addActionListener(this);
		add(this.reset);
	}
	
	private void setMap() {
		
		// �׸� �簢���� ���� ������ Nemo�迭�� Ȱ���ؼ� ����
		int x = 700/2-50*10/2;
		int y = 700/2-50*10/2;
		for(int i=0; i<this.map.length; i++) {
			for(int j=0; j<this.map[i].length; j++) {
				this.map[i][j] = new Nemo(x,y,50,50);
				x += 50;
			}
			x = 700/2-50*10/2;
			y += 50;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {		// ������
		super.paintComponent(g); // ��ü �׷��� ������Ʈ�� ����� ����
		// �׸� �׸���
		// JComponent �� ���� �������̵���
		// paintComponent() �޼ҵ尡 ���� ���� Graphics �� Ȱ��
		// drawRect(x,y,width,height) �޼ҵ带 ���
//		g.drawRect(100, 100, 100, 100);
		
//		g.setColor(Color.blue);
//		g.drawRect(10, 10, 100, 100);
//		this.xx++;
		
		// map line
		for(int i=0; i<this.map.length; i++) {
			for(int j=0; j<this.map.length; j++) {
				Nemo nemo = this.map[i][j];
				Nemo temp = new Nemo(nemo.x-nemo.width/2, nemo.y-nemo.height/2, nemo.width, nemo.height);
				g.setColor(Color.black);
				g.drawRect(temp.x, temp.y, temp.width, temp.height);
				
				if(i == this.map.length-1) {
					g.drawRect(temp.x, temp.y+temp.height, temp.width, temp.height);
				}
				if(j == this.map.length-1) {
					g.drawRect(temp.x+temp.width, temp.y, temp.width, temp.height);
				}
				if(i == this.map.length-1 && j == this.map.length-1)
					g.drawRect(temp.x+temp.width, temp.y+temp.height, temp.width, temp.height);
				
			}
		}
		
		if(this.play) {
			// stone
			for(int i=0; i<this.map.length; i++) {
				for(int j=0; j<this.map[i].length; j++) {
					
					Nemo nemo = this.map[i][j];
					// ���̵���� guide line
					g.setColor(Color.pink);
					g.drawRect(nemo.x, nemo.y, nemo.width, nemo.height);
//				g.fillRect(nemo.x, nemo.y, nemo.width, nemo.height); // ä���
					
					// storn
					if(nemo.click) {
						if(nemo.id == 1) {
							g.setColor(Color.black);
						}
						else if(nemo.id == 2){
							g.setColor(Color.white);
						}
						// �ձ۰Ը����
						g.drawRoundRect(nemo.x+10, nemo.y+10, nemo.width-20, nemo.height-20, nemo.width-20, nemo.height-20); 
						g.fillRoundRect(nemo.x+10, nemo.y+10, nemo.width-20, nemo.height-20, nemo.width-20, nemo.height-20);
					}
				}
			}
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {	// Ŭ���߻� ��
		// TODO Auto-generated method stub
		System.out.println("�ƾ�");
		
		// Ŭ���� �߻��� ������ ��ǥ������ ����
		int x = e.getX();
		int y = e.getY();
		
		// check
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				Nemo stone = this.map[i][j];
				if (!stone.click && x >= stone.x && x < stone.x + stone.width && y >= stone.y
						&& y < stone.y + stone.height) {
					stone.click = true;
					stone.id = this.turn;
					
					checkWin();
					
					this.turn = this.turn == 1 ? 2 : 1;
					
					if(!this.play)
						this.play = true;
				}
			}
		}
	}

	private void checkWin() {
		
		this.win = this.win == 0 ? checkHori() : this.win;
		this.win = this.win == 0 ? checkVerti() : this.win;
		this.win = this.win == 0 ? checkDia() : this.win;
		this.win = this.win == 0 ? checkReverse() : this.win;
		
		if(this.win != 0) {
			System.out.printf("p%d�� ��\n", this.win);
			new AlertWin(String.format("p%d�� ��\n", this.win));
		}
	}
	
	private int checkReverse() {
		for(int i=4; i<this.map.length; i++) {
			for(int j=0; j<this.map[i].length-4; j++) {
				if(this.map[i][j].id == this.turn) {
					int cnt = 0;
					for(int k=0; k<5; k++) {
						if(this.map[i-k][j+k].id == this.turn) {
							cnt++;
						}
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}

	private int checkDia() {
		for(int i=0; i<this.map.length-4; i++) {
			for(int j=0; j<this.map[i].length-4; j++) {
				if(this.map[i][j].id == this.turn) {
					
					int cnt = 0;
					for(int k=0; k<5; k++) {
						if(this.map[i+k][j+k].id == this.turn) {
							cnt++;
						}
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}

	private int checkVerti() {
		for(int i=0; i<this.map.length; i++) {
			int cnt = 0;
			for(int j=0; j<this.map[i].length; j++) {
				if(this.map[j][i].id == this.turn)
					cnt++;
				else
					cnt = 0;
				if(cnt == 5)
					return this.turn;
			}
		}
		return 0;
	}

	private int checkHori() {
		for(int i=0; i<this.map.length; i++) {
			int cnt = 0;
			for(int j=0; j<this.map[i].length; j++) {
				if(this.map[i][j].id == this.turn) {
					cnt++;
				}
				else {
					cnt = 0;
				}
				if(cnt == 5) {
					return this.turn;
				}
			}
		}
		return 0;
	}

	@Override
	public void mousePressed(MouseEvent e) {	// Ŭ- (������)
		// TODO Auto-generated method stub
		System.out.println("Ŭ-");
	}

	@Override
	public void mouseReleased(MouseEvent e) {	// -�� (����)
		// TODO Auto-generated method stub
		System.out.println("-��");
	}

	@Override
	public void mouseEntered(MouseEvent e) {	// listener �� add�� ���� ������ ���콺�� ������
		// TODO Auto-generated method stub
		System.out.println("hello~");
	}

	@Override
	public void mouseExited(MouseEvent e) {		// listener �� add�� ���� ������ ���콺�� ������ ����
		// TODO Auto-generated method stub
		System.out.println("bye~");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.reset) {
			this.play = false;
			this.turn = 1;
			this.win = 0;
			setMap();
		}
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
}

class OmokGame extends JFrame{
	
	private Board board = new Board();
	public OmokGame() {
		super("Omok Game");
		setBounds(50,50,700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(board);
		
		setVisible(true);
		revalidate();
	}
}

public class Ex_Omok2{

	public static void main(String[] args) {

		OmokGame omok = new OmokGame();
	}

}
