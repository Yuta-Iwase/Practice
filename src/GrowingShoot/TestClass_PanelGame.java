package GrowingShoot;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TestClass_PanelGame extends JPanel{
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(new Color(255,255,255));
	}
}
