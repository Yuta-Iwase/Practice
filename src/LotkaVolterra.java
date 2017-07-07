import java.io.File;
import java.io.PrintWriter;

public class LotkaVolterra{
	public static void main(String[] args) throws Exception{
		// パラメータ定義
		int T = 100;
		double delta_t = 0.01;
		double u0 = 50;
		double v0 = 100;
		double a = 60;
		double b = 40;

		// 以降計算
		PrintWriter pw_u = new PrintWriter(new File("LotkaVolterra/predator.dat"));
		PrintWriter pw_v = new PrintWriter(new File("LotkaVolterra/prey.dat"));
		double u = u0;
		double v = v0;
		pw_u.println(0 + "\t" + u);
		pw_v.println(0 + "\t" + v);
		double next_u, next_v;
		for(int i=1;i<=T;i++){
			next_u = u / (1 - delta_t*(v-b));
			next_v = v*(1+delta_t*a) - delta_t*v*next_u;
			u = next_u;
			v = next_v;
			pw_u.println(i + "\t" + u);
			pw_v.println(i + "\t" + v);
			System.out.println(u);
			System.out.println(v);
			System.out.println();
		}

		pw_u.close();
		pw_v.close();
	}
}
