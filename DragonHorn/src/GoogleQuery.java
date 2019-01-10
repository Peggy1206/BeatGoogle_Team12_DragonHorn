import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery {
	public String searchKeyword;
	public String url;
	public String content;
	
	public GoogleQuery(String searchKeyword) {
		this.searchKeyword = searchKeyword;
		this.url = "https://www.google.com.tw/search?q=" + searchKeyword + "&oe=utf8&num=20";
	}
	
	private String fetchContent() throws IOException {
		String retVal = "";
		URL urlStr = new URL(this.url);
		URLConnection connection = urlStr.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/5.0(Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inReader = new InputStreamReader(inputStream, "UTF8");
		BufferedReader bf = new BufferedReader(inReader);
		
	
		String line = null;
		while((line = bf.readLine())!=null) {
			retVal += line;
		}
		return retVal;
	}
	
	public HashMap<String, String> query() throws IOException{
		if(this.content == null) {
			this.content = fetchContent();
		}
		HashMap<String,String> retVal = new HashMap<String , String>();
		Document document = Jsoup.parse(this.content);
		Elements lis = document.select("div.g");
		
		for (Element li:lis) {
			try {
				Element h3 = li.select ("h3.r").get(0);
				String title = h3.text();
				
				Element cite = li.select ("cite").get(0);
				String citeUrl = cite.text();
				//System.out.println(title + " " + citeUrl);
				retVal.put(title, citeUrl);
			}catch(IndexOutOfBoundsException e) {
				//Do nothing
			}
		}
		return retVal;
	}

}

/*package HW6;

import java.io.IOException;
import java.util.ArrayList;

public class WebNode {
	public WebNode parent;
	public ArrayList<WebNode> children;
	public WebPage webPage;
	public double nodeScore;

	public WebNode(WebPage webPage) {
		super();
		this.webPage = webPage;
		this.children = new ArrayList<WebNode>();
	}

	public void setNodeScore(ArrayList<Keyword> keywords) throws IOException {
		// this method should be called in post-order mode

		webPage.setScore(keywords);
		nodeScore = webPage.score;

		// webPage.score += all children's nodeScore
		for (WebNode child : children) {
			nodeScore += child.nodeScore;
		}
	}

	public void addChild(WebNode child) {
		this.children.add(child);
		child.parent = this;
	}
	
	public boolean isTheLastChild() {
		if (this.parent == null) return true;
		ArrayList<WebNode> siblings = this.parent.children;
		
		return this.equals(siblings.get(siblings.size() - 1));
	}
	
	public int getDepth() {
		int retVal = 1;
		WebNode currNode = this;
		
		while (currNode.parent != null) {
			retVal ++;
			currNode = currNode.parent;
		}
		return retVal;
	}
}
*/