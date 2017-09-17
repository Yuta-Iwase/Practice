import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class test3 {

	public static void main(String[] args) throws Exception{
		int sampleNum = 10;

		PrintWriter pw = new PrintWriter(new File("sList.csv"));
		Scanner scan = null;

		String folderName = null;
		String listName = null;

		File list = null;

		int[][] sList = new int[240][sampleNum];

		int index;
		String indexString;
		int currentInt = 0;
		int currentIntIndex;
		for(int i=0;i<sampleNum;i++) {
			index = i+1;

			if(index<10) indexString = "0" + index;
			else indexString = "" + index;
			folderName = "sample" + indexString;

			listName = folderName + "/property.txt";
			list = new File(listName);

			scan = new Scanner(list);
			currentIntIndex = 0;
			while(scan.hasNext()) {
				currentInt = scan.nextInt();
				if(currentIntIndex%4==1) {
					sList[currentIntIndex/4][i] = currentInt;
				}
				currentIntIndex++;
			}
			scan.close();
		}

		for(int i=0;i<240;i++) {
			for(int j=0;j<sampleNum-1;j++) {
				pw.print(sList[i][j] + ",");
			}
			pw.print(sList[i][sampleNum-1]);
			pw.println();
		}

		pw.close();
	}
}
