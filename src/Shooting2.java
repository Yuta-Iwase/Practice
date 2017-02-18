///////////////////////////////////////////////////////////////////////////////
//	�V���[�e�B���O�Q�[���Q�@�e���ˁE���@����ʂ���͂ݏo���Ȃ�
//	�t�@�C���� : Shooting2.java
//	�A�v���b�g�T�C�Y 300�~400
//	�g�p�摜�F
//	jiki.gif�i���@�摜������A32�~32�s�N�Z���j
//  tama.gif�i�e�摜�A8�~8�s�N�Z���j

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Shooting2 extends Applet implements Runnable, KeyListener {
	Image jiki;				// ���@�摜
	int jx, jy;			// ���@�̍��W
	Image tama;				// �e�摜
	int tx[]=new int[10000], ty[]=new int[10000];			// �e�̍��W
	boolean tflag[] =new boolean[10000];			// �e�̔��˃t���O
	boolean keyLeft, keyRight, keySpace;	// �L�[�����t���O
	Thread gameThread;		// �Q�[���X���b�h
	int i1,i2=1,i3=1;
	
	// ������
	public void init() {
		// ���@�̏������W��ݒ肷��
		jx = 134; jy = 150;
		// �L�[�t���O���N���A
		keyLeft = keyRight = keySpace = false;
		// �w�i�F�����F�ɐݒ�
		setBackground(Color.black);
		// �O�i�F�𔒐F�ɐݒ�
		setForeground(Color.white);
		// ���@�摜�ǂݍ���
		jiki = getImage(getDocumentBase(), "jiki.gif");
		tama= getImage(getDocumentBase(), "tama.gif");
		for(i1=1;i1==500;i1++){
			tx[i1]=100;
			ty[i1]=100;
			tflag[i1] = false;
		}
		// �L�[���͂̎󂯕t���J�n
		addKeyListener(this);
		// �t�H�[�J�X��v��
		requestFocus();
	}

	// �Q�[���X���b�h�̊J�n
	public void start() {
		if(gameThread == null) {
			gameThread = new Thread(this);
			gameThread.start();
		}
	}

	// �Q�[���X���b�h�̒�~
	public void stop() {
		gameThread = null;
	}

	// �Q�[���X���b�h�̃��C��
	public void run() {
		while (gameThread == Thread.currentThread()) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				break;
			}
			// �Q�[�����C������
			// ���ړ�
			if (keyLeft) {
				jx -= 8;
				if (jx < 0) jx = 0;
			}
			// �E�ړ�
			if (keyRight) {
				jx += 8;
				if (jx > 300 - 32) jx = 300 - 32;
			}
			// �e����
			if (keySpace){
				tx[i2] = jx + 12;
				ty[i2] = jy;
				tflag[i2] = true;
				++i2;
				
			}
			


			// �e����
			for(i3=1;i3<=100;i3++){
				if (tflag[i3]) {
					if (ty[i3] < 0) tflag[i3] = false;
					else ty[i3] = ty[i3] - 8;
				}
			}
			
			
			
			if(i2>=50){
				i2=1;				
			}
			
			repaint();
		}
	}

	// �L�[�������ꂽ�Ƃ��̏���
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			// ���L�[�������ꂽ�Ƃ�
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			// �E�L�[�������ꂽ�Ƃ�
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			// �X�y�[�X�L�[�������ꂽ�Ƃ�
			case KeyEvent.VK_SPACE: keySpace = true; break;
		}
	}

	// �L�[�������ꂽ�Ƃ��̏���
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			// ���L�[�������ꂽ�Ƃ�
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			// �E�L�[�������ꂽ�Ƃ�
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			// �X�y�[�X�L�[�������ꂽ�Ƃ�
			case KeyEvent.VK_SPACE: keySpace = false; break;
		}
	}

	// �L�[���^�C�v���ꂽ�Ƃ��̏���
	public void keyTyped(KeyEvent e) {}

	// �`��
	public void paint(Graphics g) {		
		// �e���o�����Ă���Ƃ��͒e�̕`��
		for(i1=1;i1<=100;i1++){
			if (tflag[i1]) g.drawImage(tama, tx[i1], ty[i1], this);
		}
					
		// ���@�̕`��
		g.drawImage(jiki, jx, jy, this);
		g.drawString("x:" +jx+ " y: " +jy, 10, 20);
		g.drawString("i2:"+i2, 10, 40);
		g.drawString("tx[1]:"+tx[1]+" ty[1]:"+ty[1], 10, 60);
		
	}
}
