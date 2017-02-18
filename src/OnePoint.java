public class OnePoint {
	private double x, y;

	// �R���X�g���N�^
	// ���ʂ�x,y����茴�_�Ƃ̋���������(a<����<1)�łȂ���΂�蒼��
	// @param a  �~C1�̔��a
	public OnePoint(double a) {
		// ���_����̋���
		double distance;
		
		// ��L�̒ʂ�̓�����s��
		do {
			x = (-1) + (2 * Math.random());
			y = (-1) + (2 * Math.random());
			distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		} while (!(a < distance && distance < 1));
		
	}

	// getX���\�b�h
	// x��Ԃ�
	// @return x  x���W
	public double getX() {
		return x;
	}

	// getY���\�b�h
	// y��Ԃ�
	// @return y  y���W
	public double getY() {
		return y;
	}

}
