import java.io.IOException;
import java.util.ArrayList;

import javax.print.attribute.Size2DSyntax;

//Sort webpages (mother webpages)

public class Rank {
	
	//public DecideInput userInput;
	public HTMLHandler handler;
	public ArrayList<Keyword> keyword = new ArrayList<Keyword>();
	
	public Rank(HTMLHandler handler) throws IOException{
		
		this.handler = handler;
		addKeyword();
		
	}
	
	private void addKeyword() {
		
		keyword.add(new Keyword("龍角散",-100));
		keyword.add(new Keyword("飲料",5));
		keyword.add(new Keyword("政大",50));
		keyword.add(new Keyword("師大",40));
		keyword.add(new Keyword("龍角峰", -20));
		keyword.add(new Keyword("黑眼豆豆" ,13));
		keyword.add(new Keyword("蜂蜜牛奶",8));
		keyword.add(new Keyword("珍芋各半",15));
		keyword.add(new Keyword("純醇奶",13));
		keyword.add(new Keyword("橙柚青",12));
		keyword.add(new Keyword("攻略",-12));


	}
	
	public void startCount() throws IOException {
		for(Tree tree : handler.urlTree) {
			countPostorder(tree.root);
		}
	}
	
	//count nodes' score by postOrder
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
	
	public ArrayList<Tree> quickSort(ArrayList<Tree> urlTree) {
		if (urlTree.size() < 2)
            return urlTree;

		Tree pivotNode = urlTree.get(urlTree.size() / 2);
        double pivot = urlTree.get(urlTree.size() / 2).root.nodeScore;
        urlTree.remove(urlTree.size() / 2);
        ArrayList<Tree> less = new ArrayList<Tree>();
        ArrayList<Tree> greater = new ArrayList<Tree>();
        ArrayList<Tree> result = new ArrayList<Tree>();
        for (Tree tree : urlTree)
        {
            if (tree.root.nodeScore > pivot)
                greater.add(tree);
            else
                less.add(tree);
        }
        result.addAll(quickSort(greater));
        result.add(pivotNode);
        result.addAll(quickSort(less));
        return result;
		
	}
	
	public void getRankResult() {
		ArrayList<Tree> list = quickSort(handler.urlTree);
		for(Tree tree : list) {
			System.out.println(tree.root.webPage.name + " " +tree.root.nodeScore);
		}
		
		
	}
}
