import java.io.File;

public class execTest {

	public static void main(String[] args) {
		try {
		      Runtime rt = Runtime.getRuntime();
//		      String folderPath = "C:/Users/Owner/Desktop/";
//		      rt.exec(folderPath + "Evernote.lnk");
//		      rt.exec("D:/Program Files (x86)/gnuplot/bin/wgnuplot.exe");
//		      rt.exec("notepad.exe " + folderPath + "test.txt");
//		      rt.exec("D:/Program Files (x86)/gnuplot/bin/wgnuplot.exe" + " D:/Java/Network/AirportTest21_TeleportTest/[plot].gplot");
//		      rt.exec("wgnuplot.exe");
//		      rt.exec("gnuplot");
		      File f = new File("D:/Program Files (x86)/gnuplot/bin/wgnuplot.exe");
		      File f2 = new File("C:/Program Files (x86)/gnuplot/bin/wgnuplot.exe");
		      System.out.println(f.exists());
		      System.out.println(f2.exists());
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
	}

}
