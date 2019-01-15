import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String keyword = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		//如果沒打關鍵字
		if(request.getParameter("keyword")== null) {
			//取得URL
			String requestUri = request.getRequestURI();
			//設定請求屬性
			request.setAttribute("requestUri", requestUri);
			//轉發至jsp
			request.getRequestDispatcher("Search.jsp").forward(request, response); 
			return;
		}
		
//		
//		// 设置刷新自动加载的事件间隔为 5 秒
//        response.setIntHeader("Refresh", 5);
//     
//        // 设置响应内容类型
//        response.setContentType("text/html;charset=UTF-8");
//     
//        // 获取当前的时间
//        Calendar calendar = new GregorianCalendar();
//        String am_pm;
//        int hour = calendar.get(Calendar.HOUR);
//        int minute = calendar.get(Calendar.MINUTE);
//        int second = calendar.get(Calendar.SECOND);
//        if(calendar.get(Calendar.AM_PM) == 0)
//            am_pm = "AM";
//        else
//            am_pm = "PM";
//     
//        String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
//        
//        PrintWriter out = response.getWriter();
//        String title = "使用 Servlet 自动刷新页面";
//        String docType = "<!DOCTYPE html> \n";
//        out.println(docType +
//            "<html>\n" +
//            "<head><title>" + title + "</title></head>\n"+
//            "<body bgcolor=\"#f0f0f0\">\n" +
//            "<h1 align=\"center\">" + title + "</h1>\n" +
//            "<p>当前时间是：" + CT + "</p>\n");
//       
//        request.getRequestDispatcher("refresh.jsp")
//		 .forward(request, response); 
//         
		/*String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for(Entry<String, String> entry : query.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    s[num][0] = key;
		    s[num][1] = value;
		    num++;
		}
		*/
		
		
		//GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		//HashMap<String, String> query = google.query();
		
		HTMLHandler handeler = new HTMLHandler(request.getParameter("keyword"));
		//System.out.println(handeler.printUrlTree());
		handeler.work();
			
		Rank rank = new Rank(handeler);
		rank.startCount();
		
		for(Tree tree : handeler.urlTree) {
			tree.eularPrintTree();
		}
		
		String[][] s = rank.getRankResult();
		request.setAttribute("query", s);

		//轉發至jsp
		request.getRequestDispatcher("googleitem.jsp")
		 .forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
	