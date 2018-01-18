// https://codeiq.jp/q/3475
import java.util.ArrayList;
import java.util.Scanner;

public class CodeIQ3475 {

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		int width,height=0;
		ArrayList<String> lines = new ArrayList<>();
		while(scan.hasNextLine()){
			lines.add(scan.nextLine());
			height++;
		}
		width = lines.get(0).length();

		Node[][] nodeMatrix = new Node[height][width];
		for(int i=0;i<height;i++){
			String currentLine = lines.get(i);
			for(int j=0;j<width;j++){
				if(currentLine.charAt(j)=='0'){
					nodeMatrix[i][j] = new Node(false);

					boolean top = (i==0);
					boolean leftEnd = (j==0);
					boolean rightEnd = (j==(width-1));

					// 左上
					if(!top && !leftEnd){
						if(!nodeMatrix[i-1][j-1].visited){
							nodeMatrix[i][j].neigthborNode.add(nodeMatrix[i-1][j-1]);
							nodeMatrix[i-1][j-1].neigthborNode.add(nodeMatrix[i][j]);
						}
					}
					// 上
					if(!top){
						if(!nodeMatrix[i-1][j].visited){
							nodeMatrix[i][j].neigthborNode.add(nodeMatrix[i-1][j]);
							nodeMatrix[i-1][j].neigthborNode.add(nodeMatrix[i][j]);
						}
					}
					// 右上
					if(!top && !rightEnd){
						if(!nodeMatrix[i-1][j+1].visited){
							nodeMatrix[i][j].neigthborNode.add(nodeMatrix[i-1][j+1]);
							nodeMatrix[i-1][j+1].neigthborNode.add(nodeMatrix[i][j]);
						}
					}
					// 左
					if(!leftEnd){
						if(!nodeMatrix[i][j-1].visited){
							nodeMatrix[i][j].neigthborNode.add(nodeMatrix[i][j-1]);
							nodeMatrix[i][j-1].neigthborNode.add(nodeMatrix[i][j]);
						}
					}
				}else{
					nodeMatrix[i][j] = new Node(true);
				}
			}
		}

		ArrayList<Node> queue = new ArrayList<>();


		int cc_count = 0;
		loop1: while(true){
			loop2: for(int i=0;i<height;i++){
				for(int j=0;j<width;j++){
					if(!nodeMatrix[i][j].visited){
						queue.add(nodeMatrix[i][j]);
						nodeMatrix[i][j].visited = true;
						cc_count++;
						break loop2;
					}
				}
			}

			if(queue.isEmpty())break loop1;

			while(!queue.isEmpty()){
				Node currentNode = queue.get(0);
				queue.remove(0);
				for(int i=0;i<currentNode.neigthborNode.size();i++){
					Node neigthborNode = currentNode.neigthborNode.get(i);
					if(!neigthborNode.visited){
						queue.add(neigthborNode);
						neigthborNode.visited = true;
					}
				}
			}

		}

		System.out.println(cc_count);

		scan.close();
	}

	private static class Node{
		boolean visited;
		ArrayList<Node> neigthborNode;
		Node(boolean v){
			visited = v;
			neigthborNode = new ArrayList<>();
		}
	}

}
