// https://codeiq.jp/q/2513
import java.util.ArrayList;
import java.util.Scanner;

public class CodeIQ2515 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int ini_c500=0,ini_c100=0,ini_c50=0,ini_c10=0,ini_c5=0,ini_c1=0;
		int instant_N;
		instant_N = N;
		while(instant_N-500>=0) {
			ini_c500++;
			instant_N-=500;
		}
		while(instant_N-100>=0) {
			ini_c100++;
			instant_N-=100;
		}
		while(instant_N-50>=0) {
			ini_c50++;
			instant_N-=50;
		}
		while(instant_N-10>=0) {
			ini_c10++;
			instant_N-=10;
		}
		while(instant_N-5>=0) {
			ini_c5++;
			instant_N-=5;
		}
		while(instant_N-1>=0) {
			ini_c1++;
			instant_N-=1;
		}

		ArrayList<pattern> queue = new ArrayList<>();
		queue.add(new pattern(ini_c500, ini_c100, ini_c50, ini_c10, ini_c5, ini_c1));
//		ArrayList<pattern> visited = new ArrayList<>();
//		visited.add(new pattern(ini_c500, ini_c100, ini_c50, ini_c10, ini_c5, ini_c1));
		boolean visit;
		int c=1;

		while(!queue.isEmpty()) {
			pattern currentPattern = queue.get(0);
			queue.remove(0);

			for(int i=0;i<currentPattern.stubList.size();i++) {
				pattern exchangedPattern = currentPattern.exchange(currentPattern.stubList.get(i));
//				exchangedPattern.print();

				visit = false;
//				for(int j=0;j<visited.size();j++) {
//					if(exchangedPattern.comp(visited.get(j))) {
//						visit = true;
//						break;
//					}
//				}
				for(int j=0;j<queue.size();j++) {
					if(exchangedPattern.comp(queue.get(j))) {
						visit = true;
						break;
					}
				}
				if(!visit) {
					queue.add(exchangedPattern);
//					visited.add(exchangedPattern);
					c++;
				}
			}
		}

		System.out.println(c);

		scan.close();
	}

}

class pattern{
	int c500,c100,c50,c10,c5,c1;
	ArrayList<Integer> stubList;
	public pattern(int a,int b,int c,int d,int e,int f) {
		c500=a;
		c100=b;
		c50=c;
		c10=d;
		c5=e;
		c1=f;

		stubList = new ArrayList<>();
		calcStub();
	}

	void calcStub() {
		stubList.clear();
		if(c500>0) stubList.add(500);
		if(c100>0) stubList.add(100);
		if(c50>0) stubList.add(50);
		if(c10>0) stubList.add(10);
		if(c5>0) stubList.add(5);
	}

	pattern exchange(int m) {
		if(m==500) return (new pattern(c500-1, c100+5, c50, c10, c5, c1));
		if(m==100) return (new pattern(c500, c100-1, c50+2, c10, c5, c1));
		if(m==50) return (new pattern(c500, c100, c50-1, c10+5, c5, c1));
		if(m==10) return (new pattern(c500, c100, c50, c10-1, c5+2, c1));
		if(m==5) return (new pattern(c500, c100, c50, c10, c5-1, c1+5));
		return null;
	}

	boolean comp(pattern p) {
		boolean result = false;
		if(c500==p.c500 && c100==p.c100 && c50==p.c50 && c10==p.c10 && c5==p.c5 && c1==p.c1) {
			result = true;
		}
		return result;
	}

	void print() {
		System.out.println("c500=" + c500 + " c100=" + c100 + " c50=" + c50 + " c10=" + c10 + " c5=" + c5 + " c1=" + c1);
	}
}
