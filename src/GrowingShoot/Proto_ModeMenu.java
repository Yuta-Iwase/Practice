package GrowingShoot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JApplet;
import javax.swing.JPanel;



public class Proto_ModeMenu{
	public static class Menu extends JApplet{
		private static final long serialVersionUID = 1L;
		// メニューの背景
		static Image menuBg;
		public Menu() {
			// ここですべての初期化を行う
			// 背景
			menuBg = getImage(getCodeBase(),"../res/menuBg.png");
		}
		
		public static class PanelMenu extends JPanel{
			private static final long serialVersionUID = 1L;
			public PanelMenu() {
				// TODO 自動生成されたコンストラクター・スタブ
			}
			public void paintComponent(Graphics g){
				// スーパークラスのpaintComponentを引き継ぐ
				super.paintComponent(g);
				// 色
				setBackground(Color.BLACK);
				// 背景描写
				g.drawImage(menuBg, 0, 0, this);
			}
		}
	}

}
