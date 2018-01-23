import java.util.Scanner;

public class CodeIQ1660 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int min = (int)Math.pow(10, N-1);
		int max = (int)Math.pow(10, N)-1;

		int count = 0;
		for(int i=min;i<=max;i++) {
//			getOne_Binary(i);
			getOne_BCD(i);
//			if(getOne_Binary(i) == getOne_BCD(i)) count++;
		}
		System.out.println(count);

		scan.close();
	}

	static int getOne_Binary(int n) {
		int count = 0;
		while(n>0) {
			if(n%2==1) count++;
			n/=2;
		}
		return count;
	}

	static int getOne_BCD(int n) {
		int count = 0;
		while(n>0) {
			count += getOne_Binary(n%10);
			n/=10;
		}
		return count;
	}

}
