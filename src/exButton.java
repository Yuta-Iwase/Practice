import javax.swing.*;

public class exButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	
	public exButton(String text , Icon icon){
		super(text,icon);
		setContentAreaFilled(false);
		 setFocusPainted(false);
		 setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
	}
	
	public exButton(Icon icon){
		super(icon);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
	}

}
