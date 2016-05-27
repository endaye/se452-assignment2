package main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Error", urlPatterns = "/Error")
public class Error extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Helper helper = new Helper(request, pw);
        helper.printHtml("site_header.html");
        pw.print("<div id='body'><section id='content'><article>");
        pw.print("<h2>Seach Error</h2>");
        pw.print("<p>An error occured while performing the search. Please try again.</p>");
        pw.print("<p>Go back to <a href='/Home'>home page</a>.</p>");
        pw.print("</article></section>");

        helper.printHtml("site_sidebar.html");
        helper.printHtml("site_footer.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}