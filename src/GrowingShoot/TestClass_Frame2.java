package GrowingShoot;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestClass_Frame2 extends JFrame{

// コンストラクタ
public TestClass_Frame2(){

super( "FRAME2" );

// ラベルを作成
JLabel label = new JLabel( "FRAME2が表示されました" );

// パネルを作成
JPanel panel = new JPanel();
//ラベルを追加
panel.add( label );

// コンテントペインにパネルを追加
getContentPane().add( panel );

// ウインドウが閉じられたときにアプリケーションを終了するように設定
setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

// サブコンポーネントの推奨サイズおよびレイアウトに合わせて
// この Window をサイズ変更するように設定
pack();

}

}
