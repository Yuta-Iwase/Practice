// LU分解検証用のコマンド生成のためだけのプログラム

import java.util.ArrayList;

public class LU_factorization_command {
	public static void main(String[] args) {
		// 行列の次元
		int N=6;

		// solveコマンドで利用する変数リスト
		ArrayList<String> vars = new ArrayList<String>();

		// generate L
		System.out.print("L={");
		for(int row=1;row<=N-1;row++){
			System.out.print("{");

			for(int col=1;col<=N-1;col++){
				if(row > col){
					vars.add(("l" + row + "" + col));
					System.out.print("l" + row + "" + col);
				}
				else if(row == col) System.out.print(1);
				else if(row < col) System.out.print(0);

				System.out.print(",");
			}
			System.out.print("0},");
		}
		System.out.print("{");
		for(int col=1;col<=N-1;col++){
			vars.add(("l" + N + "" + col));
			System.out.print("l" + N + "" + col + ",");
		}
		System.out.print("1}};");


		System.out.println();


		// generate U
		System.out.print("U={");
		for(int row=1;row<=N-1;row++){
			System.out.print("{");

			for(int col=1;col<=N-1;col++){
				if(row > col) System.out.print(0);
				else{
					vars.add(("u" + row + "" + col));
					System.out.print("u" + row + "" + col);
				}

				System.out.print(",");
			}
			vars.add(("u" + row + "" + N));
			System.out.print("u" + row + "" + N + "},");
		}
		System.out.print("{");
		for(int col=1;col<=N-1;col++){
			System.out.print("0,");
		}
		vars.add(("u" + N + "" + N));
		System.out.print("u" + N + "" + N + "}};");


		System.out.println();


		// generate A
		System.out.print("A={");
		for(int row=1;row<=N-1;row++){
			System.out.print("{");

			for(int col=1;col<=N-1;col++){
				System.out.print("a" + row + "" + col);

				System.out.print(",");
			}
			System.out.print("a" + row + "" + N + "},");
		}
		System.out.print("{");
		for(int col=1;col<=N-1;col++){
			System.out.print("a" + N + "" + col + ",");
		}
		System.out.print("a" + N + "" + N + "}};");


		System.out.println();


		// solve command
		String solveCommand = "";
		solveCommand += "Solve[A==L.U,{" ;
		for(int i=0;i<vars.size()-1;i++){
			solveCommand += (vars.get(i) + ",") ;
		}
		solveCommand += (vars.get(vars.size()-1));
		solveCommand += ("}][[1]];"); // [[1]]はなぜか1x4x4のテンソル化してしまうので、それを戻す操作
		System.out.println("subL=L/." + solveCommand);
		System.out.println("subU=U/." + solveCommand);
		System.out.print("MatrixForm[Simplify[subL.subU]]");

	}
}
