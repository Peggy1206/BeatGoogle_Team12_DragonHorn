import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyStore.TrustedCertificateEntry;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Calendar;
import java.util.Date;
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
	static  String keyword = "";

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
		
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			//轉發至jsp
			request.getRequestDispatcher("Search.jsp").forward(request, response); 
			return;
		}
//		// 设置刷新自动加载时间为 5 秒
//        response.setIntHeader("Refresh", 5);
//		try {
//        String requestUri = request.getRequestURI();
//		request.setAttribute("requestUri", requestUri);
//        request.getRequestDispatcher("refresh.jsp")
//		 .forward(request, response); 
//		
//		response.sendRedirect("refresh.jsp");
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
//		}catch(NullPointerException e) {
//			System.out.println("不能跑");
//		}
		
//		//轉發至jsp	
//		request.getRequestDispatcher("googleitem.jsp") 
//		.forward(request, response); 
	
}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
	