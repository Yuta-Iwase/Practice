import java.io.PrintStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ViolationCodeTest1 {
  static Calendar c = 
    new GregorianCalendar(1995, GregorianCalendar.MAY, 23);
private static PrintStream printf;
  @SuppressWarnings("deprecation")
public static void main(String[] args) {  
    // args[0] はクレジットカードの有効期限を指す
    // args[0] には %1$tm, %1$te, %1$tY といった悪意ある引数が含まれるかもしれない
    // 第1引数は 05 (May) を、第2引数は  23 (day) を、第3引数は 1995 (year) を出力する
    // cと比較し、一致しない場合に以下の行を出力する
	  System.out.println("↓違反コード");
//    System.out.printf(args[0] +  " did not match! %nHINT: It was issued on %1$terd of some month", c);
    
    System.out.println();
    
    // args[0] はクレジットカードの有効期限を指す
    // cと比較し、一致しない場合に以下の行を出力する
//    args[0] = c;    
//    System.out.println("↓適合コード");
//    System.out.printf("The input did not match! " + "HINT: It was issued on %1$terd of some month", c);
    
    long year = 1994 - 1970;
    long sec = year * 365L * 24L * 60L * 60L * 1000L + (6+(365-29)) * 24 * 60 * 60 * 1000L;
    
    setPrintf(System.out.printf("%td" + "%n", new Date(sec)));
    System.out.println(sec);
  }
public static PrintStream getPrintf() {
	return printf;
}
public static void setPrintf(PrintStream printf) {
	ViolationCodeTest1.printf = printf;
}
}