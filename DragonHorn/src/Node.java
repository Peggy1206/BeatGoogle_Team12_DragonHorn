
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
		this.nodeScore = webPage.sumScore;
		
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
/*package HW6;

import java.io.IOException;
import java.util.ArrayList;

public class WebTree {
	public WebNode root;

	public WebTree(WebPage rootPage) {
		this.root = new WebNode(rootPage);
	}

	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException {
		setPostOrderScore(root, keywords);
	}

	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException {
		for (WebNode child : startNode.children) {
			setPostOrderScore(child, keywords);
		}

		startNode.setNodeScore(keywords);
	}

	public void eularPrintTree() {
		eularPrintTree(root);
	}

	private void eularPrintTree(WebNode startNode) {
		int nodeDepth = startNode.getDepth();

		if (nodeDepth > 1) {
			System.out.print("\n" + repeat("\t", nodeDepth - 1));
		}
		
		System.out.print("(" + startNode.webPage.name + ", " + startNode.nodeScore);

		for (WebNode child : startNode.children) {
			eularPrintTree(child);
		}

		System.out.print(")");
		if (startNode.isTheLastChild()) {
			System.out.print("\n" + repeat("\t", nodeDepth - 2));
		}
	}

	private String repeat(String str, int repeat) {
		String retVal = "";

		for (int i = 0; i < repeat; i++) {
			retVal += str;
		}
		return retVal;
	}
}
*/