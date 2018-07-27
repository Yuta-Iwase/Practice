import java.io.File;
import java.io.PrintWriter;

public class ProductTest {

	public static void main(String[] args) throws Exception{
		int N = 200;
		double gamma = 2.7;
		int kmin = 4;
		int w_cutoff = 4030;

//		int wmax = N*N;
		int wmax = w_cutoff;

		// 予めp(k)を出しておく
		double[] degreeDist = new double[N+1];
		for(int i=0;i<N+1;i++) {
			degreeDist[i] = Math.pow(i, -gamma);
		}

		PrintWriter pw = new PrintWriter(new File("C:\\desktop\\weightDist.csv"));
		double[] weightDist = new double[wmax+1];
		int[] divisors = new int[wmax+1];
		for(int w=1;w<=wmax;w++) {
			int kmax = w<N ? w : N;
			for(int k=kmin;k<=kmax;k++) {
				if(w%k==0 && w/k<=N && kmin<=w/k) {
					int k_prime = w/k;
					weightDist[w] += k * k_prime * degreeDist[k] * degreeDist[k_prime];
					divisors[w]++;
				}
			}
			if(weightDist[w]>0) {
				System.out.println(w + "\t" + weightDist[w] + "\t" + divisors[w]);
				pw.println(w + "," + weightDist[w] + "," + divisors[w]);
			}
		}

		pw.close();

	}

}
