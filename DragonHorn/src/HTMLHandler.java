import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStream;
import java.util.Scanner;

public class HTMLHandler {

	public ArrayList<Tree> urlTree;

	private static Set<String> alloverurl = new HashSet<String>();

	public HashMap<String, String> searchResult;

	String Keyword;

	// call GoogleQuery to get the search result, and put the result in the
	// "searchResult"(HashMap)
	public HTMLHandler() throws IOException {
		searchResult = new HashMap<String, String>();

		Scanner scanner = new Scanner(System.in);
		if (scanner.hasNext()) {
			Keyword = scanner.next();
			GoogleQuery googleQuery = new GoogleQuery(Keyword);
			searchResult = googleQuery.query();

		}
		// use all url in the searchResult to build the tree
		// urlTree is a forest
		urlTree = buildTree();

	}

	// put the url and the title in webPage, and use this webPage new a tree(webPage
	// is the root)
	// and treeList is a forest that put all trees
	public ArrayList<Tree> buildTree() throws IOException {

		ArrayList<Tree> treeList = new ArrayList<Tree>();
		for (String item : searchResult.keySet()) {

			if (searchResult.get(item).contains("http") || !item.contains("Facebook")) {

				Tree tree = new Tree(new WebPage(searchResult.get(item), item));
				treeList.add(tree);

			}

		}
		return treeList;
	}

	// a method to check the content of urlTree
	public String printUrlTree() {
		StringBuilder result = new StringBuilder();
		for (Tree url : urlTree) {
			result.append(url.root.webPage.url + "\n ");
		}

		return result.toString();
	}

	// For all trees in urlTree, do workurl() to get children
	public void work() throws IOException {
		for (Tree r : urlTree) {
			String url = r.root.webPage.getUrl();

			try {
				ArrayList<String> children = workurl(url);
				for (int i = 0; i < children.size(); i++) {
					r.root.addChild(new Node(new WebPage(children.get(i), i)));
				}
			} catch (Exception exp) {
				System.out.println("Can't access the URL:(");

			}
		}

	}

	// get children
	public static ArrayList<String> workurl(String strurl) {
		String href = "";
		ArrayList<String> tempChild = new ArrayList<String>();
		// 創建url爬取核心對象

		if (!(alloverurl.contains(strurl))) {
			// 創建url爬取核心對象

			try {
				URL url = new URL(strurl);
				// 通過url創建與網頁的連接
				URLConnection conn = url.openConnection();
				// 通過鏈接取得網頁返回的數據
				conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

				InputStream is = conn.getInputStream();

				// conn.getContentEncoding();
				// 一般按行讀取網頁數據，並進行內容分析
				// 因此用BufferedReader和InputStreamReader把字節流轉化為字符流的緩衝流
				// 進行轉換時，需要處理編碼格式問題
				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				// 按行讀取並打印
				String line = null;
				// 正則表達式的匹配規則提取該網頁的鏈接
				Pattern p = Pattern.compile("<a .*href=.+</a>");

				while ((line = br.readLine()) != null) {
					// System.out.println(line);
					// 編寫正則，匹配超鏈接地址
					// pw.println(line);
					Matcher m = p.matcher(line);
					while (m.find()) {
						href = m.group();
						// 找到超鏈接地址並截取字符串
						// 有無引號
						href = href.substring(href.indexOf("href="));
						if (href.charAt(5) == '\"') {
							href = href.substring(6);
						} else {
							href = href.substring(5);
						}
						// 截取到引號或者空格或者到">"結束
						try {
							href = href.substring(0, href.indexOf("\""));
						} catch (Exception e) {
							try {
								href = href.substring(0, href.indexOf(" "));
							} catch (Exception e1) {
								href = href.substring(0, href.indexOf(">"));
							}
						}
						if (href.startsWith("http:") || href.startsWith("https:")) {

							// 將url地址放到隊列中
							tempChild.add(href);

						}

					}

				}

				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("Wait...");
			}
		}

		return tempChild;
	}
}
