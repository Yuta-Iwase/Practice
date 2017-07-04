import java.io.File;
import java.io.PrintWriter;

public class LotkaVolterra{
	public static void main(String[] args) throws Exception{
		// パラメータ定義
		int T = 10;
		double u0 = 500;
		double v0 = 10000;
		double a = 10;
		double b = 10;

		// 以降計算
		PrintWriter pw_u = new PrintWriter(new File("LotkaVolterra/predator.csv"));
		PrintWriter pw_v = new PrintWriter(new File("LotkaVolterra/prey.csv"));
		double u = u0;
		double v = v0;
		pw_u.println(0 + "," + u);
		pw_v.println(0 + "," + v);
		double next_u, next_v;
		for(int i=1;i<=T;i++){
			next_u = (u + u*(v-b));
			next_v = (v + v*(a-u));
			u = next_u;
			v = next_v;
			pw_u.println(i + "," + u);
			pw_v.println(i + "," + v);
			System.out.println(u);
			System.out.println(v);
			System.out.println();
		}

		pw_u.close();
		pw_v.close();
	}
}
