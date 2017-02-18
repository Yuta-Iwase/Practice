import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Othello extends JFrame implements MouseListener,MouseMotionListener {
	private static final long serialVersionUID = 1;

	public static exButton buttonArray[][];// �{�^���p�̔z��
	public static JLabel border, bkground, menu, sign, scoreBL, scoreWL, skillImg, SP;
	public Container c;
	
	public static ImageIcon blackIcon, whiteIcon, boardIcon;
	
	public static ImageIcon BigBangIcon, RemoveOneIcon, ChargeIcon, SuperNovaIcon;
	public static exButton BigBangButton, RemoveOneButton, ChargeButton, SuperNovaButton;
	
	// ���ɔz�u����F
	// -1����    1����
	public static int setStatus;
	
	// ����\�͎g�p��
	// spPresent�Ƃ́A���v���C���[�̃X�L���c��
	public static int spB, spW, spPresent=1;
	
	Reversing rev;
	Resource res;
	Counting cou;

	public Othello() {
		res = new Resource();
		cou = new Counting();
		
		// �E�B���h�E���쐬����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �E�B���h�E�����Ƃ��ɁC����������悤�ɐݒ肷��
		setTitle("Othello"); // �E�B���h�E�̃^�C�g����ݒ肷��
		setSize(770, 610); // �E�B���h�E�̃T�C�Y��ݒ肷��
		setLocationRelativeTo(null);
		setResizable(false);		
		c = getContentPane(); // �t���[���̃y�C�����擾����
		c.setLayout(null); // �������C�A�E�g�̐ݒ���s��Ȃ�
		c.setBackground(new Color(0,0,0));

		// �A�C�R���̐ݒ�
		whiteIcon = new ImageIcon("");
		blackIcon = new ImageIcon("");
		boardIcon = new ImageIcon("");
		
		// ����\�̓A�C�R���̐ݒ�
		BigBangIcon = new ImageIcon("");
		RemoveOneIcon = new ImageIcon("");
		ChargeIcon = new ImageIcon("");
		SuperNovaIcon = new ImageIcon("");


		// �{�^��(�}�X)�̐���
		buttonArray = new exButton[8][8];// �{�^���̔z����T�쐬����[0]����[4]�܂Ŏg����
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				buttonArray[i][j] = new exButton(boardIcon);// �{�^���ɃA�C�R����ݒ肷��
				c.add(buttonArray[i][j]);// �y�C���ɓ\��t����
				buttonArray[i][j].setBounds((i+1) * 60, (j+1)*60, 60, 60);// �{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
				buttonArray[i][j].addMouseListener(this);// �{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
				buttonArray[i][j].addMouseMotionListener(this);// �{�^�����}�E�X�œ��������Ƃ����Ƃ��ɔ�������悤�ɂ���
				buttonArray[i][j].setActionCommand(Integer.toString(i));// �{�^���ɔz��̏���t������i�l�b�g���[�N����ăI�u�W�F�N�g�����ʂ��邽�߁j
			}
		}
		
		// BigBang�{�^���̐ݒ�
		// setActionCommand�̓R�}���h��ID
		BigBangButton = new exButton(BigBangIcon);
		c.add(BigBangButton);
		BigBangButton.setBounds(590, 200, 173, 51);
		BigBangButton.addMouseListener(this);
		BigBangButton.addMouseMotionListener(this);
		BigBangButton.setActionCommand(Integer.toString(8*8 + 1));
		
		// RemoveOne�{�^���̐ݒ�
		RemoveOneButton = new exButton(RemoveOneIcon);
		c.add(RemoveOneButton);
		RemoveOneButton.setBounds(590, 260, 173, 51);
		RemoveOneButton.addMouseListener(this);
		RemoveOneButton.addMouseMotionListener(this);
		RemoveOneButton.setActionCommand(Integer.toString(8*8 + 2));
		
		// Charge�{�^���̐ݒ�
		ChargeButton = new exButton(ChargeIcon);
		c.add(ChargeButton);
		ChargeButton.setBounds(590, 320, 173, 51);
		ChargeButton.addMouseListener(this);
		ChargeButton.addMouseMotionListener(this);
		ChargeButton.setActionCommand(Integer.toString(8*8 + 3));
		
		// SuperNova�{�^���̐ݒ�
		SuperNovaButton = new exButton(SuperNovaIcon);
		c.add(SuperNovaButton);
		SuperNovaButton.setBounds(590, 380, 173, 51);
		SuperNovaButton.addMouseListener(this);
		SuperNovaButton.addMouseMotionListener(this);
		SuperNovaButton.setActionCommand(Integer.toString(8*8 + 3));
		
		// �d�؂���̐ݒ�
		Icon imgBo = new ImageIcon("img/border.png");
		border = new JLabel(imgBo);
		c.add(border);
		border.setBounds(0, 0, 600, 600);
		
		// ���̃R�}��
		scoreWL = new JLabel("");
		scoreWL.setText("<html><font size=\"8\" face=\"Monotype Corsiva\" color=\"black\">" + Counting.scoreW + "</font></html>");
		c.add(scoreWL);
		scoreWL.setBounds(665, 35, 40, 30);
		
		// ���̃R�}��
		scoreBL = new JLabel("");
		scoreBL.setText("<html><font size=\"8\" face=\"Monotype Corsiva\" color=\"white\">" + Counting.scoreB + "</font></html>");
		c.add(scoreBL);
		scoreBL.setBounds(665, 110, 40, 30);
		
		//�X�L���c�����l�̕`�ʐݒ�
		SP = new JLabel("");
		SP.setText("<html><b><font face=\"Monotype Corsiva\" color=\"white\"  style=\"font-size : 80pt;\">" + spPresent + "</font></b></html>");
		c.add(SP);
		SP.setBounds(672, 465, 148, 80);
		
		//�X�L���c���摜�̐ݒ�
		skillImg = new JLabel("");
		c.add(skillImg);
		skillImg.setBounds(590, 465, 148, 80);
		
		// �A�z��̐ݒ�
		sign = new JLabel("");
		c.add(sign);
		sign.setBounds(600, 10, 150, 150);
		
		// �E���̃o�[�̐ݒ�
		menu = new JLabel("");
		c.add(menu);
		menu.setBounds(580, -10, 200, 600);
		
		// �w�i�̐ݒ�
		Icon imgBa = new ImageIcon("img/bkground.png");
		bkground = new JLabel(imgBa);
		c.add(bkground);
		bkground.setBounds(0, 0, 800, 600);
		
		
		// �Z�b�g�X�e�[�^�X������������
		// -1�ō��A1�Ŕ�
		setStatus = -1;
		// ����\�͎g�p�񐔂̏�����
		spB = 1;
		spW = 1;
		// Reversing�N���X���`
		rev = new Reversing();
	}
	
	public static void main(String[] args) {
		Othello gui = new Othello();
		
		gui.setVisible(true);
		gui.res.changeWhite(3, 3);
		gui.res.changeBlack(3, 4);
		gui.res.changeBlack(4, 3);
		gui.res.changeWhite(4, 4);
		gui.res.setSpecialAbilityIcon();
		gui.res.setBorder();
		gui.cou.count();
		System.out.println(Counting.scoreB);
		gui.res.setSign();
		gui.res.setSkillB();
		gui.res.setMenu();
		gui.res.setbkground();
		new CheckSettable().checkSettable();
		gui.repaint();
	}
	
	// �{�^�����N���b�N�����Ƃ��̏���
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");
		// �N���b�N�����I�u�W�F�N�g�𓾂�D�^���Ⴄ�̂ŃL���X�g����
		exButton theButton = (exButton) e.getComponent();
		// theIcon�ɂ́C���݂̃{�^���ɐݒ肳�ꂽ�A�C�R��������
		Icon theIcon = theButton.getIcon();
		
		if(RemoveOne.RemoveOneFlagB || RemoveOne.RemoveOneFlagW){
			// RemoveOne�������̍s��
			for(int i=0; i<8 ; i++){
				for(int j=0 ; j<8 ;j++){
					if(e.getSource() == buttonArray[i][j]){
						// �t���O��false�ɖ߂�
						RemoveOne.RemoveOneFlagB = false;
						RemoveOne.RemoveOneFlagW = false;
						// �I���������W�̃R�}����菜��
						Reversing.status[i][j] = 0;
						// �ĕ`��
						new Refresh().refresh();
						// ����Ƀ^�[������
						// �^�[�����蔻��͌�ɂ��邩����v
						setStatus *= -1;
					}
				}
			}
		}else if(SuperNova.SuperNovaFlag){
			// SuperNova�������̍s��
			for(int i=0; i<8 ; i++){
				for(int j=0 ; j<8 ;j++){
					if(e.getSource() == buttonArray[i][j]){
						// �t���O��false�ɖ߂�
						SuperNova.SuperNovaFlag = false;
						// �I���������W�̃R�}�Ƃ��̎��͂𔽓]������
						for(int k=-1 ; k<2 ; k++){
							for(int l=-1 ; l<2 ; l++){
								try{
									Reversing.status[i+k][j+l] *= -1;
								}catch(ArrayIndexOutOfBoundsException errorMSG){
									// do nothing
									System.out.println("error catch");
								}
							}
						}
						// �ĕ`��
						new Refresh().refresh();
						// ����Ƀ^�[������
						// �^�[�����蔻��͌�ɂ��邩����v
						setStatus *= -1;
					}
				}
			}
		}else if(e.getSource() == BigBangButton && 1 <= spPresent ){
			//BigBang�{�^����I�������ꍇ�̑�����L�q
			System.out.println("BigBang clicked");
			new BigBang();
			new Refresh().refresh();
			new CheckSettable().checkSettable();
		}else if(e.getSource() == ChargeButton && 1 <= spPresent ){
			//Charge�{�^����I�������ꍇ�̑�����L�q
			System.out.println("Charge clicked");
			new Charge();
			new Refresh().refresh();
			new CheckSettable().checkSettable();
		}else if(e.getSource() == RemoveOneButton && 1 <= spPresent){
			//RemoveOne�{�^����I�������ꍇ�̑�����L�q
			System.out.println("RemoveOne clicked");
			new RemoveOne();
			new Refresh().refresh();
		}else if(e.getSource() == SuperNovaButton && 1 <= spPresent){
			//RemoveOne�{�^����I�������ꍇ�̑�����L�q
			System.out.println("SuperNova clicked");
			new SuperNova();
			new Refresh().refresh();
		}else if(e.getSource() == BigBangButton && 1 > spPresent){
			// do nothing
		}else if(e.getSource() == ChargeButton && 1 > spPresent){
			// do nothing
		}else if(e.getSource() == RemoveOneButton && 1 > spPresent){
			// do nothing
		}else if(e.getSource() == SuperNovaButton && 1 > spPresent){
			// do nothing
		}else{
			// ���ʂɃR�}���N���b�N�����Ƃ��̏���
		
			int x=0,y=0;
			for(int i=0; i<8 ; i++){
				for(int j=0 ; j<8 ;j++){
					if(e.getSource() == buttonArray[i][j]){
						x = i;
						y = j;
					}
				}
			}
		
			int set;
		
			if(Reversing.status[x][y] == 0 && setStatus == -1){
				res.changeBlack(x, y);
				set = -1;
				rev.reverse(x, y, set);
				setStatus = 1;
				if(!Reversing.changed){
					System.out.println("return boardIcon");
					theButton.setIcon(boardIcon);
					setStatus = -1; 
				}
			}else if (Reversing.status[x][y] == 0 && setStatus == 1) {// �A�C�R����boardIcon�Ɠ����Ȃ�
				res.changeWhite(x, y);
				set = 1;
				rev.reverse(x, y, set);
				setStatus = -1;
				if(!Reversing.changed){
					System.out.println("return boardIcon");
					theButton.setIcon(boardIcon);
					setStatus = 1; 
				}
			} else if (theIcon.equals(whiteIcon)) {
				System.out.println("whiteIcon is clicked");
			} else {
				System.out.println("blackIcon is clicked");
			}		
		}
		
		// �Q�[���I������(�^�[������ݒ�)
		new CheckSettable().checkSettable();
		if (!CheckSettable.AllSetB) {
			System.out.println("Pass!");
			Othello.setStatus = 1;
			new CheckSettable().checkSettable();
			if (!CheckSettable.AllSetW){
				System.out.println("GameSet!!");
			}
		}
		if (!CheckSettable.AllSetW) {
			System.out.println("Pass!");
			Othello.setStatus = -1;
			new CheckSettable().checkSettable();
			if (!CheckSettable.AllSetB){
				System.out.println("GameSet!!");
			}
		}
		
		// �X�L���c���摜�̃A�C�R���؂�ւ�
		if(Othello.setStatus == 1){
			res.setSkillW();
			spPresent = spW;
		}else{
			res.setSkillB();
			spPresent = spB;
		}
		SP.setText("<html><b><font face=\"Monotype Corsiva\" color=\"white\"  style=\"font-size : 80pt;\">" + spPresent + "</font></b></html>");
		
		//���^�[���̃v���C���[�̃X�L���i���ɂ���ăX�L���\����ς���
		if(spPresent <= 0){
			res.setCannotUseAbility();
		}else{
			res.setSpecialAbilityIcon();
		}
		
		cou.count();
		repaint();// ��ʂ̃I�u�W�F�N�g��`�悵����
	}

	public void mouseEntered(MouseEvent e) {// �}�E�X���I�u�W�F�N�g�ɓ������Ƃ��̏���
	}

	public void mouseExited(MouseEvent e) {// �}�E�X���I�u�W�F�N�g����o���Ƃ��̏���
	}

	public void mousePressed(MouseEvent e) {// �}�E�X�ŃI�u�W�F�N�g���������Ƃ��̏����i�N���b�N�Ƃ̈Ⴂ�ɒ��Ӂj
	}

	public void mouseReleased(MouseEvent e) {// �}�E�X�ŉ����Ă����I�u�W�F�N�g�𗣂����Ƃ��̏���
	}

	public void mouseDragged(MouseEvent e) {// �}�E�X�ŃI�u�W�F�N�g�Ƃ��h���b�O���Ă���Ƃ��̏���
	}

	public void mouseMoved(MouseEvent e) {// �}�E�X���I�u�W�F�N�g��ňړ������Ƃ��̏���
	}
}
