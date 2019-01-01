import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Test {

	static String search = "";
	static HashMap<String,String> searchResult = null;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()) {
			String Keyword = scanner.next();
			GoogleQuery googleQuery = new GoogleQuery(Keyword);
			searchResult = googleQuery.query();
		}
		
		//while(!searchResult.isEmpty()) {
			for(String s : searchResult.values()) {
	            System.out.println(s);
			}
	        //System.out.println();
			
			
			
			
	    	/*String urlStr = sc.next();
	    	String keyword = sc.next();
	    	
	    	WordCounter counter = new WordCounter(urlStr);
	    	int count = counter.countKeyword(keyword);
	    	System.out.println( keyword + " appears " +count + " times");*/
	    	
	    /*}
		System.out.println(search);*/
			scanner.close();
	}
}
