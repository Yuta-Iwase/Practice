///////////////////////////////////////////////////////////////////////////////
//	�V���[�e�B���O�Q�[���P�@���@�ړ�
//	�t�@�C���� : Shooting01.java
//	�A�v���b�g�T�C�Y 300�~400
//	�g�p�摜�F
// 	jiki.gif�i���@�摜������A32�~32�s�N�Z���j

import java.applet.*;
import java.awt.*;
import java.awt.event.*;



@SuppressWarnings("serial")
public class Shooting1 extends Applet implements Runnable, KeyListener {
	Image jiki;				// ���@�摜
	int jx, jy;			// ���@�̍��W
	boolean keyLeft;		// ���L�[��������Ă����true
	boolean keyRight;		// �E�L�[��������Ă����true
	Thread gameThread;		// �Q�[���X���b�h

	// ������
	public void init() {
		// ���@�̏������W��ݒ肷��
		jx = 134; jy = 150;
		// �L�[�t���O���N���A
		keyLeft = keyRight = false;
		// �w�i�F�����F�ɐݒ�
		setBackground(Color.black);
		// �O�i�F�𔒐F�ɐݒ�
		setForeground(Color.white);
		// ���@�摜�ǂݍ���
		jiki = getImage(getDocumentBase(), "jiki.gif");
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
			} 
			catch (InterruptedException e) {
				break;
			}
			// �Q�[�����C������
			if (keyLeft) jx -= 8;
			if (keyRight) jx += 8;
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
		}
	}

	// �L�[�������ꂽ�Ƃ��̏���
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			// ���L�[�������ꂽ�Ƃ�
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			// �E�L�[�������ꂽ�Ƃ�
			case KeyEvent.VK_RIGHT: keyRight = false; break;
		}
	}

	// �L�[���^�C�v���ꂽ�Ƃ��̏���
	public void keyTyped(KeyEvent e) {}

	// �`��
	public void paint(Graphics g) {
		// ���@�̕\��
		g.drawImage(jiki, jx, jy, this);

	}
	
}
