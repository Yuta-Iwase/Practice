import javax.swing.*;
import java.awt.event.*;
import javax.script.*;

public class calculator extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	double result_n = 0;
	String result_s = "";
	String formula_B = "";
	String formula ="";
	boolean Flag_s = false;

	JTextField ObjText = new JTextField();
	JTextField command = new JTextField();
	JButton ObjB[] = new JButton[10];
	JButton ObjB_C = new JButton("Çb");
	JButton ObjB_E = new JButton("ÅÅ");
	JButton ObjB_P = new JButton("Å{");
	JButton ObjB_M = new JButton("Å|");
	JButton ObjB_T = new JButton("Å~");
	JButton ObjB_D = new JButton("ÅÄ");

	public calculator() {
		JFrame ObjF = new JFrame("Calculator");

		ObjF.setSize(360, 339);
		ObjF.setResizable(false);
		ObjF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ObjF.setLocationRelativeTo(null);

		ObjF.getContentPane().setLayout(null);

		ObjF.getContentPane().add(ObjText);
		ObjF.getContentPane().add(command);

		for (int t = 0; t < 10; t++) {
			String T = String.valueOf(t);
			ObjB[t] = new JButton(T);
			ObjB[t].addActionListener(this);
			ObjF.getContentPane().add(ObjB[t]);
		}

		ObjB_C.addActionListener(this);
		ObjB_E.addActionListener(this);
		ObjB_P.addActionListener(this);
		ObjB_M.addActionListener(this);
		ObjB_T.addActionListener(this);
		ObjB_D.addActionListener(this);
		ObjF.getContentPane().add(ObjB_C);
		ObjF.getContentPane().add(ObjB_E);
		ObjF.getContentPane().add(ObjB_P);
		ObjF.getContentPane().add(ObjB_M);
		ObjF.getContentPane().add(ObjB_T);
		ObjF.getContentPane().add(ObjB_D);
		

		ObjB_C.setBounds(   0,   0,  50, 50);
		ObjText.setBounds( 50,   0,6000, 50);
		ObjB[7].setBounds(  0,  50, 100, 60);
		ObjB[8].setBounds(100,  50, 100, 60);
		ObjB[9].setBounds(200,  50, 100, 60);

		ObjB[4].setBounds(0,   110, 100, 60);
		ObjB[5].setBounds(100, 110, 100, 60);
		ObjB[6].setBounds(200, 110, 100, 60);

		ObjB[1].setBounds(0, 170, 100, 60);
		ObjB[2].setBounds(100, 170, 100, 60);
		ObjB[3].setBounds(200, 170, 100, 60);

		ObjB[0].setBounds(0, 230, 100, 60);
		command.setBounds(0, 290, 360, 20);

		ObjB_E.setBounds(100, 230, 200, 60);
		ObjB_P.setBounds(300, 50, 60, 60);
		ObjB_M.setBounds(300, 110, 60, 60);
		ObjB_T.setBounds(300, 170, 60, 60);
		ObjB_D.setBounds(300, 230, 60, 60);


		ObjF.setVisible(true);
	}
	
	public double calculation(String s){
		double result;
		try {
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("JavaScript");
			result = (double) engine.eval(s);
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int t = 0; t < 10; t++) {
			if (e.getSource() == ObjB[t]) {
				if(Flag_s){
					formula += t;
					formula_B = formula;
					ObjText.setText(null);
					result_s = "" + t;
					ObjText.setText(result_s);
				}else{
					formula += t;
					result_s += t;
					formula_B = formula;
					ObjText.setText(result_s);
				}
				command.setText(formula);
				Flag_s = false;
			}
		}
		
		if (e.getSource() == ObjB_P){
			if(Flag_s){
				formula = formula_B + "+";
			}else{
				ObjText.setText(null);
				ObjText.setText(Double.toString(calculation(formula)));
				formula += "+";
			}
			command.setText(formula);
			Flag_s = true;
		}
		
		if (e.getSource() == ObjB_M){
			if(Flag_s){
				formula = formula_B + "-";
			}else{
				ObjText.setText(null);
				ObjText.setText(Double.toString(calculation(formula)));
				formula += "-";
			}
			command.setText(formula);
			Flag_s = true;
		}
		
		if (e.getSource() == ObjB_T){
			if(Flag_s){
				formula = formula_B + "*";
			}else{
				ObjText.setText(null);
				ObjText.setText(Double.toString(calculation(formula)));
				formula += "*";
			}
			command.setText(formula);
			Flag_s = true;
		}
		
		if (e.getSource() == ObjB_D){
			if(Flag_s){
				formula = formula_B + "/";
			}else{
				ObjText.setText(null);
				ObjText.setText(Double.toString(calculation(formula)));
				formula += "/";
			}
			command.setText(formula);
			Flag_s = true;
		}
		
		if (e.getSource() == ObjB_E){
			if(Flag_s){
				formula = formula_B;
			}else{
				ObjText.setText(null);
				ObjText.setText(Double.toString(calculation(formula)));
				formula = "" + calculation(formula);
				formula_B = "" + calculation(formula);
			}
			command.setText(formula);
			Flag_s = false;
		}
		
		if (e.getSource() == ObjB_C){
			result_n = 0;
			result_s = "";
			formula_B = "";
			formula ="";
			Flag_s = false;
			
			ObjText.setText(null);
			command.setText(null);
		}


	}

	public static void main(String[] args) {
		new calculator();
	}

}
