///////////////////////////////////////////////////////////////////////////////
//	シューティングゲーム２　弾発射・自機が画面からはみ出さない
//	ファイル名 : Shooting2.java
//	アプレットサイズ 300×400
//	使用画像：
//	jiki.gif（自機画像上向き、32×32ピクセル）
//  tama.gif（弾画像、8×8ピクセル）

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Shooting2 extends Applet implements Runnable, KeyListener {
	Image jiki;				// 自機画像
	int jx, jy;			// 自機の座標
	Image tama;				// 弾画像
	int tx[]=new int[10000], ty[]=new int[10000];			// 弾の座標
	boolean tflag[] =new boolean[10000];			// 弾の発射フラグ
	boolean keyLeft, keyRight, keySpace;	// キー押下フラグ
	Thread gameThread;		// ゲームスレッド
	int i1,i2=1,i3=1;
	
	// 初期化
	public void init() {
		// 自機の初期座標を設定する
		jx = 134; jy = 150;
		// キーフラグをクリア
		keyLeft = keyRight = keySpace = false;
		// 背景色を黒色に設定
		setBackground(Color.black);
		// 前景色を白色に設定
		setForeground(Color.white);
		// 自機画像読み込み
		jiki = getImage(getDocumentBase(), "jiki.gif");
		tama= getImage(getDocumentBase(), "tama.gif");
		for(i1=1;i1==500;i1++){
			tx[i1]=100;
			ty[i1]=100;
			tflag[i1] = false;
		}
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
			} catch (InterruptedException e) {
				break;
			}
			// ゲームメイン処理
			// 左移動
			if (keyLeft) {
				jx -= 8;
				if (jx < 0) jx = 0;
			}
			// 右移動
			if (keyRight) {
				jx += 8;
				if (jx > 300 - 32) jx = 300 - 32;
			}
			// 弾発射
			if (keySpace){
				tx[i2] = jx + 12;
				ty[i2] = jy;
				tflag[i2] = true;
				++i2;
				
			}
			


			// 弾消滅
			for(i3=1;i3<=100;i3++){
				if (tflag[i3]) {
					if (ty[i3] < 0) tflag[i3] = false;
					else ty[i3] = ty[i3] - 8;
				}
			}
			
			
			
			if(i2>=50){
				i2=1;				
			}
			
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
			// スペースキーが押されたとき
			case KeyEvent.VK_SPACE: keySpace = true; break;
		}
	}

	// キーが離されたときの処理
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			// 左キーが離されたとき
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			// 右キーが離されたとき
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			// スペースキーが離されたとき
			case KeyEvent.VK_SPACE: keySpace = false; break;
		}
	}

	// キーがタイプされたときの処理
	public void keyTyped(KeyEvent e) {}

	// 描画
	public void paint(Graphics g) {		
		// 弾が出現しているときは弾の描画
		for(i1=1;i1<=100;i1++){
			if (tflag[i1]) g.drawImage(tama, tx[i1], ty[i1], this);
		}
					
		// 自機の描画
		g.drawImage(jiki, jx, jy, this);
		g.drawString("x:" +jx+ " y: " +jy, 10, 20);
		g.drawString("i2:"+i2, 10, 40);
		g.drawString("tx[1]:"+tx[1]+" ty[1]:"+ty[1], 10, 60);
		
	}
}
