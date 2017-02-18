import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class test2 implements ActionListener {
        private JFrame mainFrame;
        private Container contentPane;
        private JTextField textField;
        private JTextArea textArea;
        private JScrollPane scrollPane;
        private JPanel buttonPane;
        private JButton addButton;
        private JButton clearButton;
        // �R���X�g���N�^
        public test2(){
                mainFrame = new JFrame("�T���v��");
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setSize(320, 200);
                mainFrame.setLocationRelativeTo(null);
                contentPane = mainFrame.getContentPane();
                
                
                
                textField = new JTextField();
                textArea = new JTextArea();
                scrollPane = new JScrollPane(textArea);
                addButton = new JButton("�ǉ�");
                clearButton = new JButton("����");
                // �u�ǉ��v�{�^���ƃA�N�V�����E���X�i�[�̊֘A�t��
                addButton.addActionListener(this);
                // �u�����v�{�^���ƃA�N�V�����E���X�i�[�̊֘A�t��
                clearButton.addActionListener(this);
                buttonPane = new JPanel();
                buttonPane.add(addButton);
                buttonPane.add(clearButton);
                contentPane.add(textField, BorderLayout.NORTH);
                contentPane.add(scrollPane, BorderLayout.CENTER);
                contentPane.add(buttonPane, BorderLayout.SOUTH);
                
                
                
                mainFrame.setVisible(true);
                
                
        }
        // ���p�҂̑���ɉ���������������
        public void actionPerformed(ActionEvent event){
                // ���[�U�̑���Ώۂ𔻒f
                if(event.getSource() == addButton) {
                        // �e�L�X�g�G���A�֕������ǉ�
                        textArea.append(textField.getText() + "\n");
                }
                if(event.getSource() == clearButton) {
                        // �e�L�X�g�G���A�̕������S����
                        textArea.setText(null);
                }
        }
        // �A�v���P�[�V�����̋N��
        public static void main(String[] args) {
                new test2();
        }
}