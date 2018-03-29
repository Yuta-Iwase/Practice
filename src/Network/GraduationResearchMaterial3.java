package Network;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

//パーコレーション方法その２
public class GraduationResearchMaterial3 {

	void PercolationExperiment2(boolean plusNeighbor, boolean brokenLCScheck, int split, int loop, int N,
			int[] degreeList, int[] neighborList, int[] addressList, String fileName, String destructionRateName,
			String fileName1, String fileName2, String fileName3, double[] printRateList) throws Exception {

		PrintWriter pw3 = new PrintWriter(new File(fileName));
		PrintWriter dBpw = new PrintWriter(new File(fileName1));
		PrintWriter udBpw = new PrintWriter(new File(fileName2));
		PrintWriter aBpw = new PrintWriter(new File(fileName3));

		PrintWriter pw_ex = new PrintWriter(new File(fileName3 + "aaaaaaaaaaaaa.txt"));

		ArrayList<Double> printRateArrayList = new ArrayList<Double>();
		for (int i = 0; i < printRateList.length; i++) {
			printRateArrayList.add(printRateList[i]);
		}

		// ネットワーク破壊＆最大連結成分計算
		ArrayList<Integer> queue = new ArrayList<Integer>();
		ArrayList<Integer> memberList = new ArrayList<Integer>();
		ArrayList<Integer> restList = new ArrayList<Integer>();
		ArrayList<Integer> sizeList = new ArrayList<Integer>(); // 各fにおける最大連結成分を記録。←いらないんじゃないかな？

		boolean[] visit = new boolean[N]; // 訪問済み頂点チェック
		boolean[] visitD = new boolean[N]; // 訪問済み頂点チェック(直接破壊されている頂点ver)
		boolean[] visitI = new boolean[N]; // 訪問済み頂点チェック(間接破壊されている頂点ver)
		boolean[] visitDI = new boolean[N]; // 訪問済み頂点チェック(破壊されている頂点ver)
		boolean[] visitN = new boolean[N]; // ☆

		int vi;
		int vj;
		int componentSize[] = new int[N]; // 一時的な連結成分置き場
		int componentD_Size[] = new int[N]; // 一時的な連結成分置き場(直接破壊されている頂点ver)
		int componentI_Size[] = new int[N]; // 一時的な連結成分置き場(間接破壊されている頂点ver)
		int componentDI_Size[] = new int[N]; // 一時的な連結成分置き場(破壊されている頂点ver)
		int componentN_Size[] = new int[N]; // ☆

		int x = 0; // 配列componentの記録場所を移動させるための変数
		int y = 0; // 配列componentの記録場所を移動させるための変数(破壊されている頂点ver)
		for (int fIndex = 0; fIndex < split; fIndex++) {
			double f = (double) fIndex / split;
			int[] demolition = new int[N + 1]; // 破壊された頂点次数を記録していく。長さは最大次数
			int[] degreeNum = new int[N + 1]; // 同じ次数を持つ頂点の個数を記録

			for (int i = 0; i < demolition.length; i++) {
				demolition[i] = 0;
				degreeNum[i] = 0;
			}
			for (int i = 0; i < N; i++) {
				degreeNum[degreeList[i]] += 1;
			}
			double sum2 = 0; // f毎の平均最大連結成分を出すための変数
			double sumD = 0; // f毎の平均(直接)破壊最大連結成分を出すための変数
			double sumI = 0; // f毎の平均(間接)破壊最大連結成分を出すための変数
			double sumDI = 0; // f毎の平均(全て)破壊最大連結成分を出すための変数
			double sumN = 0.0; // ☆

			for (int t = 0; t < loop; t++) {
				for (int i = 0; i < N; i++) {
					visitD[i] = true;
					visitI[i] = true;
					visitDI[i] = true;
					visit[i] = false;
					restList.add(i);
				}
				// ランダム頂点破壊
				for (int l = 0; l < N; l++) {
					if ((Math.random() > 1 - f) && (restList.indexOf(l) != -1)) {
						restList.remove(restList.indexOf(l));
						visit[l] = true;
						visitD[l] = false;
						visitDI[l] = false;
						demolition[degreeList[l]] += 1;
					}
				}

				// ここから連鎖破壊
				if (plusNeighbor == true) {
					for (int l = 0; l < N; l++) {
						if (visitD[l] == false) {
							for (int j = 0; j < degreeList[l]; j++) {
								if (visit[neighborList[addressList[l] + j]] == false) {
									restList.remove(restList.indexOf(neighborList[addressList[l] + j]));
									visitI[neighborList[addressList[l] + j]] = false;
									visitDI[neighborList[addressList[l] + j]] = false;
									visit[neighborList[addressList[l] + j]] = true;
									demolition[degreeList[neighborList[addressList[l] + j]]] += 1;
								}
							}
						}
					}
				}
				// ☆ココまでを踏まえて生存ノードのリストを構築
				for(int nodeIndex=0;nodeIndex<N;nodeIndex++) {
					if(visit[nodeIndex]) {
						visitN[nodeIndex] = true;
					}else {
						visitN[nodeIndex] = false;
					}
				}

				memberList.clear();
				boolean contFlag;
				// ここから破壊された頂点の最大連結成分計算
				if (brokenLCScheck == true) {
					// まず直接破壊された頂点の連結成分を計算
					contFlag = true;
					if (contFlag) {
						int v0;
						contFlag = false;
						for(int i=0;i<visitD.length;i++){
							if(!visitD[i]){
								v0 = i;
								memberList.add(v0);
								visitD[v0] = true;
								queue.add(v0);
								contFlag = true;
								break;
							}
						}

						while (contFlag) {
							if (queue.isEmpty()) {
								componentD_Size[y] = memberList.size();
								memberList.clear();

								vi=-1;
								contFlag = false;
								for(int i=0;i<visitD.length;i++){
									if(!visitD[i]){
										vi = i;
										memberList.add(vi);
										visitD[vi] = true;
										queue.add(vi);
										contFlag = true;
										break;
									}
								}

								memberList.add(vi);
								y++;
							} else {
								vi = queue.get(0);
								visitD[vi] = true;
								queue.remove(0);
							}
							if(vi>=0){
								for (int i = 0; i < degreeList[vi]; i++) {
									vj = neighborList[addressList[vi] + i];
									if (visitD[vj] == false) {
										queue.add(vj);
										memberList.add(vj);
										visitD[vj] = true;
									}
								}
							}
						}
						componentD_Size[y] = memberList.size(); // whileから出てきた際の最後の探索での連結成分を記録。
						int D_LCC_size = componentD_Size[0]; // 仮に最大連結成分を最初の探索での島とする。
						for (int i = 0; i <= y; i++) { // 比較して最大連結成分を割り出す。
							if (D_LCC_size < componentD_Size[i]) {
								D_LCC_size = componentD_Size[i];
							}
						}
						// sizeList.add();
						memberList.clear();
						y = 0;
						sumD += (double) D_LCC_size;
					} // 直接破壊の最大連結成分終了
					memberList.clear();

					// まず直接破壊された頂点の連結成分を計算
					contFlag = true;
					if (contFlag) {
						int v0;
						contFlag = false;
						for(int i=0;i<visitI.length;i++){
							if(!visitI[i]){
								v0 = i;
								memberList.add(v0);
								visitI[v0] = true;
								queue.add(v0);
								contFlag = true;
								break;
							}
						}

						while (contFlag) {
							if (queue.isEmpty()) {
								componentI_Size[y] = memberList.size();
								memberList.clear();

								vi=-1;
								contFlag = false;
								for(int i=0;i<visitI.length;i++){
									if(!visitI[i]){
										vi = i;
										memberList.add(vi);
										visitI[vi] = true;
										queue.add(vi);
										contFlag = true;
										break;
									}
								}

								memberList.add(vi);
								y++;
							} else {
								vi = queue.get(0);
								visitI[vi] = true;
								queue.remove(0);
							}
							if(vi>=0){
								for (int i = 0; i < degreeList[vi]; i++) {
									vj = neighborList[addressList[vi] + i];
									if (visitI[vj] == false) {
										queue.add(vj);
										memberList.add(vj);
										visitI[vj] = true;
									}
								}
							}
						}
						componentI_Size[y] = memberList.size(); // whileから出てきた際の最後の探索での連結成分を記録。
						int I_LCC_size = componentI_Size[0]; // 仮に最大連結成分を最初の探索での島とする。
						for (int i = 0; i <= y; i++) { // 比較して最大連結成分を割り出す。
							if (I_LCC_size < componentI_Size[i]) {
								I_LCC_size = componentI_Size[i];
							}
						}
						// sizeList.add();
						memberList.clear();
						y = 0;
						sumI += (double) I_LCC_size;
					} // 直接破壊の最大連結成分終了
					memberList.clear();

					// まず直接破壊された頂点の連結成分を計算
					contFlag = true;
					if (contFlag) {
						int v0;
						contFlag = false;
						for(int i=0;i<visitDI.length;i++){
							if(!visitDI[i]){
								v0 = i;
								memberList.add(v0);
								visitDI[v0] = true;
								queue.add(v0);
								contFlag = true;
								break;
							}
						}

						while (contFlag) {
							if (queue.isEmpty()) {
								componentDI_Size[y] = memberList.size();
								memberList.clear();

								vi=-1;
								contFlag = false;
								for(int i=0;i<visitDI.length;i++){
									if(!visitDI[i]){
										vi = i;
										queue.add(vi);
										memberList.add(vi);
										visitDI[vi] = true;
										contFlag = true;
										break;
									}
								}

								y++;
								memberList.add(vi);
							} else {
								vi = queue.get(0);
								queue.remove(0);
							}
							if(vi>=0){
								for (int i = 0; i < degreeList[vi]; i++) {
									vj = neighborList[addressList[vi] + i];
									if (!visitDI[vj]) {
										queue.add(vj);
										memberList.add(vj);
										visitDI[vj] = true;
									}
								}
							}
						}
						componentDI_Size[y] = memberList.size(); // whileから出てきた際の最後の探索での連結成分を記録。
						int DI_LCC_size = componentDI_Size[0]; // 仮に最大連結成分を最初の探索での島とする。
						for (int i = 0; i <= y; i++) { // 比較して最大連結成分を割り出す。
							if (DI_LCC_size < componentDI_Size[i]) {
								DI_LCC_size = componentDI_Size[i];
							}
						}
						// sizeList.add();
						memberList.clear();
						y = 0;
						sumDI += (double) DI_LCC_size;
					} // 直接破壊の最大連結成分終了
					memberList.clear();



					// まず直接破壊された頂点の連結成分を計算
					contFlag = true;
					if (contFlag) {
						int v0;
						contFlag = false;
						for(int i=0;i<visitN.length;i++){
							if(!visitN[i]){
								v0 = i;
								memberList.add(v0);
								visitN[v0] = true;
								queue.add(v0);
								contFlag = true;
								break;
							}
						}

						while (contFlag) {
							if (queue.isEmpty()) {
								componentN_Size[y] = memberList.size();
								memberList.clear();

								vi=-1;
								contFlag = false;
								for(int i=0;i<visitN.length;i++){
									if(!visitN[i]){
										vi = i;
										memberList.add(vi);
										visitN[vi] = true;
										queue.add(vi);
										contFlag = true;
										break;
									}
								}

								memberList.add(vi);
								y++;
							} else {
								vi = queue.get(0);
								visitN[vi] = true;
								queue.remove(0);
							}

							if(vi>=0){
								for (int i = 0; i < degreeList[vi]; i++) {
									vj = neighborList[addressList[vi] + i];
									if (visitN[vj] == false) {
										queue.add(vj);
										memberList.add(vj);
										visitN[vj] = true;
									}
								}
							}
						}
						componentN_Size[y] = memberList.size(); // whileから出てきた際の最後の探索での連結成分を記録。
						int N_LCC_size = componentN_Size[0]; // 仮に最大連結成分を最初の探索での島とする。
						for (int i = 0; i <= y; i++) { // 比較して最大連結成分を割り出す。
							if (N_LCC_size < componentN_Size[i]) {
								N_LCC_size = componentN_Size[i];
							}
						}
						// sizeList.add();
						memberList.clear();
						y = 0;
						sumN += (double) N_LCC_size;
					} // 直接破壊の最大連結成分終了
					memberList.clear();
				}

				if (!restList.isEmpty()) { // restListが空の場合以下１行の動作でエラーが出るため
					int v0 = restList.get(0);
					queue.add(v0); // 訪れた頂点を蓄える、幅優先探索に利用
					memberList.add(v0); // 訪れた頂点を記載
					visit[v0] = true;
					while (!restList.isEmpty()) {
						if (queue.isEmpty()) { // グラフが分裂している場合の分岐
							componentSize[x] = memberList.size(); // 連結成分を記録
							memberList.clear();
							vi = restList.get(0); // キューが空でもまだrestListに頂点が残っている場合を考える。(複数のグラフに分かれている場合)
							visit[vi] = true;
							memberList.add(vi);
							restList.remove(restList.indexOf(vi));
							x++;
						} else {
							vi = queue.get(0);
							restList.remove(restList.indexOf(vi));
							queue.remove(0);
						}
						for (int i = 0; i < degreeList[vi]; i++) {
							vj = neighborList[addressList[vi] + i];
							if ((visit[vj] == false) && (restList.indexOf(vj) != -1)) {
								queue.add(vj);
								memberList.add(vj);
								visit[vj] = true;
							}
						}
					}
					componentSize[x] = memberList.size(); // whileから出てきた際の最後の探索での連結成分を記録。

					int largestComponentSize = componentSize[0]; // 仮に最大連結成分を最初の探索での島とする。

					for (int i = 0; i <= x; i++) { // 比較して最大連結成分を割り出す。
						if (largestComponentSize < componentSize[i]) {
							largestComponentSize = componentSize[i];
						}
					}
					sizeList.add(largestComponentSize);
					restList.clear();
					memberList.clear();
					x = 0;
					sum2 += (double) largestComponentSize;
				}
			}
			pw3.println(f + "\t" + sum2 / loop); // loop回数で割って平均最大連結成分を出す。
			// System.out.println(f + "\t" + sum2/loop);
			// System.out.println("Direct "+f + "\t" + sumD/loop);
			dBpw.println(f + "\t" + sumD / loop);
			// System.out.println("UnDirect "+f + "\t" + sumUD/loop);
			udBpw.println(f + "\t" + sumI / loop);
			// System.out.println("AllBroken"+f + "\t" + sumAB/loop);
			aBpw.println(f + "\t" + sumDI / loop);

			pw_ex.println(f + "\t" + sumN / loop);

			if (printRateArrayList.size() > 0) {
				if (f == printRateArrayList.get(0)) {
					PrintWriter pw4 = new PrintWriter(new File(destructionRateName + f + ".txt"));
					for (int i = 0; i < (int) (0.1 * N); i++) {
						if (degreeNum[i] != 0) {
							// System.out.println(i+"\t"+(1.0-((double)demolition[i]/degreeNum[i])));
							pw4.println(i + "\t" + (1.0 - ((double) demolition[i] / (degreeNum[i] * loop))));
						}
					}
					pw4.close();
				}
				if (f >= printRateArrayList.get(0)) {
					printRateArrayList.remove(0);
				}
			}
			if ((fIndex + 1) % 10 == 0) {
				System.out.println("type2進捗度" + (fIndex + 1) + "%: " + new Date().toString());
			}
		}
		dBpw.close();
		udBpw.close();
		aBpw.close();

		pw_ex.close();

		// System.out.println(sizeList);
		pw3.close();
	}

	void PercolationExperiment2(boolean plusNeighbor, boolean brokenLCS, int split, int loop, int N, int[] degreeList,
			int[] neighborList, int[] addressList, String fileName, String destructionRateName, String fileName1,
			String fileName2, String fileName3) throws Exception {
		double[] empty = {};
		PercolationExperiment2(plusNeighbor, brokenLCS, split, loop, N, degreeList, neighborList, addressList, fileName,
				destructionRateName, fileName1, fileName2, fileName3, empty);
	}
}
