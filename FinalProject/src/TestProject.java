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
	public static String keyword = "";
	public static String[][] result;
	static boolean isRunning = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestProject() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		if (request.getParameter("keyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			// 轉發至jsp
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		keyword = request.getParameter("keyword");
//		// 设置刷新自动加载时间为 5 秒
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				isRunning = true;
				HTMLHandler handeler;
				try {
					handeler = new HTMLHandler(keyword);

					// System.out.println(handeler.printUrlTree());
					handeler.work();

					Rank rank = new Rank(handeler);
					rank.startCount();

					for (Tree tree : handeler.urlTree) {
						tree.eularPrintTree();
					}
				//	rank.getRankResult();

					result = rank.getRankResult();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		

		if (result == null) {
			System.out.println("hi");
			response.setIntHeader("Refresh", 5);
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("refresh.jsp").forward(request, response);
			if (!isRunning)
				thread.start();

	
		} else {
	
			request.setAttribute("query", result);
			request.getRequestDispatcher("googleitem.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
