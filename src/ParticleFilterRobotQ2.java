import java.io.File;
import java.io.PrintWriter;


public class ParticleFilterRobotQ2 {
	static int tmax = 18;
	static double Lx=5.0,Ly=3.0; //部屋の大きさ
	static int N0=100;
	static int nxmax=(int)Lx*N0, nymax=(int)Ly*N0;
	static int s[][] = new int[nxmax+1][nymax+1]; //部屋をマスで分ける
	static int kmax=8; //方位の数
	static double d[] = new double[kmax]; //各方位での距離
	
	public ParticleFilterRobotQ2() {
		for(int i=1;i<nxmax;i++){
			for(int j=1;j<nymax;j++){
				s[i][j] = 0;
			}
		}
		for(int j=0;j<=nymax;j++){
			s[0][j] = 1;
		}
		for(int j=0;j<=nymax;j++){
			s[nxmax][j] = 1;
		}
		for(int i=1;i<nxmax;i++){
			s[i][0] = 1;
		}
		for(int i=1;i<nxmax;i++){
			s[i][nymax] = 1;
		}
	}
	
	
	
	public static void main(String[] args) throws Exception{
		new ParticleFilterRobotQ2();
		double data[][]={
				{0.82,1.159655121145938,1.5,1.5,1.5,1.5,1.29,1.159655121145938},
				{1.01,1.4283556979968262,1.5,1.5,1.5,1.5,1.5,1.4283556979968262},
				{0.61,0.862670273047588,1.5,1.5,1.5,1.5,1.42,0.862670273047588},
				{0.96,1.3576450198781713,1.5,1.5,1.5,1.5,1.43,1.3576450198781713},
				{1.18,1.5,1.5,1.5,1.5,1.5,1.28,1.5},
				{0.79,1.1172287142747452,1.5,1.5,1.5,1.5,1.07,1.1172287142747452},
				{0.84,1.1879393923933999,1.5,1.5,1.5,1.5,1.45,1.1879393923933999},
				{1.08,1.5,1.5,1.5,1.5,1.5,1.5,1.5},
				{0.67,0.9475230867899738,1.5,1.5,1.5,1.5,1.3,0.9475230867899738},
				{0.64,0.9050966799187808,1.5,1.5,1.5,1.5,1.17,0.9050966799187808},
				{0.76,1.0748023074035522,1.5,1.5,1.5,1.5,1.5,1.0748023074035522},
				{0.68,0.9616652224137048,1.5,1.5,1.5,1.5,1.2,0.9616652224137048},
				{0.96,1.3576450198781713,1.5,1.5,1.5,1.4849242404917498,1.05,1.3576450198781713},
				{0.75,1.0606601717798214,1.5,1.5,1.5,1.173797256769669,0.83,1.0606601717798214},
				{0.29,0.4101219330881976,1.5,1.5,1.5,0.9192388155425119,0.65,0.4101219330881976},
				{0.47,0.6646803743153548,1.5,1.5,1.5,0.2262741699796952,0.16,0.2262741699796952},
				{0.76,1.0748023074035522,1.5,1.5,1.5,0.1414213562373095,0.1,0.1414213562373095},
				{0.59,0.8343860018001261,1.5,1.5,1.5,0.4808326112068524,0.34,0.4808326112068524},
				{0.81,1.145512985522207,1.5,1.5,1.5,0.1414213562373095,0.1,0.1414213562373095},
				{1.28,1.5,1.5,1.5,1.5,0.4101219330881976,0.29,0.4101219330881976}};
		
		double dx[]={
				0.0,0.27788582023390007,-0.14898626613984067,0.008280845467578679,-0.15587516322558015,-0.20898016691971488,0.3819895293151956,0.10883405942000657,-0.2540994076040666,-0.13459916854563203,0.48833198821000656,-0.45560031309622984,-0.15498328406069195,-0.21388129462850625,-0.186508456344311,-0.4855460329258363,-0.061239865294977464,0.24030903855659788,-0.24030903855659788,0.19181598101908745};
		
		double dy[]={
				0.0,-0.19149802394415127,0.4067210610120666,-0.35026460304710305,-0.22119224846106045,0.38817001819448693,-0.050043945227319586,-0.2393375073694699,0.4045316790393616,0.03421123790454672,-0.12261451201990825,0.07827341347661898,-0.2709536202634406,0.20806349546820258,0.45470425516660296,-0.17452426425377832,-0.29041819510375344,0.16649847890983693,-0.2188543955543092,-0.4725344349605538};
		
		String aaaaaaaa = "Robot"+(tmax-1)+"Q2.dat";
		PrintWriter aa = new PrintWriter(aaaaaaaa);
		aa.println(data[tmax-1][6] + "\t" + data[tmax-1][4]);
		aa.close();
		
		int nmax = 100;

		double x[] = new double[nmax];
		double y[] = new double[nmax];
		for(int n=0;n<nmax;n++){
			x[n] = (Lx-0.2)*Math.random()+0.1;
			y[n] = (Ly-0.2)*Math.random()+0.1;
		}
		
		double vx,vy;
		double a=1.0,s=0.25;
		double as=a*s;
		double r1,r2;
		final double pi = Math.PI;
		double[] xp = new double[nmax];
		double[] yp = new double[nmax];
		double error;
		double[] w = new double[nmax];
		double sum;
		int np;
		int j,jmax;
		int N;
		double R;
		int l;
		String fileName = "robotposition" + (tmax-1) + "Q2.dat";
		PrintWriter pw  = new PrintWriter(new File(fileName));
		for(int t=0;t<tmax;t++){
			sum = 0.0;
			for(int n=0;n<nmax;n++){
				r1=Math.random();
				r2=Math.random();
				vx = as * Math.sqrt(-2*Math.log(r1)) * Math.cos(2*pi*r2);
				r1=Math.random();
				r2=Math.random();
				vy = as * Math.sqrt(-2*Math.log(r1)) * Math.cos(2*pi*r2);
				
				xp[n] = x[n]+dx[t]+vx;
				if(xp[n]<0.1)xp[n]=0.1;
				else if(xp[n]>Lx-0.1) xp[n]=Lx-0.1;
				yp[n] = y[n]+dy[t]+vy;
				if(yp[n]<0.1)yp[n]=0.1;
				else if(yp[n]>Ly-0.1) yp[n]=Ly-0.1;
				
				distance(xp[n],yp[n]);
				error=0.0;
				for(int k=0;k<kmax;k++){
					error += (data[t][k]-d[k])*(data[t][k]-d[k]);
				}
				w[n] = Math.exp(-error/(2*s*s)) / Math.sqrt(2*pi*s*s);
				sum += w[n];
			}
			for(int n=0;n<nmax;n++) w[n] /= sum;
			
			np=0;
			sum=0.0;
			j=0;
			N = nmax;
			for(int n=0;n<nmax;n++){
				np = (int)(w[n]*nmax);
				jmax = j+np;
				N -= np;
				w[n] -= np/(double)nmax;
				sum += w[n];
				for(int k=j;k<jmax;k++){
					x[k] = xp[n];
					y[k] = yp[n];
				}
				j = jmax;
			}
			R = N + Math.random();
			for(int n=0;n<nmax;n++) w[n] *= N/sum;
			j=N;
			for(int n=0;n<nmax;n++){
				R -= w[n];
				l = (int)R;
				if(j!=l){
					x[nmax-N]=xp[n];
					y[nmax-N]=yp[n];
					N--;
				}
				j=l;
			}
		}
		
		for(int n=0;n<nmax;n++) pw.println(x[n] + "\t" + y[n]);
		System.out.println("ファイル名「" + fileName + "」を出力しました");
		pw.close();
	}
	


	private static void distance(double x,double y){
		int i,j,nx,ny,nstep;
		final int[] di = { 0, 1, 1, 1, 0,-1,-1,-1};
		final int[] dj = { 1, 1, 0,-1,-1,-1, 0, 1};
		double[] ud ={1.0,Math.sqrt(2)};
		nx=(int)(x*N0+0.5);
		ny=(int)(y*N0+0.5);
		for(int k=0;k<kmax;k++){
			i=nx;
			j=ny;
			nstep=0;
			while(s[i][j]==0){
				i += di[k];
				j += dj[k];
				nstep++;
			}
			d[k] = nstep*ud[k%2]/N0;
			if(d[k]>1.5) d[k] = 1.5;//(robotQ2.datの場合)	
		}	
	}
	
	
}
