

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Startup", urlPatterns = "/Startup")
public class Startup extends HttpServlet
{
	public void init() throws ServletException
	{
		String path = this.getServletContext().getRealPath("");
		SaxParserXMLdataStore data = new SaxParserXMLdataStore(path);

		new ConsoleHashMap(data.getConsoles());
		new GameHashMap(data.getGames());
		new UserHashMap();
		new TabletHashMap();
    }
}
