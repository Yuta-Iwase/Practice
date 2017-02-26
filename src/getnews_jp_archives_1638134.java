
// http://getnews.jp/archives/1638134

public class getnews_jp_archives_1638134 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		int N=107;
		
		int[] F = new int[N];
		
		double[] r = new double[N];
		
		double y = 1+Math.sqrt(3)/2;
		double naname = Math.sqrt(0.5*0.5 + y*y);
		
		r[0] = 1;
		F[0] = 1;
		for(int i=1;i<N;i++){
			r[i] = naname*r[i-1];
			F[i] = (int)(r[i]*r[i]);
			System.out.println(F[i]/106.0);
		}
		
	}

}
