
public class RetireTest {

	public static void main(String[] args) {
		double[] p = new double[6];
		for(int n=0;n<6;n++){
			p[n] = comb(5, n)*Math.pow(0.5, 5);
		}

		double R;
		double b;
		int currentDigit;
		String currentCode;
		double[] result = new double[64];

		double maxE = 0;
		String maxCode = "";
		for(int i=0;i<64;i++){
			R = 0;
			b = 1.0;
			currentCode = "";
			for(int n=0;n<6;n++){
				currentDigit = digit(i, n+1);
				if(currentDigit==1){
					R += n * p[n];
				}else{
					b -= p[n]*0.5;
				}
				currentCode = currentDigit + currentCode;
			}
			result[i] = R / b;
			System.out.println(currentCode + "\t" + result[i] + "\tR=" + R + "\tb=" + b);


			if(maxE < result[i]){
				maxE = result[i];
				maxCode = currentCode;
			}
		}

		System.out.println();
		System.out.println("max is");
		System.out.println(maxCode + "\t" + maxE);
	}

	public static double comb(int left,int right){
		long a=1,b=1;
		for(int i=0;i<right;i++){
			a *= (left-i);
			b *= (right-i);
		}
		double result = (double)a/b;

		return result;
	}

	public static int digit(int n, int digit){
		int x;
		int b = ((int)Math.pow(2, digit-1));
		x = n/b;

		int result;
		result = x%2;

		return result;
	}

}
