package JohoKiso;

import java.util.Scanner;

public class Pokemon2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("敵ポケモンの体力を入力したください ＞ ");
		int scanHP = scan.nextInt();
		System.out.print("敵ポケモンの攻撃力を入力したください ＞ ");
		int scanATK = scan.nextInt();
		System.out.print("敵ポケモンの守備力を入力したください ＞ ");
		int scanDEF = scan.nextInt();

		Pikachu pikachu = new Pikachu(100,60,30);
		Enemy lizardon = new Enemy(scanHP,scanATK,scanDEF);

		pikachu.printInfo();
		lizardon.printInfo();

		System.out.println("----戦闘開始----");

		lizardon.attack(pikachu);
		pikachu.attack(lizardon);

		lizardon.flameThrower(pikachu);
		pikachu.tailWhip(lizardon);

		lizardon.fireBlast(pikachu);
		pikachu.thunder(lizardon);

		lizardon.flameThrower(pikachu);
		pikachu.thunderBolt(lizardon);

		lizardon.megaEvolution();
		pikachu.ironTail(lizardon);

		lizardon.flameThrower(pikachu);
		pikachu.doubleEdge(lizardon);

		lizardon.fireBlast(pikachu);
		pikachu.voltTackle(lizardon);

		System.out.println("----戦闘終了----");

		pikachu.printInfo();
		lizardon.printInfo();

		scan.close();
	}

}
