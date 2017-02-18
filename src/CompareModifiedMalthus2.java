/* 必修問題1(4)用その2
 * dt=0.02,N=50とき、
 * マルサス関数を改良オイラー法で近似した関数を
 * (x方向に)0.02刻みで点を取り
 * その後、正規の解との差を(絶対値を利用して)出力する
 */

import java.io.*;

public class CompareModifiedMalthus2 {
	public static void main(String[] args) throws FileNotFoundException {
		// 書き込み用オブジェクトの生成
		PrintWriter pw = new PrintWriter(new File("CompareModifiedMalthus2.dat")); 
		
		// 近似した点を求める計算をN回行う
		int N = 50;
		
		// 近似値をxとおく(初期値は仮定より0.1)
		// なお、この値はfor文の周回の中で変動していく
		// グラフ上では「縦軸に」当たるため解釈に注意すること
		double x = 0.1;
		
		// 仮定より
		double alpha = 3.0;
		
		// x方向の刻む間隔をdtとする
		double dt = 0.02;
		
		// 初期条件下での近似値xと正規の解との差は0
		System.out.println(0 + "\t" + 0);
		
		for(int i=1 ; i<=N ; i++){
			// (改良していない)オイラー法より前の点を参照し新たに点を作る
			// その値をEulerとおく(すなわちEulerは改良前のxに等しい)
			double Euler = x + (alpha*x)*dt ;
			// 改良オイラー法による修正を行う
			x = x + ( alpha*x + alpha*Euler ) * dt / 2 ;
			// 正規の解0.1*Math.exp(alpha*(i*dt))と近似値xとの比較を行いその差をdifferenceとする
			double difference = Math.abs( x   -    0.1*Math.exp(alpha*(i*dt)));
			// 差を出力する
			System.out.println((i*dt) + "\t" + difference);
			pw.println((i*dt) + "\t" + difference);
		}
		
		pw.close();
		

	}
}
