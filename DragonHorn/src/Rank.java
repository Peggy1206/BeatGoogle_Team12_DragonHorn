import java.io.IOException;
import java.util.ArrayList;

//Sort webpages (mother webpages)

public class Rank {
	
	//public DecideInput userInput;
	public HTMLHandler handler;
	public ArrayList<Keyword> keyword = new ArrayList<Keyword>();
	public ArrayList<Tree> urlTree;
	
	public Rank(HTMLHandler handler) throws IOException{
		
		this.handler = handler;
		this.urlTree = handler.urlTree;
		addKeyword();
		startCount();
	}
	
	private void addKeyword() {
		
		keyword.add(new Keyword("龍角散",-10));
		keyword.add(new Keyword("飲料",10));
		keyword.add(new Keyword("政大",3));
		keyword.add(new Keyword("師大",3));
		keyword.add(new Keyword("黑眼豆豆",15));
		keyword.add(new Keyword("蜂蜜牛奶",13));
		keyword.add(new Keyword("珍芋各半",15));
		keyword.add(new Keyword("純醇奶",13));
		keyword.add(new Keyword("橙柚青",15));

	}
	
	public void startCount() throws IOException {
		for(Tree tree : urlTree) {
			countPostorder(tree.root);
		}
	}
	
	//count nodes' score
	void countPostorder(Node node) throws IOException 
    { 
        if (node == null) {
            return; 
        }
        if(node.children.isEmpty()) {
        	node.setNodeScore(keyword);
        }else {
        	
        	for(Node child : node.children) {
        		countPostorder(child);
        	}
        	node.setNodeScore(keyword);
        }
        
    }
	
	//sum each tree
	public void sum() throws IOException {
		for(Tree tree : urlTree) {
			sumPostorder(tree.root);
		}

	}
	
	//for each tree sum their own scores(add children)
	public void sumPostorder(Node node) throws IOException{
		if (node == null) {
            return; 
        }
        if(!node.children.isEmpty()) {
            for(Node child : node.children) {
            	sumPostorder(child);
        	}
            for(Node child : node.children) {
            	node.webPage.sumScore += child.webPage.sumScore; 
            }
        }else {
        	node.webPage.sumScore = node.webPage.score;
        	
        }
        
	}
	
	public void print() {

		for(Tree tree : urlTree) {
			tree.eularPrintTree();
		}
	}
	
}
