// https://codeiq.jp/q/3117
import java.util.Scanner;

public class CodeIQ3117 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextInt()) {
			int N = scan.nextInt();
			int l = 1999999/N;
			// 合同式と不等式を活用することでNから直ちに解を求めることができる。
			System.out.println((N%2) * (l/2 + l%2));
		}
		scan.close();
	}

}
