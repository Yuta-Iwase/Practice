// ブラックバスの駆除シミュレーション

public class BlackBassTest {
	public static void main(String[] args) {
		int time = 100;
		
		int adult = 500;
		int child = 700;
		int egg = 700;
		
		// 大人×pre_a_c=捕食される子供の数
		double pre_a_c = 0.1;
		// 親が卵を生む倍率
		double bring_egg = 2.0;
		// 大人>子供のときの処理
		double survival = 0.8;
		// 駆除される大人の倍率
		double extermination_rate = 0.6;
		
		for(int i=0;i<time;i++){
			System.out.println(i + "年目：");
			System.out.println("大人:" + adult);
			System.out.println("子供:" + child);
			System.out.println("卵:" + egg);
			System.out.println();
			
			// 子供捕食
			child -= (int)(adult*pre_a_c);
			// 成長(大人は死亡)
			adult = child;
			child = egg;
			// 駆除
			adult -= (int)(adult*extermination_rate);
			// 卵を生む
			egg = (int)(adult*bring_egg);
			
			// 大人>子供なら (餌の数が足りないため) 大人=子供となる
			if(adult > child) adult=(int)(child*survival);
			
		}

	}
}
