package draw_gui;

import javax.swing.JFrame;

class ExdrawFrame extends JFrame{
	public ExdrawFrame() {
		super("drag");
		setLayout(null);
		setBounds(100,100,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		add();
		
		setVisible(true);
		revalidate();
	}
	
}

public class Ex_draw {

	public static void main(String[] args) {
		
		ExdrawFrame frame = new ExdrawFrame();
	}

}
