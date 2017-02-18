import javax.swing.*;


public class BaseJFrame{
	
	JFrame mainFrame = new JFrame();
	
	public BaseJFrame(String title , int x,int y){
		JFrame mainFrame = new JFrame(title);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize( x , y );
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(false);
	}
	
	public void set(){
		mainFrame.setVisible(true);
	}

    
	

	

}
