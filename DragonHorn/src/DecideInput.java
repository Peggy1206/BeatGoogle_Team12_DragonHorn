import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DecideInput {
	static String search = "";
	static HashMap<String, String> searchResult;
	//String keyword = Test.keyword;
	String Keyword;
	public ArrayList<String> searchList;

	public DecideInput() throws IOException {
		searchResult = new HashMap<String, String>();
		searchList = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			Keyword = scanner.next();
			GoogleQuery googleQuery = new GoogleQuery(Keyword);
			searchResult = googleQuery.query();
			/*for (String item : searchResult.values()) {
				searchList.add(item);

			}*/
			addSearchList();
		}
	}
	
	public void addSearchList() {
		
		for(String item : searchResult.keySet()) {
			searchList.add(searchResult.get(item));
			//System.out.println(searchResult.get(item));
		}
		
	}

	public void getResult() {
		for(String url : searchList) {
			System.out.println(url);
		}	
	}

}
