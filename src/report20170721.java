
public class report20170721 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		int N = 100000;
		long value;
		int mod=1987;
		int r = 101;
		for(long i=-N;i<N;i++){
//			value = i*i - 64*i + 943;
			value = i*i;
			if(value%mod==r){
				System.out.println("解=" + i + " value=" + value);
			}
		}

	}

}
