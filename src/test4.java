
public class test4 {

	public static void main(String[] args) {
		int a = 123;
		Object a_alt = (Object)a;
		String s = a_alt.toString();
		int b = Integer.parseInt(s);
		
		System.out.println(b);

	}

}
