// 乱数を用いてべき則に従う分布の次数を生成
// 「実験用」,「計測用」,「確認用」を抜いて構わない
public class PLDist {
	public static void main(String[] args) {
		int N=100000; //分布実験用,試行回数
		double gamma = 2.0; //べき分布のガンマ
		
		// 作業変数定義
		double r;
		double degree; 
		int[] dist = new int[100000]; //計測用
		for(int i=0;i<dist.length;i++) dist[i]=0; //計測用
		
		for(int i=0;i<N;i++){
			r = Math.random();
			degree = Math.pow((1-r),1/(1-gamma)); //次数生成
			dist[(int)degree]++; //計測用
		}
		
		// 分布プロット、ちゃんとべき分布になっているか確認用
		for(int i=0;i<dist.length;i++){
			if(dist[i] != 0) System.out.println(i + "\t" + dist[i]);
		}
	}
}
