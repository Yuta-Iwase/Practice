import java.util.Arrays;
import java.util.Comparator;

public class ArraysSortTest {

	public static void main(String[] args) {
		// {1,2,3,4,5}をシャッフルさせる。
		// これを何度も行い、"1"が何番目に来たかをカウントしていった。
		// 均等にシャッフルしているなら、どの場所にも20%の確率で来るはずである。

		int[] counter = new int[5];

		for(int t=0;t<10000000;t++){
			Number[] list = {1,2,3,4,5};
			Arrays.sort(list, new Comparator<Number>() {
				public int compare(Number a, Number b) {
					double r = Math.random();
					if(r<0.5){
						return 1;
					}else{
						return -1;
					}
				}
			});
			for(int i=0;i<list.length;i++){
				if(list[i].intValue()==1){
					counter[i]++;
				}
			}
		}

		for(int i=0;i<5;i++){
			System.out.println("counter[" + i + "]:\t" + counter[i]);
		}

	}

}
