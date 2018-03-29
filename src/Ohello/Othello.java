package Ohello;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Othello extends JFrame implements MouseListener,MouseMotionListener {
	private static final long serialVersionUID = 1;

	public static exButton buttonArray[][];// ボタン用の配列
	public static JLabel border, bkground, menu, sign, scoreBL, scoreWL, skillImg, SP;
	public Container c;

	public static ImageIcon blackIcon, whiteIcon, boardIcon;

	public static ImageIcon BigBangIcon, RemoveOneIcon, ChargeIcon, SuperNovaIcon;
	public static exButton BigBangButton, RemoveOneButton, ChargeButton, SuperNovaButton;

	// 次に配置する色
	// -1→黒    1→白
	public static int setStatus;

	// 特殊能力使用回数
	// spPresentとは、現プレイヤーのスキル残数
	public static int spB, spW, spPresent=1;

	Reversing rev;
	Resource res;
	Counting cou;

	public Othello() {
		res = new Resource();
		cou = new Counting();

		// ウィンドウを作成する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ウィンドウを閉じるときに，正しく閉じるように設定する
		setTitle("Othello"); // ウィンドウのタイトルを設定する
		setSize(770, 610); // ウィンドウのサイズを設定する
		setLocationRelativeTo(null);
		setResizable(false);
		c = getContentPane(); // フレームのペインを取得する
		c.setLayout(null); // 自動レイアウトの設定を行わない
		c.setBackground(new Color(0,0,0));

		// アイコンの設定
		whiteIcon = new ImageIcon("");
		blackIcon = new ImageIcon("");
		boardIcon = new ImageIcon("");

		// 特殊能力アイコンの設定
		BigBangIcon = new ImageIcon("");
		RemoveOneIcon = new ImageIcon("");
		ChargeIcon = new ImageIcon("");
		SuperNovaIcon = new ImageIcon("");


		// ボタン(マス)の生成
		buttonArray = new exButton[8][8];// ボタンの配列を５個作成する[0]から[4]まで使える
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				buttonArray[i][j] = new exButton(boardIcon);// ボタンにアイコンを設定する
				c.add(buttonArray[i][j]);// ペインに貼り付ける
				buttonArray[i][j].setBounds((i+1) * 60, (j+1)*60, 60, 60);// ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
				buttonArray[i][j].addMouseListener(this);// ボタンをマウスでさわったときに反応するようにする
				buttonArray[i][j].addMouseMotionListener(this);// ボタンをマウスで動かそうとしたときに反応するようにする
				buttonArray[i][j].setActionCommand(Integer.toString(i));// ボタンに配列の情報を付加する（ネットワークを介してオブジェクトを識別するため）
			}
		}

		// BigBangボタンの設定
		// setActionCommandはコマンドのID
		BigBangButton = new exButton(BigBangIcon);
		c.add(BigBangButton);
		BigBangButton.setBounds(590, 200, 173, 51);
		BigBangButton.addMouseListener(this);
		BigBangButton.addMouseMotionListener(this);
		BigBangButton.setActionCommand(Integer.toString(8*8 + 1));

		// RemoveOneボタンの設定
		RemoveOneButton = new exButton(RemoveOneIcon);
		c.add(RemoveOneButton);
		RemoveOneButton.setBounds(590, 260, 173, 51);
		RemoveOneButton.addMouseListener(this);
		RemoveOneButton.addMouseMotionListener(this);
		RemoveOneButton.setActionCommand(Integer.toString(8*8 + 2));

		// Chargeボタンの設定
		ChargeButton = new exButton(ChargeIcon);
		c.add(ChargeButton);
		ChargeButton.setBounds(590, 320, 173, 51);
		ChargeButton.addMouseListener(this);
		ChargeButton.addMouseMotionListener(this);
		ChargeButton.setActionCommand(Integer.toString(8*8 + 3));

		// SuperNovaボタンの設定
		SuperNovaButton = new exButton(SuperNovaIcon);
		c.add(SuperNovaButton);
		SuperNovaButton.setBounds(590, 380, 173, 51);
		SuperNovaButton.addMouseListener(this);
		SuperNovaButton.addMouseMotionListener(this);
		SuperNovaButton.setActionCommand(Integer.toString(8*8 + 3));

		// 仕切り線の設定
		Icon imgBo = new ImageIcon("img/border.png");
		border = new JLabel(imgBo);
		c.add(border);
		border.setBounds(0, 0, 600, 600);

		// 白のコマ数
		scoreWL = new JLabel("");
		scoreWL.setText("<html><font size=\"8\" face=\"Monotype Corsiva\" color=\"black\">" + Counting.scoreW + "</font></html>");
		c.add(scoreWL);
		scoreWL.setBounds(665, 35, 40, 30);

		// 黒のコマ数
		scoreBL = new JLabel("");
		scoreBL.setText("<html><font size=\"8\" face=\"Monotype Corsiva\" color=\"white\">" + Counting.scoreB + "</font></html>");
		c.add(scoreBL);
		scoreBL.setBounds(665, 110, 40, 30);

		//スキル残数数値の描写設定
		SP = new JLabel("");
		SP.setText("<html><b><font face=\"Monotype Corsiva\" color=\"white\"  style=\"font-size : 80pt;\">" + spPresent + "</font></b></html>");
		c.add(SP);
		SP.setBounds(672, 465, 148, 80);

		//スキル残数画像の設定
		skillImg = new JLabel("");
		c.add(skillImg);
		skillImg.setBounds(590, 465, 148, 80);

		// 陰陽印の設定
		sign = new JLabel("");
		c.add(sign);
		sign.setBounds(600, 10, 150, 150);

		// 右側のバーの設定
		menu = new JLabel("");
		c.add(menu);
		menu.setBounds(580, -10, 200, 600);

		// 背景の設定
		Icon imgBa = new ImageIcon("img/bkground.png");
		bkground = new JLabel(imgBa);
		c.add(bkground);
		bkground.setBounds(0, 0, 800, 600);


		// セットステータスを初期化する
		// -1で黒、1で白
		setStatus = -1;
		// 特殊能力使用回数の初期化
		spB = 1;
		spW = 1;
		// Reversingクラスを定義
		rev = new Reversing();
	}

	public static void main(String[] args) {
		Othello gui = new Othello();

		gui.setVisible(true);
		gui.res.changeWhite(3, 3);
		gui.res.changeBlack(3, 4);
		gui.res.changeBlack(4, 3);
		gui.res.changeWhite(4, 4);
		gui.res.setSpecialAbilityIcon();
		gui.res.setBorder();
		gui.cou.count();
		System.out.println(Counting.scoreB);
		gui.res.setSign();
		gui.res.setSkillB();
		gui.res.setMenu();
		gui.res.setbkground();
		new CheckSettable().checkSettable();
		gui.repaint();
	}

	// ボタンをクリックしたときの処理
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");
		// クリックしたオブジェクトを得る．型が違うのでキャストする
		exButton theButton = (exButton) e.getComponent();
		// theIconには，現在のボタンに設定されたアイコンが入る
		Icon theIcon = theButton.getIcon();

		if(RemoveOne.RemoveOneFlagB || RemoveOne.RemoveOneFlagW){
			// RemoveOne発動中の行動
			for(int i=0; i<8 ; i++){
				for(int j=0 ; j<8 ;j++){
					if(e.getSource() == buttonArray[i][j]){
						// フラグをfalseに戻す
						RemoveOne.RemoveOneFlagB = false;
						RemoveOne.RemoveOneFlagW = false;
						// 選択した座標のコマを取り除く
						Reversing.status[i][j] = 0;
						// 再描写
						new Refresh().refresh();
						// 相手にターンを回す
						// ターン送り判定は後にあるから大丈夫
						setStatus *= -1;
					}
				}
			}
		}else if(SuperNova.SuperNovaFlag){
			// SuperNova発動中の行動
			for(int i=0; i<8 ; i++){
				for(int j=0 ; j<8 ;j++){
					if(e.getSource() == buttonArray[i][j]){
						// フラグをfalseに戻す
						SuperNova.SuperNovaFlag = false;
						// 選択した座標のコマとその周囲を反転させる
						for(int k=-1 ; k<2 ; k++){
							for(int l=-1 ; l<2 ; l++){
								try{
									Reversing.status[i+k][j+l] *= -1;
								}catch(ArrayIndexOutOfBoundsException errorMSG){
									// do nothing
									System.out.println("error catch");
								}
							}
						}
						// 再描写
						new Refresh().refresh();
						// 相手にターンを回す
						// ターン送り判定は後にあるから大丈夫
						setStatus *= -1;
					}
				}
			}
		}else if(e.getSource() == BigBangButton && 1 <= spPresent ){
			//BigBangボタンを選択した場合の操作を記述
			System.out.println("BigBang clicked");
			new BigBang();
			new Refresh().refresh();
			new CheckSettable().checkSettable();
		}else if(e.getSource() == ChargeButton && 1 <= spPresent ){
			//Chargeボタンを選択した場合の操作を記述
			System.out.println("Charge clicked");
			new Charge();
			new Refresh().refresh();
			new CheckSettable().checkSettable();
		}else if(e.getSource() == RemoveOneButton && 1 <= spPresent){
			//RemoveOneボタンを選択した場合の操作を記述
			System.out.println("RemoveOne clicked");
			new RemoveOne();
			new Refresh().refresh();
		}else if(e.getSource() == SuperNovaButton && 1 <= spPresent){
			//RemoveOneボタンを選択した場合の操作を記述
			System.out.println("SuperNova clicked");
			new SuperNova();
			new Refresh().refresh();
		}else if(e.getSource() == BigBangButton && 1 > spPresent){
			// do nothing
		}else if(e.getSource() == ChargeButton && 1 > spPresent){
			// do nothing
		}else if(e.getSource() == RemoveOneButton && 1 > spPresent){
			// do nothing
		}else if(e.getSource() == SuperNovaButton && 1 > spPresent){
			// do nothing
		}else{
			// 普通にコマをクリックしたときの処理

			int x=0,y=0;
			for(int i=0; i<8 ; i++){
				for(int j=0 ; j<8 ;j++){
					if(e.getSource() == buttonArray[i][j]){
						x = i;
						y = j;
					}
				}
			}

			int set;

			if(Reversing.status[x][y] == 0 && setStatus == -1){
				res.changeBlack(x, y);
				set = -1;
				rev.reverse(x, y, set);
				setStatus = 1;
				if(!Reversing.changed){
					System.out.println("return boardIcon");
					theButton.setIcon(boardIcon);
					setStatus = -1;
				}
			}else if (Reversing.status[x][y] == 0 && setStatus == 1) {// アイコンがboardIconと同じなら
				res.changeWhite(x, y);
				set = 1;
				rev.reverse(x, y, set);
				setStatus = -1;
				if(!Reversing.changed){
					System.out.println("return boardIcon");
					theButton.setIcon(boardIcon);
					setStatus = 1;
				}
			} else if (theIcon.equals(whiteIcon)) {
				System.out.println("whiteIcon is clicked");
			} else {
				System.out.println("blackIcon is clicked");
			}
		}

		// ゲーム終了判定(ターン送り設定)
		new CheckSettable().checkSettable();
		if (!CheckSettable.AllSetB) {
			System.out.println("Pass!");
			Othello.setStatus = 1;
			new CheckSettable().checkSettable();
			if (!CheckSettable.AllSetW){
				System.out.println("GameSet!!");
			}
		}
		if (!CheckSettable.AllSetW) {
			System.out.println("Pass!");
			Othello.setStatus = -1;
			new CheckSettable().checkSettable();
			if (!CheckSettable.AllSetB){
				System.out.println("GameSet!!");
			}
		}

		// スキル残数画像のアイコン切り替え
		if(Othello.setStatus == 1){
			res.setSkillW();
			spPresent = spW;
		}else{
			res.setSkillB();
			spPresent = spB;
		}
		SP.setText("<html><b><font face=\"Monotype Corsiva\" color=\"white\"  style=\"font-size : 80pt;\">" + spPresent + "</font></b></html>");

		//次ターンのプレイヤーのスキル段数によってスキル表示を変える
		if(spPresent <= 0){
			res.setCannotUseAbility();
		}else{
			res.setSpecialAbilityIcon();
		}

		cou.count();
		repaint();// 画面のオブジェクトを描画し直す
	}

	public void mouseEntered(MouseEvent e) {// マウスがオブジェクトに入ったときの処理
	}

	public void mouseExited(MouseEvent e) {// マウスがオブジェクトから出たときの処理
	}

	public void mousePressed(MouseEvent e) {// マウスでオブジェクトを押したときの処理（クリックとの違いに注意）
	}

	public void mouseReleased(MouseEvent e) {// マウスで押していたオブジェクトを離したときの処理
	}

	public void mouseDragged(MouseEvent e) {// マウスでオブジェクトとをドラッグしているときの処理
	}

	public void mouseMoved(MouseEvent e) {// マウスがオブジェクト上で移動したときの処理
	}
}