
public class Charge {

	public Charge() {
		// 使用回数を1減らし、(必ず1減ります)
		// 条件を満たせば2増やす
		// 結果的には 満たす⇒1増える 満たさない⇒1減る という結果になる。
		
		// 確率は 敵の制圧率*0.7 ⇒ 0.7で掛けるのは1では有利すぎるため
				switch(Othello.setStatus){
				case -1:
					// 黒の場合
					Othello.spB --;
					if(Math.random() < ((1 - (Counting.scoreB / (double)(Counting.scoreB + Counting.scoreW))) * 0.75) ) Othello.spB += 2;
					System.out.println("Charge.class: " + ((1 - (Counting.scoreB / (double)(Counting.scoreB + Counting.scoreW))) * 0.8));
					Othello.setStatus =  1;
					break;
				
				case 1:
					// 白の場合
					Othello.spW --;
					if(Math.random() < ((1 - (Counting.scoreB / (double)(Counting.scoreB + Counting.scoreW))) * 0.75) ) Othello.spW += 2;
					System.out.println("Charge.class: " + ((1 - (Counting.scoreW / (double)(Counting.scoreB + Counting.scoreW))) * 0.8));
					Othello.setStatus = -1;
					break;
				}				
	}

}
