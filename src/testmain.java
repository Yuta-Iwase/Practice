
public class testmain {
	
	static int loopTime = 0;
	static boolean loopLimiter = false;
	
	public static void multiFor(int a,int b,int l){
		loopTime++;
		for(int i=0 ; i<a ; i++){
			if(l >= b){
				act();
			}else{
				multiFor(a,b,l+1);
			}
		}		
	}
	
	static int a = 0;
	
	public static void act(){
		a++;
		System.out.println("“®ì" + a + "‰ñ–Ú");
	}
	

	public static void main(String[] args) {
		
		for(int i=0;i<100000;i++){
			int fir = (int)(((double)i/10 - (int)i/10)*10);
			int sec = (int)(((double)i/100 - (int)i/100)*10);
			int thr = (int)(((double)i/1000 - (int)i/1000)*10);
			
			
			if(fir==9 && sec==9 && thr==9){
				System.out.println(i);
			}
			
			
			
		}
		
		
	}
		
		
		
		
		
		
		
		
		
		
}

