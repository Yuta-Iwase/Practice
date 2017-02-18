import java.net.URL;

import javax.swing.ImageIcon;


public class Reversing {
	// �e�ڂ̏��
	// 0�͋�A-1�͍��A1�͔�
	static int[][] status;
	// �ݒu�����F�̎��
	// -1�͍��A1�͔�
	static int set;
	
	static boolean changed;
	
	private URL BPath,WPath;
	
	public Reversing(){
		new Resource();
		
		set = 0;
		changed = false;
		BPath = Resource.BPath;
		WPath = Resource.WPath;
		
		status = new int[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				status[i][j] = 0;
			}
		}
		status[3][3] = 1;
		status[3][4] =-1;
		status[4][3] =-1;
		status[4][4] = 1;
		
	}
	
	
	public void reverse(int x ,int y , int set){
		BPath = getClass().getResource("img/Black.png");
		WPath = getClass().getResource("img/White.png");	
		
		changed = false;
		System.out.println("At Reversing.class:" + " x=" + x + " y=" + y +" set=" + set );
		// �΂ߕ����̌��E�l
		int max;
		////////////////
		// ���ɂ��� //
		////////////////
		if(set == -1){
			status[x][y]=-1;
			// ���ɂ���
			for(int l=1 ; l<=x ; l++){
				if(status[x-l][y] == -1){
					for(int i=(l-1);i>0;i--){
						status[x-i][y] = -1;
						Othello.buttonArray[x-i][y].setIcon(new ImageIcon(BPath));
						System.out.println("changeblack");
						changed = true;
					}
					break;
				}else if(status[x-l][y] == 0){
					break;
				}else if(status[x-l][y] == 1){
					// do nothing;
				}
			}
			// �E�ɂ���
			for(int r=1 ; r<=(7-x) ; r++){
				if(status[x+r][y] == -1){
					for(int i=(r-1);i>0;i--){
						status[x+i][y] = -1;
						Othello.buttonArray[x+i][y].setIcon(new ImageIcon(BPath));
						System.out.println("changeblack");
						changed = true;
					}
					break;
				}else if(status[x+r][y] == 0){
					break;
				}else if(status[x+r][y] == 1){
					// do nothing;
				}
			}
			// ��ɂ���
			for(int u=1 ; u<=y ; u++){
				if(status[x][y-u] == -1){
					for(int i=(u-1);i>0;i--){
						status[x][y-i] = -1;
						Othello.buttonArray[x][y-i].setIcon(new ImageIcon(BPath));
						System.out.println("changeblack");
						changed = true;
					}
					break;
				}else if(status[x][y-u] == 0){
					break;
				}else if(status[x][y-u] == 1){
					// do nothing;
				}
			}
			// ���ɂ���
			for(int d=1 ; d<=(7-y) ; d++){
				if(status[x][y+d] == -1){
					for(int i=(d-1);i>0;i--){
						status[x][y+i] = -1;
						Othello.buttonArray[x][y+i].setIcon(new ImageIcon(BPath));
						System.out.println("changeblack");
						changed = true;
					}
					break;
				}else if(status[x][y+d] == 0){
					break;
				}else if(status[x][y+d] == 1){
					// do nothing;
				}
			}
			// ����ɂ���
			if(x>=y){
				max = y;
			}else{
				max = x;
			}
			for(int l=1 ; l<=max ; l++){
				if(status[x-l][y-l] == -1){
					for(int i=(l-1);i>0;i--){
						status[x-i][y-i] = -1;
						Othello.buttonArray[x-i][y-i].setIcon(new ImageIcon(BPath));
						System.out.println("changeblack");
						changed = true;
					}
					break;
				}else if(status[x-l][y-l] == 0){
					break;
				}else if(status[x-l][y-l] == 1){
					// do nothing;
				}
			}
			// �E��ɂ���
			if((7-x)>=y){
				max = y;
			}else{
				max = (7-x);
			}
			for(int r=1 ; r<=max ; r++){
				if(status[x+r][y-r] == -1){
					for(int i=(r-1);i>0;i--){
						status[x+i][y-i] = -1;
						Othello.buttonArray[x+i][y-i].setIcon(new ImageIcon(BPath));
						System.out.println("changeblack");
						changed = true;
					}
					break;
				}else if(status[x+r][y-r] == 0){
					break;
				}else if(status[x+r][y-r] == 1){
					// do nothing;
				}
			}
			// �����ɂ���
			if(x>=(7-y)){
				max = (7-y);
			}else{
				max = x;
			}
			for(int l=1 ; l<=max ; l++){
				if(status[x-l][y+l] == -1){
					for(int i=(l-1);i>0;i--){
						status[x-i][y+i] = -1;
						Othello.buttonArray[x-i][y+i].setIcon(new ImageIcon(BPath));
						System.out.println("changeblack");
						changed = true;
					}
					break;
				}else if(status[x-l][y+l] == 0){
					break;
				}else if(status[x-l][y+l] == 1){
					// do nothing;
				}
			}
			// �E���ɂ���
		 	if((7-x)>=(7-y)){
				max = (7-y);
			}else{
				max = (7-x);
			}
			for(int r=1 ; r<=max ; r++){
				if(status[x+r][y+r] == -1){
					for(int i=(r-1);i>0;i--){
						status[x+i][y+i] = -1;
						Othello.buttonArray[x+i][y+i].setIcon(new ImageIcon(BPath));
						System.out.println("changeblack");
						changed = true;
					}
					break;
				}else if(status[x+r][y+r] == 0){
					break;
				}else if(status[x+r][y+r] == 1){
					// do nothing;
				}
			}
			
		}
			
		////////////////
		// ���ɂ��� //
		////////////////
		if(set == 1){
			status[x][y] = 1;
			System.out.println("�ʉ�");
			// ���ɂ���
			for(int l=1 ; l<=x ; l++){
				if(status[x-l][y] == 1){
					for(int i=(l-1);i>0;i--){
						status[x-i][y] = 1;
						Othello.buttonArray[x-i][y].setIcon(new ImageIcon(WPath));
						System.out.println("changewhite");
						changed = true;
					}
					break;
				}else if(status[x-l][y] == 0){
					break;
				}else if(status[x-l][y] == -1){
					// do nothing;
				}
			}
			// �E�ɂ���
			for(int r=1 ; r<=(7-x) ; r++){
				if(status[x+r][y] == 1){
					for(int i=(r-1);i>0;i--){
						status[x+i][y] = 1;
						Othello.buttonArray[x+i][y].setIcon(new ImageIcon(WPath));
						System.out.println("changewhite");
						changed = true;
					}
					break;
				}else if(status[x+r][y] == 0){
					break;
				}else if(status[x+r][y] == -1){
					// do nothing;
				}
			}
			// ��ɂ���
			for(int u=1 ; u<=y ; u++){
				if(status[x][y-u] == 1){
					for(int i=(u-1);i>0;i--){
						status[x][y-i] = 1;
						Othello.buttonArray[x][y-i].setIcon(new ImageIcon(WPath));
						System.out.println("changewhite");
						changed = true;
					}
					break;
				}else if(status[x][y-u] == 0){
					break;
				}else if(status[x][y-u] == -1){
					// do nothing;
				}
			}
			// ���ɂ���
			for(int d=1 ; d<=(7-y) ; d++){
				if(status[x][y+d] == 1){
					for(int i=(d-1);i>0;i--){
						status[x][y+i] = 1;
						Othello.buttonArray[x][y+i].setIcon(new ImageIcon(WPath));
						System.out.println("changewhite");
						changed = true;
					}
					break;
				}else if(status[x][y+d] == 0){
					break;
				}else if(status[x][y+d] == -1){
					// do nothing;
				}
			}
			// ����ɂ���
			if(x>=y){
				max = y;
			}else{
				max = x;
			}
			for(int l=1 ; l<=max ; l++){
				if(status[x-l][y-l] == 1){
					for(int i=(l-1);i>0;i--){
						status[x-i][y-i] = 1;
						Othello.buttonArray[x-i][y-i].setIcon(new ImageIcon(WPath));
						System.out.println("changewhite");
						changed = true;
					}
					break;
				}else if(status[x-l][y-l] == 0){
					break;
				}else if(status[x-l][y-l] == -1){
					// do nothing;
				}
			}
			// �E��ɂ���
			if((7-x)>=y){
				max = y;
			}else{
				max = (7-x);
			}
			for(int r=1 ; r<=max ; r++){
				if(status[x+r][y-r] == 1){
					for(int i=(r-1);i>0;i--){
						status[x+i][y-i] = 1;
						Othello.buttonArray[x+i][y-i].setIcon(new ImageIcon(WPath));
						System.out.println("changewhite");
						changed = true;
					}
					break;
				}else if(status[x+r][y-r] == 0){
					break;
				}else if(status[x+r][y-r] == -1){
					// do nothing;
				}
			}
			// �����ɂ���
			if(x>=(7-y)){
				max = (7-y);
			}else{
				max = x;
			}
			for(int l=1 ; l<=max ; l++){
				if(status[x-l][y+l] == 1){
					for(int i=(l-1);i>0;i--){
						status[x-i][y+i] = 1;
						Othello.buttonArray[x-i][y+i].setIcon(new ImageIcon(WPath));
						System.out.println("changewhite");
						changed = true;
					}
					break;
				}else if(status[x-l][y+l] == 0){
					break;
				}else if(status[x-l][y+l] == -1){
					// do nothing;
				}
			}
			// �E���ɂ���
		 	if((7-x)>=(7-y)){
				max = (7-y);
			}else{
				max = (7-x);
			}
			for(int r=1 ; r<=max ; r++){
				if(status[x+r][y+r] == 1){
					for(int i=(r-1);i>0;i--){
						status[x+i][y+i] = 1;
						Othello.buttonArray[x+i][y+i].setIcon(new ImageIcon(WPath));
						System.out.println("changewhite");
						changed = true;
					}
					break;
				}else if(status[x+r][y+r] == 0){
					break;
				}else if(status[x+r][y+r] == -1){
					// do nothing;
				}
			}
		}
		
		if(!changed){
			status[x][y] = 0;
			System.out.println("At Reversing.class: didn't reverse");
			
		}
		System.out.println("finish reversing");

		
	}


}
