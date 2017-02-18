import java.io.File;
import java.io.PrintWriter;


public class KalmanFilter_2 {
	public static void main(String[] args) throws Exception{
		PrintWriter pw1,pw2 = null;
		int tmax = 56;
		
		double a = 1.0;
		double s = 1.0;
		double as = a*s;
		
		double[][] x  = new double[tmax][2];
		double[][] xp = new double[tmax][2];
		Matrix[] V = new Matrix[tmax];
		for(int i=0;i<tmax;i++)V[i]=new Matrix(2);
		Matrix[] Vp = new Matrix[tmax];
		for(int i=0;i<tmax;i++)Vp[i]=new Matrix(2);
		
		double std;
		double Q;
		double[] K = new double[2];
		
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
		x[0][0] = y[0];
		x[1][0] = y[1];
		x[1][1] = y[0];
		V[1].set(0,0, s*s);
		V[1].set(0,1, 0.0);
		V[1].set(1,0, 0.0);
		V[1].set(1,1, s*s);
		
		// 予想 フィルタ
		Q = as*as;
		for(int t=2;t<tmax;t++){
			// 予想 x->xp
			xp[t][0] = 2*x[t-1][0] - x[t-1][0];
			xp[t][1] = x[t-1][0];
			// 予想 V->Vp
			Vp[t].set(0,0,
					4*V[t-1].get(0,0)-2*V[t-1].get(0,1)-2*V[t-1].get(1,0)+V[t-1].get(1,1)
					);
			Vp[t].set(0,1,
					2*V[t-1].get(0,0)-V[t-1].get(1,0)
					);
			Vp[t].set(1,0,
					2*V[t-1].get(0,0)-V[t-1].get(0,1)
					);
			Vp[t].set(1,1,
					V[t-1].get(0,0)
					);
			// フィルタ
			K[0] = Vp[t].get(0,0) / (s*s + Vp[t].get(0,0));
			K[1] = Vp[t].get(1,0) / (s*s + Vp[t].get(0,0));
			// フィルタ xの更新
			x[t][0] = xp[t][0] + K[0]*(y[t]-xp[t][0]);
			x[t][1] = xp[t][1] + K[1]*(y[t]-xp[t][0]);
			// フィルタ Vの更新
			V[t].set(0,0,
					Vp[t].get(0,0)-K[0]*Vp[t].get(0,0)
					);
			V[t].set(0,1,
					Vp[t].get(0,1)-K[0]*Vp[t].get(0,1)
					);
			V[t].set(1,0,
					Vp[t].get(1,0)-K[1]*Vp[t].get(0,0)
					);
			V[t].set(0,1,
					Vp[t].get(1,1)-K[1]*Vp[t].get(0,1)
					);
		}
		pw1 = new PrintWriter(new File("filtering.dat"));
		for(int t=0;t<tmax;t++) pw1.println(t + "\t" + x[t][0]);
		pw1.close();
		
		
		// 平滑化
		double[][] xs = new double[tmax][2];
		Matrix[] Vs = new Matrix[tmax];
		for(int i=0;i<tmax;i++)Vs[i]=new Matrix(2);
		Matrix A = new Matrix(2);
		xs[tmax-1][0] = x[tmax-1][0];
		xs[tmax-1][1] = x[tmax-1][1];
		Vs[tmax-1].set(0,0, V[tmax-1].get(0,0));
		Vs[tmax-1].set(0,1, V[tmax-1].get(0,1));
		Vs[tmax-1].set(1,0, V[tmax-1].get(1,0));
		Vs[tmax-1].set(1,1, V[tmax-1].get(1,1));
		double detVp;
		double[] arg = new double[4];
		double gosa = 300.0; //☆
		for(int t=tmax-2 ; t>=0 ; t--){
			detVp = Vp[t+1].get(0,0)*Vp[t+1].get(1,1)-Vp[t+1].get(0,1)*Vp[t+1].get(1,0);
			A.set(0,0,
					((2*V[t].get(0,0)-V[t].get(0,1))*(     Vp[t+1].get(1,1))+V[t].get(0,0)*((-1)*Vp[t+1].get(1,0)))/detVp
					);
			A.set(0,1,
					((2*V[t].get(0,0)-V[t].get(0,1))*((-1)*Vp[t+1].get(0,1))+V[t].get(0,0)*(     Vp[t+1].get(0,0)))/detVp
					);
			A.set(1,0,
					((2*V[t].get(1,0)-V[t].get(1,1))*(     Vp[t+1].get(1,1))+V[t].get(1,0)*((-1)*Vp[t+1].get(1,0)))/detVp
					);
			A.set(1,1,
					((2*V[t].get(1,0)-V[t].get(1,1))*((-1)*Vp[t+1].get(0,1))+V[t].get(1,0)*(     Vp[t+1].get(0,0)))/detVp
					);
			xs[t][0] = x[t][0]+A.get(0,0)*(xs[t+1][0]-xp[t+1][0])+A.get(0,1)*(xs[t+1][1]-xp[t+1][1]);
			xs[t][1] = x[t][1]+A.get(1,0)*(xs[t+1][0]-xp[t+1][0])+A.get(1,1)*(xs[t+1][1]-xp[t+1][1]);
			System.out.println(x[t][0] + "\t" + ((xs[t+1][0]-xp[t+1][0])) + "\t" + ((xs[t+1][1]-xp[t+1][1])));
			System.out.println(x[t][1] + "\t" + ((xs[t+1][0]-xp[t+1][0])) + "\t" + ((xs[t+1][1]-xp[t+1][1])));
			System.out.println(A.get(0,0));
			System.out.println(A.get(0,1));
			System.out.println(A.get(1,0));
			System.out.println(A.get(1,1));
			System.out.println();
			arg[0] = A.get(0,0)*(Vs[t+1].get(0,0)-Vp[t+1].get(0,0))+A.get(0,1)*(Vs[t+1].get(1,0)-Vp[t+1].get(1,0));
			arg[1] = A.get(0,0)*(Vs[t+1].get(0,1)-Vp[t+1].get(0,1))+A.get(0,1)*(Vs[t+1].get(1,1)-Vp[t+1].get(1,1));
			arg[2] = A.get(1,0)*(Vs[t+1].get(0,0)-Vp[t+1].get(0,0))+A.get(1,1)*(Vs[t+1].get(1,0)-Vp[t+1].get(1,0));
			arg[3] = A.get(1,0)*(Vs[t+1].get(0,1)-Vp[t+1].get(0,1))+A.get(1,1)*(Vs[t+1].get(1,1)-Vp[t+1].get(1,1));
			Vs[t].set(0,0,
					V[t].get(0,0)+arg[0]*A.get(0,0)+arg[1]*A.get(0,1)
					);
			Vs[t].set(0,1,
					V[t].get(0,1)+arg[0]*A.get(1,0)+arg[1]*A.get(1,1)
					);
			Vs[t].set(1,0,
					V[t].get(1,0)+arg[2]*A.get(0,0)+arg[3]*A.get(0,1)
					);
			Vs[t].set(1,1,
					V[t].get(1,1)+arg[2]*A.get(1,0)+arg[3]*A.get(1,1)
					);
		}
		pw2 = new PrintWriter(new File("smoothing.dat"));
		for(int t=0;t<tmax;t++) pw2.println(t + "\t" + xs[t][0]);
		for(int t=0;t<tmax;t++) System.out.println(t + "\t" + xs[t][0]);
		pw2.close();
		
	}
}
class Matrix{
	double[][] a;
	Matrix(int d){
		a = new double[d][d];
	}
	double get(int x,int y){
		return a[x][y];
	}
	void set(int x,int y,double input){
		a[x][y] = input;
	}
	void set(double[][] input){
		a = input;
	}
}
