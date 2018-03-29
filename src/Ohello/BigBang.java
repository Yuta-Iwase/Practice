package Ohello;

public class BigBang {

	public BigBang() {
		// BigBang使用後相手ターンに移る。
		// また、使用回数を1減らす
		switch(Othello.setStatus){
		case -1:
			Othello.setStatus =  1;
			Othello.spB --;
			break;

		case 1:
			Othello.setStatus = -1;
			Othello.spW --;
			break;
		}

		// BigBang 効果内容
		if(Math.random() * 10 < 3.0){
			for(int x=0 ; x<8 ; x++){
				for(int y=0 ; y<8 ; y++){
					Reversing.status[x][y] *= -1;
				}
			}
		}


	}
}
