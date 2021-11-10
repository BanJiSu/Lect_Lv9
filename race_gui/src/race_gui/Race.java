package race_gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class RacePanel extends MyUtil implements Runnable{

	
	public Horses[] horse = new Horses[5];
	
	private JButton resetbtn = new JButton();
	private JButton startbtn = new JButton();

	private Random rn = new Random();
	private int nn = 1;
	
	private int ms;
	private boolean isRun;
	private JLabel timer = new JLabel("ready");
	
	
	public RacePanel() {
		setLayout(null);
		setBounds(0, 0, 900, 600);
		setBackground(new Color(247, 208, 138));
		
		setHorse();
		setbtn();
		
		setTimer();
	
	}
	private void setTimer() {
		timer.setBounds(30,500,100,50);
		add(timer);
		
	}

	@Override
	public void run() {
		while(true) {
			if(isRun) {
				this.ms++;
				this.timer.setText(String.format("%3d.%3d", this.ms/1000,this.ms%1000));
			}
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	private void setbtn() {
		startbtn.setBounds(600,500,100,50);
		startbtn.setText("start");
		startbtn.addActionListener(this);
		add(startbtn);
		resetbtn.setBounds(750,500,100,50);
		resetbtn.setText("reset");
		resetbtn.addActionListener(this);
		add(resetbtn);
		
		
	}

	private void setHorse() {
		
		for(int i=0; i<5; i++) {
			horse[i] = new Horses();
			horse[i].setNum(i+1);
			horse[i].setX(50);
			horse[i].setY(50 + i*80);
			horse[i].setW(60);
			horse[i].setH(60);
			horse[i].setFilename(String.format("image/horse%d.png", i+1));
			Image im = new ImageIcon(horse[i].getFilename()).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			horse[i].setImage(new ImageIcon(im));			
			
//			setBounds(horse[i].getX(),horse[i].getY(),horse[i].getW(),horse[i].getH());
//			add(horse[i]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.resetbtn) {
			System.out.println("reset");
			for(int i=0; i<5; i++) {
				horse[i].setState(2);
				horse[i].setX(50);
				horse[i].setRank(0);
			}
			isRun = false;
			timer.setText("ready");
			this.ms = 0;
			this.nn = 1;
		}
		else if(e.getSource() == this.startbtn) {
			System.out.println("start");
			for(int i=0; i<5; i++) {
				horse[i].setState(horse[i].RUN);
			}
			isRun = true;
		}
		
//		// 선생님풀이
//		if(e.getSource() instanceof JButton) {
//			JButton target = (JButton) e.getSource();
//			if(target == this.resetbtn) {
//				this.isRun = this.isRun ? false :true;
//				this.resetbtn.setText(this.isRun ? "reset" : "start");
//			}
//		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<5; i++) {
			g.drawImage(horse[i].getImage().getImage(), horse[i].getX(), horse[i].getY(), null);
			g.drawLine(50, 110 + i*80, 700, 110 + i*80);
		}
		g.drawLine(750, 50, 750, 450);
		
		for(int i=0; i<5; i++) {
			if(horse[i].getRank() != 0) {
				g.setFont(new Font("",Font.BOLD,40));
				g.drawString(horse[i].getRank()+"", 750+20, 50 + horse[i].getY());
				g.setFont(new Font("",Font.PLAIN,20));
				g.drawString(" 등"+horse[i].getRecord()+"",750+40, 50 + horse[i].getY());
				
			}
		}
		
		try {
			Thread.sleep(50);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		setmove();
		repaint();
	}

	private void setmove() {
		// test
		
		for(int i=0; i<5; i++) {
			int n = rn.nextInt(5);
			if(horse[i].getState() == horse[i].RUN && horse[i].getX() <=700) {
				horse[i].setX(horse[i].getX()+n);
			}
			if(horse[i].getX() >= 700 && horse[i].getState() == horse[i].RUN) {
				horse[i].setRank(nn);
				horse[i].setX(700);
				horse[i].setState(horse[i].GOAL);
				horse[i].setRecord(String.format("%4d.%3d", this.ms/1000,this.ms%1000));
				nn++;
			}
			
			
		}
		
		
	}
}

public class Race extends JFrame{
	
	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int W = dm.width;
	public static final int H = dm.height;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	
	private RacePanel rp = new RacePanel();
	
	public Race() {
		super("Race");
		setLayout(null);
		setBounds(100,100,900,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(rp);
		
		setVisible(true);
		revalidate();
		rp.run();
	}
}
