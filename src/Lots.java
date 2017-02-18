import java.util.Random;

public class Lots {
	public static void main(String[] args) {
		int a1=0;
		int ran1=0,ran2=0;
		
		boolean F,S,T;
		
		F=true;
		S=true;
		T=true;
		
		Random rnd = new Random();
        ran1 = rnd.nextInt(3);
        switch(ran1){
        case 0:
        	System.out.println("カフェラテ マイルド");
        	a1 = 0;
        	F = false;
        	break;
        case 1:
        	System.out.println("ダース");
        	a1 = 1;
        	S = false;
        	break;
        case 2:
        	System.out.println("Mt.RSINIER");
        	a1 = 2;
        	T = false;
        	break;
        }
        
        switch(a1){
        case 0:
        	ran2 = rnd.nextInt(2);
        	if(ran2 == 0){
        		ran2 = 2;
        	}
        	break;
        case 1:
        	ran2 = rnd.nextInt(2);
        	if(ran2 == 1){
        		ran2 = 2;
        	}
        	break;
        case 2:
        	ran2 = rnd.nextInt(2);
        	break;
        }
        
        switch(ran2){
        case 0:
        	System.out.println("カフェラテ マイルド");
        	F = false;
        	break;
        case 1:
        	System.out.println("ダース");
        	S = false;
        	break;
        case 2:
        	System.out.println("Mt.RSINIER");
        	T = false;
        	break;
        }
        
        if(!F && !S){
        	System.out.println("Mt.RSINIER");
        }else if(!S && !T){
        	System.out.println("カフェラテ マイルド");
        }else if(!T && !F){
        	System.out.println("ダース");
        }
	}

}
