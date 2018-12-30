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

import org.omg.CORBA.portable.InputStream;

public class HTMLHandler {

	// 等待爬取的url
	private static List<String> allwaiturl = new ArrayList<String>();
	// 爬取過的url
	private static Set<String> alloverurl = new HashSet<String>();
	// 記錄所有url的深度進行爬取判斷
	private static Map<String, Integer> allurldepth = new HashMap<String, Integer>();
	// 爬取得深度
	private static int maxdepth = 2;

	public static void main(String args[]) {
		// 確定爬取的網頁地址，此處為噹噹網首頁上的圖書分類進去的網頁
		// 網址為 http://book.dangdang.com/
		// String strurl="http://search.dangdang.com/?key=%BB%FA%D0%B5%B1%ED&act=input";
		String strurl = "http://book.dangdang.com/";

		workurl(strurl, 1);

	}
// Get child from the url
	public HTMLHandler() throws IOException {
		
		for(Tree r : Tree.urlTree) {
			String url = r.root.webPage.getUrl();
			ArrayList<String> children = workurl(url,1);
			for(int i = 0; i< children.size(); i++) {
				r.root.addChild(new Node(new WebPage(children.get(i), i)));
			}
			//(workurl(url, 1), 2)));
		}

	}
	
	
	public static ArrayList<String> workurl(String strurl, int depth) {
		String href = "";
		ArrayList<String> tempChild = new ArrayList<String>();
		// 判斷當前url是否爬取過
		if (!(alloverurl.contains(strurl) || depth > maxdepth)) {
			// 創建url爬取核心對象

			try {
				URL url = new URL(strurl);
				// 通過url創建與網頁的連接
				URLConnection conn = url.openConnection();
				// 通過鏈接取得網頁返回的數據
				InputStream is = (InputStream) conn.getInputStream();

				System.out.println(conn.getContentEncoding());
				// 一般按行讀取網頁數據，並進行內容分析
				// 因此用BufferedReader和InputStreamReader把字節流轉化為字符流的緩衝流
				// 進行轉換時，需要處理編碼格式問題
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));

				// 按行讀取並打印
				String line = null;
				// 正則表達式的匹配規則提取該網頁的鏈接
				Pattern p = Pattern.compile("<a .*href=.+</a>");
				// 創建一個輸出流，用於保存文檔,文檔名為執行時間，以防重複
				//PrintWriter pw = new PrintWriter(new File(System.currentTimeMillis() + ".txt"));

				while ((line = br.readLine()) != null) {
					// System.out.println(line);
					// 編寫正則，匹配超鏈接地址
					//pw.println(line);
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
							// 輸出該網頁存在的鏈接
							// System.out.println(href);
							// 將url地址放到隊列中
							allwaiturl.add(href);
							tempChild.add(href);
							allurldepth.put(href, depth + 1);
						}

					}

				}
				//pw.close();
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 將當前url歸列到alloverurl中
			alloverurl.add(strurl);
			//System.out.println(strurl + "網頁爬取完成，已爬取數量：" + alloverurl.size() + "，剩餘爬取數量：" + allwaiturl.size());
		}
		// 用遞歸的方法繼續爬取其他鏈接
		/*String nexturl = allwaiturl.get(0);
		allwaiturl.remove(0);
		workurl(nexturl, allurldepth.get(nexturl));*/
		
		return tempChild;
	}

}
