package dataStructure;

public class Heap {

	private int n; // データ数
	private int[] heap; // 注意：heap[1]..heap[n]を使用
	private int maxSize;
	
	public Heap(int maxSize){
		n = 0;
		heap = new int[maxSize + 1]; // 上の注意から+1必要
		this.maxSize = maxSize;
	}
	
	public void pushHeap(int x){
		if (++n >= maxSize){
			System.out.println("Heap Overflow");
			System.exit(1);
		}
                heap[n] = x;
                int i = n;
                int j = i / 2; // 親
		while (j>0 && x>heap[j]){
                        heap[i] = heap[j];
                        i = j;
                        j = i/2;
		}
                heap[i] = x;
	}
	
	public int deleteMax(){
		if (n == 0){
			System.err.println("Heap Underflow");
			System.exit(1);
		}
		// 最大値(heap[1])をxに格納
		int x = heap[1];
		
		// 次の最大値を捜索
		heap[1] = heap[n--];
		int i = 1;
		while (i * 2 <= n){
			int j = i * 2;
			if (j + 1 <= n && heap[j] < heap[j + 1]) j++;
			if (heap[i] >= heap[j]) break;
			
			// heap[i]とheap[j]を交換
			int t = heap[i];
			heap[i] = heap[j];
			heap[j] = t;
			
			i = j;
		}
		
		// 最大値を出力
		return x;
	}
	
	public static void main(String[] args) {
		Heap myHeap = new Heap(999);

		for (int k = 1; k <= 10; k++){
			for (int i = 1; i <= 2 * k; i++){
				myHeap.pushHeap(i);
			}
			for (int i = 1; i <= k * k; i++){
				System.out.print(myHeap.deleteMax() + " ");
			}		
		}
	}

}
