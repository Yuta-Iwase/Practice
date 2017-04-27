
// 線形合同法/Linear Congruential Generators/LCGs

public class LCGs {
	public static void main(String[] args) {
		int seeds = 10;
		int times = 100;
		
		int A = 7;
		int B = 3;
		int mod = 22;
		
		int[] x = new int[times];
		x[0] = seeds;
		System.out.println(0 + "\t" + x[0]);
		for(int i=1;i<times;i++){
			x[i] = (A*x[i-1]+B)%mod;
			if(x[i]==seeds) System.out.println();
			System.out.println(i + "\t" + x[i]);
		}
		
		Math.random();

	}
}
