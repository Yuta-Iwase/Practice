import java.util.ArrayList;

public class aaaa {

	public static void main(String[] args) {
		int times = 10000;
		long stamp = System.currentTimeMillis();
		ArrayList<Integer> aa = new ArrayList<>();
		int[] bb = new int[times];

		for(int t=0;t<1000;t++) {
			for(int i=0;i<times;i++) {
				aa.add(i);
			}
			for(int i=0;i<times;i++) {
				int temp = aa.get(i);
			}
		}

//		for(int t=0;t<1000;t++) {
//			for(int i=0;i<times;i++) {
//				bb[i] = i;
//			}
//			for(int i=0;i<times;i++) {
//				int temp = bb[i];
//			}
//		}

		System.out.println(System.currentTimeMillis()-stamp);
	}

}
