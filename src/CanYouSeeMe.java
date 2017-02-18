import java.util.Scanner;

public class CanYouSeeMe {
	// 二点のオブジェクトを渡し、成す直線と原点との距離を求めるメソッド
	// @param p1  点P1のオブジェクト
	// @param p2  点P2のオブジェクト
	// @return distance 原点と直線の距離
	public double measureDistance(OnePoint p1, OnePoint p2) {
		// returnで返す点と直線の距離
		double distance;

		// 零除算を起こさないために場合分け
		if (p1.getX() == p2.getX()) {
			// 二点のX座標が等しいときその座標が原点との距離となる。
			distance = p1.getX();
		} else {
			// 簡単のため、点P1から点P2への傾きをあらかじめ求めておく。
			double slope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
			// 点と直線の距離の公式に代入
			distance = Math.abs(-p1.getX() * slope + p1.getY())/ Math.sqrt(Math.pow(slope, 2) + Math.pow(-1, 2));
		}
		
		// 距離distanceを返す。
		return distance;
	}
	
	// 二点を通る線分内に、ある点(下記の通り)が存在するか確かめるメソッド
	// ある点―二点を通る直線とその直線に直交する直線との交点
	// @param p1  点P1のオブジェクト
	// @param p2  点P2のオブジェクト
	// @return p 交点が線分内にあるかの真偽
	public boolean checkIntersection(OnePoint p1, OnePoint p2){
		// measureDistance同様、点P1から点P2への傾きをあらかじめ求めておく。
		double slope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
		// 交点(前述)のx座標を求め、それを『x』とする。
		double x = (slope/(Math.pow(slope, 2) + 1)) * (p1.getX() * slope - p1.getY());
		// 交点(前述)のx座標が、二点の成分の間にあるか真偽を調べそれを『p』とする。
		boolean p = Math.min(p1.getX(), p2.getX()) <= x   &&   x <= Math.max(p1.getX(), p2.getX());
		// 真偽pを返す。
		return p;
	}
	
	// オブジェクト生成
	private static Scanner keyBoardScanner;
	private static CanYouSeeMe canYouSeeMe;

	public static void main(String[] args) {
		// 生成する組数
		int n;
		// 円 C1 の半径
		double a;
		// n組のうち見通せるものを数える変数
		int seeable = 0;
		// オブシェクト定義
		keyBoardScanner = new Scanner(System.in);
		canYouSeeMe = new CanYouSeeMe();

		// キーボード入力で組数を受け付ける
		// nが0以下の整数ならばやり直し
		do {
			System.out.print("生成する組数(正の整数) = ");
			n = keyBoardScanner.nextInt();
		} while (n <= 0);

		// キーボード入力で半径を受け付ける
		// 0<a<1を満たさないのであればやり直し
		do {
			System.out.print("円 C1 の半径(0<a<1なる実数) = ");
			a = keyBoardScanner.nextDouble();
		} while (!(0 < a && a < 1));

		// n回 点p1,p2を取り可視性を調べ、数え上げる。
		// forの書式がやや特異なものであることに注意する。
		for (int i = 1; i <= n; i++) {
			// 半径aのオブジェクトp1,p2を生成
			OnePoint p1 = new OnePoint(a);
			OnePoint p2 = new OnePoint(a);
			// メッセージ表示
			System.out.printf("%6d 組目の P1(%8.5f, %8.5f), P2(%8.5f, %8.5f) --> ", i,p1.getX(),p1.getY(), p2.getX(), p2.getY());
			// p1,p2によって作られた線分が円C1と交わるか調べる。
			// 「(点と直線の距離) <= a  且つ  交点が線分内にある」を満たすとき見通せない
			// それ以外ならば見通せる。
			if(canYouSeeMe.measureDistance(p1, p2) <= a && canYouSeeMe.checkIntersection(p1, p2)){
				System.out.println("見通せません。");
			}else{
				System.out.println("見通せます。");
				// カウント増加
				seeable++;
			}				
		}
		
		// 結果表示
		// ハイフン左23個、右13個
		System.out.println("-----------------------まとめ-------------");
		System.out.printf("領域Ｂ内に選んだ P1, P2 の組数 = %8d\n", n);
		System.out.printf("　　その内、互いに見通せた組数 = %8d\n", seeable);
		System.out.printf("　　　　　　互いに見通せた確率 = %8.5f\n", ((double)seeable/n));

	}

}
