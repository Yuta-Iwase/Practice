public class OnePoint {
	private double x, y;

	// コンストラクタ
	// 平面にx,yを取り原点との距離を求め(a<距離<1)でなければやり直し
	// @param a  円C1の半径
	public OnePoint(double a) {
		// 原点からの距離
		double distance;
		
		// 上記の通りの動作を行う
		do {
			x = (-1) + (2 * Math.random());
			y = (-1) + (2 * Math.random());
			distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		} while (!(a < distance && distance < 1));
		
	}

	// getXメソッド
	// xを返す
	// @return x  x座標
	public double getX() {
		return x;
	}

	// getYメソッド
	// yを返す
	// @return y  y座標
	public double getY() {
		return y;
	}

}
