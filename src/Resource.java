// 使用するデータのパスを入力する。

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Resource {
	static URL BPath,WPath;
	static public ImageIcon blackIcon, whiteIcon, boardIcon;
	public Icon imgBo,imgBa;
	
	// 設置可能アイコンのパス
	// Black(White)SettblePath の略
	static URL BSPath,WSPath;
	
	static URL BigBangPath, RemoveOnePath, ChargePath, SuperNovaPath;
	static URL N_BigBangPath, N_RemoveOnePath, N_ChargePath, N_SuperNovaPath;
	
	public Resource() {
		// 設置可能アイコンのパスの初期化
		BSPath = getClass().getResource("img/settable-b.png");
		WSPath = getClass().getResource("img/settable-w.png");
		
		BPath = getClass().getResource("img/Black.png");
		WPath = getClass().getResource("img/White.png");
		
		BigBangPath = getClass().getResource("img/BigBang.png");
		RemoveOnePath = getClass().getResource("img/RemoveOne.png");
		ChargePath = getClass().getResource("img/Charge.png");
		SuperNovaPath = getClass().getResource("img/SuperNova.png");
		
		N_BigBangPath = getClass().getResource("img/N_BigBang.png");
		N_RemoveOnePath = getClass().getResource("img/N_RemoveOne.png");
		N_ChargePath = getClass().getResource("img/N_Charge.png");
		N_SuperNovaPath = getClass().getResource("img/N_SuperNova.png");

		

//		blackIcon = new ImageIcon(BPath);
//		whiteIcon = new ImageIcon(WPath);
//		boardIcon = new ImageIcon("");
	}
	

	// ※「new ImageIcon『getResource由来のパス』」は非常に危険

	
	public void changeBlack(int x, int y){
		Othello.buttonArray[x][y].setIcon(new ImageIcon(BPath));
	}
	public void changeWhite(int x, int y){
		Othello.buttonArray[x][y].setIcon(new ImageIcon(WPath));
	}
	public void removeIcon(int x, int y){
		Othello.buttonArray[x][y].setIcon(new ImageIcon(""));
	}
	public void setBorder(){
		Othello.border.setIcon(new ImageIcon(getClass().getResource("img/border.png")));
	}
	
	public void setSign(){
		Othello.sign.setIcon(new ImageIcon(getClass().getResource("img/sign.png")));
	}
	public void setSkillB(){
		Othello.skillImg.setIcon(new ImageIcon(getClass().getResource("img/SkillB.png")));
	}
	public void setSkillW(){
		Othello.skillImg.setIcon(new ImageIcon(getClass().getResource("img/SkillW.png")));
	}
	public void setSpecialAbilityIcon(){
		Othello.BigBangButton.setIcon(new ImageIcon(BigBangPath));
		Othello.RemoveOneButton.setIcon(new ImageIcon(RemoveOnePath));
		Othello.ChargeButton.setIcon(new ImageIcon(ChargePath));
		Othello.SuperNovaButton.setIcon(new ImageIcon(SuperNovaPath));
		
	}
	public void setCannotUseAbility(){
		Othello.BigBangButton.setIcon(new ImageIcon(N_BigBangPath));
		Othello.RemoveOneButton.setIcon(new ImageIcon(N_RemoveOnePath));
		Othello.ChargeButton.setIcon(new ImageIcon(N_ChargePath));
		Othello.SuperNovaButton.setIcon(new ImageIcon(N_SuperNovaPath));
		
	}
	public void setMenu(){
		Othello.menu.setIcon(new ImageIcon(getClass().getResource("img/menu.png")));
	}
	
	public void setbkground(){
		Othello.bkground.setIcon(new ImageIcon(getClass().getResource("img/bkground.png")));
	}

}
