import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DecideInput {
	static String search = "";
	static HashMap<String, String> searchResult = new HashMap<String, String>();
	//String keyword = Test.keyword;
	String Keyword;
	public static ArrayList<String> searchList = new ArrayList<String>();

	public DecideInput() throws IOException {
		Scanner scanner = new Scanner(System.in);
		//while (scanner.hasNextLine()) {
			Keyword = scanner.next();
			GoogleQuery googleQuery = new GoogleQuery(Keyword);
			searchResult = googleQuery.query();
			/*for (String item : searchResult.values()) {
				searchList.add(item);

			}*/
			for(String item : searchResult.keySet()) {
				searchList.add(searchResult.get(item));
			}
		//}
	}

	public ArrayList<String> getResult() {
		return searchList;
	}

}
