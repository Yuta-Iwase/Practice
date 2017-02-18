import java.io.File;
import java.io.PrintWriter;

public class ParticleFilter {
	
	public static void main(String[] args) throws Exception{
		PrintWriter pw  = null;
		PrintWriter pw1 = null;
		
		int n,t; //for分で使う変数

		// 株価データ読み込み&表示
		double y[] ={13094.58984,12933.17969,12914.66016,13254.88965,13124.99023,13168.41016,13430.91016,
				13303.59961,13023.0498,12956.7998,13019.41016,13165.4502,12865.0498,12851.69043,12752.20996,
				12666.04004,12878.66016,12778.70996,12752.95996,12768.25,13072.87012,12834.17969,12609.46973,
				12689.58984,12557.66016,12212.23047,12624.45996,12400.65039,12346.62988,12102.5,12214.75977,
				11609.71973,11749.79004,11489.2998,11920.86035,12090.58984,12115.03027,12006.53027,11893.16016,
				11743.61035,11259.86035,11368.25977,11154.75977,10938.13965,10473.08984,10155.90039,9203.32031,
				9157.49023,8276.42969,9447.57031,9547.46973,8458.4502,8693.82031,9005.58984,9306.25,8674.69043,
				8460.98047,7649.08008,7162.8999,7621.91992,8211.90039,9029.75977,8576.98047};
		int T = y.length; //(=63)
		for(t=0;t<T;t++) System.out.println(t + "\t" + y[t]);
		
		// 1.初期分布
		int nmax=100;
		double[] x = new double[nmax];
		double v0,s=100;
		double pi = Math.PI;
		double r1,r2;
		for(n=0;n<nmax;n++){
			// 乱数生成
			r1=Math.random();
			r2=Math.random();
			v0 = y[0] + s * Math.sqrt(-2*Math.log(r1)) * Math.cos(2*pi*r2);
			// 粒子生成
			x[n] = v0;
		}
		
		
		int tmax=56;
		double L, R, average, variance, std;
		double sum;
		double[] xBef = new double[nmax];
		for(n=0;n<nmax;n++) xBef[n]=x[n];
		L = 0.0; //対数スケール出力用
//		for(t=1;t<=tmax;t++){
			// 4.出力
			pw  = new PrintWriter(new File("pfiltering.dat"));
			pw1 = new PrintWriter(new File("pfilteringav.dat"));
			for(t=1;t<=tmax;t++){
				//print
				for(n=0;n<nmax;n++){
					pw.println((t-1) + "\t" + x[n]);
				}
				sum = 0.0;
				for(n=0;n<nmax;n++){
					sum += x[n];
				}
				average = sum/nmax;
				sum = 0.0;
				for(n=0;n<nmax;n++){
					sum += (x[n]-average) * (x[n]-average);
				}
				variance = sum/nmax;
				std = Math.sqrt(variance); //STandard Deviation(標準偏差)
				System.out.println(t + "\t" + average + "\t" + std); 
				pw1.println((t-1) + "\t" + average);
				
				// 2.予測分布
				double[] xp = new double[nmax];
				double v;
				double a=1.0;
				double as=a*s;
				for(n=0;n<nmax;n++){
					// 乱数生成
					r1=Math.random();
					r2=Math.random();
					v = as * Math.sqrt(-2*Math.log(r1)) * Math.cos(2*pi*r2);
					// 予測分布生成
//					xp[n] = x[n] +v; //1階トレンド・モデル
					xp[n] = 2*x[n] -xBef[n] +v; // 2階トレンド・モデル
				}
				
				for(n=0;n<nmax;n++) xBef[n]=x[n];
				
				// 3.フィルタ分布
				double[] w = new double[nmax];
				double ss2 = 2*s*s;
				double sqrt2pis = Math.sqrt(2*pi) * s;
				sum = 0.0;
				// 3.(1)
				for(n=0;n<nmax;n++){
					w[n] = Math.exp(-(y[t]-xp[n])*(y[t]-xp[n])/ss2)/sqrt2pis;
					w[n] = Math.exp(-(y[t]-xp[n])*(y[t]-xp[n])/(2*s*s))/Math.sqrt(2*pi*s*s);
					sum += w[n];
				}
				L += Math.log(sum);
				// 3.(2)
				for(n=0;n<nmax;n++){
					w[n] /= sum;
				}

				// 3.(3)
				sum = 0.0;
				int np=0;
				int N = nmax;
				int j=0,jmax;
				for(n=0;n<nmax;n++){
					np = (int)(w[n]*nmax); //端数切捨ての粒子数
					w[n] -= np/(double)nmax; //端数分を分配するときに必要となる確率(後に*(N/sum)とすることで完成)
					sum += w[n]; //全事象の和を集計

					N -= np; //グラフ上でまだ定義されていない粒子数

					jmax = j+np;
					for(int k=j;k<jmax;k++){ //インデックスj～jmaxまでのnp個をforで回す
						x[k] = xp[n];
					}
					j = jmax; //次ループ用にjを更新
				}

				// 3.(4)
				for(n=0;n<nmax;n++) w[n] *= N/sum;    //w[n]を規格化
				R = N +Math.random();
				j = N;
				int k;
				for(n=0;n<nmax;n++){
					R -= w[n];
					k = (int)R;
					if(j != k){
						x[nmax-N] = xp[n];
						--N;
					}
					j = k;
				}
			}
			pw.close();
			pw1.close();
			
//		}

	}
}
