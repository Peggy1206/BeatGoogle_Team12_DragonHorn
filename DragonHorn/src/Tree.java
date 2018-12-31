
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
		for (Node child : startNode.childern) {
			setPostOrderScore(child, keywords);
		}

		startNode.setNodeScore(keywords);
	}

	public void printTree() {
		printTree(root);
	}

	private void printTree(Node startNode) {
		// (root,score
		// (node1,score)
		// (node2,score)
		// )
		// 一層一層print出來-> for迴圈
		// startNode -> 開始搜的node
		// 檢查有沒有小孩，有小孩往下找，沒有直接印
		for (int i = 0; i < startNode.getDepth(); i++) {
			System.out.println("  ");
		}
		if (startNode.childern.isEmpty()) {
			System.out.println("(" + startNode.webPage.getUrl() + ", " + startNode.nodeScore + ")");
		} else {
			System.out.println("(" + startNode.webPage.getUrl() + ", " + startNode.nodeScore + ")");
			for (Node child : startNode.childern) {
				printTree(child);
			}
			for (int i = 0; i < startNode.getDepth(); i++) {
				System.out.println("  ");
			}
			System.out.println(")");
		}

	}

}
