package pinya;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (request.getParameter("keyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("search.jsp").forward(request, response);
			return;
		}
		String rwString = request.getParameter("weight");
		double requestWeight = 1;
		if (rwString != null) {
			requestWeight = Double.parseDouble(rwString);
		}

//  GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
//  HashMap<String, String> query = google.query();
//  
//  String[][] s = new String[query.size()][2];
		KeywordList list = new KeywordList();
		ArrayList<Node> nodeList = new ArrayList<Node>();
		Sorter sorter;
		list.add(new Keyword(request.getParameter("keyword"), requestWeight));
		HashMap<String, String> result = new HashMap<String, String>();
		result = list.getSearchEngineResult();

		for (Entry<String, String> re : result.entrySet()) {
			String url = re.getValue();
			String name = re.getKey();
			Node node = new Node(url, list, name);
			node.addChildren();
			node.setNodeScore();

			if (node.totalScore != 0)
				nodeList.add(node);
		}
		sorter = new Sorter(nodeList);
		sorter.rank();
		String[][] s = new String[nodeList.size()][3];

		for (int i = 0; i < nodeList.size(); i++) {

			Node node = nodeList.get(i);
			s[i][0] = node.getName();
			s[i][1] = node.getURL();
			s[i][2] = String.valueOf(node.getTotalScore());
		}
		request.setAttribute("query", s);
		request.getRequestDispatcher("googleitem.jsp").forward(request, response);

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