package JohoKiso;

public class Pokemon {

	public static void main(String[] args) {
		Pikachu pikachu = new Pikachu(100,60,30);
		Enemy lizardon = new Enemy(80,40,40);

		pikachu.printInfo();
		lizardon.printInfo();

		System.out.println("----戦闘開始----");

		lizardon.attack(pikachu);
		pikachu.attack(lizardon);

		System.out.println("----戦闘終了----");

		pikachu.printInfo();
		lizardon.printInfo();
	}

}
