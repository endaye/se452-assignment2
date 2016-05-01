

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TabletList", urlPatterns = "/TabletList")
public class TabletList extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Tablet> hm = new HashMap<String, Tablet>();

		if (CategoryName == null) {
			hm.putAll(TabletHashMap.apple);
			hm.putAll(TabletHashMap.microsoft);
			hm.putAll(TabletHashMap.samsung);
			name = "";
		} else {
			if (CategoryName.equals("apple")) {
				hm.putAll(TabletHashMap.apple);
				name = TabletHashMap.string_apple;
			} else if (CategoryName.equals("microsoft")) {
				hm.putAll(TabletHashMap.microsoft);
				name = TabletHashMap.string_microsoft;
			} else if (CategoryName.equals("samsung")) {
				hm.putAll(TabletHashMap.samsung);
				name = TabletHashMap.string_samsung;
			}
		}

		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
        pw.print("<div id='body'><section id='content'><article class='expanded'>");
        pw.print("<h2>"+name+" Tablets</h2>");
		for (Map.Entry<String, Tablet> entry : hm.entrySet()) {
			Tablet Tablet = entry.getValue();
            pw.print(new GenerateItemHtmlHandler(Tablet).getHtml());
        }
		pw.print("</article></section>");

		helper.printHtml("site_sidebar.html");
		helper.printHtml("site_footer.html");
	}
}
