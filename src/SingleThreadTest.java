
public class SingleThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleThread a = new SingleThread("A",500);
		SingleThread b = new SingleThread("\tB",700);
		SingleThread c = new SingleThread("\t\tC",1100);
		
		a.start();
		b.start();
		c.start();

	}

}
