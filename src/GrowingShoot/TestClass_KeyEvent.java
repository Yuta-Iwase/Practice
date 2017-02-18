package GrowingShoot;

import java.awt.*;
import java.awt.event.*;



public class TestClass_KeyEvent extends Frame
implements KeyListener,Runnable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int x = 320, y = 200;
	  static int vx = 0, vy = 0;
	  static Thread keyEventTestGame = null;

	  public static void main(String ar[]){
	    new TestClass_KeyEvent();
	  }

	  TestClass_KeyEvent(){
	    setTitle("KeyEvent Test");
	    setSize(640,400);
	    setVisible(true);
	    addKeyListener(this);
	    start();
	  }

	  
	  public void start() {

	      keyEventTestGame = new Thread(this);

	      keyEventTestGame.start();
	   
	  }

	  
	  public void run() {
	    while(true){
	      x += vx;
	      y += vy;
	      repaint();
	      try {
	        Thread.sleep(100);
	      } catch (InterruptedException e) {}
	    }  
	  }
	  public void paint(Graphics g){
	    g.setColor(Color.black);
	    g.fillRect(x, y,32,32);
	  }


	  
	  public void keyPressed(KeyEvent keyEvent){
	    int key = keyEvent.getKeyCode();
	     switch (key) {
	      case KeyEvent.VK_LEFT:
	         vx = -5;
	        break;
	      case KeyEvent.VK_RIGHT:
	         vx = 5;
	        break;
	      case KeyEvent.VK_UP:
	         vy = -5;
	        break;
	      case KeyEvent.VK_DOWN:
	         vy = 5;
	    }
	  }

	  
	  public void keyReleased(KeyEvent keyEvent){
	    int key = keyEvent.getKeyCode();
	     switch (key) {
	      case KeyEvent.VK_LEFT:
	         vx = 0;
	        break;
	      case KeyEvent.VK_RIGHT:
	         vx = 0;
	        break;
	      case KeyEvent.VK_UP:
	         vy = 0;
	        break;
	      case KeyEvent.VK_DOWN:
	         vy = 0;
	    }
	  }
	  public void keyTyped(KeyEvent keyEvent){}
	}