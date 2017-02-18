
class test {
	public static void main(String[] args){
		double Num = 23.45;
		String StNum = Double.toString(Num);
		String[] SpritNum = StNum.split("\\.");
		
		String StI = SpritNum[0];
		String StD = "0." + SpritNum[1];
		
		int i = Integer.parseInt(StI);
		double d = Double.parseDouble(StD);
		
		String StBitI = Integer.toBinaryString(i);
		
		String StBitD = "";
		int ex = -1;
		do{
			if(d >= Math.pow(2, ex)){
				StBitD += 1;
				d -= Math.pow(2, ex);
			}else{
				StBitD += 0;
			}
			ex--;
		}while(d!=0.0);
		
		double BitNum = Double.parseDouble(StBitI + "." + StBitD);
		
		System.out.println(BitNum);
		
		
		
	}
}