package dataStructure;

// 配列によるキューの実現(pp.47-51)
public class QueueByArray {

	private int head; // データの範囲は、queue[head+1]...queue[tail]
	private int tail;
	private int[] queue;
	private int maxSize; // 注意:maxSize個のデータを入れた直後にOverflowエラーを起こす
	
	// コンストラクタ
	public QueueByArray(int maxSize){
		head = 0;
		tail = 0;
		queue = new int[maxSize];
		this.maxSize = maxSize;
	}
	
	public void enqueue(int x){
		tail = (tail + 1) % maxSize;
		queue[tail] = x;
		if (head == tail){
			System.err.println("Error: Overflow");
			System.exit(1);
		}
	}
	
	public int dequeue(){
		if (head == tail){
			System.err.println("Error: Underflow");
			System.exit(1);
		}
                head = (head+1)%maxSize;
                return queue[head];
	}
	
	public static void main(String[] args) {
		QueueByArray myQueue = new QueueByArray(55);

		for (int k = 1; k <= 10; k++){
			for (int i = 1; i <= 2 * k; i++){
				myQueue.enqueue(i);
			}
			for (int i = 1; i <= k * k; i++){
				System.out.print(myQueue.dequeue() + " ");
			}
		}
	}
}
