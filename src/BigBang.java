
public class BigBang {

	public BigBang() {
		// BigBang�g�p�㑊��^�[���Ɉڂ�B
		// �܂��A�g�p�񐔂�1���炷
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
		
		// BigBang ���ʓ��e
		if(Math.random() * 10 < 3.0){
			for(int x=0 ; x<8 ; x++){
				for(int y=0 ; y<8 ; y++){
					Reversing.status[x][y] *= -1;
				}
			}	
		}

		
	}
}
