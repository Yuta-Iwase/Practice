
public class TimeMeasurement {

	public static void main(String[] args) {
		int digits = 11;
		long times = 1;
		for(int i=0;i<digits;i++) times*=10;
		final double PI = Math.PI;
		double x=0;
		double time_a;

		System.out.println(x + "(警告潰し)");
		System.out.println(String.format("%,3d", times));
		System.out.println();

		time_a = System.currentTimeMillis();
		for(long i=0;i<times;i++){
			x = PI/2;
		}
		System.out.println(System.currentTimeMillis() - time_a);

		time_a = System.currentTimeMillis();
		for(long i=0;i<times;i++){
			x = PI*0.5;
		}
		System.out.println(System.currentTimeMillis() - time_a);
	}

}
