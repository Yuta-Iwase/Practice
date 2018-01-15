// https://codeiq.jp/q/3116
import java.util.Scanner;

public class CodeIQ3116 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while(scan.hasNextLine()) {
			String s = scan.nextLine();

			String bef_c;
			String c = "";
			int bef_i;
			int i;
			int currentPoint = 0;
			while(currentPoint<s.length()) {
				bef_c = c;
				c = String.valueOf(s.charAt(currentPoint));
				if(bef_c.length()>0) {
					bef_i = Integer.parseInt(bef_c);
				}else {
					bef_i = -1;
				}
				i = Integer.parseInt(c);
				if(bef_i+1==i || bef_i-1==i) {
					s = s.substring(0, currentPoint-1) + s.substring(currentPoint+1,s.length());

					c = "";
					currentPoint = 0;
				}else {
					currentPoint++;
				}
			}
			System.out.println(s);
		}

		scan.close();
	}

}
