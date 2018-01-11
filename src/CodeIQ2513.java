// https://codeiq.jp/q/2513

import java.util.ArrayList;
import java.util.Scanner;

class CodeIQ2513{
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        for(int i=0;i<N;i++) {
        	A.add(scan.nextInt());
        }

        boolean find = false;
        for(int i=0;i<N;i++) {
        	for(int j=i+1;j<N;j++) {
        		if(A.get(i)+A.get(j)==256) {
        			find = true;
        			break;
        		}
        	}
        }

        if(find) {
        	System.out.println("yes");
        }else {
        	System.out.println("no");
        }


        scan.close();
    }
}