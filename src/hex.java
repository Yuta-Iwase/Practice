// 10進nが与えられた時、16進で返すプログラム
// ✳ArrayList禁止

public class hex {

	public static void main(String[] args) {
		int n = 100;
		
		int digitN = 0;
		int[] digit = new int[1000];
		
		while(n!=0){
			digit[digitN] = n%16;
			n /= 16;
			digitN++;
		}
		
		char[] c = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		for(int i=digitN-1;i>=0;i--){
			System.out.print(c[digit[i]]);
		}
		

	}

}
