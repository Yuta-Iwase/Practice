import java.awt.*;
import javax.swing.*;

public class JLabelSample1 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lbl1 = new JLabel();
	JLabel lbl2 = new JLabel("ƒ‰ƒxƒ‹‚Q");
	JLabel lbl3 = new JLabel("ƒ‰ƒxƒ‹‚R", JLabel.CENTER);
	
	int a;

	public JLabelSample1() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocation(100, 100);
		setLayout(new GridLayout(3, 1));
		add(lbl1);
		add(lbl2);
		add(lbl3);
		
		a = 10;
		lbl1.setText("<html><font size=\"6\" face=\"Times New Roman\">" +  a  +  "texttest" + "</font></html>");
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new JLabelSample1();
	}
}