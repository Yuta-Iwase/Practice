package Network;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderRw {
	public static void main(String[] args) throws Exception {
		String fileName = "caida2.csv";
		Scanner scan = new Scanner(new File(fileName));
		double rewiringProbability = 0.05;
		int typeSelectorswitch = 1;			//0:各頂点毎、1:全確率破壊→連鎖破壊、2:0と1を同じグラフで行う
		int N = 26475;
		int M = 53381;
		int[][] pairList = new int[M][2];
		int[] degreeList = new int[N];
		int[] neighborList = new int[2 * M];
		int[] addressList = new int[N];
		int[] cursor = new int[N];
		ArrayList<ArrayList<Integer>> neighborMatrix = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < N; i++) {
			neighborMatrix.add(new ArrayList<Integer>());
		}

//		scan.nextLine();
		// scan.nextLine();
		// pairList
		int currentLine = 0;
		while (scan.hasNextInt()) {
			int left = scan.nextInt();
			int right = scan.nextInt();
			// System.out.println(left + "\t" + right);
			pairList[currentLine][0] = left;
			pairList[currentLine][1] = right;
			currentLine += 1;
		}
		// degreeList
		for (int i = 0; i < N; i++) {
			degreeList[i] = 0;
		}
		for (int i = 0; i < M; i++) {
			degreeList[pairList[i][0]] += 1;
			degreeList[pairList[i][1]] += 1;
		}

		// addressList
		System.out.println("addressList");
		addressList[0] = 0;
		// System.out.println(addressList[0]);
		for (int i = 1; i < N; i++) {
			addressList[i] = addressList[i - 1] + degreeList[i - 1];
			// System.out.println(addressList[i]);
		}

		// neighborList
		System.out.println("neighborList");
		for (int i = 0; i < N; i++) {
			cursor[i] = addressList[i];
		}
		for (int j = 0; j < M; j++) {
			neighborList[cursor[pairList[j][1]]] = pairList[j][0];
			cursor[pairList[j][1]] += 1;
			neighborList[cursor[pairList[j][0]]] = pairList[j][1];
			cursor[pairList[j][0]] += 1;
		}

		System.out.println("check");
//		PrintWriter xpw = new PrintWriter(new File("aaaaaa.txt"));
//		 for (int i = 0; i < 2 * M; i++) {
//		 System.out.println(i + "\t" + neighborList[i]);
//		 xpw.println(i + "\t" + neighborList[i]);
//		 }
//		 xpw.close();
		scan.close();
//		// リワイヤリング
//		Sfmt rnd = new Sfmt((int) System.currentTimeMillis());
//		int targetNode;
//		boolean rewiringFlag;
//		double r;
//		for (int i = 0; i < M; i++) {
//			int left = pairList[i][0];
//			int right = pairList[i][1];
//			r = Math.random();
//			do {
//				rewiringFlag = true;
//				if (r < rewiringProbability) {
//					targetNode = (int) (N * Math.random());
//					if (rnd.NextUnif() < 0.5) {
//						for (int j = 0; j < neighborMatrix.get(left).size(); j++) {
//							if (targetNode == neighborMatrix.get(left).get(j)) {
//								rewiringFlag = false;
//								break;
//							}
//						}
//						if (targetNode == left)
//							rewiringFlag = false;
//						if (rewiringFlag = true) {
//							pairList[i][1] = targetNode;
//							neighborMatrix.get(left).remove(neighborMatrix.get(left).indexOf(right));
//							neighborMatrix.get(right).remove(neighborMatrix.get(right).indexOf(left));
//							neighborMatrix.get(left).add(targetNode);
//							neighborMatrix.get(targetNode).add(left);
//						}
//					} else {
//						for (int j = 0; j < neighborMatrix.get(right).size(); j++) {
//							if (targetNode == neighborMatrix.get(right).get(j)) {
//								rewiringFlag = false;
//								break;
//							}
//						}
//						if (targetNode == right)
//							rewiringFlag = false;
//						if (rewiringFlag = true) {
//							pairList[i][1] = targetNode;
//							neighborMatrix.get(left).remove(neighborMatrix.get(left).indexOf(right));
//							neighborMatrix.get(right).remove(neighborMatrix.get(right).indexOf(left));
//							neighborMatrix.get(right).add(targetNode);
//							neighborMatrix.get(targetNode).add(right);
//						}
//					}
//				}
//			} while (!rewiringFlag);
//
//		}

		System.out.println("debag:line 124" );

		double []printRateList = {0.1,0.2};
		String folderName1 = "RealDate";
		new File(folderName1).mkdirs();
		folderName1 = folderName1 + "/";
		String folderName2 = "BrokenNodeLCS";
		new File(folderName2).mkdirs();
		folderName2 = folderName2 + "/";

		if (typeSelectorswitch == 0) {		//各頂点ごとに確率fで破壊→連鎖を行うパーコレーション
			GraduationResearchMaterial obj = new GraduationResearchMaterial();
			String fileName1 = "DBN,LCSver.txt";
			String fileName2 = "UDBN,LCSver";
			String fileName3 = "ABN,LCSver";
			obj.PercolationExperiment(true, true, 100, 100, N, degreeList, neighborList, addressList,
					folderName1 + "loop100C.txt", folderName1 + "loop100CDestRate",
					folderName2 + fileName1,folderName2 + fileName2, folderName2 + fileName3, printRateList);
			System.out.println("type1 finished");
		} else if(typeSelectorswitch == 1){//全頂点を確率fで破壊、その後に連鎖破壊を行うパーコレーション
			GraduationResearchMaterial2 obj2 = new GraduationResearchMaterial2();
			String type2fileName1 = "type2DBN,LCSvert";
			String type2fileName2 = "type2UDBN,LCSver";
			String type2fileName3 = "type2ABN,LCSver";

			System.out.println("debag:line 149" );

			obj2.PercolationExperiment2(true,true,100,100,N, degreeList, neighborList, addressList,
					folderName1+"type2loop100C.txt",folderName1+"type2loop100CDestRate",
					folderName2+type2fileName1,folderName2+type2fileName2,folderName2+type2fileName3,printRateList);
			System.out.println("type2 finished");
		}else if(typeSelectorswitch == 2){ //同じグラフでtype1とtype2の２つを行う
			GraduationResearchMaterial obj = new GraduationResearchMaterial();
			String fileName1 = "DBN,LCSverloop100C.txt";
			String fileName2 = "UDBN,LCSverloop100C.txt";
			String fileName3 = "ABN,LCSverloop100C.txt";
			obj.PercolationExperiment(true, true, 100, 100, N, degreeList, neighborList, addressList,
					folderName1 + "RGloop100C.txt", folderName1 + "RGloop100CDestRate",
					folderName2 + fileName1,folderName2 + fileName2, folderName2 + fileName3, printRateList);
			System.out.println("type1 finished");
			GraduationResearchMaterial2 obj2 = new GraduationResearchMaterial2();
			String type2fileName1 = "type2DBN,LCSverloop100C.txt";
			String type2fileName2 = "type2UnDBN,LCSverloop100C.txt";
			String type2fileName3 = "type2ABN,LCSverloop100C.txt";
			obj2.PercolationExperiment2(true,true,100,100,N, degreeList, neighborList, addressList,
					folderName1+"type2loop100C.txt",folderName1+"type2loop100CDestRate",
					folderName2+type2fileName1,folderName2+type2fileName2,folderName2+type2fileName3,printRateList);
			System.out.println("type2 finished");
		}
		System.out.println("平均次数"+(1.0 * M) / N);
	}

}
