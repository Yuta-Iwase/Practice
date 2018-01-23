package Network;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

//パーコレーション方法その２
public class GraduationResearchMaterial2 {

	public static void main(String[] args) throws Exception {

		PrintWriter pw2 = null;
		pw2 = new PrintWriter(new File("testEN-2.csv"));

		Random random = new Random();

		int N = 53381;
		int kmin = 2;
		int kcut = (int) (0.1 * N);
		double gan = 2.7;
		double p[] = new double[kcut + 1];
		double sum = 0.0;
		double[] pcum = new double[N];
		int[] degreeList = new int[N];
		boolean success = false;
		int errorNum = 0;

		// 次数分布
		for (int k = kmin; k <= kcut; k++) {
			p[k] = Math.pow(k, -gan);
			sum += p[k];// 規格化定数
		}
		for (int k = kmin; k <= kcut; k++) {
			p[k] /= sum;
		}

		// 累積分布pcum[i]
		pcum[0] = p[0];
		for (int i = 1; i <= kcut; i++) {
			pcum[i] = p[i] + pcum[i - 1];
		}

		// degreeListの作成
		double ran1;
		int a;
		a = kmin;
		for (int i = 0; i < N; i++) {
			ran1 = Math.random();
			while (ran1 > pcum[a]) {
				a++;
			}
			degreeList[i] = a;
			a = 0;
		}

		// 頂点の次数の総和(辺の総数m)
		int m = 0;
		for (int i = 0; i < N; i++) {
			m += degreeList[i];
		}
		if (m % 2 != 0) {
			degreeList[0] += 1;
			m = 0;
			for (int i = 0; i < N; i++) {
				m += degreeList[i];
			}
		}
		// 隣接リストを作成
		int[] addressList = new int[N];
		int[] neighborList = new int[m];

		// addressList作成。
		addressList[0] = 0;
		for (int i = 1; i < N; i++) {
			addressList[i] = addressList[i - 1] + degreeList[i - 1];
		}
		while (success == false) {
			// コンフィグレーションモデルを作成。
			int[] stubList = new int[m];
			int rest = 0;
			int[] cursor = new int[N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < degreeList[i]; j++) {
					stubList[rest++] = i;
				}
			}
			for (int i = 0; i < N; i++) {
				cursor[i] = addressList[i];
			}
			int pos1, pos2, node1, node2;
			errorNum = 0;
			while (rest != 0) {
				pos1 = random.nextInt(rest);
				pos2 = random.nextInt(rest);
				if (pos1 < pos2) {
					int temp = pos2;
					pos2 = pos1;
					pos1 = temp;
				}
				node1 = stubList[pos1];
				node2 = stubList[pos2];
				boolean connect = true;
				if (node1 == node2) {
					connect = false;
					errorNum += 1;
				}
				if (connect) {
					for (int i = addressList[node1]; i < cursor[node1]; i++) {
						if (neighborList[i] == node2) {
							connect = false;
							errorNum += 1;
							break;
						}
					}
				}
				if (connect) {
					errorNum = 0;
					neighborList[cursor[node1]] = node2;
					cursor[node1] += 1;
					neighborList[cursor[node2]] = node1;
					cursor[node2] += 1;
					stubList[pos1] = stubList[rest - 1];
					stubList[pos2] = stubList[rest - 2];
					rest -= 2;
				}
				if (errorNum > 100) {
					System.out.println("正常に動作しませんでした。" + "\t" + success);
					break;
				}
			}
			if (errorNum < 100) {
				success = true;
			}

		} // コンフィグ成功するまでやるループ
		System.out.println("errorNum:" + errorNum + "\t" + success);
		// pairList作成
		int[][] pairList = new int[m / 2][2];
		int left, right;
		int cursor2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = addressList[i]; j < addressList[i] + degreeList[i]; j++) {
				left = i;
				right = neighborList[j];
				if (left < right) {
					pairList[cursor2][0] = left;
					pairList[cursor2][1] = right;
					cursor2 += 1;
				}
			}
		}

		// for (int i = 0; i < cursor2; i++) {
		// System.out.println(pairList[i][0] + "\t" + pairList[i][1]);
		// pw2.println(pairList[i][0]+"\t"+pairList[i][1]);
		// }
		pw2.close();

		double[] printRateList = { 0.1, 0.2 };
		GraduationResearchMaterial2 obj2 = new GraduationResearchMaterial2();
		String folderName1 = "type2Configuration";
		new File(folderName1).mkdirs();
		folderName1 = folderName1 + "/";
		String folderName2 = "type2BrokenNodeLCS";
		new File(folderName2).mkdirs();
		folderName2 = folderName2 + "/";
		String fileName1 = folderName2 + "debag";
		String fileName2 = folderName2 + "debag";
		String fileName3 = folderName2 + "debag";
		obj2.PercolationExperiment2(true, true, 1, 1, N, degreeList, neighborList, addressList,
				folderName1 + "debag.txt", folderName1 + "debag", fileName1, fileName2, fileName3, printRateList);
		System.out.println("平均次数" + (1.0 * m) / N);
	}

	void PercolationExperiment2(boolean plusNeighbor, boolean brokenLCScheck, int split, int loop, int N,
			int[] degreeList, int[] neighborList, int[] addressList, String fileName, String destructionRateName,
			String fileName1, String fileName2, String fileName3, double[] printRateList) throws Exception {

		PrintWriter pw3 = new PrintWriter(new File(fileName));
		PrintWriter dBpw = new PrintWriter(new File(fileName1));
		PrintWriter udBpw = new PrintWriter(new File(fileName2));
		PrintWriter aBpw = new PrintWriter(new File(fileName3));

		ArrayList<Double> printRateArrayList = new ArrayList<Double>();
		for (int i = 0; i < printRateList.length; i++) {
			printRateArrayList.add(printRateList[i]);
		}

		// ネットワーク破壊＆最大連結成分計算
		ArrayList<Integer> queue = new ArrayList<Integer>();
		ArrayList<Integer> memberList = new ArrayList<Integer>();
		ArrayList<Integer> restList = new ArrayList<Integer>();
		ArrayList<Integer> sizeList = new ArrayList<Integer>(); // 各fにおける最大連結成分を記録。←いらないんじゃないかな？
		ArrayList<Integer> directBrokenList = new ArrayList<Integer>(); // 確率fで直接破壊された頂点を記録。
		ArrayList<Integer> undirectBrokenList = new ArrayList<Integer>(); // 連鎖破壊に巻き込まれた頂点を記録。
		ArrayList<Integer> BrokenList = new ArrayList<Integer>(); // 破壊された頂点を記録。
		ArrayList<Integer> NormalNodeList = new ArrayList<Integer>(); // ☆破壊されていない頂点のリスト
		boolean[] visitQ = new boolean[N]; // 訪問済み頂点チェック
		boolean[] visitDBQ = new boolean[N]; // 訪問済み頂点チェック(直接破壊されている頂点ver)
		boolean[] visitUDBQ = new boolean[N]; // 訪問済み頂点チェック(間接破壊されている頂点ver)
		boolean[] visitABQ = new boolean[N]; // 訪問済み頂点チェック(破壊されている頂点ver)
		boolean[] visitNor = new boolean[N]; // ☆

		int vi;
		int vj;
		int componentSize[] = new int[N]; // 一時的な連結成分置き場
		int componentDBSize[] = new int[N]; // 一時的な連結成分置き場(直接破壊されている頂点ver)
		int componentUDBSize[] = new int[N]; // 一時的な連結成分置き場(間接破壊されている頂点ver)
		int componentABSize[] = new int[N]; // 一時的な連結成分置き場(破壊されている頂点ver)

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
			double sumUD = 0; // f毎の平均(間接)破壊最大連結成分を出すための変数
			double sumAB = 0; // f毎の平均(全て)破壊最大連結成分を出すための変数
			for (int t = 0; t < loop; t++) {
				for (int i = 0; i < N; i++) {
					visitDBQ[i] = true;
					visitUDBQ[i] = true;
					visitABQ[i] = true;
					visitQ[i] = false;
					restList.add(i);
				}
				// ランダム頂点破壊
				for (int l = 0; l < N; l++) {
					if ((Math.random() > 1 - f) && (restList.indexOf(l) != -1)) {
						restList.remove(restList.indexOf(l));
						visitQ[l] = true;
						visitDBQ[l] = false;
						visitABQ[l] = false;
						demolition[degreeList[l]] += 1;
						directBrokenList.add(l);
						BrokenList.add(l);
					}
				}

				// ここから連鎖破壊
				if (plusNeighbor == true) {
					for (int l = 0; l < N; l++) {
						if (visitDBQ[l] == false) {
							for (int j = 0; j < degreeList[l]; j++) {
								if (visitQ[neighborList[addressList[l] + j]] == false) {
									restList.remove(restList.indexOf(neighborList[addressList[l] + j]));
									visitUDBQ[neighborList[addressList[l] + j]] = false;
									visitABQ[neighborList[addressList[l] + j]] = false;
									visitQ[neighborList[addressList[l] + j]] = true;
									demolition[degreeList[neighborList[addressList[l] + j]]] += 1;
									undirectBrokenList.add(neighborList[addressList[l] + j]);
									BrokenList.add(neighborList[addressList[l] + j]);
								}
							}
						}
					}
				}
				// ☆ココまでを踏まえて生存ノードのリストを構築
				for(int nodeIndex=0;nodeIndex<N;nodeIndex++) {
					if(visitQ[nodeIndex]) {
						NormalNodeList.add(nodeIndex);
						visitNor[nodeIndex] = true;
					}else {
						visitNor[nodeIndex] = false;
					}
				}

				memberList.clear();
				// ここから破壊された頂点の最大連結成分計算
				if (brokenLCScheck == true) {
					// まず直接破壊された頂点の連結成分を計算
					if (!directBrokenList.isEmpty()) {
						int v0 = directBrokenList.get(0);
						memberList.add(v0);
						visitDBQ[v0] = true;
						queue.add(v0);
						while (!directBrokenList.isEmpty()) {
							if (queue.isEmpty()) {
								componentDBSize[y] = memberList.size();
								memberList.clear();
								vi = directBrokenList.get(0);
								visitDBQ[vi] = true;
								memberList.add(vi);
								directBrokenList.remove(directBrokenList.indexOf(vi));
								y++;
							} else {
								vi = queue.get(0);
								directBrokenList.remove(directBrokenList.indexOf(vi));
								queue.remove(0);
							}
							for (int i = 0; i < degreeList[vi]; i++) {
								vj = neighborList[addressList[vi] + i];
								if ((visitDBQ[vj] == false) && (directBrokenList.indexOf(vj) != -1)) {
									queue.add(vj);
									memberList.add(vj);
									visitDBQ[vj] = true;
								}
							}
						}
						componentDBSize[y] = memberList.size(); // whileから出てきた際の最後の探索での連結成分を記録。
						int directBrokenLCS = componentDBSize[0]; // 仮に最大連結成分を最初の探索での島とする。
						for (int i = 0; i <= y; i++) { // 比較して最大連結成分を割り出す。
							if (directBrokenLCS < componentDBSize[i]) {
								directBrokenLCS = componentDBSize[i];
							}
						}
						// sizeList.add();
						directBrokenList.clear();
						memberList.clear();
						y = 0;
						sumD += (double) directBrokenLCS;
					} // 直接破壊の最大連結成分終了
					memberList.clear();

					// 次に間接破壊された頂点の連結成分を計算
					if (!undirectBrokenList.isEmpty()) {
						int v0 = undirectBrokenList.get(0);
						memberList.add(v0);
						visitUDBQ[v0] = true;
						queue.add(v0);
						while (!undirectBrokenList.isEmpty()) {
							if (queue.isEmpty()) {
								componentUDBSize[y] = memberList.size();
								memberList.clear();
								vi = undirectBrokenList.get(0);
								visitUDBQ[vi] = true;
								memberList.add(vi);
								undirectBrokenList.remove(undirectBrokenList.indexOf(vi));
								y++;
							} else {
								vi = queue.get(0);
								undirectBrokenList.remove(undirectBrokenList.indexOf(vi));
								queue.remove(0);
							}
							for (int i = 0; i < degreeList[vi]; i++) {
								vj = neighborList[addressList[vi] + i];
								if ((visitUDBQ[vj] == false) && (undirectBrokenList.indexOf(vj) != -1)) {
									queue.add(vj);
									memberList.add(vj);
									visitUDBQ[vj] = true;
								}
							}
						}
						componentUDBSize[y] = memberList.size();
						int undirectBrokenLCS = componentUDBSize[0];
						for (int i = 0; i <= y; i++) {
							if (undirectBrokenLCS < componentUDBSize[i]) {
								undirectBrokenLCS = componentUDBSize[i];
							}
						}
						// sizeList.add();
						undirectBrokenList.clear();
						memberList.clear();
						y = 0;
						sumUD += (double) undirectBrokenLCS;
					} // 間接破壊の最大連結成分終了
					memberList.clear();

					// ここから破壊された全ての頂点の最大連結成分計算
					if (!BrokenList.isEmpty()) {
						int v0 = BrokenList.get(0);
						memberList.add(v0);
						visitABQ[v0] = true;
						queue.add(v0);
						while (!BrokenList.isEmpty()) {
							if (queue.isEmpty()) {
								componentABSize[y] = memberList.size();
								memberList.clear();
								vi = BrokenList.get(0);
								visitABQ[vi] = true;
								memberList.add(vi);
								BrokenList.remove(BrokenList.indexOf(vi));
								y++;
							} else {
								vi = queue.get(0);
								BrokenList.remove(BrokenList.indexOf(vi));
								queue.remove(0);
							}
							for (int i = 0; i < degreeList[vi]; i++) {
								vj = neighborList[addressList[vi] + i];
								if ((visitABQ[vj] == false) && (BrokenList.indexOf(vj) != -1)) {
									queue.add(vj);
									memberList.add(vj);
									visitABQ[vj] = true;
								}
							}
						}
						// System.out.println("last all cc size=" +
						// memberList.size());
						componentABSize[y] = memberList.size();

						int BrokenLCS = componentABSize[0];
						// System.out.println("y=" + y);
						for (int i = 0; i <= y; i++) {
							if (BrokenLCS < componentABSize[i]) {
								BrokenLCS = componentABSize[i];
							}
						}
						// sizeList.add();
						BrokenList.clear();
						memberList.clear();
						y = 0;
						sumAB += (double) BrokenLCS;
					}
					memberList.clear();



					// TODO ☆生存ノードのLCC_size を計算
					if (!NormalNodeList.isEmpty()) {
						int v0 = undirectBrokenList.get(0);
						memberList.add(v0);
						visitUDBQ[v0] = true;
						queue.add(v0);
						while (!undirectBrokenList.isEmpty()) {
							if (queue.isEmpty()) {
								componentUDBSize[y] = memberList.size();
								memberList.clear();
								vi = undirectBrokenList.get(0);
								visitUDBQ[vi] = true;
								memberList.add(vi);
								undirectBrokenList.remove(undirectBrokenList.indexOf(vi));
								y++;
							} else {
								vi = queue.get(0);
								undirectBrokenList.remove(undirectBrokenList.indexOf(vi));
								queue.remove(0);
							}
							for (int i = 0; i < degreeList[vi]; i++) {
								vj = neighborList[addressList[vi] + i];
								if ((visitUDBQ[vj] == false) && (undirectBrokenList.indexOf(vj) != -1)) {
									queue.add(vj);
									memberList.add(vj);
									visitUDBQ[vj] = true;
								}
							}
						}
						componentUDBSize[y] = memberList.size();
						int undirectBrokenLCS = componentUDBSize[0];
						for (int i = 0; i <= y; i++) {
							if (undirectBrokenLCS < componentUDBSize[i]) {
								undirectBrokenLCS = componentUDBSize[i];
							}
						}
						// sizeList.add();
						undirectBrokenList.clear();
						memberList.clear();
						y = 0;
						sumUD += (double) undirectBrokenLCS;
					} // 間接破壊の最大連結成分終了
					memberList.clear();

				}
				memberList.clear();

				if (!restList.isEmpty()) { // restListが空の場合以下１行の動作でエラーが出るため
					int v0 = restList.get(0);
					queue.add(v0); // 訪れた頂点を蓄える、幅優先探索に利用
					memberList.add(v0); // 訪れた頂点を記載
					visitQ[v0] = true;
					while (!restList.isEmpty()) {
						if (queue.isEmpty()) { // グラフが分裂している場合の分岐
							componentSize[x] = memberList.size(); // 連結成分を記録
							memberList.clear();
							vi = restList.get(0); // キューが空でもまだrestListに頂点が残っている場合を考える。(複数のグラフに分かれている場合)
							visitQ[vi] = true;
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
							if ((visitQ[vj] == false) && (restList.indexOf(vj) != -1)) {
								queue.add(vj);
								memberList.add(vj);
								visitQ[vj] = true;
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
			udBpw.println(f + "\t" + sumUD / loop);
			// System.out.println("AllBroken"+f + "\t" + sumAB/loop);
			aBpw.println(f + "\t" + sumAB / loop);

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
