package main;

import item.GenerateItemHtmlHandler;
import item.console.Console;
import item.game.*;
import item.accessory.*;
import item.tablet.*;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@WebServlet(name = "AutoCompleteServlet", urlPatterns = "/autocomplete")
public class AutoCompleteServlet extends HttpServlet {

    private ServletContext context;
    private HashMap<String, Game> games;
    private HashMap<String, Console> consoles;
    private HashMap<String, Tablet> tablets;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");
        String targetId = request.getParameter("id");
        String type = request.getParameter("type");
        StringBuffer sb = new StringBuffer();
        Helper helper = new Helper(request, response);
        games = helper.getGames();
        consoles = helper.getConsoles();
        tablets = helper.getTablets();

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/Error").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {
            // check if user sent empty string
            if (!targetId.equals("")) {
                //System.out.println(targetId);
                Iterator it = games.keySet().iterator();
                while (it.hasNext()) {
                    String id = (String) it.next();
                    Game game = (Game) games.get(id);

                    if (id.toLowerCase().startsWith(targetId) ||
                            game.getName().toLowerCase().startsWith(targetId) ||
                            game.getCondition().toLowerCase().startsWith(targetId) ||
                            game.getRetailer().toLowerCase().startsWith(targetId)) {
                        sb.append("<item>");
                        sb.append("<id>" + id + "</id>");
                        sb.append("<type>Game</type>");
                        sb.append("<name>" + game.getName() + "</name>");
                        sb.append("</item>");
                        namesAdded = true;
                    }
                }
                it = consoles.keySet().iterator();
                while (it.hasNext()) {
                    String id = (String) it.next();
                    Console console = (Console) consoles.get(id);

                    if (id.toLowerCase().startsWith(targetId) ||
                            console.getName().toLowerCase().startsWith(targetId) ||
                            console.getCondition().toLowerCase().startsWith(targetId) ||
                            console.getRetailer().toLowerCase().startsWith(targetId)) {
                        sb.append("<item>");
                        sb.append("<id>" + id + "</id>");
                        sb.append("<type>Console</type>");
                        sb.append("<name>" + console.getName() + "</name>");
                        sb.append("</item>");
                        namesAdded = true;
                    }
                }
                it = tablets.keySet().iterator();
                while (it.hasNext()) {
                    String id = (String) it.next();
                    Tablet tablet = (Tablet) tablets.get(id);

                    if (id.toLowerCase().startsWith(targetId) ||
                            tablet.getName().toLowerCase().startsWith(targetId) ||
                            tablet.getCondition().toLowerCase().startsWith(targetId) ||
                            tablet.getRetailer().toLowerCase().startsWith(targetId)) {
                        sb.append("<item>");
                        sb.append("<id>" + id + "</id>");
                        sb.append("<type>Tablet</type>");
                        sb.append("<name>" + tablet.getName() + "</name>");
                        sb.append("</item>");
                        namesAdded = true;
                    }
                }

            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<items>" + sb.toString() + "</items>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {
            // put the target item in the request scope to display
            if (targetId != null && type != null) {
                PrintWriter pw = response.getWriter();
                helper.printHtml("site_header.html");
                pw.print("<div id='body'><section id='content'><article class='expanded'>");
                pw.print("<h2>Search Result</h2>");
                if (type.equalsIgnoreCase("game") && games.containsKey(targetId.trim())) {
                    pw.print(new GenerateItemHtmlHandler(targetId, games.get(targetId)).getHtml());
                } else if (type.equalsIgnoreCase("console") && consoles.containsKey(targetId.trim())) {
                    pw.print(new GenerateItemHtmlHandler(targetId, consoles.get(targetId)).getHtml());
                } else if (type.equalsIgnoreCase("tablet") && tablets.containsKey(targetId.trim())) {
                    pw.print(new GenerateItemHtmlHandler(targetId, tablets.get(targetId)).getHtml());
                }
                pw.print("</article></section>");
                helper.printHtml("site_sidebar.html");
                helper.printHtml("site_footer.html");
            }
        }
    }
}
