package JohoKiso;

public class Enemy {
	// 敵ポケモンは『リザードン/Lizardon』として進める。
	int hp;
	int atk;
	int def;
	String name;

	// 「かみなり」用、麻痺フラグ、こちらのクラスで管理することとする
	boolean paralysis;

	public Enemy(int inputHP, int inputATK, int inputDEF) {
		hp = inputHP;
		atk = inputATK;
		def = inputDEF;
		name = "リザードン";

		paralysis = false; // 「かみなり」用
	}

	public void printInfo(){
		System.out.println(name + "の体力は" + hp + "、攻撃力は" + atk + "、防御力は" + def + "です。");
		if(paralysis) System.out.println(name + "は　まひしている！");
	}

	public void attack(Pikachu pika){
		// 麻痺なら行動不能
		if(paralysis){
			System.out.println(this.name + "しびれてうごけない！");
		}else{
			int damage = this.atk - pika.def;
			if(damage<0) damage=0; //余裕がある人向け、ダメージが負の時、ダメージ0にする
			pika.hp -= damage;
			System.out.println(this.name + "の攻撃！" + pika.name + "に" + damage + "のダメージを与えた！");
		}
	}

	//// ここからオマケ
	/** 必中しない大技「だいもんじ」<br>
	 * 命中85%<br>
	 * 攻撃力は元の攻撃力を倍率強化する(1.5倍) <br>
	 * */
	public void fireBlast(Pikachu pika){
		if(paralysis){
			System.out.println(this.name + "しびれてうごけない！");
		}else{
			System.out.print(this.name + "の　だいもんじ！　");
			if(Math.random()<0.85){
				int damage = (int)(this.atk*1.5 - pika.def);
				if(damage<0) damage=0; //ダメージが負の時、ダメージ0にする
				pika.hp -= damage;
				System.out.println(pika.name + "は" + damage +"のダメージを受けた！");
			}else{
				System.out.println();
				System.out.println("相手の　" + pika.name + "には　当たらなかった！");
			}
		}
	}

	/** 「かえんほうしゃ」
	* 攻撃力は元の攻撃力を倍率強化する(1.2倍) */
	public void flameThrower(Pikachu pika){
		if(paralysis){
			System.out.println(this.name + "しびれてうごけない！");
		}else{
			int damage = (int)(this.atk*1.2 - pika.def);
			if(damage<0) damage=0; //ダメージが負の時、ダメージ0にする
			pika.hp -= damage;
			System.out.println(this.name + "の　かえんほうしゃ！　" + pika.name + "は" + damage +"のダメージを受けた！");
		}
	}

	/** 新要素「メガシンカ」
	* 自身を強化
	* いつまでも麻痺して行動できないので、コレで解除できるものとする*/
	public void megaEvolution(){
		System.out.println(this.name + "の　" + this.name + "ナイトと　プレイヤーの　メガバンクルが　反応した！");
		System.out.println(this.name + "は　" + "メガ" + this.name + "に　メガシンカした！");
		this.atk *= 1.2;
		this.def *= 1.1;
		this.paralysis = false;
	}

}
