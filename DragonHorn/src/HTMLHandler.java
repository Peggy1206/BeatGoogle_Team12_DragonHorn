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

	// 爬取過的url
	private static Set<String> alloverurl = new HashSet<String>();
	// 記錄所有url的深度進行爬取判斷
	// private static Map<String, Integer> allurldepth = new HashMap<String,
	// Integer>();
	// 爬取得深度
	private static int maxdepth = 2;

	public ArrayList<Tree> urlTree;

	// public DecideInput decide;

	public HashMap<String, String> searchResult;

	String Keyword;

	// public static ArrayList<Tree> urlTree = new ArrayList<Tree>();

	// Get child from the url
	public HTMLHandler() throws IOException {
		searchResult = new HashMap<String, String>();

		Scanner scanner = new Scanner(System.in);
		if (scanner.hasNext()) {
			Keyword = scanner.next();
			GoogleQuery googleQuery = new GoogleQuery(Keyword);
			searchResult = googleQuery.query();

		}
		// work();
		urlTree = buildTree();

	}

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

	public String printUrlTree() {
		StringBuilder result = new StringBuilder();
		for (Tree url : urlTree) {
			result.append(url.root.webPage.url + "\n ");
		}

		return result.toString();
	}

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

	public static ArrayList<String> workurl(String strurl) {
		String href = "";
		ArrayList<String> tempChild = new ArrayList<String>();
		// 判斷當前url是否爬取過
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
