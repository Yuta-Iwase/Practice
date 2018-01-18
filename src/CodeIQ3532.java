// https://codeiq.jp/q/3532
import java.util.Scanner;

public class CodeIQ3532 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		int answer = -1;
		int currentSeqNum = 0;
		int currentNum = 1;
		while(currentSeqNum < n) {
			for(int i=1;i<=currentNum;i++) {
				int sum = 0;
				int k;
				for(k=i;k<=currentNum;k++) {
					sum += k;
					if(sum >= currentNum) break;
				}
				if(sum==currentNum) {
					k = k+1;
					sum = 0;
					int l;
					for(l=k;l<=currentNum;l++) {
						sum += l;
						if(sum >= currentNum) {
							break;
						}
					}
					if(sum == currentNum) {
						answer = currentNum;
						currentSeqNum++;
						break;
					}
				}
			}
			currentNum++;
		}

		System.out.println(answer);
		scan.close();
	}

}
