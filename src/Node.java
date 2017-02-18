

public class Node {
	String name;
	Node outNode;
	Node nextNode;
	// 追加Node
	// 詳細はHexagon.javaに記載
	Node resetNode;
	Node lastNode;
	
	public Node(String name) {
		this.name = name;
	}
}
