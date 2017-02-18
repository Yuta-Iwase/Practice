import javax.swing.ImageIcon;


public class CheckSettable {

	static boolean[][] settableB = new boolean[8][8];
	static boolean[][] settableW = new boolean[8][8];

	// 全域設置可能フラグ
	// 各色について配置できる場所が1カ所でもあると、真を返す
	static boolean AllSetB;
	static boolean AllSetW;

	public CheckSettable() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				settableB[x][y] = false;
				settableW[x][y] = false;
			}
		}
	}

	public void checkSettable() {
		System.out.println("to start checking :by CheckSettable.class");

		// 設置可能フラグの初期化
		// ※このフラグは呼ばれるたびに初期化される必要がある
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				settableB[x][y] = false;
				settableW[x][y] = false;
			}
		}

		// 全域設置可能フラグの初期化
		// ※このフラグは呼ばれるたびに初期化される必要がある
		AllSetB = false;
		AllSetW = false;

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (Reversing.status[x][y] == 0) {

					// 斜め方向の限界値
					int max;

					// //////////////
					// 黒について //
					// //////////////
					if (Othello.setStatus == -1) {
						// 左について
						for (int l = 1; l <= x; l++) {
							if (Reversing.status[x - l][y] == -1) {
								for (int i = (l - 1); i > 0; i--) {
									settableB[x][y] = true;
									AllSetB = true;
								}
								break;
							} else if (Reversing.status[x - l][y] == 0) {
								break;
							} else if (Reversing.status[x - l][y] == 1) {
								// do nothing;
							}
						}
						// 右について
						for (int r = 1; r <= (7 - x); r++) {
							if (Reversing.status[x + r][y] == -1) {
								for (int i = (r - 1); i > 0; i--) {
									settableB[x][y] = true;
									AllSetB = true;
								}
								break;
							} else if (Reversing.status[x + r][y] == 0) {
								break;
							} else if (Reversing.status[x + r][y] == 1) {
								// do nothing;
							}
						}
						// 上について
						for (int u = 1; u <= y; u++) {
							if (Reversing.status[x][y - u] == -1) {
								for (int i = (u - 1); i > 0; i--) {
									settableB[x][y] = true;
									AllSetB = true;
								}
								break;
							} else if (Reversing.status[x][y - u] == 0) {
								break;
							} else if (Reversing.status[x][y - u] == 1) {
								// do nothing;
							}
						}
						// 下について
						for (int d = 1; d <= (7 - y); d++) {
							if (Reversing.status[x][y + d] == -1) {
								for (int i = (d - 1); i > 0; i--) {
									settableB[x][y] = true;
									AllSetB = true;
								}
								break;
							} else if (Reversing.status[x][y + d] == 0) {
								break;
							} else if (Reversing.status[x][y + d] == 1) {
								// do nothing;
							}
						}
						// 左上について
						if (x >= y) {
							max = y;
						} else {
							max = x;
						}
						for (int l = 1; l <= max; l++) {
							if (Reversing.status[x - l][y - l] == -1) {
								for (int i = (l - 1); i > 0; i--) {
									settableB[x][y] = true;
									AllSetB = true;
								}
								break;
							} else if (Reversing.status[x - l][y - l] == 0) {
								break;
							} else if (Reversing.status[x - l][y - l] == 1) {
								// do nothing;
							}
						}
						// 右上について
						if ((7 - x) >= y) {
							max = y;
						} else {
							max = (7 - x);
						}
						for (int r = 1; r <= max; r++) {
							if (Reversing.status[x + r][y - r] == -1) {
								for (int i = (r - 1); i > 0; i--) {
									settableB[x][y] = true;
									AllSetB = true;
								}
								break;
							} else if (Reversing.status[x + r][y - r] == 0) {
								break;
							} else if (Reversing.status[x + r][y - r] == 1) {
								// do nothing;
							}
						}
						// 左下について
						if (x >= (7 - y)) {
							max = (7 - y);
						} else {
							max = x;
						}
						for (int l = 1; l <= max; l++) {
							if (Reversing.status[x - l][y + l] == -1) {
								for (int i = (l - 1); i > 0; i--) {
									settableB[x][y] = true;
									AllSetB = true;
								}
								break;
							} else if (Reversing.status[x - l][y + l] == 0) {
								break;
							} else if (Reversing.status[x - l][y + l] == 1) {
								// do nothing;
							}
						}
						// 右下について
						if ((7 - x) >= (7 - y)) {
							max = (7 - y);
						} else {
							max = (7 - x);
						}
						for (int r = 1; r <= max; r++) {
							if (Reversing.status[x + r][y + r] == -1) {
								for (int i = (r - 1); i > 0; i--) {
									settableB[x][y] = true;
									AllSetB = true;
								}
								break;
							} else if (Reversing.status[x + r][y + r] == 0) {
								break;
							} else if (Reversing.status[x + r][y + r] == 1) {
								// do nothing;
							}
						}

						// 設置可能アイコンに変更
						// 要:repaint();
						if (settableB[x][y]) {
							Othello.buttonArray[x][y].setIcon(new ImageIcon(Resource.BSPath));
						} else {
							Othello.buttonArray[x][y]
									.setIcon(Othello.boardIcon);
						}

					}

					// //////////////
					// 白について //
					// //////////////
					if (Othello.setStatus == 1) {
						// 左について
						for (int l = 1; l <= x; l++) {
							if (Reversing.status[x - l][y] == 1) {
								for (int i = (l - 1); i > 0; i--) {
									settableW[x][y] = true;
									AllSetW = true;
								}
								break;
							} else if (Reversing.status[x - l][y] == 0) {
								break;
							} else if (Reversing.status[x - l][y] == -1) {
								// do nothing;
							}
						}
						// 右について
						for (int r = 1; r <= (7 - x); r++) {
							if (Reversing.status[x + r][y] == 1) {
								for (int i = (r - 1); i > 0; i--) {
									settableW[x][y] = true;
									AllSetW = true;
								}
								break;
							} else if (Reversing.status[x + r][y] == 0) {
								break;
							} else if (Reversing.status[x + r][y] == -1) {
								// do nothing;
							}
						}
						// 上について
						for (int u = 1; u <= y; u++) {
							if (Reversing.status[x][y - u] == 1) {
								for (int i = (u - 1); i > 0; i--) {
									settableW[x][y] = true;
									AllSetW = true;
								}
								break;
							} else if (Reversing.status[x][y - u] == 0) {
								break;
							} else if (Reversing.status[x][y - u] == -1) {
								// do nothing;
							}
						}
						// 下について
						for (int d = 1; d <= (7 - y); d++) {
							if (Reversing.status[x][y + d] == 1) {
								for (int i = (d - 1); i > 0; i--) {
									settableW[x][y] = true;
									AllSetW = true;
								}
								break;
							} else if (Reversing.status[x][y + d] == 0) {
								break;
							} else if (Reversing.status[x][y + d] == -1) {
								// do nothing;
							}
						}
						// 左上について
						if (x >= y) {
							max = y;
						} else {
							max = x;
						}
						for (int l = 1; l <= max; l++) {
							if (Reversing.status[x - l][y - l] == 1) {
								for (int i = (l - 1); i > 0; i--) {
									settableW[x][y] = true;
									AllSetW = true;
								}
								break;
							} else if (Reversing.status[x - l][y - l] == 0) {
								break;
							} else if (Reversing.status[x - l][y - l] == -1) {
								// do nothing;
							}
						}
						// 右上について
						if ((7 - x) >= y) {
							max = y;
						} else {
							max = (7 - x);
						}
						for (int r = 1; r <= max; r++) {
							if (Reversing.status[x + r][y - r] == 1) {
								for (int i = (r - 1); i > 0; i--) {
									settableW[x][y] = true;
									AllSetW = true;
								}
								break;
							} else if (Reversing.status[x + r][y - r] == 0) {
								break;
							} else if (Reversing.status[x + r][y - r] == -1) {
								// do nothing;
							}
						}
						// 左下について
						if (x >= (7 - y)) {
							max = (7 - y);
						} else {
							max = x;
						}
						for (int l = 1; l <= max; l++) {
							if (Reversing.status[x - l][y + l] == 1) {
								for (int i = (l - 1); i > 0; i--) {
									settableW[x][y] = true;
									AllSetW = true;
								}
								break;
							} else if (Reversing.status[x - l][y + l] == 0) {
								break;
							} else if (Reversing.status[x - l][y + l] == -1) {
								// do nothing;
							}
						}
						// 右下について
						if ((7 - x) >= (7 - y)) {
							max = (7 - y);
						} else {
							max = (7 - x);
						}
						for (int r = 1; r <= max; r++) {
							if (Reversing.status[x + r][y + r] == 1) {
								for (int i = (r - 1); i > 0; i--) {
									settableW[x][y] = true;
									AllSetW = true;
								}
								break;
							} else if (Reversing.status[x + r][y + r] == 0) {
								break;
							} else if (Reversing.status[x + r][y + r] == -1) {
								// do nothing;
							}
						}

						// 設置可能アイコンに変更
						// 要:repaint();
						if (settableW[x][y]) {
							Othello.buttonArray[x][y].setIcon(new ImageIcon(Resource.WSPath));
						} else {
							Othello.buttonArray[x][y]
									.setIcon(Othello.boardIcon);
						}

					}
				}

			}
		}
	}

}
