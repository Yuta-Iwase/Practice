package Ohello;

// コマ数をカウントして点数表示する。


public class Counting {
	static int scoreB, scoreW;
	public Counting() {
		scoreB = 0;
		scoreW = 0;
	}

	public void count(){
		scoreB = 0;
		scoreW = 0;
		for(int x=0;x<8;x++){
			for(int y=0;y<8;y++){
				if(Reversing.status[x][y] == -1){
					scoreB++;
				}

				if(Reversing.status[x][y] ==  1){
					scoreW++;
				}
			}
		}

		Othello.scoreBL.setText("<html><font size=\"8\" face=\"Monotype Corsiva\" color=\"white\">" + scoreB + "</font></html>");
		Othello.scoreWL.setText("<html><font size=\"8\" face=\"Monotype Corsiva\" color=\"black\">" + scoreW + "</font></html>");

		if(scoreB >= 10){
			Othello.scoreBL.setBounds(655, 110, 40, 30);
		}else{
			Othello.scoreBL.setBounds(665, 110, 40, 30);
		}

		if(scoreW >= 10){
			Othello.scoreWL.setBounds(655, 35, 40, 30);
		}else{
			Othello.scoreWL.setBounds(665, 35, 40, 30);
		}
	}

}