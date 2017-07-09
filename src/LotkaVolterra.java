import java.io.File;
import java.io.PrintWriter;

public class LotkaVolterra{
	public static void main(String[] args) throws Exception{
		// パラメータ定義
		int T = 100;
		double delta_t = 0.02;
		double u0 = 50;
		double v0 = 100;
		double a = 60;
		double b = 40;

		// 以降計算
		PrintWriter pw_u = new PrintWriter(new File("LotkaVolterra/predator.dat"));
		PrintWriter pw_v = new PrintWriter(new File("LotkaVolterra/prey.dat"));
		double u = u0;
		double v = v0;
		// t=0の出力
		pw_u.println(0 + "\t" + u);
		pw_v.println(0 + "\t" + v);
		System.out.println(0 + "\t" + u);
		System.out.println(0 + "\t" + v);
		System.out.println();
		// t=1からTまでの出力
		double next_u, next_v;
		for(int t=1;t<=T;t++){
			// 区分オイラー法
			next_u = u / (1 - delta_t*(v-b));
			next_v = v*(1+delta_t*a) - delta_t*v*next_u;
			u = next_u;
			v = next_v;
			// 出力
			pw_u.println(t + "\t" + u);
			pw_v.println(t + "\t" + v);
			System.out.println(u);
			System.out.println(v);
			System.out.println();
		}

		pw_u.close();
		pw_v.close();
	}
}
