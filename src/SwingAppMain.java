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
          JFrame mainFrame = new JFrame("サンプル");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(320,160);
        
        // mainFrame.setLocationRelativeTo(null);
        
         // JFrameよりContentPaneを取得
         Container contentPane = new Container();
         contentPane = mainFrame.getContentPane();
         // ラベルのインスタンスを生成
         JLabel label = new JLabel("SwingLabel");
         // ボタンのインスタンスを生成
         JButton button = new JButton("SwingButton");
        // ラベルをContentPaneに配置
        contentPane.add(label, BorderLayout.CENTER);
        // ボタンをContentPaneに配置
        contentPane.add(button, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }
} 