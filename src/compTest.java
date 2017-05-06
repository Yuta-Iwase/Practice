import java.util.Arrays;
import java.util.Comparator;

// 二次元配列のソート用クラス

public class compTest{

	public int[][] array_int;
	public double[][] array_double;
	boolean isDouble;
	
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
        array_int = array;
        isDouble = false;
    	
    	this.key = key;
        
        if(ascending_order){
        	multiplier = 1;
        }else{
        	multiplier = -1;
        }

    	Arrays.sort(array, new innerComparator_int());
    }
    
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
    public compTest(double[][] array, int key, boolean ascending_order) {
        array_double = array;
        isDouble = true;
    	
    	this.key = key;
        
        if(ascending_order){
        	multiplier = 1;
        }else{
        	multiplier = -1;
        }

    	Arrays.sort(array, new innerComparator_double());
    }
    
    /**
     * ソート済みリストを返す。getList()はただの表記揺れ対策。内容は変わらない。<br>
     * @return array : ソート済み配列<br>
     */
    public int[][] getArray_int(){
    	return array_int;
    }
    /**
     * ソート済みリストを返す。getList()はただの表記揺れ対策。内容は変わらない。<br>
     * @return array : ソート済み配列<br>
     */
    public double[][] getArray_double(){
    	return array_double;
    }
    /**
     * ソート済みリストを返す。getArray()はただの表記揺れ対策。内容は変わらない。<br>
     * @return array : ソート済み配列<br>
     */
    public int[][] getList_int(){
    	return array_int;
    }
    /**
     * ソート済みリストを返す。getArray()はただの表記揺れ対策。内容は変わらない。<br>
     * @return array : ソート済み配列<br>
     */
    public double[][] getList_double(){
    	return array_double;
    }
    
    private class innerComparator_int implements Comparator<int[]>{
        @Override
        public int compare(int[] o1, int[] o2) {
            return (o1[key] - o2[key])*multiplier;
        }
    }
    private class innerComparator_double implements Comparator<double[]>{
        @Override
        public int compare(double[] o1, double[] o2) {
            return (int)((o1[key] - o2[key])*multiplier*10);
        }
    }
    
}