
public class RemoveOne {
	
	static boolean RemoveOneFlagB, RemoveOneFlagW;
	public RemoveOne() {
		RemoveOneFlagB = false;
		RemoveOneFlagW = false;
		
		// �g�p�񐔂�1���炷
		switch(Othello.setStatus){
		case -1:
			Othello.spB --;
			RemoveOneFlagB = true;
			break;
		
		case 1:
			Othello.spW --;
			RemoveOneFlagW = true;
			break;
		}
		
		
		
		
	}
	
	

}
