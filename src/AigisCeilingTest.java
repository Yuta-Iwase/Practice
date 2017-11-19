// Ctrl+Enterで実行できます。


public class AigisCeilingTest {

	public static void main(String[] args) {
	    // 試行回数、大きくするとタイムアウトします。
		long N = 33L*(long)Math.pow(10, 6);

		// シミュレーション部分
		long loseCountBlack = 0;
		long loseCountWhite = 0;
		long sumBlack = 0;
		long sumWhite = 0;
		for(long i=0;i<N;i++){
			if(loseCountBlack>=33){
				sumBlack++;
				loseCountBlack=0;
				loseCountWhite++;
				continue;
			}

			if(loseCountWhite>=10){
				sumWhite++;
				loseCountBlack++;
				loseCountWhite=0;
				continue;
			}

			double r = Math.random();
			if(r < 0.13){
				if(r < 0.03){
					sumBlack++;
					loseCountBlack=0;
					loseCountWhite++;
					continue;
				}else{
					sumWhite++;
					loseCountBlack++;
					loseCountWhite=0;
					continue;
				}
			}else{
				loseCountBlack++;
				loseCountWhite++;
			}
		}

		System.out.println("試行回数：" + N);
		System.out.println("黒の期待値：" +  ((double)sumBlack)/N );
		System.out.println("白の期待値：" +  ((double)sumWhite)/N );

	}

}
