// https://codeiq.jp/q/3115

import java.util.Arrays;
import java.util.Scanner;

public class CodeIQ3115 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			int numbers[] = new int[4];
			for(int i=0;i<4;i++) {
				numbers[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
			}
			Arrays.sort(numbers);

			int result;
			if(numbers[0]==0) {
				result = numbers[1];
			}else {
				result = numbers[3];
			}

			System.out.println(result);
		}

		scan.close();
	}

}
