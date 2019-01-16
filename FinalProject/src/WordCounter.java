
//Count the number of occurrences of keywords in a webpage

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

public class WordCounter {
	private String urlStr;
	private String content;

	// constructor
	public WordCounter(String urlStr) throws IOException {
		this.urlStr = urlStr;
	}

	private String fetchContent() throws IOException {
		  if(this.urlStr.contains("likefoodway")==true) {
		   String no_server = "";
		   return no_server;
		  }
		  // HW3
		  String retVal = "";
		  try{
		   if(this.urlStr.contains("http")!=true) {
		    this.urlStr = "https://" + urlStr;
		   }
		   URL url = new URL(this.urlStr);
		   URLConnection conn = url.openConnection();
		   HttpURLConnection connection = (HttpURLConnection)conn;
		   if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
		   InputStream in = conn.getInputStream();
		   BufferedReader br = new BufferedReader(new InputStreamReader(in));

		   String line = null;

		   while ((line = br.readLine()) != null) {
		    retVal = retVal + line + "\n";
		    //System.out.println(retVal);
		   }
		   in.close();
		   }
		  } catch (MalformedURLException e) {
		   // TODO: handle exception
		   System.out.println("Wait...:)");
		   e.printStackTrace();
		   }
		   catch (SSLHandshakeException e) {
		   // TODO: handle exception
		   //e.printStackTrace();
		    System.out.println("不安全的網站");
		  }catch (ConnectException e) {
		   // TODO: handle exception
		   System.out.println("無法取得連線");
		  }catch (UnknownHostException e) {
			// TODO: handle exception
			   System.out.println("怪人");
		}catch(ProtocolException e) {
			System.out.println("再跳ㄚ");
		}catch (SocketException e) {
			// TODO: handle exception
			System.out.println("socks");
		}
		  
		  
		  return retVal;

		  /*
		   * InputStream in = address.openStream(); BufferedReader reader = new
		   * BufferedReader(new InputStreamReader(in)); StringBuilder result = new
		   * StringBuilder(); String line; while((line = reader.readLine()) != null) {
		   * result.append(line); } System.out.println(result.toString());
		   */
		 }
	
	public int countKeyword(String k) throws IOException {
		// HW3

		if (content == null) {
			content = fetchContent();
		}

		// To do a case-insensitive search, we turn the whole content and keyword into
		// upper-case:
		content = content.toUpperCase();
		k = k.toUpperCase();

		int retVal = 0;
		int fromIdx = 0;
		int found = -1;

		while ((found = content.indexOf(k, fromIdx)) != -1) {
			retVal++;
			fromIdx = found + k.length();
		}

		return retVal;

	}
}
