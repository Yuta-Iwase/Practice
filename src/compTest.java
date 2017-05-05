import java.util.Arrays;
import java.util.Comparator;

// 二次元配列のソート用クラス

public class compTest implements Comparator<int[]> {

	public int[][] array;
	
    /** ソートキーとなる col インデクス (arr[row][col]) */
    private int key = 0;
    private short multiplier;

    /**
     * 二次元配列のソート用クラス<br>
     * <br>
     * ・arrayはソートしたいリスト<br>
     * ・keyはソートの基準とする列<br>
     * ・ascending_orderはtrueで小さい順,falseで大きい順<br>
     * <br>
     * コンストラクタの時点でソートを実行する。<br>
     * [使用法]getArrayまたはgetListでソート後のリストを取得できる。<br>
     */
    public compTest(int[][] array, int key, boolean ascending_order) {
        this.array = array;
    	
    	this.key = key;
        
        if(ascending_order){
        	multiplier = 1;
        }else{
        	multiplier = -1;
        }
        

    	Arrays.sort(array, this);
    }

    /**
     * 比較関数。ブラックボックスで構わない。<br>
     */
    @Override
    public int compare(int[] o1, int[] o2) {
        return (o1[key] - o2[key])*multiplier;
    }
    
    /**
     * ソート済みリストを返す。getList()はただの表記揺れ対策。内容は変わらない。<br>
     * @return array : ソート済み配列<br>
     */
    public int[][] getArray(){return array;}
    /**
     * ソート済みリストを返す。getList()はただの表記揺れ対策。内容は変わらない。<br>
     * @return array : ソート済み配列<br>
     */
    public int[][] getList(){return array;}
    
}