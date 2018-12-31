import java.io.IOException;
import java.util.ArrayList;

//Sort webpages (mother webpages)

public class Rank {
	
	public DecideInput userInput;
	public HTMLHandler handler;
	public ArrayList<Keyword> keyword = new ArrayList<Keyword>();
	
	public Rank() throws IOException{
		
		userInput = new DecideInput();
		handler = new HTMLHandler();
		addKeyword();
	}
	
	public void addKeyword() {
		
		keyword.add(new Keyword("龍角散",-10));
		keyword.add(new Keyword("飲料",10));
		keyword.add(new Keyword("政大",3));
		keyword.add(new Keyword("師大",3));
		keyword.add(new Keyword("黑眼豆豆",15));
		keyword.add(new Keyword("蜂蜜牛奶",13));
		keyword.add(new Keyword("珍芋各半",15));
		keyword.add(new Keyword("純醇奶",13));
		keyword.add(new Keyword("橙柚青",13));

	}
	
	public void startCount() throws IOException {
		for(Tree tree : handler.urlTree) {
			countPostorder(tree.root);
		}
	}
	
	void countPostorder(Node node) throws IOException 
    { 
        if (node == null) {
            return; 
        }
        if(node.childern != null) {
        	for(Node child : node.childern) {
        		countPostorder(child);
        	}
        }else {
        	node.webPage.setScore(keyword);
        }
        
    }
	
	public void sum() {
		for(Tree tree : handler.urlTree) {
			
		}

	}
	
	public void sumPostorder(Node node) throws IOException{
		if (node == null) {
            return; 
        }
        if(node.childern != null) {
            for(Node child : node.childern) {
            	sumPostorder(child);
        	}
        }else {
        	node.webPage.sumScore = node.webPage.score;
        	
        }
        
	}
}
