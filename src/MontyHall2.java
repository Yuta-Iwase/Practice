import java.util.Random;
import java.util.Scanner;

public class MontyHall2 {

	public static void main(String[] args) {
		Random rnd = new Random();
		Scanner scan = new Scanner(System.in);
		rnd.setSeed(123456789);
		
		// 繰り返し回数入力
		System.out.print("繰り返し回数を入力してください。> ");
		int times = scan.nextInt();
		
		// カウンター用変数の定義
		int changeN=0; //回答者が選択した箱を変更した回数と、
		int notChangeN=0; //変えなかった回数
		int hitN_change=0; //選択した箱を変更した場合の中で、「当たった」回数
		int hitN_notChange=0; //選択した箱を変更しなかった場合の中で、「当たった」回数
		
		// ループで使う変数をあらかじめ変数を定義
		int hit;
		int player;
		int mon;
		int yy;
		int change;
		
		// ループのはじまり
		for(int t=0;t<times;t++){
			// 正解設定
			hit = rnd.nextInt(3);
			
			// プレイヤーの選択(1回目)
		    player = rnd.nextInt(3);
		    
		    // 司会のヒント
		    mon = rnd.nextInt(3);
		    while(mon == hit || mon == player){
		    	mon = rnd.nextInt(3);
		    }
		    yy = -1; //プレイヤー及び司会のいずれでもない箱yyを定義
		    for(int i=0;i<3;i++){
		    	yy = i;
		    	if(!(yy==player || yy==mon)) break;
		    }
		    
		    // 選び直し(選択2回目)
		    change = rnd.nextInt(2);
		    if(change==0) player = yy;
		    
		    // カウンター集計
		    if(change==0) changeN++;
		    else notChangeN++;
		    if(change==0 && player==hit) hitN_change++;
		    if(change==1 && player==hit) hitN_notChange++;
		}
		
		System.out.println("回答者が選択した箱を変更したのは " + times + " 回中 " + changeN + " 回で、その正答率は " + ((double)hitN_change/changeN) + " でした。");
		System.out.println("回答者が選択した箱を変更しなかったのは " + times + " 回中 " + notChangeN + " 回で、その正答率は " + ((double)hitN_notChange/notChangeN) + " でした。");
	}
}