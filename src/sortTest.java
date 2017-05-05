import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class sortTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int N = 10;
		ArrayList list = new ArrayList<Integer>();
		for(int i=0;i<N;i++){
			list.add(i+100);
			System.out.print((i+100) + " ");
		}
		System.out.println();
		System.out.println();
		
		int c=0;
		int r;
		int[][] a = new int[N][2];
		while(!list.isEmpty()){
			r = (int)(Math.random()*list.size());
			
			a[c][0] = c;
			a[c][1] = (int) list.get(r);
			
			list.remove(r);
			System.out.println(a[c][0] + "\t" + a[c][1]);
			c++;
		}
		
		System.out.println();
		
		int[][] sorted_a = new compTest(a,1,false).getArray();
		for(int i=0;i<sorted_a.length;i++){
			System.out.println(sorted_a[i][0] + "\t" + sorted_a[i][1]);
		}

	}

}
