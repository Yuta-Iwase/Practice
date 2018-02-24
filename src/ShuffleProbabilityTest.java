import java.util.ArrayList;
import java.util.Date;

public class ShuffleProbabilityTest {

	// listの要素「1」が何番目に来たか集計しシャッフルの精度を測る
	public static void main(String[] args) {
		long time = (long)Math.pow(10, 8);

		System.out.println("開始: " + new Date().toString());
		System.out.println();
		long[] count = {0, 0, 0, 0, 0};
		for(long t=0;t<time;t++){
			int[] list = {1, 2, 3, 4, 5};
			shuffle(list);
			for(int i=0;i<list.length;i++){
				if(list[i]==1){
					count[i]++;
					break;
				}
			}
		}
		for(int i=0;i<count.length;i++){
			System.out.println((double)count[i]/time);
		}
		System.out.println();
		System.out.println("終了: " + new Date().toString());
	}

	private static void shuffle(int[] list){
		ArrayList<Integer> array = new ArrayList<>();
		for(int i=0;i<list.length;i++) array.add(list[i]);
		int remainingElements = array.size();
		for(int i=0;i<list.length;i++){
			int r = (int)(remainingElements*Math.random());
			list[i] = array.get(r);
			array.remove(r);
			remainingElements--;
		}
	}

}
