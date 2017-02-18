import java.util.Random;

/*
 *  六角形で1つの単位が生成される構造を作る。
 *
 *  六角形には3つ、ほかの六角形と繋がるNodeを持つことにする。
 *  その3つは以下のとおり(六角形を生成された順に六角形A,B,C,…と名付けることにする)
 *
 *  outNode : 次の六角形へ移るNode(ただし最後の六角形の場合は最初に戻る)
 *    例:六角形C→六角形D
 *  resetNode: 最初の六角形(六角形A)へ戻るNode
 *    例:六角形D→六角形A , 六角形E→六角形A
 *  lastNode: 最後の三角形に進むNode
 *    例:※最後の三角形を六角形Fとする
 *       六角形B→六角形F
 */


public class Hexagon {
	// 確認がしやすいように各頂点(Node)を配列で定義しておく
	Node[] vertex = new Node[6];
	// 前述の3つのNodeを記憶するための配列を作る
	// 0:outNode 1:resetNode 2:lastNode と対応させる
	int[] num = new int[3];

	public Hexagon(String name){
		// 各頂点の命名
		for(int i=0 ; i<6 ; i++){
			vertex[i] = new Node(name + i);
		}

		// 各頂点を巡回するように結合
		for(int i=0 ; i<=4 ; i++){
			vertex[i].nextNode = vertex[i+1];
		}
		vertex[5].nextNode = vertex[0];

		// 3つの数値を選ぶメソッドで定義
		// このメソッドについては後述
		this.num = Hexagon.pickThreeNum();
	}

	// 乱数生成用オブジェクト
	static Random myRandom = new Random();

	// 0から5の内から重複なしで3つの数を選ぶメソッド
	static public int[] pickThreeNum(){
		int[] num = new int[3];

		// 1つ目を選ぶ
		num[0] = myRandom.nextInt(6);
		// 2つ目を選ぶ
		do{
			num[1] = myRandom.nextInt(6);
		}while(num[1] == num[0]);
		// 3つ目を選ぶ
		do{
			num[2] = myRandom.nextInt(6);
		}while( (num[2] == num[0]) || (num[2] == num[1]) );

		return num;
	}

	// Node xをnextHexのランダムなNodeに接続させる
	static void connectO(Node x, Hexagon nextHex){
		x.outNode = nextHex.vertex[myRandom.nextInt(6)];
	}

	// Node x を初めに生成されたHexagonにresetNodeで接続する
	static void connectR(Node x, Hexagon firstHex){
		x.resetNode = firstHex.vertex[myRandom.nextInt(6)];
	}

	// Node x を最後に生成されたHexagonにlastNodeで接続する
	static void connectL(Node x, Hexagon lastHex){
		x.lastNode = lastHex.vertex[myRandom.nextInt(6)];
	}

	public static void main(String[] args) {
		// 六角形を6つ生成し繋ぐ
		Hexagon[] hex = new Hexagon[6];
		int a=0;
		for(char c='A'; c<='F' ; c++){
			hex[a++] = new Hexagon(String.valueOf(c));
		}
		for(int i=0 ; i<6 ; i++){
			Hexagon.connectO(hex[i].vertex[ hex[i].num[0] ] , hex[(i+1)%6]);
			Hexagon.connectR(hex[i].vertex[ hex[i].num[1] ] , hex[0]);
			Hexagon.connectL(hex[i].vertex[ hex[i].num[2] ] , hex[5]);
		}

		// 動作試験用の部分
		Node v = hex[0].vertex[myRandom.nextInt(6)];
		// あらかじめ各六角形のoutNode,resetNode,lastNodeを確認する
		for(int i=0; i<6 ; i++){
			System.out.print("Hexagon[" + i + "]    ");
			for(int j=0 ; j<6 ; j++){
				if(hex[i].vertex[j].outNode != null){
					System.out.print("outNode:" + hex[i].vertex[j].name + "  ");
				}
			}
			for(int j=0 ; j<6 ; j++){
				if(hex[i].vertex[j].resetNode != null){
					System.out.print("resetNode:" + hex[i].vertex[j].name + "  ");
				}
			}
			for(int j=0 ; j<6 ; j++){
				if(hex[i].vertex[j].lastNode != null){
					System.out.println("lastNode:" + hex[i].vertex[j].name);
				}
			}
		}
		System.out.println();
		// 動作開始
		for(int i=1 ; i<=60 ; i++){
			System.out.printf("%2d: " + v.name + "   ",i);
			if(v.outNode != null && myRandom.nextInt(2)==0){
				v = v.outNode;
				System.out.println("outNode  を通過しました");
			}else if(v.resetNode != null && myRandom.nextInt(3)==0){
				v = v.resetNode;
				System.out.println("resetNodeを通過しました");
			}else if(v.lastNode != null && myRandom.nextInt(4)==0){
				v = v.lastNode;
				System.out.println("lastNode を通過しました");
			}else{
				v = v.nextNode;
				System.out.println("");
			}

		}
	}

}
