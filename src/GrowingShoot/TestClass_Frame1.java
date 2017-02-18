package GrowingShoot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestClass_Frame1 extends JFrame implements ActionListener{

// コンストラクタ
public TestClass_Frame1(){

super( "FRAME1" );

// ボタンを作成
JButton button = new JButton( "FRAME2へ" );

// ボタンのアクションリスナークラスとして
// このクラス(Fram1.class)を登録
// このクラス(Fram1.class)は
// ActionListenerクラス
// をimpleしているためリスナークラスとして
// 登録することができる。
// ここで登録したので、ボタン押下時に
// actionPerformedが呼び出される。
button.addActionListener( this );

// パネルを作成
JPanel panel = new JPanel();

// ボタン追加
panel.add(button);

// コンテントペインにパネルを追加
getContentPane().add(panel);

// ウインドウが閉じられたときにアプリケーションを終了するように設定
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// サブコンポーネントの推奨サイズおよびレイアウトに合わせて
// この Window をサイズ変更するように設定
pack();

}

// メイン
public static void main( String[] args ){ 

TestClass_Frame1 frame = new TestClass_Frame1();
frame.setVisible( true );

}

// イベントをハンドルする関数
// 画面上のボタンが押下された時
// 呼び出される
public void actionPerformed( ActionEvent ae ){

try{

// 次画面のFram2を生成
TestClass_Frame2 next = new TestClass_Frame2();

// 次画面を表示
next.setVisible( true );

// 本画面(Fram1)を非表示
// これでいいの？？
setVisible( false );

}catch( Exception e ){

e.printStackTrace();

}

}

}

