public class Automobile {
	// 変数について longitude→経度 latitude→緯度 angle→角度 fuel→燃料
	// これらは、コンストラクタの引数で定義される。
	double longitude,latitude,angle,fuel;
	
	
	// 燃費定数
	final double c1,c2;
	
	// スピードを表す変数
	double speed;
	
	// 目的地名
	String target;
	// 目的地の経度、緯度
	double target_k,target_i;
	
	// コンストラクタ
	// @param k 経度
	// @param i 緯度
	// @param a 角度
	// @param f 燃料
	public Automobile(double k,double i,double a,double f){
		longitude = k;
		latitude = i;
		angle = a;
		fuel = f;
		
		c1 = 12.3;
		c2 = 0.00001;
		
		speed = 0;
		
		System.out.printf("燃料定数が c1 = %.1f , c2 =  %.6f  の自動車を１台生成しました。%n",c1,c2);
	}
	
	// printInfoメソッド
	public void printInfo(){
		// 経度・緯度および進行方向を、それぞれ[度]の単位で小数点以下5位まで表示する。
		// また、速さと残燃料をそれぞれ小数点以下1位まで表示する。
		System.out.printf("現在位置：東経 %.5f度，北緯 %.5f度，進行方向 %9.5f度，速さ%6.1f[km/h]，燃料%5.1f[ℓ]%n",longitude,latitude,angle,speed,fuel);
	}
	
	// moveToメソッド
	// @param k 移動先の経度
	// @param i 移動先の緯度 
	public void moveTo(double k,double i){
		// distance_k,iはそれぞれ経度方向、緯度方向の距離の差
		// これらは、便宜上 負の値をとることがある。
		double distance_k = 89.600  * (k - longitude);
		double distance_i = 111.319 * (i - latitude);
		// 二点間の距離
		double distance = Math.sqrt( Math.pow(distance_k, 2) + Math.pow(distance_i, 2) );
		
		// 燃料の減少
		fuel -= distance * (1/c1);
		// 経度、緯度変更
		longitude = k;
		latitude = i;
		// メッセージ表示
		System.out.printf("%s (%9.5f度，%9.5f度)へ移動します。",target,k,i);
		System.out.printf("%6.3f[km]移動しました。%n",distance);
	}
	
	// changeSpeedメソッド
	// @param s 変更したい速度の値
	public void changeSpeed(double s){
		// 燃料の減少
		fuel -= ( c2 * Math.pow( (s - speed), 2) );
		// スピード変更
		speed = s;
		// メッセージ表示
		System.out.printf("スピードを%6.1f[km/h] に変えました。%n",speed);
	}
	
	// changeAngleメソッド
	// @param a 変更したい角度の値
	public void changeAngle(double a){
		// 角度変更
		angle = a;
		// メッセージ表示
		System.out.printf("%s へ向けて進行方向を%10.5f 度に変えました。%n",target,a);
	}
	
	// measureAngleメソッド
	// @param k 測りたい地点の経度
	// @param i 測りたい地点の緯度
	// @return betweenAngle_D 二点間の角度(度数法)
	public double measureAngle(double k,double i){
		// distance_k,iはそれぞれ経度方向、緯度方向の距離の差
		// これらは、便宜上 負の値をとることがある。
		double distance_k = 89.600  * (k - longitude);
		double distance_i = 111.319 * (i - latitude);
		
		// betweenAngle_R 二点間の角度(弧度法) 
		// Math.atanは、弧度法で、かつ、第一・第四象限で返すことに注意する。
		double betweenAngle_R = Math.atan(distance_k/distance_i);
		// 角度を第一・第四象限から全象限で表現できるように拡張する。
		if(distance_i<0){
			betweenAngle_R += Math.PI;
		}
		
		// 弧度法を度数法に変換する。
		double betweenAngle_D = betweenAngle_R * (180/Math.PI);
		
		// 度数法に直した二点間の角度を返り値として返す。
		return betweenAngle_D;
		}

	public static void main(String[] args) {
		Automobile Auto = new Automobile(140.442612,	36.402140,	180.0,	40.0);
		Auto.printInfo();
		System.out.println();
		
		Auto.target ="日立おさかなセンター";
		Auto.target_k = 140.615258; Auto.target_i = 36.492228;
		System.out.printf("現在位置から次の目的地への方位角は%9.5f度です。%n",Auto.measureAngle(Auto.target_k,Auto.target_i));
		Auto.changeAngle(Auto.measureAngle(Auto.target_k,Auto.target_i));
		Auto.changeSpeed(80.0);
		Auto.printInfo();
		Auto.moveTo(Auto.target_k,Auto.target_i);
		Auto.printInfo();
		Auto.changeSpeed(0);
		Auto.printInfo();
		System.out.println();
		
		Auto.target ="西山荘";
		Auto.target_k = 140.509654; Auto.target_i = 36.544359;
		System.out.printf("現在位置から次の目的地への方位角は%9.5f度です。%n",Auto.measureAngle(Auto.target_k,Auto.target_i));
		Auto.changeAngle(Auto.measureAngle(Auto.target_k,Auto.target_i));
		Auto.changeSpeed(30.0);
		Auto.printInfo();
		Auto.moveTo(Auto.target_k,Auto.target_i);
		Auto.printInfo();
		Auto.changeSpeed(0);
		Auto.printInfo();
		System.out.println();
		
		Auto.target ="茨大理学部Ｅ棟玄関前";
		Auto.target_k = 140.442612; Auto.target_i = 36.402140;
		System.out.printf("現在位置から次の目的地への方位角は%9.5f度です。%n",Auto.measureAngle(Auto.target_k,Auto.target_i));
		Auto.changeAngle(Auto.measureAngle(Auto.target_k,Auto.target_i));
		Auto.changeSpeed(40.0);
		Auto.printInfo();
		Auto.moveTo(Auto.target_k,Auto.target_i);
		Auto.printInfo();
		Auto.changeSpeed(0);
		Auto.printInfo();
		
		Auto.target ="真南";
		Auto.changeAngle(180.00000);
	}
	
}