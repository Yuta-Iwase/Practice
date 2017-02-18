///////////////////////////////////////////////////////////////////////////////
//	シューティングゲーム１　自機移動
//	ファイル名 : Shooting01.java
//	アプレットサイズ 300×400
//	使用画像：
// 	jiki.gif（自機画像上向き、32×32ピクセル）

import java.applet.*;
import java.awt.*;
import java.awt.event.*;



@SuppressWarnings("serial")
public class Shooting1 extends Applet implements Runnable, KeyListener {
	Image jiki;				// 自機画像
	int jx, jy;			// 自機の座標
	boolean keyLeft;		// 左キーが押されていればtrue
	boolean keyRight;		// 右キーが押されていればtrue
	Thread gameThread;		// ゲームスレッド

	// 初期化
	public void init() {
		// 自機の初期座標を設定する
		jx = 134; jy = 150;
		// キーフラグをクリア
		keyLeft = keyRight = false;
		// 背景色を黒色に設定
		setBackground(Color.black);
		// 前景色を白色に設定
		setForeground(Color.white);
		// 自機画像読み込み
		jiki = getImage(getDocumentBase(), "jiki.gif");
		// キー入力の受け付け開始
		addKeyListener(this);
		// フォーカスを要求
		requestFocus();
	}
	

	// ゲームスレッドの開始
	public void start() {
		if(gameThread == null) {
			gameThread = new Thread(this);
			gameThread.start();
		}
	}

	// ゲームスレッドの停止
	public void stop() {
		gameThread = null;
	}

	// ゲームスレッドのメイン
	public void run() {
		while (gameThread == Thread.currentThread()) {
			try {
				Thread.sleep(20);
			} 
			catch (InterruptedException e) {
				break;
			}
			// ゲームメイン処理
			if (keyLeft) jx -= 8;
			if (keyRight) jx += 8;
			repaint();
		}
	}

	// キーが押されたときの処理
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			// 左キーが押されたとき
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			// 右キーが押されたとき
			case KeyEvent.VK_RIGHT: keyRight = true; break;
		}
	}

	// キーが離されたときの処理
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			// 左キーが離されたとき
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			// 右キーが離されたとき
			case KeyEvent.VK_RIGHT: keyRight = false; break;
		}
	}

	// キーがタイプされたときの処理
	public void keyTyped(KeyEvent e) {}

	// 描画
	public void paint(Graphics g) {
		// 自機の表示
		g.drawImage(jiki, jx, jy, this);

	}
	
}
