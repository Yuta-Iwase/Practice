
public class GenericsSample {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		GenericsSample obj = new GenericsSample();
		obj.<Double,Integer>plus(9.5);
	}
	
	<T1 extends Number,T2> void plus(T1 a){
		System.out.println(a.intValue()*2);
	}

}
