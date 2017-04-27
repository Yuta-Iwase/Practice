import java.util.Random;
import java.util.Scanner;

public class MontyHall1 {

	public static void main(String[] args) {
		Random rnd = new Random();
		Scanner scan = new Scanner(System.in);
		rnd.setSeed(123456789);
		
		// 正解設定
		int hit = rnd.nextInt(3);
		System.out.println("正解：" + hit);
		
		// プレイヤーの選択(1回目)
		System.out.println("ここにある３つの箱(0,1,2)のうち１つが当たりです。さて、それは何番でしょう？");
		System.out.print("箱 0,1,2 のどれかを選んでください。> ");
	    int player = scan.nextInt();
	    while(!(player==0 || player==1 || player==2)){
	    	System.out.println("0,1,2以外の言葉が入力されています。");
	    	System.out.print("0,1,2のいずれかを入力してください。> ");
	    	player = scan.nextInt();
	    }
	    System.out.println("あなたは " + player + " 番の箱を選びました。");
	    
	    // 司会のヒント
	    int mon = rnd.nextInt(3);
	    while(mon == hit || mon == player){
	    	mon = rnd.nextInt(3);
	    }
	    System.out.println("ここでヒントを出しましょう。" + mon + " 番の箱はハズレです。");
	    int yy = -1;
	    for(int i=0;i<3;i++){
	    	yy = i;
	    	if(!(yy==player || yy==mon)) break;
	    }
	    System.out.println("いま、あなたが選んだ " + player +" 番の箱と、" + yy +" 番の箱が残っています。");
	    
	    // 選び直し(選択2回目)
	    System.out.print("ここで箱を選び直してもかまいません。箱を変えますか？（Yes/No）> ");
	    String str = scan.next();
	    while(!(str.equals("Yes") || str.equals("No"))){
	    	System.out.println("Yes,No以外の言葉が入力されています。");
	    	System.out.print("Yes,Noのいずれかを入力してください。> ");
	    	str = scan.next();
	    }
	    if(str.equals("Yes")){
	    	System.out.println("わかりました。箱を変えます。");
	    	player = yy;
	    }else{
	    	System.out.println("わかりました。箱は変えません。");
	    }
	    
	    // 結果発表
	    if(player == hit){
	    	System.out.println("最終的にあなたが選んだ " + player + " 番は---当たりです！");
	    }else{
	    	System.out.println("最終的にあなたが選んだ " + player + " 番は---残念、ハズレです！");
	    } 
	}
}
