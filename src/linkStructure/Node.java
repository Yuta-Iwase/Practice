package linkStructure;

public class Node {
	// NodeオブジェクトにはString型1つ と Node型2つ のフィールド変数を持つ
	String name;
	Node outNode;
	Node nextNode;
	
	public Node(String name) {
		super();
		this.name = name;
	}

	public static void main(String[] args) {
		Node head = new Node("0");
		Node tail = head;
		for(int i=1 ; i<=5 ; i++){
			tail.nextNode = new Node(Integer.toString(i));
			tail = tail.nextNode;
		}
		tail.nextNode = head;  //オブジェクトの等号関係は常にパラメーターを同期する
		// この時点でtail.nextNode.nextNode.nextNode.nextNode.nextNode.nextNodeまで生成されている
		Node v = head;
		for(int i=1 ; i<=14 ; i++){
			System.out.print(v.name + " ");
			v = v.nextNode;
		}
	}

}
