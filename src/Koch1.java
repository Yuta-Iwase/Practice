
public class Koch1 {
	public static void main(String[] args) {
		int i,m,M=1,Mmax=128;
		
		int limitF = 9;
		Mmax = (int)Math.pow(2, limitF);
		
		double x[][] = new double[Mmax][3];
		double y[][] = new double[Mmax][3];
		double sqrt3 = Math.sqrt(3);
		
		x[0][0] = 0.0;
		y[0][0] = 0.0;
		
		x[0][1] = 1.0;
		y[0][1] = 0.0;
		
		x[0][2] = 0.5;
		y[0][2] = 1/(sqrt3*2);
		
		

		
		double befX[][] = new double[Mmax][3];
		double befY[][] = new double[Mmax][3];
		
		int n;
		
		for(n=1 ; n<=limitF ; n++){
			for(m=0 ; m<M ; m++){
				// 座標保存
				befX[m][0] = x[m][0];
				befY[m][0] = y[m][0];
				befX[m][1] = x[m][1];
				befY[m][1] = y[m][1];
				befX[m][2] = x[m][2];
				befY[m][2] = y[m][2];
				
				// f1
				x[m][0] = befX[m][2];
				y[m][0] = befY[m][2];
				
				x[m][1] = befX[m][0];
				y[m][1] = befY[m][0];
				
				x[m][2] = befX[m][0] + (befX[m][1] - befX[m][0])/3;
				y[m][2] = befY[m][0] + (befY[m][1] - befY[m][0])/3;
				
				// f2
				x[m+M][0] = befX[m][1];
				y[m+M][0] = befY[m][1];
				
				x[m+M][1] = befX[m][2];
				y[m+M][1] = befY[m][2];
				
				x[m+M][2] = befX[m][1] + (befX[m][0] - befX[m][1])/3;
				y[m+M][2] = befY[m][1] + (befY[m][0] - befY[m][1])/3;				
			}
			M *= 2;
		}
		
		boolean someLines = false;
		
		for(m=0 ; m<M ; m++){
			if(someLines){
				for(i=0 ; i<3 ; i++){
					System.out.println(x[m][i] + "\t" + y[m][i]);
				}
				System.out.println(x[m][0] + "\t" + y[m][0]);
				System.out.println();
			}else{
				System.out.println(x[m][0] + "\t" + y[m][0]);
				System.out.println(x[m][2] + "\t" + y[m][2]);
				System.out.println(x[m][1] + "\t" + y[m][1]);
				System.out.println();
			}			
		}
		
	}
}
