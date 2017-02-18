
public class SelfLocalization {
	static double Lx=5.0,Ly=3.0; //部屋の大きさ
	static int tmax=20; 
	static int kmax=8; //方位の数
	static int N0=100; 
	static int nxmax=(int)Lx*N0, nymax=(int)Ly*N0;
	static double d[] = new double[kmax]; //各方位での距離
	static int s[][] = new int[nxmax+1][nymax+1]; //部屋をマスで分ける
	
	
	public static void main(String[] args) {
		int k,n,t,N,nmax=100,jmax;
		
		// 部屋の壁際は1、内側は0
		for(int i=1;i<nxmax;i++){
			for(int j=1;j<nymax;j++){
				s[i][j] = 0;
			}
		}
		for(int j=0;j<=nymax;j++){
			s[0][j] = 1;
		}
		for(int j=0;j<=nymax;j++){
			s[nymax][j] = 1;
		}
		for(int i=1;i<nxmax;i++){
			s[i][0] = 1;
		}
		for(int i=1;i<nxmax;i++){
			s[i][nymax] = 1;
		}
		
		
		
		
	}
	
	static int t;
	static double[] roboX  = new double[tmax];
	static double[] dx = new double[tmax];
	static double[] roboY  = new double[tmax];
	static double[] dy = new double[tmax];
	// (2)処理部分
	protected static void moving(){
		/// 自走行動処理
		// x方向
		// 移動距離を設定
		dx[t] = Math.random()-0.5;
		roboX[t] = roboX[t-1] + dx[t];
		// 壁に10cmまで近づいたときの処理
		if(roboX[t] < 0.1) roboX[t]=0.1;
		else if(roboX[t] > Lx-0.1) roboX[t]=Lx-0.1;
		dx[t] = roboX[t]-roboX[t-1];
		// y方向
		dy[t] = Math.random()-0.5;
		roboY[t] = roboY[t-1] + dy[t];
		// 壁に10cmまで近づいたときの処理
		if(roboY[t] < 0.1) roboY[t]=0.1;
		else if(roboY[t] > Lx-0.1) roboY[t]=Lx-0.1;
		dy[t] = roboY[t]-roboY[t-1];
	}
	
	private static void distance(double x,double y){
		int i,j,nx,ny,nstep;
		final int[] di = { 0, 1, 1, 1, 0,-1,-1,-1};
		final int[] dj = { 1, 1, 0,-1,-1,-1, 0, 1};
		double[] ud ={1.0,Math.sqrt(2)};
		nx=(int)(x*N0+0.5);
		ny=(int)(y*N0+0.5);
		for(int k=0;k<kmax;k++){
			i=nx;
			j=ny;
			nstep=0;
			while(s[i][j]==0){
				i += di[k];
				j += dj[k];
				nstep++;
				
			}
			d[k] = nstep*ud[k%2]/N0;
//			if(d[k]>1.5) d[k] = 1.5;//(robotQ2.datの場合)
			
		}	
	}
	
	
	
	
	
	
	
}
