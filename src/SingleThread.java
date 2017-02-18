public class SingleThread extends Thread{
	String str;
	int time;

	SingleThread(String s, int t) {
		str = s;
		time = t;
	}

	public void run() {

		for (int i = 0; i < 5; i++) {
			System.out.println("No." + i + " : " + str);

			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
		}
	}

}
