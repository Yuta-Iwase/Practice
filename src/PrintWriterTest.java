import java.io.File;
import java.io.PrintWriter;

public class PrintWriterTest {

	public static void main(String[] args) throws Exception{
		PrintWriter pw = new PrintWriter(new File("pwTest.txt"));

		pw.println("Hello World!");

		pw.close();

	}

}
