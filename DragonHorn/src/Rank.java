import java.io.IOException;
import java.util.ArrayList;

//Sort webpages (mother webpages)

public class Rank {
	
	//public DecideInput userInput;
	public HTMLHandler handler;
	public ArrayList<Keyword> keyword = new ArrayList<Keyword>();
	
	public Rank(HTMLHandler decide) throws IOException{
		
		//userInput = new DecideInput();
		this.handler = decide;
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
        if(!node.childern.isEmpty()) {
        	for(Node child : node.childern) {
        		countPostorder(child);
        	}
        }else {
        	node.webPage.setScore(keyword);
        }
        
    }
	
	public void sum() throws IOException {
		for(Tree tree : handler.urlTree) {
			sumPostorder(tree.root);
		}

	}
	
	public void sumPostorder(Node node) throws IOException{
		if (node == null) {
            return; 
        }
        if(!node.childern.isEmpty()) {
            for(Node child : node.childern) {
            	sumPostorder(child);
        	}
            for(Node child : node.childern) {
            	node.webPage.sumScore += child.webPage.sumScore; 
            }
        }else {
        	node.webPage.sumScore = node.webPage.score;
        	
        }
        
	}
	
	public void printTree() {
		for(Tree tree : handler.urlTree) {
			tree.printTree();
		}
	}
}
