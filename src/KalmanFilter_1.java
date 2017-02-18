import java.io.File;
import java.io.PrintWriter;


public class KalmanFilter_1 {
	public static void main(String[] args) throws Exception{
		PrintWriter pw1,pw2 = null;
		int tmax = 21;
		
		double a = 1.0;
		double s = 1.0;
		double as = a*s;
		
		double[] x  = new double[tmax];
		double[] xp = new double[tmax];
		double[] V  = new double[tmax];
		double[] Vp = new double[tmax];
		
		double std;
		double Q,e,S,K;
		
		// Data
		double y[] ={13094.58984,12933.17969,12914.66016,13254.88965,13124.99023,13168.41016,13430.91016,
				13303.59961,13023.0498,12956.7998,13019.41016,13165.4502,12865.0498,12851.69043,12752.20996,
				12666.04004,12878.66016,12778.70996,12752.95996,12768.25,13072.87012,12834.17969,12609.46973,
				12689.58984,12557.66016,12212.23047,12624.45996,12400.65039,12346.62988,12102.5,12214.75977,
				11609.71973,11749.79004,11489.2998,11920.86035,12090.58984,12115.03027,12006.53027,11893.16016,
				11743.61035,11259.86035,11368.25977,11154.75977,10938.13965,10473.08984,10155.90039,9203.32031,
				9157.49023,8276.42969,9447.57031,9547.46973,8458.4502,8693.82031,9005.58984,9306.25,8674.69043,
				8460.98047,7649.08008,7162.8999,7621.91992,8211.90039,9029.75977,8576.98047};
		int T = y.length; //(=63)
		
		// 初期状態
		x[0] = y[0];
		V[0] = s*s;
		double xBef = x[0];
		
		// 予想 フィルタ
		Q = as*as;
		for(int t=1;t<tmax;t++){
			// 予想 x->xp
//			xp[t] = x[t-1];// 1階
			if(t==1) xp[t] = x[t-1];
			else xp[t] = 2*x[t-1] - x[t-2];
			// 予想 V-Vp
			Vp[t] = V[t-1] + Q;
			// フィルタ
			e = y[t] - xp[t];
			S = s*s + Vp[t];
			K = Vp[t] / S;
			// フィルタ xの更新
			x[t] = xp[t] + K*e;
			// フィルタ Vの更新
			V[t] = (1-K) * Vp[t];
			
			xBef = xp[t];
		}		
		pw1 = new PrintWriter(new File("filtering.dat"));
		for(int t=0;t<tmax;t++) pw1.println(t + "\t" + x[t]);
		pw1.close();
		
		
		// 平滑化
		double[] xs = new double[tmax];
		double[] Vs = new double[tmax];
		double A;
		xs[tmax-1] = x[tmax-1];
		Vs[tmax-1] = V[tmax-1];
		for(int t=tmax-2 ; t>=0 ; t--){
			A = V[t] / Vp[t+1];
			xs[t] = x[t] + A*(xs[t+1] - xp[t+1]);
			System.out.println((xs[t+1] - xp[t+1]));
			Vs[t] = V[t] + A*(Vs[t+1] - Vp[t+1])*A;
		}
		pw2 = new PrintWriter(new File("smoothing.dat"));
		for(int t=0;t<tmax;t++) pw2.println(t + "\t" + xs[t]);
		pw2.close();
		
	}
}
