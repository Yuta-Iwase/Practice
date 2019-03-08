import java.util.Random;

public class FisherYatesBox {

	public static void main(String[] args) {
		long seed = 1234;
		Random rnd = new Random(seed);
		
		int N = 20;
		int[] list = new int[N];
		for(int i=0;i<N;i++) {
			list[i] = i;
		}
		
		int[] box = new int[N];
		for(int i=0;i<N;i++) {
			box[i] = list[i];
		}
		
		int[] shuffledList = new int[N];
		int rem = N;
		for(int i=0;i<N;i++) {
			int popIndex = rnd.nextInt(rem);
			int pop = box[popIndex];
			
			if(popIndex != rem-1) {
				int temp = box[popIndex];
				box[popIndex] = box[rem-1];
				box[rem-1] = temp;
				
			}else {
				//do nothing
			}
			rem--;
			shuffledList[i] = pop;
			System.out.println(pop);
		}
		
		

	}

}
 