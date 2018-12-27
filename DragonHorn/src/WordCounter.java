
//Count the number of occurrences of keywords in a webpage

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	private String urlStr;
	private String content;
	
	//constructor
	public WordCounter(String urlStr) {
		this.urlStr = urlStr;
	}
	
	private String fetchContent() throws IOException{
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
	
	public int countKeyword(String k) throws IOException{
		if (content == null) {
			content = fetchContent();
			}
		
		content = content.toUpperCase();
		k = k.toUpperCase();

		int count = 0;
		while (content.indexOf(k) != -1) {
			content = content.substring(content.indexOf(k) + k.length(), content.length());
		    count++;
		    }
		return count;
	}

}
