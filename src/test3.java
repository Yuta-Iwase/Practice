
public class test3 {

	public static void main(String[] args) {
		int T=100;
		double x=1.0;
		
		for(int i=0;i<T;i++){
			System.out.println(i + "\t" + x);
			x = Math.sqrt(2*x);
		}
	}

}
