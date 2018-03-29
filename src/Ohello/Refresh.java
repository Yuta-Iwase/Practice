package Ohello;
public class Refresh {

	/**
	 * @param args
	 */
	
	public Refresh() {

	}
	
	public void refresh(){

		for(int x=0 ; x<8 ; x++){
			for(int y=0 ; y<8 ; y++){
				switch(Reversing.status[x][y]){
				case -1 :
					new Resource().changeBlack(x, y);
					break;
				case  1 :
					new Resource().changeWhite(x, y);
					break;
				case  0 :
					new Resource().removeIcon(x, y);
					break;
				}
			}
		}
	}

}
