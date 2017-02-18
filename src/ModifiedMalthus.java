/* 必修問題1(2)用
 * ここではマルサス関数を改良オイラー法で近似した関数を
 * (x方向に)0.01刻みで点を取り出力するプログラムを書く
 */

import java.io.*;

public class ModifiedMalthus {
	public static void main(String[] args) throws FileNotFoundException {
		// 書き込み用オブジェクトの生成
		PrintWriter pw = new PrintWriter(new File("ModifiedMalthus.dat")); 
		
		// 近似した点を求める計算をN回行う
		int N = 100;
		
		// 近似値をxとおく(初期値は仮定より0.1)
		// なお、この値はfor文の周回の中で変動していく
		// グラフ上では「縦軸に」当たるため解釈に注意すること
		double x = 0.1;
		
		// 仮定より
		double alpha = 3.0;
		
		// x方向の刻む間隔をdtとする
		double dt = 0.01;
		
		// 初期条件下での近似値の点を出力する
		System.out.println(0 + "\t" + x);
		
		for(int i=1 ; i<=N ; i++){
			// (改良していない)オイラー法より前の点を参照し新たに点を作る
			// その値をEulerとおく(すなわちEulerは改良前のxに等しい)
			double Euler = x + (alpha*x)*dt ;
			// 改良オイラー法による修正を行う
			x = x + ( alpha*x + alpha*Euler ) * dt / 2 ;
			// 現在の点情報を出力する
			System.out.println((i*dt) + "\t" + x);
			pw.println((i*dt) + "\t" + x);
		}
		
		pw.close();
		

	}
}
