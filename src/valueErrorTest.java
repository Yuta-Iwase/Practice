
public class valueErrorTest {

	public static void main(String[] args) {
		double x = 1001/50.0;
		double y = 0.0;
		for(int i=0;i<50;i++) {
			y += x;
		}
		System.out.println(y);
		System.out.println(1001<=y);

	}

}
