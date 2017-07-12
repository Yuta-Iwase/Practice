
public class extendsTest_sub extends extendsTest_super{
	
	public extendsTest_sub() {
		// TODO 自動生成されたコンストラクター・スタブ
		super();
	}
	
	public static void main(String[] args) {
		new extendsTest_sub();
		System.out.println(a);
		new extendsTest_sub().B();
		new extendsTest_sub().x();
		
	}
	
	void B(){
		A();
		super.main(new String[3]);
	}
	
	void x(){
		System.out.println("x");
	}
	
}
