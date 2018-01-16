

import java.util.Scanner;


public class scannerTest {

	public static void main(String[] args) {
		System.out.println(test5());
	}

 	/**
	 * 解説:
	 * 無限ループを構築し、正しい入力ならbreak,誤った入力ならcontinueで流れを制御。
	 * 特徴:
	 * ・while(true)記法が必要。
	 * ・[課題①]!scan.hasNextInt()のときscan.next()で流すことを知っていないといけない。
	 * ・continue,breakを知っていないといけない。
	 * ○短く書くことができる。
	 * ○流れがわかりやすい。
	 */
	static int test1() {
		System.out.println("run test1...");
		Scanner scan = new Scanner(System.in);

		int x;
		while(true) {
			System.out.print("write anything$ ");
			if(!scan.hasNextInt()) {
				scan.next();
				continue;
			}else {
				x = scan.nextInt();
			}
			if(!(0<=x && x<=2)) {
				continue;
			}else {
				break;
			}
		}

		scan.close();
		return x;
	}

	/**
	 * 解説:
	 * boolean loop で制御されたwhileループを構築。
	 * loopは、正しい入力になったときはじめてfalseとなる。
	 * そのため、誤った入力を打ち続ける限りループを続けることになる。
	 * 特徴:
	 * ・booleanが必須。
	 * ・2つのbooleanで制御されているため楽には把握できない。
	 * ・[課題①]!scan.hasNextInt()のときscan.next()で流すことを知っていないといけない。
	 * ○boolean以外難しいコードが無い
	 */
	static int test2() {
		System.out.println("run test2...");
		Scanner scan = new Scanner(System.in);
		int x = -1;

		boolean loop = true;
		boolean isInt;
		while(loop) {
			System.out.print("write anything$ ");

			isInt = false;
			if(scan.hasNextInt()) {
				x = scan.nextInt();
				isInt = true;
			}else {
				scan.next();
			}

			if(isInt) {
				if(0<=x && x<=2) {
					loop = false;
				}
			}

		}

		scan.close();
		return x;
	}

	/**
	 * 解説:
	 * int xを定義し、入力が整数ならその値、そうでないなら適当な整数(今回は123)とする。
	 * whileのループ条件を 0<=x<=2 とすれば、正しい入力をした場合のみループから脱出できる。
	 * 特徴:
	 * ・「入力が整数でないなら、xを適当な整数にする」という考え方はすぐには思いつけない?
	 * ・似たようなコードが2箇所書かれていて美しくない→do-while文で解決できる。
	 * ・[課題①]!scan.hasNextInt()のときscan.next()で流すことを知っていないといけない。
	 * ○難しい命令を使用しない
	 */
	static int test3() {
		System.out.println("run test3...");
		Scanner scan = new Scanner(System.in);
		int x;

		System.out.print("write anything$ ");
		if(scan.hasNextInt()) {
			x = scan.nextInt();
		}else {
			x = 123;
			scan.next();
		}

		while(!(0<=x && x<=2)) {
			System.out.print("write anything$ ");
			if(scan.hasNextInt()) {
				x = scan.nextInt();
			}else {
				x = 123;
				scan.next();
			}
		}

		scan.close();
		return x;
	}

	/**
	 * 解説:
	 * 課題①へのアンチテーゼ。
	 * 特徴:
	 * ・Stringからintへ変更できるという発想をすることができるか?
	 * ・この型の変更にはInteger.parseIntメソッドやtry-catch構文などある程度のjavaの知識を要求されるため難解。
	 * ○課題①を触れることなく問題を解決できる。
	 * ○比較的コードが短い。
	 */
	static int test4() {
		System.out.println("run test4...");
		Scanner scan = new Scanner(System.in);
		int x = -1;


		do {
			System.out.print("write anything$ ");
			String s = scan.next();
			try {
				x = Integer.parseInt(s);
			}catch (Exception e) {}
		}while(!(0<=x && x<=2));

		scan.close();
		return x;
	}


	static int test5() {
		System.out.println("run test5...");
		Scanner scan = new Scanner(System.in);
		int x;

		do {
			System.out.print("write anything$ ");
			while(!scan.hasNextInt()) {
				System.out.print("write anything$ ");
				scan.next();
			}
			x = scan.nextInt();
		}while(!(0<=x && x<=2));

		scan.close();
		return x;
	}

}