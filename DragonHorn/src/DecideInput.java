import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DecideInput {
	static String search = "";
	static HashMap<String, String> searchResult = new HashMap<String, String>();
	String keyword = Test.keyword;
	public static ArrayList<String> searchList = new ArrayList<String>();

	public DecideInput() throws IOException {
		GoogleQuery googleQuery = new GoogleQuery(keyword);
		searchResult = googleQuery.query();
		for(String item:searchResult.values())
		{
			searchList.add(item);
				
		}
	}
	
}
