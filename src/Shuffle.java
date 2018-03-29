import java.util.ArrayList;

public class Shuffle {
	public static void main(String[] args) {
		// シャッフルしたい配列
		int[] list = {1, 2, 3, 4, 5};

		// シャッフルのための仮置きリスト
		ArrayList<Integer> array = new ArrayList<>();

		// listの内容をarrayへ格納
		for(int i=0;i<list.length;i++){
			array.add(list[i]);
		}

		// arrayからランダムに要素を取り出しlist[i]へ割り当てていく
		for(int i=0;i<list.length;i++){
			/*
			 * ↓のarray.size()より実行速度の速い書き方があると思うけど
			 *  効果が薄い and 細かすぎる and 可読性が落ちるので割愛
			*/
			// 0～array.size()-1 の間の整数rをランダムに取得
			int r = (int)(array.size()*Math.random());

			// list[i]へ割り当て
			list[i] = array.get(r);
			array.remove(r);
		}
	}
}
