
public class sfmtTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Sfmt rnd = new Sfmt(123);
		for(int i=0; i<100;i++){
			double r = rnd.NextUnif();
			System.out.println(r);	
		}
		for(int i=0; i<100;i++){
			double r2 = rnd.NextInt(10);
			System.out.println(r2);	
		}

	}

}
