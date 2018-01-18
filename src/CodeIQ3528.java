// https://codeiq.jp/q/3528
import java.util.ArrayList;
import java.util.Scanner;

public class CodeIQ3528 {

	static ArrayList<Node> nodeList;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String s = scan.next();
		char[] input = new char[3];
		for(int i=0;i<3;i++) input[i]=s.charAt(i);

		initialize();
		openGate(input[0]);

		ArrayList<Node> queue = new ArrayList<>();
		queue.add(nodeList.get(encode(input[1])));
		nodeList.get(encode(input[1])).dist = 0;
		int goalNodeIndex = encode(input[2]);
		while(!queue.isEmpty()) {
			Node currentNode = queue.get(0);
			queue.remove(0);
			for(int i=0;i<currentNode.neighborNodeList.size();i++) {
				Node neighborNode = currentNode.neighborNodeList.get(i);
				if(neighborNode.dist > currentNode.dist+1) {
					queue.add(neighborNode);
					neighborNode.dist = currentNode.dist+1;
					if(neighborNode.index == goalNodeIndex) break;
				}
			}
		}

		System.out.println(nodeList.get(goalNodeIndex).dist);

		scan.close();
	}

	static void initialize() {
		nodeList = new ArrayList<>();
		for(int i=0;i<36;i++) nodeList.add(new Node(i));

		add(0,6);
		add(1,2);
		add(2,1); add(2,3); add(2,8);
		add(3,2); add(3,4);
		add(4,3); add(4,5); add(4,10);
		add(5,4);

		add(6,0); add(6,7);
		add(7,6); add(7,8);
		add(8,2); add(8,7);
		add(9,10); add(9,15);
		add(10,4); add(10,9); add(10,16);
		add(11,17);

		add(12,13);
		add(13,12); add(13,19);
		add(14,20);
		add(15,9);
		add(16,10);
		add(17,11); add(17,23);

		add(18,19); add(18,24);
		add(19,13); add(19,18); add(19,20); add(19,25);
		add(20,14); add(20,19); add(20,21);
		add(21,20);
		add(22,23); add(22,28);
		add(23,17); add(23,22); add(23,29);

		add(24,18); add(24,30);
		add(25,19);
		add(26,27); add(26,32);
		add(27,26); add(27,28);
		add(28,22); add(28,27);
		add(29,23); add(29,35);

		add(30,24); add(30,31);
		add(31,30); add(31,32);
		add(32,26); add(32,31);
		add(33,34);
		add(34,33); add(34,35);
		add(35,29); add(35,34);
	}

	private static void add(int i,int j) {
		nodeList.get(i).neighborNodeList.add(nodeList.get(j));
	}

	static int encode(char c) {
		if(c<='9') {
			return c-'0';
		}else {
			return c-'A'+10;
		}
	}

	static void openGate(char c) {
		if(c=='a') {
			add(7,13);
			add(13,7);
		}else if(c == 'b') {
			add(14,15); add(15,14);
		}else if(c == 'c') {
			add(16,22); add(22,16);
		}else if(c == 'd') {
			add(10,11); add(11,10);
		}
	}

	private static class Node{
		int index;
		int dist;
		ArrayList<Node> neighborNodeList = new ArrayList<>();

		Node(int i) {
			index = i;
			dist = Integer.MAX_VALUE;
		}
	}

}
