
public class sqrt2 {
	int a,b;
	public static void main(String[] args) {
//		sqrt2 cal = new sqrt2(0,0); 
//		for(int i=-3 ; i<=3 ; i++){
//			for(int j=-3;j<=3;j++){
//				for(int k=-3;k<=3;k++){
//					for(int l=-3;l<=3;l++){
//						sqrt2 A = new sqrt2(i,j);
//						sqrt2 B = new sqrt2(k,l);
//						System.out.print("i="+i + "\tj="+j + "\tk=" + k + "\tl=" + l + "\t:");
//						cal.cross(A, B).printer();
//						boolean bool = (Math.abs(A.get2())!=1 || Math.abs(A.get1())!=1) && (Math.abs(A.get2())!=2 || Math.abs(A.get1())!=2);
//						if(cal.cross(A, B).get1()==2 && cal.cross(A, B).get2()==0 && A.get2()!=0 && bool){
//							System.out.println("\tI have 2");
//						}
//					}
//				}
//			}
//		}
		
		
		for(int i=0;i<=5;i++){
			for(int j=0;j<=5;j++){
				if((i*i - 2*(j*j))/3.0 ==  (i*i - 2*(j*j))/3){
					System.out.println("i=" + i + " j=" + j + "\tnomber is " + (i*i - 2*(j*j)));
				}
			}
		}
		
		
	}
	
	public sqrt2(int a,int b) {
		this.a=a;
		this.b=b;
	}
	
	public sqrt2 cross(sqrt2 X,sqrt2 Y){
		int cross1,cross2;
		cross1 = X.get1()*Y.get1() + 2*X.get2()*Y.get2();
		cross2 = X.get1()*Y.get2() +   X.get2()*Y.get1();
		return (new sqrt2(cross1,cross2));
	}
	
	public int get1(){
		return a;
	}
	
	public int get2(){
		return b;
	}
	
	public void printer(){
		System.out.println("AB=\t" + get1() + "\t+ " + get2() + "\t√2");
	}
	
	
}
