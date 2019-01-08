
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
/*package HW6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	private String urlStr;
	private String content;

	public WordCounter(String urlStr) {
		this.urlStr = urlStr;
	}

	private String fetchContent() throws IOException {
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String retVal = "";

		String line = null;

		while ((line = br.readLine()) != null) {
			retVal = retVal + line + "\n";
		}

		return retVal;
	}

	public int countKeyword(String keyword) throws IOException {
		if (content == null) {
			content = fetchContent();
		}

		// To do a case-insensitive search, we turn the whole content and keyword into
		// upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();

		int retVal = 0;
		int fromIdx = 0;
		int found = -1;

		while ((found = content.indexOf(keyword, fromIdx)) != -1) {
			retVal++;
			fromIdx = found + keyword.length();
		}

		return retVal;
	}
}
*/
