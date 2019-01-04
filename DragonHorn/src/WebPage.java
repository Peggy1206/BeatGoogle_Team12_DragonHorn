import java.io.IOException;
import java.util.ArrayList;

//Calculate the scores of web pages

public class WebPage {
	public String url;
	public String name;
	public int num;
	public WordCounter counter;
	public double score;
	public double sumScore;//the score add itself and its children
	
	
	public WebPage(String url, String name) throws IOException {
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);
	}
	
	public WebPage(String url, int name) throws IOException {
		this.url = url;
		this.num = name;
		this.counter = new WordCounter(url);
	}
	
	public void setScore(ArrayList<Keyword> keywords) throws IOException {
		this.score = 0;
		
		for(Keyword k : keywords) {
			this.score +=counter.countKeyword(k.name) * k.weight;
		}
		
	}
	
	public String getUrl() {
		return url;
	}
}
