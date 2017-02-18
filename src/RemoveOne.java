
public class RemoveOne {
	
	static boolean RemoveOneFlagB, RemoveOneFlagW;
	public RemoveOne() {
		RemoveOneFlagB = false;
		RemoveOneFlagW = false;
		
		// égópâÒêîÇ1å∏ÇÁÇ∑
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
