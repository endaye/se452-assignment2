

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AccessoryList", urlPatterns = "/AccessoryList")
public class AccessoryList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String CategoryName = request.getParameter("maker");
		String ConsoleName = request.getParameter("console");
		HashMap<String, Console> hm = new HashMap<String, Console>();
		if(CategoryName.equals("microsoft")){
			hm.putAll(ConsoleHashMap.microsoft);
		}
		else if(CategoryName.equals("sony")){
			hm.putAll(ConsoleHashMap.sony);
		}
		else if(CategoryName.equals("nintendo")){
			hm.putAll(ConsoleHashMap.nintendo);
		}
		
		Console console = hm.get(ConsoleName);
		
		Helper helper = new Helper(request,pw);
		helper.printHtml("site_header.html");
		pw.print("<div id='body'><section id='content'><article class='expanded'>");
		pw.print("<h2>" + console.getName() + " Accessories</h2>");
		for(Map.Entry<String, Accessory> entry : console.getAccessories().entrySet()){
			Accessory accessory = entry.getValue();
			pw.print(new GenerateItemHtmlHandler(accessory).getHtml());
		}
		pw.print("</article></section>");

		helper.printHtml("site_sidebar.html");
		helper.printHtml("site_footer.html");
	}
}
