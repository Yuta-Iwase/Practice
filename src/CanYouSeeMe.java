import java.util.Scanner;

public class CanYouSeeMe {
	// ��_�̃I�u�W�F�N�g��n���A���������ƌ��_�Ƃ̋��������߂郁�\�b�h
	// @param p1  �_P1�̃I�u�W�F�N�g
	// @param p2  �_P2�̃I�u�W�F�N�g
	// @return distance ���_�ƒ����̋���
	public double measureDistance(OnePoint p1, OnePoint p2) {
		// return�ŕԂ��_�ƒ����̋���
		double distance;

		// �돜�Z���N�����Ȃ����߂ɏꍇ����
		if (p1.getX() == p2.getX()) {
			// ��_��X���W���������Ƃ����̍��W�����_�Ƃ̋����ƂȂ�B
			distance = p1.getX();
		} else {
			// �ȒP�̂��߁A�_P1����_P2�ւ̌X�������炩���ߋ��߂Ă����B
			double slope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
			// �_�ƒ����̋����̌����ɑ��
			distance = Math.abs(-p1.getX() * slope + p1.getY())/ Math.sqrt(Math.pow(slope, 2) + Math.pow(-1, 2));
		}
		
		// ����distance��Ԃ��B
		return distance;
	}
	
	// ��_��ʂ�������ɁA����_(���L�̒ʂ�)�����݂��邩�m���߂郁�\�b�h
	// ����_�\��_��ʂ钼���Ƃ��̒����ɒ������钼���Ƃ̌�_
	// @param p1  �_P1�̃I�u�W�F�N�g
	// @param p2  �_P2�̃I�u�W�F�N�g
	// @return p ��_���������ɂ��邩�̐^�U
	public boolean checkIntersection(OnePoint p1, OnePoint p2){
		// measureDistance���l�A�_P1����_P2�ւ̌X�������炩���ߋ��߂Ă����B
		double slope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
		// ��_(�O�q)��x���W�����߁A������wx�x�Ƃ���B
		double x = (slope/(Math.pow(slope, 2) + 1)) * (p1.getX() * slope - p1.getY());
		// ��_(�O�q)��x���W���A��_�̐����̊Ԃɂ��邩�^�U�𒲂ׂ�����wp�x�Ƃ���B
		boolean p = Math.min(p1.getX(), p2.getX()) <= x   &&   x <= Math.max(p1.getX(), p2.getX());
		// �^�Up��Ԃ��B
		return p;
	}
	
	// �I�u�W�F�N�g����
	private static Scanner keyBoardScanner;
	private static CanYouSeeMe canYouSeeMe;

	public static void main(String[] args) {
		// ��������g��
		int n;
		// �~ C1 �̔��a
		double a;
		// n�g�̂������ʂ�����̂𐔂���ϐ�
		int seeable = 0;
		// �I�u�V�F�N�g��`
		keyBoardScanner = new Scanner(System.in);
		canYouSeeMe = new CanYouSeeMe();

		// �L�[�{�[�h���͂őg�����󂯕t����
		// n��0�ȉ��̐����Ȃ�΂�蒼��
		do {
			System.out.print("��������g��(���̐���) = ");
			n = keyBoardScanner.nextInt();
		} while (n <= 0);

		// �L�[�{�[�h���͂Ŕ��a���󂯕t����
		// 0<a<1�𖞂����Ȃ��̂ł���΂�蒼��
		do {
			System.out.print("�~ C1 �̔��a(0<a<1�Ȃ����) = ");
			a = keyBoardScanner.nextDouble();
		} while (!(0 < a && a < 1));

		// n�� �_p1,p2���������𒲂ׁA�����グ��B
		// for�̏����������قȂ��̂ł��邱�Ƃɒ��ӂ���B
		for (int i = 1; i <= n; i++) {
			// ���aa�̃I�u�W�F�N�gp1,p2�𐶐�
			OnePoint p1 = new OnePoint(a);
			OnePoint p2 = new OnePoint(a);
			// ���b�Z�[�W�\��
			System.out.printf("%6d �g�ڂ� P1(%8.5f, %8.5f), P2(%8.5f, %8.5f) --> ", i,p1.getX(),p1.getY(), p2.getX(), p2.getY());
			// p1,p2�ɂ���č��ꂽ�������~C1�ƌ���邩���ׂ�B
			// �u(�_�ƒ����̋���) <= a  ����  ��_���������ɂ���v�𖞂����Ƃ����ʂ��Ȃ�
			// ����ȊO�Ȃ�Ό��ʂ���B
			if(canYouSeeMe.measureDistance(p1, p2) <= a && canYouSeeMe.checkIntersection(p1, p2)){
				System.out.println("���ʂ��܂���B");
			}else{
				System.out.println("���ʂ��܂��B");
				// �J�E���g����
				seeable++;
			}				
		}
		
		// ���ʕ\��
		// �n�C�t����23�A�E13��
		System.out.println("-----------------------�܂Ƃ�-------------");
		System.out.printf("�̈�a���ɑI�� P1, P2 �̑g�� = %8d\n", n);
		System.out.printf("�@�@���̓��A�݂��Ɍ��ʂ����g�� = %8d\n", seeable);
		System.out.printf("�@�@�@�@�@�@�݂��Ɍ��ʂ����m�� = %8.5f\n", ((double)seeable/n));

	}

}
