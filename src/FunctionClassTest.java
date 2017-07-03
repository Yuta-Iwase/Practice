import java.util.function.Function;

public class FunctionClassTest {

	public static void main(String[] args) {
		Function<String, Integer> function = string -> Integer.parseInt(string);
        System.out.println(function.apply("12345")+1);
        
        Function<Integer, Integer> myFunction = (a -> a*2) ;
        System.out.println(myFunction.apply(10));

	}

}
