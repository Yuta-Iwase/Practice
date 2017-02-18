package dataStructure;

// 配列によるスタックの実現(pp.44-46)
public class StackByArray {

	private int top; // 蓄えられているデータの個数
	private int[] stack;
	private int maxSize;
	
	// コンストラクタ
	public StackByArray(int maxSize){
		top = 0;
		stack = new int[maxSize];
		this.maxSize = maxSize;
	}
	
	public void push(int x){
		if (top == maxSize){
			System.err.println("Error: Overflow");
			System.exit(1);
		}
		stack[top++] = x;
	}
	
	public int pop(){
		if (top == 0){
			System.out.println("Error: Underflow");
			System.exit(1);
		}
		return stack[--top];
	}
	
	public static void main(String[] args) {
		StackByArray myStack = new StackByArray(55);
		for (int k = 1; k <= 10; k++){
			for (int i = 1; i <= 2 * k; i++){
				myStack.push(i);

			}
			for (int i = 1; i <= k * k; i++){
				System.out.print(myStack.pop()+" ");
			}
		}
	}
}
