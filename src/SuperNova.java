
public class SuperNova {
	public static boolean SuperNovaFlag;
	public SuperNova() {
		SuperNovaFlag = true;
		
		// �g�p�񐔂�1���炷
		// �����ł̓^�[��������s��Ȃ��̂�����
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
