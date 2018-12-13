
//record webpages

import java.io.IOException;
import java.util.ArrayList;

public class Node {
	public Node parent;
	public ArrayList<Node> childern;
	public WebPage webPage;
	public double nodeScore;
	
	public Node(WebPage webPage) {
		this.webPage = webPage;
		this.childern = new ArrayList<Node>();
	}
	
	public void setNodeScore(ArrayList<Keyword> keywords) throws IOException {
		webPage.setScore(keywords);
		this.nodeScore = webPage.score;
		
		for(Node child : childern) {
			this.nodeScore += child.nodeScore;
		} 
	}
	
	public void addChild(Node child) {
		this.childern.add(child);
		child.parent = this;
	}
	
	public int getDepth() {
		int retVal = 1;
		Node currNode = this;
	
		while(currNode.parent != null) {
			retVal ++;
			currNode = currNode.parent;
		}
		return retVal;
	}

}

