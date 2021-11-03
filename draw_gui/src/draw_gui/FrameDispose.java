package draw_gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FrameDispose extends JFrame implements MouseListener{
	
	private JButton close = new JButton("close");
	
	public FrameDispose() {
		super("test");
		setLayout(null);
		setBounds(100,100,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		close.setBounds(350,400,100,50);
		close.addMouseListener(this);
		add(close);
		
		setVisible(true);
		revalidate();
	}

	public static void main(String[] args) {
		
		FrameDispose test = new FrameDispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");
		if(e.getSource() == this.close)
			this.dispose();
		
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

}
