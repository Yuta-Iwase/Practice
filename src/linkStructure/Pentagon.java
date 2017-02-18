package linkStructure;

import java.util.Random;

public class Pentagon {

	Node head;
	
	public Pentagon(String name){
		this.head = new Node(name + '0');
		Node tail = this.head;
		for(char c='1' ; c<='5' ; c++){
			tail.nextNode = new Node(name + c);
			tail = tail.nextNode;
		}
		tail.nextNode = this.head;
	}
	
	static Random myRandom = new Random();
	
	// ランダムにこのPentagonのentry節を変え、それを返す
	private Node randomNode(){
		for(int i=1 ; i<=10 ; i++){
			
			if(myRandom.nextBoolean())  //ランダムに真偽を返す
				head = head.nextNode;
		}
		return head;
	}
	
	// Pentagon this のどれかの節からPentagon pのどれかの節に接続する
	public void connect(Pentagon p){
		this.randomNode().outNode = p.randomNode();
	}
	
	public static void main(String[] args) {
		// 五角形を五角形につなぐ
		Pentagon headPenta = new Pentagon("A");
		Pentagon tailPenta = headPenta;
		for(char c='B' ; c<='E' ; c++){
			Pentagon newPenta = new Pentagon(String.valueOf(c));
			tailPenta.connect(newPenta);  //tailPenta と newPentaを接続
			tailPenta = newPenta;  //移動先にフォーカスをあわせる
		}
		tailPenta.connect(headPenta);
		
		Node v = headPenta.randomNode();
		for(int i=1 ; i<=30 ; i++){
			System.out.print(v.name + " ");
			if(v.outNode != null && myRandom.nextBoolean()){
				v = v.outNode;
			}else{
				v = v.nextNode;
			}
		}
	}

}
