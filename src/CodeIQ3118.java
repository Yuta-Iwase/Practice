// https://codeiq.jp/q/3118
import java.util.ArrayList;
import java.util.Scanner;

public class CodeIQ3118 {

	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);

		ArrayList<ArrayList<Node>> nodeMatrix = new ArrayList<>();

		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			ArrayList<Node> currentList = new ArrayList<>();
			for(int i=0;i<line.length();i++) {
				int weight = Integer.parseInt(String.valueOf(line.charAt(i)));
				currentList.add(new Node(weight));
			}
			nodeMatrix.add(currentList);
		}

		int x = nodeMatrix.get(0).size();
		int y = nodeMatrix.size();
		for(int i=0;i<x-1;i++) {
			for(int j=0;j<y-1;j++) {
				nodeMatrix.get(i).get(j).add(nodeMatrix.get(i).get(j+1));
				nodeMatrix.get(i).get(j).add(nodeMatrix.get(i+1).get(j));
			}
			nodeMatrix.get(i).get(y-1).add(nodeMatrix.get(i+1).get(y-1));
		}
		for(int j=0;j<y-1;j++) {
			nodeMatrix.get(x-1).get(j).add(nodeMatrix.get(x-1).get(j+1));
		}

		ArrayList<Node> queue = new ArrayList<>();
		queue.add(nodeMatrix.get(0).get(0));
		nodeMatrix.get(0).get(0).dist = nodeMatrix.get(0).get(0).weight;
		nodeMatrix.get(0).get(0).visit = true;
		while(!queue.isEmpty()) {
			Node currentNode = queue.get(0);
			queue.remove(0);
			for(int i=0;i<currentNode.neightberList.size();i++) {
				Node neightborNode = currentNode.get(i);
				if(neightborNode.dist > currentNode.dist+neightborNode.weight) {
					neightborNode.dist = currentNode.dist+neightborNode.weight;
				}
				if(!neightborNode.visit) {
					queue.add(neightborNode);
					neightborNode.visit = true;
				}
			}
		}

		System.out.println(nodeMatrix.get(y-1).get(x-1).dist);

		scan.close();
	}

	private static class Node{
		int weight;
		int dist;
		boolean visit;
		ArrayList<Node> neightberList = null;

		public Node(int w) {
			weight = w;
			dist = Integer.MAX_VALUE;
			visit = false;
			neightberList = new ArrayList<>();
		}

		void add(Node v) {
			neightberList.add(v);
		}

		Node get(int i) {
			return neightberList.get(i);
		}
	}

}
