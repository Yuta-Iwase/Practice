import java.applet.*;
import java.awt.*;
import java.lang.Math;


@SuppressWarnings("serial")
public class diamond extends Applet{
	public void paint(Graphics g){
		int k,j,x1,x2,y1,y2,r=100;
		double rd=3.14159/180;
		setBackground(Color.blue);
		setForeground(Color.white);
		for(k=0;k<=14;k++){
			x1=(int)(r*Math.cos(k*22.5*rd)+100);
			y1=(int)(r*Math.sin(k*22.5*rd)+100);
			for(j=k+1;j<=15;j++){
				x2=(int)(r*Math.cos(j*22.5*rd)+100);
				y2=(int)(r*Math.sin(j*22.5*rd)+100);
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}

}
