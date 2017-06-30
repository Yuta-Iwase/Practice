
public class find29 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int x, y, z;
		double alpha1, alpha2;

		for (int a = 0; a < 200; a++) {
			for (int b = 0; b < 200; b++) {
				for (int c = 0; c < 200; c++) {
					x = (int)Math.pow(-1.0, a%2) * (a/2); 
					y = (int)Math.pow(-1.0, b%2) * (b/2);
					z = (int)Math.pow(-1.0, c%2) * (c/2);
					if (y*y - 4 * x * z == 29) {
//						System.out.println(a + "\t" + b + "\t"  +c);
						alpha1 = (-1.0*y + Math.sqrt(29)) / (2.0*x);
						alpha2 = (-1.0*y -Math.sqrt(29))/(2.0*x);
//						System.out.println(alpha1);
//						System.out.println(alpha2);
//						System.out.println();
						if(alpha1>1 && -1<alpha2 && alpha2<0){
							System.out.println(x + "\t" + y + "\t"  +z);
						}
					}
				}
			}
		}

	}

}
