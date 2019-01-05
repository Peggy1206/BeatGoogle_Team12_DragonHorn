import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DecideInput {
	static String search = "";
	public HashMap<String, String> searchResult;
	//String keyword = Test.keyword;
	String Keyword;
	//public ArrayList<String> searchList;

	public DecideInput() throws IOException {
		searchResult = new HashMap<String, String>();
		//searchList = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			Keyword = scanner.next();
			GoogleQuery googleQuery = new GoogleQuery(Keyword);
			searchResult = googleQuery.query();
			/*for (String item : searchResult.values()) {
				searchList.add(item);

			}*/
			//addSearchList(searchResult);
		}
	}
	
	/*
	public void addSearchList(HashMap<String, String> result) {
		
		for(String item : result.keySet()) {
			searchList.add(result.get(item));
			//System.out.println(searchResult.get(item));
		}
		//getResult();
	}

	public void getResult() {
		for(String url : searchList) {
			System.out.println(url);
		}	
	}*/

}
