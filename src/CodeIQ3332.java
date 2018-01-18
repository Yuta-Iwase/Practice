import java.util.Scanner;

// https://codeiq.jp/q/3332

public class CodeIQ3332 {

	static int[] termList = {1,1,2,3,5,8,13,5,2,7,9,0,9,9,2,11,13,8,5,13,2,15,1,0};

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextInt()) {
			System.out.println(fibonacci(scan.nextInt()-1));
		}
		scan.close();
	}

	static int fibonacci(int n) {
		return termList[n%24];
	}



}
