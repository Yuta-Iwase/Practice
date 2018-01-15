// https://codeiq.jp/q/2516
import java.util.ArrayList;
import java.util.Scanner;

public class CodeIQ2516 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int M = scan.nextInt();
		int N = scan.nextInt();

		scan.nextLine();

		Node[][] list = new Node[M][N];
		for(int i=0;i<M;i++) {
			String line = scan.nextLine();
			for(int j=0;j<N;j++) {
				char status = line.charAt(j);
				boolean occupied = false;
				if(status=='#') {
					occupied = true;
				}
				list[i][j] = new Node(i,j,occupied);
			}
		}

		ArrayList<Node> queue = new ArrayList<>();
		queue.add(list[0][0]);
		queue.get(0).dist=-1;
		int x,y;
		int currentDist=-1;
		main: while(currentDist<M*N && !queue.isEmpty()) {
			Node v = queue.get(0);
			queue.remove(0);

			currentDist = v.dist;

			// 右方向
			x = v.x;
			y = v.y;
			while(true) {
				if(x+1>=M) break;
				x++;
				Node near = list[x][y];
				if(near.occupied || near.dist<v.dist+1) break;
				else {
					queue.add(near);
					near.dist = currentDist+1;
					if(near.x==M-1 && near.y==N-1) break main;
				}
			}

			// 左方向
			x = v.x;
			y = v.y;
			while(true) {
				if(x-1<0) break;
				x--;
				Node near = list[x][y];
				if(near.occupied || near.dist<v.dist+1) break;
				else {
					queue.add(near);
					near.dist = currentDist+1;
					if(near.x==M-1 && near.y==N-1) break main;
				}
			}

			// 下方向
			x = v.x;
			y = v.y;
			while(true) {
				if(y+1>=N) break;
				y++;
				Node near = list[x][y];
				if(near.occupied || near.dist<v.dist+1) break;
				else {
					queue.add(near);
					near.dist = currentDist+1;
					if(near.x==M-1 && near.y==N-1) break main;
				}
			}

			// 上方向
			x = v.x;
			y = v.y;
			while(true) {
				if(y-1<0) break;
				y--;
				Node near = list[x][y];
				if(near.occupied || near.dist<v.dist+1) break;
				else {
					queue.add(near);
					near.dist = currentDist+1;
					if(near.x==M-1 && near.y==N-1) break main;
				}
			}
		}
		list[0][0].dist = 0;

		System.out.println(list[M-1][N-1].dist);

		scan.close();
	}

	private static class Node{
		int x,y;
		int dist;
		boolean occupied;
		public Node(int i, int j, boolean o) {
			x = i;
			y = j;
			dist = Integer.MAX_VALUE;
			occupied = o;
		}
	}
}
