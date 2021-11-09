package race_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class RacePanel extends MyUtil{

//	private ImageIcon icon = new ImageIcon("image/horse1.png");
	
	public Horses[] horse = new Horses[5];
	
	private JButton resetbtn = new JButton();
	private JButton startbtn = new JButton();

	Random rn = new Random();
	private int nn = 1;
	
	public RacePanel() {
		setLayout(null);
		setBounds(0, 0, 900, 600);
		setBackground(new Color(247, 208, 138));
		
		setHorse();
		setbtn();
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
			}
		}
		else if(e.getSource() == this.startbtn) {
			System.out.println("start");
			for(int i=0; i<5; i++) {
				horse[i].setState(horse[i].RUN);
			}
		}
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
				g.drawString(horse[i].getRank()+"", 750+5, 50 + horse[i].getY());
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
				nn++;
			}
			
		}
		
//		for(int i=0; i<5; i++) {
//			if(horse[1].getState() == horse[i].GOAL) {
//				System.out.println(horse[i].getNum()+ "¹ø : " + horse[i].getRank());
//			}
//		}
		
	}
}

public class Race extends JFrame{
	public Race() {
		super("Race");
		setLayout(null);
		setBounds(100,100,900,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new RacePanel());
		
		setVisible(true);
		revalidate();
	}
}
