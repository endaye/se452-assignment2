package item;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.*;

@WebServlet(name = "GameList", urlPatterns = "/GamesList")
public class GamesList extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");
        HashMap<String, Game> hm = new HashMap<String, Game>();

        if (CategoryName==null) {
            hm.putAll(GameHashMap.electronicArts);
            hm.putAll(GameHashMap.activision);
            hm.putAll(GameHashMap.takeTwoInteractive);
            name = "";
        } else {
            if(CategoryName.equals("electronicArts")) {
                hm.putAll(GameHashMap.electronicArts);
                name = GameHashMap.string_electronicArts;
            }
            else if (CategoryName.equals("activision")) {
                hm.putAll(GameHashMap.activision);
                name = GameHashMap.string_activision;
            }
            else if (CategoryName.equals("takeTwoInteractive")) {
                hm.putAll(GameHashMap.takeTwoInteractive);
                name = GameHashMap.string_takeTwoInteractive;
            }
        }

        Helper helper = new Helper(request,pw);
        helper.printHtml("site_header.html");
        pw.print("<div id='body'><section id='content'><article class='expanded'>");
        pw.print("<h2>"+name+" Games</h2>");
        for(Map.Entry<String, Game> entry : hm.entrySet()){
            Game game = entry.getValue();
            pw.print(new GenerateItemHtmlHandler(entry.getKey(), game).getHtml());
        }
        pw.print("</article></section>");

        helper.printHtml("site_sidebar.html");
        helper.printHtml("site_footer.html");
    }
}
