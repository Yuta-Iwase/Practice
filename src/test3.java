
import java.util.ArrayList;

public class test3 {

	public static void main(String[] args) {
		ArrayList<ArrayList<Object>> matrix = new ArrayList<ArrayList<Object>>();

		char a = 'a';
		int b = 1;
		double c = 3.14;

		int N = 10;

		for(int i=0;i<N;i++){
			ArrayList<Object> currentList = new ArrayList<Object>();
			currentList.add(a);
			currentList.add(b);
			currentList.add(c);
			matrix.add(currentList);
			a++; b++; c*=1.5;
		}

		for(int i=0;i<matrix.size();i++){
			for(int j=0;j<matrix.get(i).size();j++){
				System.out.print(matrix.get(i).get(j) + " ");
			}
			System.out.println();
		}

	}

}
