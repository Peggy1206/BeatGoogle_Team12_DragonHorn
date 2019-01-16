
//organize node

import java.io.IOException;
import java.util.ArrayList;

public class Tree {
	// 一種資料
	public Node root;

	public Tree(WebPage rootPage) throws IOException {
		this.root = new Node(rootPage);
	}

	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException {
		setPostOrderScore(root, keywords);
	}

	private void setPostOrderScore(Node startNode, ArrayList<Keyword> keywords) throws IOException {
		for (Node child : startNode.children) {
			setPostOrderScore(child, keywords);
		}

		startNode.setNodeScore(keywords);
	}

	public void eularPrintTree() {
		eularPrintTree(root);
	}

	private void eularPrintTree(Node startNode) {

		int nodeDepth = startNode.getDepth();

		if (nodeDepth > 1) {
			System.out.print("\n" + repeat("\t", nodeDepth - 1));
		}

		System.out.print("(" + startNode.webPage.url + ", " + startNode.nodeScore);

		for (Node child : startNode.children) {
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
