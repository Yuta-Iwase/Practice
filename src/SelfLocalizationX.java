
public class SelfLocalizationX {
	//static属性(メソッド等からもアクセス可能な)変数宣言
	// 各フェーズに書かれた順に宣言することとする
	// 記述無し
	final static int tmax=50;
	final int N0 = 100; //単位変化用(cm→m)
	static int t;
	static double[] x  = new double[tmax];
	static double[] dx = new double[tmax];
	
	// (1)変数宣言
	final static double Lx=5.0, Ly=3.0;
	
	// (3)変数宣言
	final static int kmax=8; //センサー(で測ることのできる方位の)数
	static double d[] = new double[kmax]; //各方位の距離
	final static int[] directionX = { 0, 1, 1, 1, 0,-1,-1,-1}; //各方位におけるx方向の単位距離
	final static int[] directionY = { 1, 1, 0,-1,-1,-1, 0, 1}; //各方位におけるy方向の単位距離
	final static double[] stepL = {1.0,Math.sqrt(2)}; // x,y方向ごとの移動距離、偶数なら1.0、奇数なら√2
	
	
	public static void main(String[] args) {
		moving();
		
		
		
		
		
		
	}
	
	// (2)処理部分
	protected static void moving(){
		/// 自走行動処理
		// x方向
		// 移動距離を設定
		dx[t] = Math.random()-0.5;
		x[t] = x[t-1] + dx[t];
		// 壁に10cmまで近づいたときの処理
		if(x[t] < 0.1) x[t]=0.1;
		else if(x[t] > Lx-0.1) x[t]=Lx-0.1;
		dx[t] = x[t]-x[t-1];
		// y方向
		dx[t] = Math.random()-0.5;
		x[t] = x[t-1] + dx[t];
		// 壁に10cmまで近づいたときの処理
		if(x[t] < 0.1) x[t]=0.1;
		else if(x[t] > Lx-0.1) x[t]=Lx-0.1;
		dx[t] = x[t]-x[t-1];
	}
	
	// (3)処理部分
	protected static void distance(){
		
	}
	
	
	
	
	
	
}
