package GrowingShoot;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JPanel;

import GrowingShoot.Proto_ModeMenu.Menu.PanelMenu;


public class Proto_GrowingShoot extends JApplet implements ActionListener{
	private static final long serialVersionUID = 1L;
	// ゲームの現在のモードの変数
	int mode;

	// コンテナ
	Container menuCnt;
	// 背景パネル
	Proto_ModeMenu.Menu.PanelMenu menuPanel;
	// メニューの背景
	Image menuBg;
	
	// スタートボタン
	ExButton startBT;
	ImageIcon startIcon = new ImageIcon("../res/mark1.png");
	
	Container test;
	
	public void init(){
		// 初期化
		menuCnt = getContentPane();
		menuPanel = new PanelMenu();
		// ウィンドウサイズ決定
		this.setSize(640,640);
		// 
		menuPanel.setLayout(null);
		// コンテナに追加
		menuCnt.add(menuPanel);

		
		startBT = new ExButton(startIcon);
		startBT.addActionListener(this);
		startBT.setBounds(280,500,90,90);
		menuPanel.add(startBT);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == startBT){
//			System.out.println("actionPerformed");
//			menuPanel = null;
			setVisible( false );
			
			try{

				// 次画面のFram2を生成
				JPanel next = new JPanel();

				// 次画面を表示
				next.setVisible( true );
			}catch( Exception er ){

				System.out.println("error is catched");
				er.printStackTrace();

			}
			repaint();
		}
	}
}
