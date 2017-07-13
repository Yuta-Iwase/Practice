import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JFrameTest extends JFrame{

	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1000, 700);
		setLayout(null);

		JLabel img = new JLabel();
		img.setIcon(new ImageIcon("ヤシャスィーン.gif"));
		img.setLayout(null);
		img.setBounds(10, 10, 911, 576);
		add(img);
		
		JPanel bg = new JPanel();
		bg.setBackground(Color.RED);
		bg.setBounds(0, 0, 1500, 1500);
		add(bg);
	}

	public static void main(String[] args) {
		JFrameTest gui = new JFrameTest();
		gui.init();
		gui.setVisible(true);


		while(true){
			System.out.println("passed");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
