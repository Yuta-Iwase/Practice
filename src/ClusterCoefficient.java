import java.io.File;
import java.util.Scanner;

public class ClusterCoefficient {
	public static void main(String[] args) throws Exception{
		int N=0,M=0;
		int[][] list;
		
		String name = "AirportNetwork.csv";
		Scanner scan = new Scanner(new File(name));
		Scanner dummyScan = new Scanner(new File(name));
		Scanner dummyScan2 = new Scanner(new File(name));
		
		while(dummyScan.hasNextLine()){
			dummyScan.nextLine();
			M++;
		}
		list = new int[M][2];
		
		String currentLine;
		int comma;
		for(int i=0;i<M;i++){
			currentLine = scan.nextLine();
			comma = currentLine.indexOf(",");
			list[i][0] = Integer.parseInt(currentLine.substring(0, comma));
			list[i][1] = Integer.parseInt(currentLine.substring(comma+1));
		}
		
		
		dummyScan2.useDelimiter("\\s|,");
		while(dummyScan2.hasNext()){
			System.out.println(dummyScan2.next());
		}
		

		int[] degree = new int[N];
		int[] tri = new int[N];
		
		
		scan.close();
		dummyScan.close();
		dummyScan2.close();
	}
}
