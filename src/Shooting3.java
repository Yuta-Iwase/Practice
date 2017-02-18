///////////////////////////////////////////////////////////////////////////////
//	�V���[�e�B���O�Q�[���R�@�G�o��E���z���
//	�t�@�C���� : Shooting3.java
//	�A�v���b�g�T�C�Y 300�~400
//	�g�p�摜�F
//	jiki.gif�i���@�摜������A32�~32�s�N�Z���j
//  tama.gif�i�e�摜�A8�~8�s�N�Z���j
//	teki.gif�i�G�摜�������A64�~64�s�N�Z���j

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Shooting3 extends Applet implements Runnable, KeyListener {
	Image jiki;				// ���@�摜
	int jx, jy;			// ���@�̍��W
	Image tama;				// �e�摜
	int tx, ty;			// �e�̍��W
	boolean tflag;			// �e�̔��˃t���O
	Image teki;				// �G�摜
	int ex, ey;			// �G�̍��W
	boolean keyLeft, keyRight, keySpace;	// �L�[�����t���O
	Thread gameThread;		// �Q�[���X���b�h
	Image offImage;			// ���z���

	// ������
	public void init() {
		// ���@�̏������W��ݒ肷��
		jx = 134; jy = 352;
		// �G�̏������W��ݒ肷��
		ex = (int)(Math.random() * (300 - 32));
		ey = -32;
		// �e�t���O���N���A����
		tflag = false;
		// �L�[�t���O���N���A
		keyLeft = keyRight = keySpace = false;
		// �w�i�F�����F�ɐݒ�
		setBackground(Color.black);
		// �O�i�F�𔒐F�ɐݒ�
		setForeground(Color.white);
		// �摜�ǂݍ���
		jiki = getImage(getDocumentBase(), "jiki.gif");
		tama = getImage(getDocumentBase(), "tama.gif");
		teki = getImage(getDocumentBase(), "teki.gif");
		// ���z��ʂ̐���
		offImage = createImage(300, 400);
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
			if (keySpace) {
				if (!tflag) {
					tx = jx + 12;
					ty = jy;
					tflag = true;
				}
			}
			// �e����
			if (tflag) {
				if (ty < 0) tflag = false;
				else ty -= 8;
			}
			// �G�ړ�
			ey += 8;
			if (ey >= 400) {
				ex = (int)(Math.random() * (300 - 32));
				ey = -32;
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
		Graphics gv = offImage.getGraphics();
		// ����ʂ̏���
		gv.clearRect(0, 0, 300, 400);
		// �e���o�����Ă���Ƃ��͒e�̕`��
		if (tflag) gv.drawImage(tama, tx, ty, this);
		// ���@�̕`��
		gv.drawImage(jiki, jx, jy, this);
		// �G�̕`��
		gv.drawImage(teki, ex, ey, this);
		// ����ʂ���\��ʂ֓]��
		g.drawImage(offImage, 0, 0, this);
	}

	// �X�V
	public void update(Graphics g) {
		paint(g);
	}
}
