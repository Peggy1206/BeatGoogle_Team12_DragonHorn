
//Count the number of occurrences of keywords in a webpage

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class WordCounter {
	private String urlStr;
	private String content;


	// constructor
	public WordCounter(String urlStr) throws IOException {
		this.urlStr = urlStr;
	}

	


	private String fetchContent() throws IOException {
		// HW3

		URL url = new URL(this.urlStr);
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String retVal = "";
		String line = null;

		while ((line = br.readLine()) != null) {
			retVal = retVal + line + "\n";
		}

		return retVal;
	}



	public int countKeyword(String k) throws IOException {
		// HW3

		if (content == null) {
			content = fetchContent();
		}

		// To do a case-insensitive search, we turn the whole content and keyword into
		// upper-case:
		content = content.toUpperCase();
		k = k.toUpperCase();

		int retVal = 0;
		int fromIdx = 0;
		int found = -1;

		while ((found = content.indexOf(k, fromIdx)) != -1) {
			retVal++;
			fromIdx = found + k.length();
		}

		return retVal;
	}

}
