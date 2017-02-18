package JohoKiso;

public class Pikachu {
	int hp;
	int atk;
	int def;
	String name;

	public Pikachu(int inputHP, int inputATK, int inputDEF){
		hp = inputHP;
		atk = inputATK;
		def = inputDEF;
		name = "ピカチュウ";
	}

	public void printInfo(){
		System.out.println(name + "の体力は" + hp + "、攻撃力は" + atk + "、防御力は" + def + "です。");
	}

	public void attack(Enemy e){
		int damage = this.atk - e.def;
		if(damage<0) damage=0; //余裕がある人向け、ダメージが負の時、ダメージ0にする
		e.hp -= damage;
		System.out.println(this.name + "の攻撃！" + e.name + "に" + damage + "のダメージを与えた！");
	}

	// ☆ポケモンの実際のメッセージメモ☆
	// ☆ポケモンSMより攻撃メッセージ『(自軍ポケモン)の　(技名)！』☆
	// ☆ポケモンSMより外したときのメッセージ『相手の　(相手ポケモン)には　当たらなかった！』☆
	public void tailWhip(Enemy e){
		System.out.println(this.name + "の　しっぽをふるこうげき！");
		// 余裕がある人向け、確率的な要素を加える
		// 命中率を70%とする
		if(Math.random() < 0.7){
			// 命中
			e.def /= 2;
			System.out.println(this.name + "は尻尾を振った！" + e.name + "の防御力は" + e.def + "に下がった！");
		}else{
			// 外した時
			System.out.println("相手の　" + e.name + "には　当たらなかった！");
		}
	}

	public void doubleEdge(Enemy e){
		// 課題の説明文を忠実に従い、ピカチュウの攻撃力に左右されず、
		// すてみタックル自体の攻撃力は100固定とする
		int damage = 100 - e.def;
		if(damage<0) damage=0; //余裕がある人向け、ダメージが負の時、ダメージ0にする
		int reactionDamage = damage/4;
		e.hp -= damage;
		this.hp -= reactionDamage;
		System.out.println(this.name + "の　すてみタックル！" + e.name + "は" + damage +"のダメージを受けた！　" + this.name + "も反動で" + reactionDamage + "のダメージを受けた！");
	}

	// 「余力のある人はこれもやってみよう」かみなりの設定
	public void thunder(Enemy e){
		// すてみタックル自体の攻撃力は70固定とする
		int damage = 70 - e.def;
		if(damage<0) damage=0; //余裕がある人向け、ダメージが負の時、ダメージ0にする
		e.hp -= damage;
		System.out.println(this.name + "の　かみなりこうげき！" + e.name + "は" + damage +"のダメージを受けた！");

		// 麻痺の設定
		if(Math.random() < 0.6){
			e.paralysis = true;
			System.out.println(e.name + "は　まひした！");
		}
	}


	//// ここからオマケ
	/** ピカチュウの十八番「10万ボルト」<br>
	* 攻撃力は元の攻撃力を倍率強化する(1.2倍)<br>
	* 麻痺の追加効果持ち(10%)<br>
	*/
	public void thunderBolt(Enemy e){
		int damage = (int)(this.atk*1.2 - e.def);
		if(damage<0) damage=0; //ダメージが負の時、ダメージ0にする
		e.hp -= damage;
		System.out.println(this.name + "の　10まんボルト！　" + e.name + "は" + damage +"のダメージを受けた！");

		// 麻痺の設定
		if(Math.random() < 0.1){
			e.paralysis = true;
			System.out.println(e.name + "は　まひした！");
		}
	}

	/** ピカチュウ専用技「ボルテッカー」<br>
	* 攻撃力は元の攻撃力を倍率強化する(1.5倍)<br>
	* 麻痺の追加効果持ち(10%)<br>
	*/
	public void voltTackle(Enemy e){
		int damage = (int)(this.atk*1.5 - e.def);
		int reactionDamage = damage/3; //3分の1が反動ダメージ
		if(damage<0) damage=0; //ダメージが負の時、ダメージ0にする
		e.hp -= damage;
		this.hp -= reactionDamage;
		System.out.println(this.name + "の　ボルテッカー！　" + e.name + "は" + damage +"のダメージを受けた！　" + this.name + "も反動で" + reactionDamage + "のダメージを受けた！");

		// 麻痺の設定
		if(Math.random() < 0.1){
			e.paralysis = true;
			System.out.println(e.name + "は　まひした！");
		}
	}

	/** 「アイアンテール」<br>
	* 攻撃力は元の攻撃力を倍率強化する(1.0倍)<br>
	* 防御低下の追加効果持ち(30%)<br>
	*/
	public void ironTail(Enemy e){
		int damage = (int)(this.atk*1.0 - e.def);
		if(damage<0) damage=0; //ダメージが負の時、ダメージ0にする
		e.hp -= damage;
		System.out.println(this.name + "の　アイアンテール！　" + e.name + "は" + damage +"のダメージを受けた！");

		// 防御低下の追加効果
		if(Math.random() < 0.3){
			System.out.println(e.name + "のぼうぎょが　さがった！");
		}
	}

}
