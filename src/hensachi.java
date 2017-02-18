
public class hensachi {
	public static void main(String[] args) {
		int[] clear = new int[9];
		int clearN;
		double[] E = new double[100];
		for(int i=0;i<100;i++) E[i]=0.0;
		double[] p = new double[9];
		double[] q = new double[9];
		for(int i=0;i<9;i++){
			if(i<6){
				p[i] = 0.5;
			}else{
				p[i] = 0.25;
			}
			q[i] = 1.0-p[i];
		}
		double EStub;

		for(int i=0;i<512;i++){
			clearN=0;
			EStub = 1.0;
			for(int n=0;n<9;n++){
				clear[n] = ( i/((int)(Math.pow(2,n))) ) % 2;
				clearN += clear[n];
			}
			for(int n=0;n<9;n++){
				if(clear[n]==1) EStub*=p[n];
				else EStub*=q[n];
			}
			E[clearN] += EStub;
		}

		for(int i=0;i<=9;i++){
			System.out.println("E[" + i + "]=" + E[i]);
		}

		System.out.println();

		double variance = 0.0;
//		double[] EX2 = new double[100];
//		double[] EX  = new double[100];
//		for(int i=0;i<100;i++){
//			EX2[i]=0.0; EX[i]=0.0;
//		}
		double EX2=0,EX=0;
		for(int i=0;i<=9;i++){
			EX2 += i*i * E[i];
			EX += i * E[i];
		}
		variance = EX2 - (EX*EX);
		System.out.println("分散:" + variance);
		System.out.println("期待値:" + EX);

		System.out.println();

		System.out.println("最悪な事象の偏差値:" + (((0.0-EX)/Math.sqrt(variance))*10+50));

	}

}
