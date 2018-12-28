import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Test {

	static String search = "";
	static HashMap<String,String> searchResult = new HashMap<String,String>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()) {
			String Keyword = scanner.next();
			GoogleQuery googleQuery = new GoogleQuery(Keyword);
			searchResult = googleQuery.query();
			for(String item : searchResult.keySet()) {
	            search += item + " " + searchResult.get(item);
			}
			 
		}
		
		
		
			scanner.close();
	}
}
