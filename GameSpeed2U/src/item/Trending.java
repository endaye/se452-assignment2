package item;

import main.Helper;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Trending", urlPatterns = "/Trending")
public class Trending extends HttpServlet {
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {

		Helper helper = new Helper(request, response.getWriter());
		String sqlStatement = "select * from items";
		String sqlResult = helper.sqlHelper(sqlStatement, 0);
		displayTrending(request, response, sqlResult);
	}

	protected void displayTrending(HttpServletRequest request,
								   HttpServletResponse response, String sqlResult)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		pw.print("<div id='body'><section id='content'><article class='expanded'>");
		pw.print("<h2>Trending</h2>");
		pw.print(sqlResult);
		pw.print("</article></section>");
		helper.printHtml("site_sidebar.html");
		helper.printHtml("site_footer.html");
	}


	@Override
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
