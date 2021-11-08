package draw_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class Content extends MyUtil{
	
	public JButton close = new JButton("close");
	
	// �׷����� ��ü
	ArrayList<Nemo> nemos = new ArrayList<Nemo>(); // <- mouseRealesed �� ��, �Ƚ��� �׸� ����ֱ� <- paintComponent()�޼ҵ忡�� �׸��� ó��
	ArrayList<Nemo> semo = new ArrayList<Nemo>();
	ArrayList<Nemo> circle = new ArrayList<Nemo>();
	
	
	private Nemo nemo = null; // <- drag �ϴ� ���� ���ŵǴ� ��ü�� �ӽ�Ȱ��
	private int x, y, startX, startY;
	
	public JButton[] btns = new JButton[3];

	private int type ;
	
	private final int RECTANGLE = 0;
	private final int TRIANGLE = 1;
	private final int CIRCLE = 2;
	

	public Content() {
		setLayout(null);
		setBounds(0,0,900,700);
		setBackground(Color.orange);
		
		
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		addKeyListener(this);
		
		setButton();
		setbtns();
	}

	private void setbtns() {
		String[] text = {"��","��","��"};
		int size = 50;
		int x = 10;
		int y = 10;
		for(int i=0; i<this.btns.length; i++) {
			btns[i] = new JButton();
			btns[i].setBounds(x,y,size,size);
			btns[i].setText(text[i]);
//			btns[i].addMouseListener(this);
			btns[i].addActionListener(this);
			
			add(btns[i]);
			x += size + 2;
		}
		
	}

	private void setButton() {
		this.close.setBounds(750,580,100,50);
		this.close.addActionListener(this);
		add(this.close);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		
		// ������ Ǯ��
		
		// �ﰢ�� �׸���
		// drawPolygon(int [], int[], int)
		// (x��ǥ�� �迭, y��ǥ�� �迭, ������ ����)
//		int[]xxx = {100, 50, 150};
//		int[]yyy = {100, 200, 200};
//		g.setColor(Color.red);
//		g.drawPolygon(xxx, yyy, 3);
		
		
		// �׸�׸��� (������)
		if(this.nemo != null) {
			g.setColor(this.nemo.getC());
			
			if(this.type == RECTANGLE) {
				g.drawRect(this.nemo.getX(), this.nemo.getY(), this.nemo.getWidth(), this.nemo.getHeight());
			}
			else if(this.type == TRIANGLE) {
				int xx[] = new int[3];
				int yy[] = new int [3];
				
				xx[0] = this.nemo.getX();
				yy[0] = this.nemo.getY();
				
				xx[1] = this.nemo.getX() - this.nemo.getWidth()/2;
				yy[1] = this.nemo.getY() + this.nemo.getHeight();
				
				xx[2] = this.nemo.getX() + this.nemo.getWidth()/2;
				yy[2] = this.nemo.getY() + this.nemo.getHeight();
				
				g.drawPolygon(xx, yy, 3);
			}
			else if(this.type == CIRCLE) {
				g.drawRoundRect(this.nemo.getX(), this.nemo.getY(), this.nemo.getWidth(), this.nemo.getHeight(), this.nemo.getWidth(), this.nemo.getHeight());
			}
			
		}
		
		// Fix
		// �׸� ��ü �迭�� ��ü �׸���
		
		// nemos
		for (Nemo n : this.nemos) {
			g.setColor(n.getC());
			g.drawRect(n.getX(), n.getY(), n.getWidth(), n.getHeight());

		}
		
		//semo
		for(Nemo n : this.semo) {
			int xx[] = new int[3];
			int yy[] = new int [3];
			
			xx[0] = n.getX();
			yy[0] = n.getY();
			
			xx[1] = n.getX() - n.getWidth()/2;
			yy[1] = n.getY() + n.getHeight();
			
			xx[2] = n.getX() + n.getWidth()/2;
			yy[2] = n.getY() + n.getHeight();
			
			g.drawPolygon(xx, yy, 3);
		}
		
		//circle
		for(Nemo n : this.circle) {
			g.setColor(n.getC());
			g.drawRoundRect(n.getX(), n.getY(), n.getWidth(), n.getHeight(), n.getWidth(), n.getHeight());
		}
		
		
		requestFocusInWindow();	// keyListener �� ���� ��Ŀ�� �ٽ� ��û
		repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {

		this.x = e.getX();
		this.y = e.getY();
		this.startX = x;	// ������ ������ ��ǥ�� �������� ����ϱ� ����
		this.startY = y;
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	
		if(this.type == RECTANGLE) {
			this.nemos.add(this.nemo);			
		}
		else if(this.type == TRIANGLE) {
			this.semo.add(this.nemo);
		}
		else if(this.type == CIRCLE) {
			this.circle.add(this.nemo);
		}
		this.nemo = null;
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
//		System.out.println("drag");
		this.x = this.startX;
		this.y = this.startY;
		
		int xx = e.getX();
		int yy = e.getY();
		
		// �׸���
		int width = this.type == TRIANGLE ? xx-x : Math.abs(xx-x);
		int height = this.type == TRIANGLE ? yy-y : Math.abs(yy-y);
		
		if(this.x > xx && width > 1) {
			x = startX - width;// ������ - width
		}
		if(this.y > yy && height > 1) {
			y = startY - height;	
		}
		
		this.nemo = new Nemo(this.x, this.y, width, height, Color.blue);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.btns[0])
				this.type = RECTANGLE;
			else if(target == this.btns[1])
				this.type = TRIANGLE;
			else if(target == this.btns[2])
				this.type = CIRCLE;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar());
//		if(e.isShiftDown() == true) {
//			if(this.gapW > this.gapH) {
//				this.gapH  = this.gapW;
//			}
//			else {
//				this.gapW = this.gapH;
//			}
//			
//		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}	
}
