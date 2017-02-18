package GrowingShoot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Game{

	public static class PanelGame extends JPanel implements ActionListener,Runnable,KeyListener{
		private static final long serialVersionUID = 1L;
		// ゲームのスレッド
		Thread th;
		// ゲームのスレッド開始しているか
		static boolean gameStart;
		// キーボードが押されているか
		static boolean keyPressed;
		// 現在押されているキー真偽
		boolean pressed_RIGHT;
		boolean pressed_DOWN;
		boolean pressed_LEFT;
		boolean pressed_UP;
		boolean pressed_S;
		
		// △撤去予定部分
		ExButton startBT;
		ImageIcon startBTIcon;
		
		// プレイヤーに対する設定
		ImageIcon playerIcon;
		static JLabel playerLabel;
		static int playerX,playerY;
		static int playerHP;
		
		// 時間経過カウンター
		int time;
		
		// 弾発射のクールタイム
		int coolTime;
		
		// 次に生成される敵のレベル
		int nextLevel;
		// 敵の大きさを測るためのオブジェクト
		static Enemy measureEnemy; 
		// 敵のArrayList
		ArrayList<Enemy> enemy;
		
		// 弾の所有に利用する定数
		static final int HOLDER_P=  1;
		static final int HOLDER_E= -1;
		
		// 弾のArrayList
		// 敵味方関係なく格納される
		ArrayList<Shot> shot;
		
		
		public PanelGame(){
			// レイアウト消去
			this.setLayout(null);
			// 背景色
			this.setBackground(new Color(255,255,255));
			
			// プレイヤー情報 初期化
			playerIcon = new ImageIcon("../res/mark1.png");
			playerLabel = new JLabel(playerIcon);
			this.add(playerLabel);
			playerX = 240; playerY = 700;
			playerLabel.setBounds(playerX, playerY, 40, 40);
			
			// 測定用オブジェクト初期化
			measureEnemy = new Enemy(0,0,0,0);
			// 敵リスト初期化
			enemy = new ArrayList<Enemy>();
			
			// 弾リスト初期化
			shot = new ArrayList<Shot>();
			
			// △撤去部分
			startBTIcon = new ImageIcon("../res/mark1.png");
			startBT = new ExButton(startBTIcon);
			this.add(startBT);
			startBT.addActionListener(this);
			startBT.setBounds(200, 30, 90, 90);
			
			// 真偽 初期化
			gameStart = false;
			keyPressed = false;
			
			// KeyListenerの諸準備
			addKeyListener(this);
			setFocusable(true);
		}
		
		// 初めてボタンが押されたときに動作する
		public void start(){
			th = new Thread(this);
			th.start();
		}
		
		// スレッドの動作		
		public void run(){
			// 初期化動作
			PanelGame.gameStart = true;
			playerHP = 100;
			time = 0;
			coolTime = 0;
			nextLevel = 1;
			System.out.println("Thread start");
			
			while(true){
				// 時間更新
				time++;
				//// 敵
				// 敵生成
				if(time%(4*60) == 0){
					System.out.println("enemy spawned"+ measureEnemy.imgEnemy.getIconWidth());
					enemy.add(new Enemy((int)(Math.random()*(GrowingShoot.width - measureEnemy.imgEnemy.getIconWidth())), 20, nextLevel, (int)(Math.random() * 2)));
					nextLevel++;
				}
				// 敵移動
				for(Enemy spawnedEnemy:enemy){
					if(spawnedEnemy.direction == -1){
						spawnedEnemy.x += 2;
					}else if(spawnedEnemy.direction == 1){
						spawnedEnemy.x -= 2;
					}
				}
				// 敵移動方向転換
				for(Enemy spawnedEnemy:enemy){
					if(spawnedEnemy.x <= 0|| spawnedEnemy.x + spawnedEnemy.getWidth() >= GrowingShoot.width){
						spawnedEnemy.direction *= -1;
					}
				}

				
				//// 自機移動
				// フレームあたりの移動量
				int increment = 5;
				// 斜め移動のとき 『移動量/√2』 にする
				if((pressed_RIGHT && pressed_DOWN) || (pressed_DOWN && pressed_LEFT) || (pressed_LEFT && pressed_UP) || (pressed_UP && pressed_RIGHT))
					increment = (int)Math.ceil(increment / Math.sqrt(2));
				// 各方向へ移動
				if(pressed_RIGHT) playerX +=increment;
				if(pressed_DOWN)  playerY +=increment;
				if(pressed_LEFT)  playerX -=increment;
				if(pressed_UP)    playerY -=increment;
				// 画面外には出れないようにする
				if(playerX < 0)playerX = 0;
				if(playerX + playerLabel.getWidth() > GrowingShoot.width)playerX = GrowingShoot.width - playerLabel.getWidth();
				if(playerY < 0)playerY = 0;
				if(playerY + playerLabel.getHeight()> GrowingShoot.height)playerY= GrowingShoot.height - playerLabel.getHeight();
				// 位置更新
				playerLabel.setBounds(playerX, playerY, 40, 40);
				
				//// 弾
				// 自機 弾生成
				if(pressed_S && coolTime <= 0){
					shot.add(new Shot(playerX + playerLabel.getWidth()/2, playerY, 1, HOLDER_P));
					coolTime = 2;
				}
				// 敵 弾生成
				if(time%(2*60) == 0){
					for(Enemy spawnedEnemy:enemy){
						shot.add(new Shot(spawnedEnemy.getX() + spawnedEnemy.getWidth()/2, spawnedEnemy.getY() + spawnedEnemy.getHeight(), spawnedEnemy.getAttack(), HOLDER_E));
					}
				}
				// 弾削除
				// ArrayListに削除する弾のIndexを対応させる
				ArrayList<Integer> removeIndex = new ArrayList<Integer>();
				for(Shot firedShot:shot){
					if(firedShot.y > GrowingShoot.height + 50|| firedShot.y < 0 - 50){
						removeIndex.add(shot.indexOf(firedShot));
					}
				}
				for(Integer i:removeIndex){
					try{
						this.remove(shot.get(i.intValue()));
						shot.remove(i.intValue());
					}catch( Exception error ){}
				}
				
				// 弾移動
				for(Shot firedShot:shot){
					switch(firedShot.getHolder()){
					case HOLDER_P:
						firedShot.y -= 14;
						break;
					case HOLDER_E:
						firedShot.y += 18;
						break;
					}
				}
				
				//// 命中判定
				// 敵が弾に命中しているか
				for(Enemy spawnedEnemy:enemy){
					for(Shot firedShot:shot){
						if(((spawnedEnemy.getX() <= firedShot.getX() && firedShot.getX() <= spawnedEnemy.getX() + spawnedEnemy.getWidth())
						&&(spawnedEnemy.getY() <= firedShot.getY() && firedShot.getY() <= spawnedEnemy.getY() + spawnedEnemy.getHeight()))
								||((spawnedEnemy.getX() <= firedShot.getX() + firedShot.getWidth() && firedShot.getX() + firedShot.getWidth() <= spawnedEnemy.getX() + spawnedEnemy.getWidth())
										&&(spawnedEnemy.getY() <= firedShot.getY() && firedShot.getY() <= spawnedEnemy.getY() + spawnedEnemy.getHeight()))
										)
						{
							if(firedShot.getHolder() == HOLDER_P){
								spawnedEnemy.hp -= firedShot.damage;
							}
						}
					}
				}
				// 自分が弾に命中しているか
					for(Shot firedShot:shot){
						if(((playerX <= firedShot.getX() && firedShot.getX() <= playerX + playerLabel.getWidth())
						&&(playerY <= firedShot.getY() + firedShot.getHeight() && firedShot.getY() + firedShot.getHeight() <= playerY + playerLabel.getHeight()))
								||((playerX <= firedShot.getX() + firedShot.getWidth() && firedShot.getX() + firedShot.getWidth() <= playerX + playerLabel.getWidth())
										&&(playerY <= firedShot.getY() + firedShot.getHeight() && firedShot.getY() + firedShot.getHeight() <= playerY + playerLabel.getHeight()))
										)
						{
							if(firedShot.getHolder() == HOLDER_E){
								System.out.println("hit!!");
								playerHP -= firedShot.damage;
							}
						}
					}
				// 敵削除
				removeIndex = new ArrayList<Integer>();
				for(Enemy spawnedEnemy:enemy){
					if(spawnedEnemy.hp <= 0){
						removeIndex.add(enemy.indexOf(spawnedEnemy));
					}
				}
				for(Integer i:removeIndex){
					try{
						this.remove(enemy.get(i.intValue()));
						enemy.remove(i.intValue());
					}catch( Exception error ){}
				}
				
				// 再描写
				repaint();
				
				// 弾のクールタイム経過
				coolTime--;
				
				// 1秒間のフレーム数
				int frames = 60;
				try{Thread.sleep((int)(1000D/frames));} catch(InterruptedException e) {};
			}
		}

		public void actionPerformed(ActionEvent event){
			//△
			if(event.getSource()==startBT){
				setVisible( false );
				try{
					GrowingShoot.pnlMenu.setVisible(true);
				}catch( Exception error ){}
			}
			
			//△//
		}
		
		public void keyPressed(KeyEvent e){
			// どのキーが押されているか
			switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
				pressed_RIGHT = true;
				break;
			case KeyEvent.VK_DOWN:
				pressed_DOWN = true;
				break;
			case KeyEvent.VK_LEFT:
				pressed_LEFT = true;
				break;
			case KeyEvent.VK_UP:
				pressed_UP = true;
				break;
			case KeyEvent.VK_S:
				pressed_S = true;
				break;
			}
			// 初めてkeyPressedを読み込んだ時にスレッドを開始する
			if(!gameStart){
				start();
			}
		}
		
		public void keyReleased(KeyEvent e){
			// どのキーが離されたか
			switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
				pressed_RIGHT = false;
				break;
			case KeyEvent.VK_DOWN:
				pressed_DOWN = false;
				break;
			case KeyEvent.VK_LEFT:
				pressed_LEFT = false;
				break;
			case KeyEvent.VK_UP:
				pressed_UP = false;
				break;
			case KeyEvent.VK_S:
				pressed_S = false;
				break;
			}
		}
		
		// 抽象メソッド、使用予定なし
		public void keyTyped(KeyEvent e){}

		// 描写部分
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			try{
				// 敵描写
				for(Enemy spawnedEnemy:enemy){
					spawnedEnemy.setBounds(spawnedEnemy.getX(), spawnedEnemy.getX(), spawnedEnemy.getIcon().getIconWidth(), spawnedEnemy.getIcon().getIconHeight());
					this.add(spawnedEnemy);
				}
				// 弾描写
				for(Shot firedShot:shot){
					firedShot.setBounds(firedShot.getX(), firedShot.getY(), firedShot.getIcon().getIconWidth(), firedShot.getIcon().getIconHeight());
					this.add(firedShot);
				}
			}catch(Exception e){}
			
		}
	}
	
	
	
	
	
	// 弾クラス
	public static class Shot extends JLabel{
		private static final long serialVersionUID = 1L;
		// x,y座標
		int x,y;
		// 弾1つ1つがダメージ値を保有する
		int damage;
		
		// 弾の所有者
		// 1:player -1:enemy
		int holder;
		
		// 弾の画像
		ImageIcon imgShotP;
		ImageIcon imgShotE;
		
		public Shot(int x,int y,int damage, int HOLDER_) {
			// 画像初期化
			imgShotP = new ImageIcon("../res/imgShotP.png");
			imgShotE = new ImageIcon("../res/imgShotE.png");
			
			// 引数読み取り
			this.x = x;
			this.y = y;
			this.damage = damage;
			this.holder = HOLDER_;
			
			// 画像割り当て
			if(HOLDER_ == PanelGame.HOLDER_P)this.setIcon(imgShotP);
			else this.setIcon(imgShotE);
		}
		
		// 各ゲッター
		public int getX(){return x;}
		public int getY(){return y;}
		public int getDamage(){return damage;}
		public int getHolder(){return holder;}
	}
	
	// 敵クラス
	public static class Enemy extends JLabel{
		private static final long serialVersionUID = 1L;
		// x,y座標
		int x,y;
		// HP
		int hp;
		// Attack
		int attack;
		// Level
		int level;
		
		// 敵の移動方向
		// -1:右 1:左
		int direction;
		
		// 敵の画像
		ImageIcon imgEnemy;
		
		// HP,AttackはLevelにより決まるため、x,y,levelのみを引数に取る		
		public Enemy(int x, int y, int level,int direction) {
			// 変数初期化
			this.x = x;
			this.y = y;
			hp = setHP(level);
			attack = setAttack(level);
			this.level = level;
			switch(direction){
			case 0:
				this.direction = -1;
				break;
			case 1:
				this.direction =  1;
				break;
			}

			
			// 画像初期化
			imgEnemy = new ImageIcon("../res/imgEnemy.png");
			// 画像割り当て
			this.setIcon(imgEnemy);
		}
		
		// Levelにより最大HPを決める
		public int setHP(int level){
			return 10 * level + 99;
		}
		// Levelにより攻撃力を決める
		public int setAttack(int level){
			return level%10 + 1;
		}
		
		// 各ゲッター
		public int getX(){return x;}
		public int getY(){return y;}
		public int getHP(){return hp;}
		public int getAttack(){return attack;}
		public int getLevel(){return level;}
	}
}

