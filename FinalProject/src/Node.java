
//record webpages

import java.io.IOException;
import java.util.ArrayList;

public class Node {
	public Node parent;
	public ArrayList<Node> children;
	public WebPage webPage;
	public double nodeScore;
	
	public Node(WebPage webPage) {
		this.webPage = webPage;
		this.children = new ArrayList<Node>();
	}
	
	public void setNodeScore(ArrayList<Keyword> keywords) throws IOException {
		webPage.setScore(keywords);
		this.nodeScore = webPage.score;
		
		for(Node child : children) {
			this.nodeScore += child.nodeScore;
		} 
	}
	
	public void addChild(Node child) {
		this.children.add(child);
		child.parent = this;
	}
	
	public boolean isTheLastChild() {
		if (this.parent == null) return true;
		ArrayList<Node> siblings = this.parent.children;
		
		return this.equals(siblings.get(siblings.size() - 1));
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
