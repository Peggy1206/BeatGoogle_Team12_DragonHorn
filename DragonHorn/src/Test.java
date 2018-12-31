import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
	
	public static String keyword = "";
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {
			keyword = scanner.next();
			
			DecideInput input = new DecideInput();
			HTMLHandler handelr = new HTMLHandler();
		
			Rank rank = new Rank();
			rank.startCount();
			rank.sum();
			rank.printTree();
		}
		
		scanner.close();
	
		
	}
}
