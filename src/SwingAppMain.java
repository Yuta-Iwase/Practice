import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class SwingAppMain {
     /**
     * @param args
     */
     public static void main(String[] args) {
          JFrame mainFrame = new JFrame("�T���v��");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(320,160);
        
        // mainFrame.setLocationRelativeTo(null);
        
         // JFrame���ContentPane���擾
         Container contentPane = new Container();
         contentPane = mainFrame.getContentPane();
         // ���x���̃C���X�^���X�𐶐�
         JLabel label = new JLabel("SwingLabel");
         // �{�^���̃C���X�^���X�𐶐�
         JButton button = new JButton("SwingButton");
        // ���x����ContentPane�ɔz�u
        contentPane.add(label, BorderLayout.CENTER);
        // �{�^����ContentPane�ɔz�u
        contentPane.add(button, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }
} 