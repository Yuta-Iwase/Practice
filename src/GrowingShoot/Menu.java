package GrowingShoot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Menu{
	public static class PanelMenu extends JPanel implements ActionListener{
		private static final long serialVersionUID = 1L;
		// ボタン
		ExButton startBT;
		// ボタンアイコン
		ImageIcon startBTIcon;
		public PanelMenu(){
			// 初期化
			startBTIcon = new ImageIcon("../res/start.png");
			startBT = new ExButton(startBTIcon);
			// ボタン追加
			this.add(startBT);
			// ActionListenerに登録
			startBT.addActionListener(this);
			// レイアウトを未定義に
			this.setLayout(null);
			// 座標設定によりボタン配置
			int x = GrowingShoot.width/2-startBTIcon.getIconWidth()/2;
			int y = GrowingShoot.height/2-startBTIcon.getIconHeight()/2;
			int width = startBTIcon.getIconWidth();
			int height = startBTIcon.getIconHeight();
				// 画面の中心を基準に移動
				y += 100;
			startBT.setBounds(x, y,width , height);
		}
		
		public void actionPerformed(ActionEvent event){
			// ゲーム画面(PanelGame)へ移行する
			if(event.getSource()==startBT){
				// 一旦全て非表示に
				setVisible( false );
				// 念のためtryで書く
				try{
					// GrowingShoot.classのコンテナにpnlGameを追加
					GrowingShoot.cnt.add(GrowingShoot.pnlGame);
					// pnlGameを可視へ
					GrowingShoot.pnlGame.setVisible(true);
					// ゲームが可視になったことをgameVisibleへ伝える
					GrowingShoot.pnlGame.requestFocusInWindow();
				}catch( Exception error ){
					// エラーメッセージを表示
					System.out.println(error);
				}
			}
		}
		
		// 描写
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			this.setBackground(new Color(0,0,0));
		}
	}
}

