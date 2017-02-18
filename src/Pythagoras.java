public class Pythagoras {
	public static void main(String[] args) {
		boolean breakFlag = false;
		int a, b, c;
		for (int A = 1; A < 10000; A++) {
			a = A;
			for (int B = 1; B < 10000; B++) {
				b = B;
				double C = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
				c = (int) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
				if (a < b && C == c && c > 1000) {
					int x[] = new int[10];
					int y[] = new int[10];
					int z[] = new int[10];

					int logX = (int) Math.log10(a);
					int logY = (int) Math.log10(b);
					int logZ = (int) Math.log10(c);

					int X = 0, Y = 0, Z = 0;

					boolean Flag[] = new boolean[10];
					for (int i = 0; i < 10; i++) {
						Flag[i] = false;
					}

					for (int i = 0; i < (logX + 1); i++) {
						x[i] = (int) ((a / Math.pow(10, (logX - i))) - X);
						X = 10 * (X + x[i]);
						Flag[x[i]] = true;
					}
					X = 0;
					for (int i = 0; i < (logY + 1); i++) {
						y[i] = (int) ((b / Math.pow(10, (logY - i))) - Y);
						Y = 10 * (Y + y[i]);
						Flag[y[i]] = true;
					}
					Y = 0;
					for (int i = 0; i < (logZ + 1); i++) {
						z[i] = (int) ((c / Math.pow(10, (logZ - i))) - Z);
						Z = 10 * (Z + z[i]);
						Flag[z[i]] = true;
					}
					Z = 0;

					boolean Flag2 = true;
					for (int i = 0; i < 10; i++) {
						if (!Flag[i]) {
							Flag2 = false;
						}
					}

					if (Flag2) {
						System.out.println(a + "\t" + b + "\t" + c);
						breakFlag = true;
						break;
					}
				}
				if (breakFlag) {
					break;
				}
			}
			if (breakFlag) {
				break;
			}
		}

	}

}
