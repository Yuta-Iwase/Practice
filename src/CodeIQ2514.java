// https://codeiq.jp/q/2514
import java.util.Scanner;

public class CodeIQ2514 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();

		int count = 0;
		int currentCount;
		int currentNum;
		for(int i=0;i<=N;i++) {
			currentNum = i;
			currentCount = 0;
			if(currentNum%2==1) {
				currentCount++;
			}
			while(currentNum>0) {
				currentNum = currentNum/2;
				if(currentNum%2==1) currentCount++;
			}
			if(currentCount==M) count++;
		}

		System.out.println(count);

		scan.close();
	}

}
