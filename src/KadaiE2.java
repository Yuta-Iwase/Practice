

import java.io.File;
import java.io.PrintWriter;

public class KadaiE2 {

	public static void main(String[] args) throws Exception {
		PrintWriter pw = null;
		pw = new PrintWriter(new File("testE.dat"));

		PrintWriter pw2 = new PrintWriter(new File("degreeList.csv"));
		PrintWriter pw3 = new PrintWriter(new File("degreeDist.txt"));

		

		int N =10000;
		int kmin = 2;
		int kcut = (int) (0.1*N);
		double gan = 2.7;
		double p[] = new double[kcut+1];
		//System.out.println(random);
		double sum = 0.0;
		double sum2 = 0.0;
		double[]pcum = new double[N];
		
		int[] dist = new int[kcut+1]; 
		

		//次数分布
		for(int k=kmin;k<=kcut;k++){
			p[k] = N*Math.pow(k,-gan);
			//System.out.println("次数分布"+k+"は"+p[k]);
			sum += p[k];
		}
		System.out.println("sum = " + sum);
		for(int k=kmin;k<=kcut;k++){
			p[k]/=sum;
			System.out.println("次数"+k+"の割合は"+p[k]);
			sum2 += p[k];
		}
		System.out.println("sum2 = " + sum2);

		//累積分布
		//cumulativeDistribution
		//System.out.println("累積分布"+0+"は"+pcum[0]);
		for(int i=1;i<=kcut;i++){
			pcum[i] = p[i] + pcum[i-1];
		//	System.out.println("累積分布"+i+"は"+pcum[i]);
		}

		//逆関数法＆頂点の割り当て
		int n[] = new int[N];
		int k2;
		double random;
		for(int i = 0;i<N;i++){
			k2 = kmin;
			random = Math.random();
			while (pcum[k2] < random){
				k2++;
			}
			if(Math.abs(pcum[k2]-random) < Math.abs(pcum[k2-1]-random)){
				n[i] = k2;
			}else if(k2 == 2){
				n[i] = k2;
			} else {
				n[i] = k2-1;
			}
			pw.println("頂点"+i+"の次数は"+n[i]+"です");
			pw2.println(i + "," + n[i]);
			dist[n[i]]++;
			//System.out.println("頂点"+i+"の次数は"+n[i]+"です");
		}
		
		for(int i=0;i<dist.length;i++){
			if(dist[i]>0){
				pw3.println(i + "\t" + dist[i]);
			}
		}
		

		pw.close();
		pw2.close();
		pw3.close();

	}
}
