

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsoleList", urlPatterns = "/ConsoleList")
public class ConsoleList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Console> hm = new HashMap<String, Console>();
        if(CategoryName==null){
			hm.putAll(ConsoleHashMap.microsoft);
			hm.putAll(ConsoleHashMap.sony);
			hm.putAll(ConsoleHashMap.nintendo);
			name = "";
		}else{
			if(CategoryName.equals("microsoft")){
				hm.putAll(ConsoleHashMap.microsoft);
				name = ConsoleHashMap.string_microsoft;
			}
			else if(CategoryName.equals("sony")){
				hm.putAll(ConsoleHashMap.sony);
				name = ConsoleHashMap.string_sony;
			}
			else if(CategoryName.equals("nintendo")){
				hm.putAll(ConsoleHashMap.nintendo);
				name = ConsoleHashMap.string_nintendo;
			}
		}
		
		Helper helper = new Helper(request,pw);
		helper.printHtml("site_header.html");
        pw.print("<div id='body'><section id='content'><article class='expanded'>");
        pw.print("<h2>" + name + " Consoles</h2>");
		for(Map.Entry<String, Console> entry : hm.entrySet()){
			Console console = entry.getValue();
            pw.print(new GenerateItemHtmlHandler(entry.getKey(), console).getHtml());
		}		
        pw.print("</article></section>");

        helper.printHtml("site_sidebar.html");
		helper.printHtml("site_footer.html");
		
	}
}
