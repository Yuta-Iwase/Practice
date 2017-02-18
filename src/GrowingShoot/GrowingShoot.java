package GrowingShoot;

import java.awt.Container;


import javax.swing.JFrame;

public class GrowingShoot extends JFrame{
	private static final long serialVersionUID = 1L;
	// コンテナ
	static Container cnt;
	// 各パネル
	static Menu.PanelMenu pnlMenu;
	static Game.PanelGame pnlGame;
	
	// アプレットの幅
	static int width;
	static int height;
	
	public GrowingShoot(){
		width = 600;
		height = 800;
		setSize(width,height);
		cnt = this.getContentPane();
		
		pnlMenu = new Menu.PanelMenu();
		pnlGame = new Game.PanelGame();
		cnt.add(pnlMenu);
	}
	
	public static void main(String[] args){
		GrowingShoot frame = new GrowingShoot();
		frame.setSize(width, height);
		frame.setVisible(true);
	}
}
