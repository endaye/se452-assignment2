package main;

import item.console.ConsoleHashMap;
import item.dataload.SaxParserXMLdataStore;
import item.game.GameHashMap;
import item.accessory.AccessoryHashMap;
import item.tablet.TabletHashMap;
import user.account.UserHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Startup", urlPatterns = "/Startup")
public class Startup extends HttpServlet
{
	public void init() throws ServletException
	{
		// read data from local xml file
		String path = this.getServletContext().getRealPath("");
		SaxParserXMLdataStore data = new SaxParserXMLdataStore(path);

		new ConsoleHashMap(data.getConsoles());
		new GameHashMap(data.getGames());
		new TabletHashMap(data.getTablets());
		new AccessoryHashMap(data.getAccessories());

		new UserHashMap();
    }
}
