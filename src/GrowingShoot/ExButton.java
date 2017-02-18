package GrowingShoot;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

public class ExButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	
	public ExButton(String text , Icon icon){
		super(text,icon);
		setContentAreaFilled(false);
		 setFocusPainted(false);
		 setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
	}
	
	public ExButton(Icon icon){
		super(icon);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
	}
	
	public ExButton(String text , Icon icon, boolean contentAreaFilled){
		super(text,icon);
		setContentAreaFilled(contentAreaFilled);
		 setFocusPainted(false);
		 setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
	}
	
	public ExButton(Icon icon, boolean contentAreaFilled){
		super(icon);
		setContentAreaFilled(contentAreaFilled);
		setFocusPainted(false);
		setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
	}

}