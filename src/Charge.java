
public class Charge {

	public Charge() {
		// �g�p�񐔂�1���炵�A(�K��1����܂�)
		// �����𖞂�����2���₷
		// ���ʓI�ɂ� ��������1������ �������Ȃ���1���� �Ƃ������ʂɂȂ�B
		
		// �m���� �G�̐�����*0.7 �� 0.7�Ŋ|����̂�1�ł͗L�������邽��
				switch(Othello.setStatus){
				case -1:
					// ���̏ꍇ
					Othello.spB --;
					if(Math.random() < ((1 - (Counting.scoreB / (double)(Counting.scoreB + Counting.scoreW))) * 0.75) ) Othello.spB += 2;
					System.out.println("Charge.class: " + ((1 - (Counting.scoreB / (double)(Counting.scoreB + Counting.scoreW))) * 0.8));
					Othello.setStatus =  1;
					break;
				
				case 1:
					// ���̏ꍇ
					Othello.spW --;
					if(Math.random() < ((1 - (Counting.scoreB / (double)(Counting.scoreB + Counting.scoreW))) * 0.75) ) Othello.spW += 2;
					System.out.println("Charge.class: " + ((1 - (Counting.scoreW / (double)(Counting.scoreB + Counting.scoreW))) * 0.8));
					Othello.setStatus = -1;
					break;
				}				
	}

}
