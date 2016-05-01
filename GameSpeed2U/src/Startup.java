

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Startup", urlPatterns = "/Startup")
public class Startup extends HttpServlet
{
	public void init() throws ServletException
	{
		String path = this.getServletContext().getRealPath("");
		new SaxParser4GameSpeedXMLdataStore(path);

		new ConsoleHashMap();
		new GameHashMap();
		new UserHashMap();
		new TabletHashMap();
    }
}
