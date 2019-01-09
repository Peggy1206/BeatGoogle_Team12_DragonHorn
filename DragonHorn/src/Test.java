import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
	
	public static String keyword = "";
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {
			keyword = scanner.next();*/
			
			
			
			HTMLHandler handeler = new HTMLHandler();
			//System.out.println(handeler.printUrlTree());
			
			handeler.work();
			
			
			
			Rank rank = new Rank(handeler);
			
			rank.startCount();
			rank.print();
			
			/*for(Tree tree : handeler.urlTree) {
				tree.eularPrintTree();
			}
			/*
			rank.sum();
			rank.printTree();*/
		
		
		//scanner.close();
	
		
	}
}
