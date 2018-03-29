package Ohello;


public class SuperNova {
	public static boolean SuperNovaFlag;
	public SuperNova() {
		SuperNovaFlag = true;

		// 使用回数を1減らす
		// ここではターン送りを行わないのが特徴
		switch(Othello.setStatus){
		case -1:
			Othello.spB --;
			break;

		case 1:
			Othello.spW --;
			break;
		}
	}


}